package com.qa.zms.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.zms.domain.Animal;
import com.qa.zms.services.AnimalService;

@RestController
@CrossOrigin
@RequestMapping("/animal")
public class AnimalController {

	private AnimalService service;

	public AnimalController(AnimalService service) {
		this.service = service;
	}

	// Get Requests
	@GetMapping("/readById/{id}")
	public ResponseEntity<Animal> readById(@PathVariable long id) {
		return new ResponseEntity<Animal>(service.readById(id), HttpStatus.OK);
	}

	@GetMapping("/readAll")
	public ResponseEntity<List<Animal>> readAll() {
		return new ResponseEntity<List<Animal>>(service.readAll(), HttpStatus.OK);
	}
	
//	@GetMapping("/readByNameAndSpecies")
//	public ResponseEntity<List<Animal>> readByNameAndSpecies(String name, String species) {
//		return new ResponseEntity<List<Animal>>(service.readByNameAndSpecies(name, species), HttpStatus.OK);
//	}

	// Post Request
	@PostMapping("/create")
	public ResponseEntity<Animal> create(@RequestBody Animal animal) {
		return new ResponseEntity<Animal>(service.create(animal), HttpStatus.CREATED);
	}

	// Put Request
	@PutMapping("/replace/{id}")
	public ResponseEntity<Animal> replace(@PathVariable long id, @RequestBody Animal animal) {
		return new ResponseEntity<Animal>(service.replace(id, animal), HttpStatus.ACCEPTED);
	}

	// Patch Request
	@PatchMapping("/update/{id}")
	public ResponseEntity<Animal> update(@PathParam("name") String name, @PathVariable long id) {
		return new ResponseEntity<Animal>(service.update(name, id), HttpStatus.ACCEPTED);
	}

	// Delete Request
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable long id) {
		return service.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT):
			new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
}
