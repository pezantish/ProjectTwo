package com.qa.zms.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.zms.domain.Animal;
import com.qa.zms.services.AnimalService;

@WebMvcTest
public class AnimalControllerUnitTest {
	
	@Autowired 
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@MockBean 
	private AnimalService service;
	
	
	@Test
	public void createTest() throws Exception {
		Animal input = new Animal("Greg", "Giraffe", 4, "RTX");
		Animal output = new Animal(3L, "Greg", "Giraffe", 4, "RTX");
		String inputJson = mapper.writeValueAsString(input);
		String outputJson = mapper.writeValueAsString(output);

		Mockito.when(service.create(input)).thenReturn(output);
		
		mvc.perform(post("/animal/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(inputJson))
				.andExpect(status().isCreated())
				.andExpect(content().json(outputJson));
	}
	
	@Test
	public void readAllTest() throws Exception {
		List<Animal> output = new ArrayList<Animal>();
		output.add(new Animal(1L, "George", "Gorilla", 7, "LBX"));
		output.add(new Animal(2L, "Mary", "Gorilla", 8, "LBX"));
		String outputToJSON = mapper.writeValueAsString(output);
		
		Mockito.when(service.readAll()).thenReturn(output);

		mvc.perform(get("/animal/readAll")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(outputToJSON));
	}
	
	@Test
	public void readByIdTest() throws Exception {
		Animal output = new Animal(1L, "George", "Gorilla", 7, "LBX");
		String outputToJSON = mapper.writeValueAsString(output);
		Mockito.when(service.readById(1L)).thenReturn(output);

		mvc.perform(get("/animal/readById/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(outputToJSON));
	}
	
	@Test
	public void replaceTest() throws Exception {
		Animal input = new Animal("George", "Gorilla", 7, "LBX");
		Animal output = new Animal(1L, "George", "Gorilla", 7, "LBX");
		String inputToJSON = mapper.writeValueAsString(input);
		String outputToJSON = mapper.writeValueAsString(output);
		
		Mockito.when(service.replace(1L, input)).thenReturn(output);
		
		mvc.perform(put("/animal/replace/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(inputToJSON))
				.andExpect(status().isAccepted())
				.andExpect(content().json(outputToJSON));
	}
		
	@Test
	public void deleteTest() throws Exception {
		Mockito.when(service.delete(1L)).thenReturn(true);
		mvc.perform(delete("/animal/delete/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

}