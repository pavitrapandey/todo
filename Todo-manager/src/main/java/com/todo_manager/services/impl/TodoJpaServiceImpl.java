package com.todo_manager.services.impl;


import com.todo_manager.Exception.ResourceNotFoundException;
import com.todo_manager.dao.TodoRepository;
import com.todo_manager.models.Todo;
import com.todo_manager.services.TodoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Primary
public class TodoJpaServiceImpl implements TodoServices {
    @Autowired
    TodoRepository todoRepository;


    @Override
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public List<Todo> getAllTodos() {
        List<Todo> todoList=todoRepository.findAll();

        return todoList;
    }

    @Override
    public Todo getTodo(int id) {
        return todoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo not found with given id", HttpStatus.NOT_FOUND));
    }

    @Override
    public Todo updateTodo(int id, Todo todo) {
      Todo todo1=  todoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo not found with given id", HttpStatus.NOT_FOUND));
      todo1.setTitle(todo.getTitle());
        todo1.setContent(todo.getContent());
        todo1.setStatus(todo.getStatus());
        todo1.setTodoDate(todo.getTodoDate());
       Todo newTodo= todoRepository.save(todo1);
      return newTodo;
    }

    @Override
    public void deleteTodo(int id) {
        Todo todo= todoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo not found with given id", HttpStatus.NOT_FOUND));
        todoRepository.delete(todo);
    }
}
