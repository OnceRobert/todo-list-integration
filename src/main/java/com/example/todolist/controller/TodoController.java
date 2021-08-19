package com.example.todolist.controller;

import com.example.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.todolist.entity.Todo;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    public TodoController(TodoService todoService){this.todoService = todoService;}

    @CrossOrigin
    @GetMapping()
    public List<Todo> getAllTodos(){
        return todoService.getTodoList();
    }

    @CrossOrigin
    @PostMapping()
    public Todo addTodo(@RequestBody Todo todo){
        return todoService.addTodoItem(todo);
    }


}
