package com.yourcompany.parisenligne.model;
import java.math.*;

import javax.persistence.*;
import javax.persistence.Entity;

import org.hibernate.annotations.*;
import org.openxava.annotations.*;

import lombok.*;

@Entity @Getter @Setter
public class Pari {
   
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
   @Money
   private BigDecimal montant;
   
   @Column(length=50)
   @Required
   private String statut;
   
   @Column(length=50)
   @Required
   private Integer cotes;
   


}

   
