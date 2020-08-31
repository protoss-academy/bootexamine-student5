package com.protosstechnology.train.bootexamine;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.protosstechnology.train.bootexamine.model.Document;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class BootexamineApplicationTests {

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() throws Exception { }

	@AfterEach
	public void tearDown() throws Exception { }

	@Test
	void addDocument_thenOk() throws Exception {
		mockMvc.perform(post("/document")
				.content("{\"documentNumber\":\"A\",\"description\":\"AAA\"}")
				.contentType("application/json")
		)
				.andExpect(status().isOk());
	}

	@Test
	void getDocument_thenOk() throws Exception {
		//ADD
		MvcResult result = mockMvc.perform(post("/document")
				.content("{\"documentNumber\":\"B\",\"description\":\"BBB\"}")
				.contentType("application/json")
		).andReturn();
		String json = result.getResponse().getContentAsString();
		Document createdDoc = new ObjectMapper().readValue(json, Document.class);
		//GET
		mockMvc.perform(get("/document/"+ createdDoc.getId() +""))
				.andExpect(status().isOk());
	}

	@Test
	void getDocument_thenNotFound() throws Exception {
		//GET
		mockMvc.perform(get("/document/1"))
				.andExpect(status().isNotFound());
	}

	@Test
	void updateDocument_thenOk() throws Exception {
		//ADD
		MvcResult result = mockMvc.perform(post("/document")
				.content("{\"documentNumber\":\"B\",\"description\":\"BBB\"}")
				.contentType("application/json")
		).andReturn();
		String json = result.getResponse().getContentAsString();
		Document createdDoc = new ObjectMapper().readValue(json, Document.class);
		//GET
		mockMvc.perform(put("/document/"+ createdDoc.getId() +"")
				.content("{\"documentNumber\":\"Bedit\",\"description\":\"BBBedit\"}")
				.contentType("application/json"))
				.andExpect(status().isOk());
	}

	@Test
	void deleteDocument_thenOk() throws Exception {
		//ADD
		MvcResult result = mockMvc.perform(post("/document")
				.content("{\"documentNumber\":\"B\",\"description\":\"BBB\"}")
				.contentType("application/json")
		).andReturn();
		String json = result.getResponse().getContentAsString();
		Document createdDoc = new ObjectMapper().readValue(json, Document.class);
		//GET
		mockMvc.perform(delete("/document/"+ createdDoc.getId() +""))
				.andExpect(status().isOk());
	}

}
