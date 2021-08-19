package com.example.todolist;

import com.example.todolist.entity.Todo;
import com.example.todolist.repository.TodoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class TodolistApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	TodoRepository todoRepository;

	@BeforeEach
	void Teardown(){
		todoRepository.deleteAll();
	}

	@Test
	void should_return_all_todo_items_when_get() throws Exception {
	    //given
		todoRepository.save(new Todo("Pay for Cbz"));
		todoRepository.save(new Todo("Pay for Crazy In Love Pre-order"));

		//when and then
		mockMvc.perform(MockMvcRequestBuilders.get("/todos"))
				.andExpect(jsonPath("$[0].text").value("Pay for Cbz"))
				.andExpect(jsonPath("$[0].done").value(false))
				.andExpect(jsonPath("$[1].text").value("Pay for Crazy In Love Pre-order"))
				.andExpect(jsonPath("$[1].done").value(false))
				.andExpect(jsonPath("$[2].text").doesNotExist());
	}




}
