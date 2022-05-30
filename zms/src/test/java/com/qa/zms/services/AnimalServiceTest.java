package com.qa.zms.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.zms.domain.Animal;
import com.qa.zms.repositories.AnimalRepository;

@SpringBootTest
public class AnimalServiceTest {
	
	@Autowired 
	private AnimalService service;
	
	@MockBean 
	private AnimalRepository repo;
	
	@Test
	public void createTest() {
		Animal input = new Animal("George", "Gorilla", 7, "LBX");
		//output
		Animal output = new Animal(1L, "George", "Gorilla", 7, "LBX");
		//instead of running saveAndFlush, return output instead
		Mockito.when(repo.saveAndFlush(input)).thenReturn(output);
		//check that the function works as expected
		assertEquals(output, service.create(input));
		//Check was called once
		Mockito.verify(repo, Mockito.times(1)).saveAndFlush(input);
	}
	
	@Test
	public void readAllTest() {
		List<Animal> animals = new ArrayList<>();
		animals.add(new Animal(1L, "George", "Gorilla", 7, "LBX"));
		Mockito.when(repo.findAll()).thenReturn(animals);
		assertEquals(animals, service.readAll());
		Mockito.verify(repo, Mockito.times(1)).findAll();
	}
	
	@Test
	public void readByIdTest() {
		Animal output = new Animal(1L, "George", "Gorilla", 7, "LBX");
		Mockito.when(repo.findById(1L)).thenReturn(Optional.of(output));
		assertEquals(output, service.readById(1L));
		Mockito.verify(repo, Mockito.times(1)).findById(1L);
	}
	
	@Test
	@Disabled
	public void updateTest() {
		Animal output = new Animal(1L, "George", "Gorilla", 7, "LBX");
		Mockito.when(repo.findById(1L)).thenReturn(Optional.of(output));
		assertEquals(output, service.readById(1L));
		Mockito.verify(repo, Mockito.times(1)).findById(1L);
	}
	
	@Test
	public void replaceTest() {
		Animal input = new Animal("Greg", "Gorilla", 7, "LBX");
		long id = 1L;
		Animal old = new Animal(id, "George", "Gorilla", 7, "LBX");
		Animal newA = new Animal(id, "Greg", "Gorilla", 7, "LBX");
		Mockito.when(repo.findById(id)).thenReturn(Optional.of(old));
		Mockito.when(repo.saveAndFlush(newA)).thenReturn(newA);
		assertEquals(newA, service.replace(id, input));
		Mockito.verify(repo, Mockito.times(1)).findById(id);
		Mockito.verify(repo, Mockito.times(1)).saveAndFlush(newA);
	}
	
	@Test
	//doesn't really do much
	public void deleteTest() {
		long id = 1L;
		Mockito.doNothing().when(repo).deleteById(id);
		Mockito.when(repo.existsById(id)).thenReturn(false);
		
		assertEquals(true, service.delete(id));
		Mockito.verify(repo, Mockito.times(1)).deleteById(id);
		Mockito.verify(repo, Mockito.times(1)).existsById(id);
	}

}
