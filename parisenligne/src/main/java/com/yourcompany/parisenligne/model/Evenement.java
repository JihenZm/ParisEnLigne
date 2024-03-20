package com.yourcompany.parisenligne.model;

import java.util.*;

import javax.persistence.*;
import javax.persistence.Entity;

import org.hibernate.annotations.*;
import org.openxava.annotations.*;

import lombok.*;

@Entity @Getter @Setter
public class Evenement {
   
	@Id
	@Hidden
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	private String id;
	
	@Column(length=50)
	@Required
	private String nom;
	   
	@Column
	@Required
	private Date date;

	@ManyToOne(fetch=FetchType.LAZY, optional=true)
    private TypeSport typeSport;



}
