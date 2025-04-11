package com.todo_manager.services;

import com.todo_manager.models.Todo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TodoServices {

    public Todo createTodo(Todo todo);
    public List<Todo> getAllTodos();
    public Todo getTodo(int id);
    public Todo updateTodo(int id, Todo todo);
    public void deleteTodo(int id);

}
