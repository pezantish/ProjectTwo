package com.qa.zms.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.zms.domain.Animal;


@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:testschema.sql",
		"classpath:testdata.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class AnimalControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	public void createTest() throws Exception {
		String input = mapper.writeValueAsString(new Animal("Greg", "Giraffe", 4, "RTX"));

		String output = mapper.writeValueAsString(new Animal(3L, "Greg", "Giraffe", 4, "RTX"));

		mvc.perform(post("/animal/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(input))
				.andExpect(status().isCreated())
				.andExpect(content().json(output));
	}
	
	@Test
	public void readAllTest() throws Exception {
		List<Animal> output = new ArrayList<Animal>();
		output.add(new Animal(1L, "George", "Gorilla", 7, "LBX"));
		output.add(new Animal(2L, "Mary", "Gorilla", 8, "LBX"));
		String outputToJSON = mapper.writeValueAsString(output);

		mvc.perform(get("/animal/readAll")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(outputToJSON));
	}
	
	@Test
	public void readByIdTest() throws Exception {
		Animal output = new Animal(1L, "George", "Gorilla", 7, "LBX");
		String outputToJSON = mapper.writeValueAsString(output);

		mvc.perform(get("/animal/readById/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(outputToJSON));
	}
	
	@Test
	public void replaceTest() throws Exception {
		String input = mapper.writeValueAsString(new Animal("Greg", "Gorilla", 7, "LBX"));
		String output = mapper.writeValueAsString(new Animal(1L, "Greg", "Gorilla", 7, "LBX"));

		mvc.perform(put("/animal/replace/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(input))
				.andExpect(status().isAccepted())
				.andExpect(content().json(output));
	}
		
	@Test
	public void deleteTest() throws Exception {
		mvc.perform(delete("/animal/delete/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}
	

}
