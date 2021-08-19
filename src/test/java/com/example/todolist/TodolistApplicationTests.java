package com.example.todolist;

import com.example.todolist.entity.Todo;
import com.example.todolist.repository.TodoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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

	@Test
	void should_add_todo_item_when_add_given_todo_text() throws Exception {
		String todo = "{"+
				"        \"text\": \"Display Candybong\"\n" +
				"    }";
		//when
		//then
		mockMvc.perform(MockMvcRequestBuilders.post("/todos")
				.contentType(MediaType.APPLICATION_JSON)
				.content(todo))
				.andExpect(jsonPath("$.text").value("Display Candybong"))
				.andExpect(jsonPath("$.done").value(false));
	}

	@Test
	void should_update_employee_when_call_update_employee_api() throws Exception
	{
		Todo savedTodo = todoRepository.save(new Todo("Eat Bananas"));
		String todo = "{"+
				"        \"text\": \"Display Candybong\"\n" +
				"    }";
		//when
		int id = savedTodo.getId();
		//then
		mockMvc.perform(MockMvcRequestBuilders.put("/todos/{id}",id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(todo))
				.andExpect(jsonPath("$.text").value("Display Candybong"))
				.andExpect(jsonPath("$.done").value(false));
	}

	@Test
	void should_return_no_todo_item_when_delete_given_id() throws Exception {
		//given
		final Todo todo = new Todo("Momo");
		Todo savedTodo = todoRepository.save(todo);

		//when
		//then
		int id = savedTodo.getId();
		mockMvc.perform(MockMvcRequestBuilders.delete("/todos/{id}",id).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}




}
