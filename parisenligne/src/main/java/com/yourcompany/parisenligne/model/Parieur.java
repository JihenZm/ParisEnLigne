package com.yourcompany.parisenligne.model;
import java.math.*;

import javax.persistence.*;
import javax.persistence.Entity;

import org.hibernate.annotations.*;
import org.openxava.annotations.*;

import lombok.*;

@Entity @Getter @Setter
public class Parieur {

	   @Id
	   @Hidden
	   @GeneratedValue(generator="system-uuid")
	   @GenericGenerator(name="system-uuid",strategy="uuid")
	   private String id;

	   @Column(length=50)
	   @Required
	   private String nom;
	   
	   @Column(length=50)
	   @Required
	   private String prenom;
	   
	   
	   @Column(length=50)
	   @Required
	   @Money
	   private BigDecimal solde;
   
}
