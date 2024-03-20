import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class CodeGenerator {

    public static void writeToFile(String fileName, String content) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println(content);
        }
        System.out.println("Generated Java code written to file: " + fileName);
    }

    public static void generateJavaCode(String inputFileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            StringBuilder entityContent = new StringBuilder();
            Map<String, StringBuilder> associations = new HashMap<>();
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("class")) {
                    if (entityContent.length() > 0) {
                        generateEntityClass(entityContent.toString(), associations);
                        entityContent.setLength(0); // Reset the StringBuilder
                    }
                    entityContent.append(line).append("\n");
                } else if (line.contains("--")) {
                    String[] parts = line.split(":");
                    String[] classNames = parts[0].trim().split("--");
                    String associationType = parts[1].trim();
                    System.out.println("Association between " + classNames[0] + " and " + classNames[1] + ": " + associationType);
                    StringBuilder associationContent = associations.getOrDefault(classNames[0], new StringBuilder());
                    associationContent.append("    // ").append(associationType).append(" with ").append(classNames[1]).append("\n");
                    associations.put(classNames[0], associationContent);
                } else if (!line.isEmpty()) {
                    entityContent.append(line).append("\n");
                }
            }
            // Generate the last entity
            if (entityContent.length() > 0) {
                generateEntityClass(entityContent.toString(), associations);
            }
        }
    }

    public static void generateEntityClass(String entityContent, Map<String, StringBuilder> associations) throws IOException {
        String[] lines = entityContent.split("\n");
        String className = null;
        StringBuilder classContent = new StringBuilder();
        for (String line : lines) {
            line = line.trim();
            if (line.startsWith("class")) {
                String[] parts = line.split("\\s+");
                className = parts[1];
                classContent.append("public class ").append(className).append(" {\n");
            } else if (line.startsWith("private")) {
                classContent.append("    ").append(line).append("\n");
            } else if (line.startsWith("public")) {
                classContent.append("    ").append(line).append("\n");
            }
        }
        // Append association content
        if (className != null) {
            StringBuilder associationContent = associations.get(className);
            if (associationContent != null) {
                System.out.println("Associations for class " + className + ":");
                System.out.println(associationContent.toString());
                classContent.append(associationContent);
            }
            classContent.append("\n}");
            writeToFile(className + ".java", classContent.toString());
        }
    }

    public static void main(String[] args) {
        try {
            generateJavaCode("C:/Users/jihen/eclipse-workspace/ClassDiagram.txt");
        } catch (IOException e) {
            System.err.println("Error reading or writing file: " + e.getMessage());
        }
    }

}
