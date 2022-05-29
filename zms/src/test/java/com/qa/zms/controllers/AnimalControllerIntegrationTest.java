package com.qa.zms.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
@Sql(scripts = { "classpath:testschema.sql",
		"classpath:testdata.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class AnimalControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	public void createTest() throws Exception {
		Animal input = new Animal("Greg", "Giraffe", "savana");
		String inputToJSON = mapper.writeValueAsString(input);

		Animal output = new Animal(2L, "Greg", "Giraffe", "savana");
		String outputToJSON = mapper.writeValueAsString(output);

		mvc.perform(post("/animal/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(inputToJSON))
				.andExpect(status().isCreated())
				.andExpect(content().json(outputToJSON));
	}
	
	@Test
	public void readAllTest() throws Exception {
		List<Animal> output = new ArrayList<Animal>();
		output.add(new Animal(1L, "George", "Gorilla", "rainforest"));
		String outputToJSON = mapper.writeValueAsString(output);

		mvc.perform(get("/animal/readAll")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isContinue())
				.andExpect(content().json(outputToJSON));
	}
	
	@Test
	public void readByIdTest() throws Exception {
		Animal input = new Animal("Greg", "Giraffe", "savana");
		String inputToJSON = mapper.writeValueAsString(input);

		mvc.perform(post("/animal/readById/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(content().json(inputToJSON));
	}

}
