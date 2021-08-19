package com.example.todolist.controller;

import com.example.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.todolist.entity.Todo;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    public TodoController(TodoService todoService){this.todoService = todoService;}

    @GetMapping()
    public List<Todo> getAllTodos(){
        return todoService.getTodoList();
    }


}
