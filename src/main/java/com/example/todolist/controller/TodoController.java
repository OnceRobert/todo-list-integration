package com.example.todolist.controller;

import com.example.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.todolist.entity.Todo;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/todos")
public class TodoController {
    private TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService){this.todoService = todoService;}

    @GetMapping()
    public List<Todo> getAllTodos(){
        return todoService.getTodoList();
    }

    @PostMapping()
    public Todo addTodo(@RequestBody Todo todo){
        return todoService.addTodoItem(todo);
    }

    @PutMapping("/{id}")
    public Todo test(@PathVariable Integer id, @RequestBody Todo todo){
        return todoService.updateTodo(id,todo);
    }
    
}
