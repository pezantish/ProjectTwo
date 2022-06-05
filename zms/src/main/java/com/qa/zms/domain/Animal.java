package com.qa.zms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Animal {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column( nullable = false) 
	private String name; 
	
	@Column(nullable = false)
	private String species; 
	
	@Column(nullable = false) 
	private long age; 
	
	@Column(nullable = false) 
	private String enclosureId; 
	
	public Animal(String name, String species, long age, String enclosureId) {
		this.name = name;
		this.species = species;
		this.age = age;
		this.enclosureId = enclosureId;
	}

	public void setAll(String name, String species, long age, String enclosureId) {
		this.name = name;
		this.species = species;
		this.age = age;
		this.enclosureId = enclosureId;
	}
}