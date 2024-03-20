package com.yourcompany.parisenligne.model;
import javax.persistence.*;
import javax.persistence.Entity;

import org.hibernate.annotations.*;
import org.openxava.annotations.*;

import lombok.*;

	@Entity
	@Getter @Setter
	public class Bookmaker {
		@Id
		@Hidden
		@GeneratedValue(generator="system-uuid")
		@GenericGenerator(name="system-uuid",strategy="uuid")
		private String id;	

		@Column(length=255)
		private String parametres;
		   
		@Column(length=255)
		private String limites;


	   
	 
	}

