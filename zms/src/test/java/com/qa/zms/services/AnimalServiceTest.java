package com.qa.zms.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
		//input
		Animal input = new Animal("George", "Gorilla", "rainforest");
		//output
		Animal output = new Animal(1L, "George", "Gorilla", "rainforest");
		//instead of running saveAndFlush, return output instead
		Mockito.when(repo.saveAndFlush(input)).thenReturn(output);
		//check that the function works as expected
		assertEquals(output, service.create(input));
		//Check was called once
		Mockito.verify(repo, Mockito.times(1)).saveAndFlush(input);
	}

}
