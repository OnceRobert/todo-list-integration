package com.example.todolist.service;

import com.example.todolist.entity.Todo;
import com.example.todolist.repository.TodoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TodoService {

    @Resource
    private TodoRepository todoRepository;

    private TodoService(){}

    public TodoService(TodoRepository todoRepository){this.todoRepository = todoRepository;}

    public List<Todo> getTodoList() {
        return todoRepository.findAll();
    }

    public Todo addTodoItem(Todo todo){
        return todoRepository.save(todo);

    }

    public Todo updateTodoItem(Integer id, Todo todo) {
        Todo updatedTodo = todoRepository.getById(id);
        updatedTodo.setDone(!updatedTodo.isDone());
        return todoRepository.save(updatedTodo);
    }

    public void deleteTodoItem(Integer id) {
        todoRepository.deleteById(id);
    }
}
