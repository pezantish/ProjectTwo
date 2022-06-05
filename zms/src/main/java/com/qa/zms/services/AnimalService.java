package com.qa.zms.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.qa.zms.domain.Animal;
import com.qa.zms.exceptions.AnimalNotFoundException;
import com.qa.zms.repositories.AnimalRepository;


@Service
public class AnimalService {

	private AnimalRepository repo;

	public AnimalService(AnimalRepository repo) {
		this.repo = repo;
	}

	// Get Requests
	public Animal readById(long id) {
		return this.repo.findById(id).orElseThrow(() -> new AnimalNotFoundException(id));
	}

	public List<Animal> readAll() {
		return this.repo.findAll();
	}
	
//	public List<Animal> readByNameAndSpecies(String name, String species) {
//		return this.repo.findByNameAndSpeciesSQL(name, species);
//	}

	// Post Requests
	public Animal create(Animal animal) {
		return this.repo.saveAndFlush(animal);
	}

	// Put Requests
	public Animal replace(long id, Animal animal) {
		Animal replacedAnimal = readById(id);
		replacedAnimal.setAll(animal.getName(), animal.getSpecies(), animal.getAge(), animal.getEnclosureId());
		return this.repo.saveAndFlush(replacedAnimal);
	}


//	// Patch Requests
//	public Animal update(String name, long id) {
//		Animal replacedAnimal = readById(id);
//		replacedAnimal.setName(name);
//		return this.repo.saveAndFlush(replacedAnimal);
//	}

	// Delete Requests
	public boolean delete(long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
}
