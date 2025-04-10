package com.todo_manager.services;

import com.todo_manager.Exception.ResourceNotFoundException;
import com.todo_manager.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TodoServices {
    Logger logger= LoggerFactory.getLogger(TodoServices.class);
    List<Todo> todoList=new ArrayList<>();
    public Todo createTodo(Todo todo){
        logger.info("todos {}", this.todoList);
        todoList.add(todo);
        return todo;
    }

    public List<Todo> getAllTodos() {
        return todoList;
    }

    public Todo getTodo(int id) {
        Todo todo= todoList.stream().filter(t->t.getId()==id).findAny().orElseThrow(()->new ResourceNotFoundException("Todo not found with given id ", HttpStatus.NOT_FOUND));
        logger.info("TODO {}", todo);
        return todo;
    }

    public Todo updateTodo(int id, Todo todo) {
        Todo todo1=todoList.stream().filter(t->t.getId()==id).findAny().get();
        todo1.setTitle(todo.getTitle());
        todo1.setContent(todo.getContent());
        todo1.setStatus(todo.getStatus());
        logger.info("Updated TODO {}", todo1);
        return todo1;
    }

    public void deleteTodo(int id) {
        logger.info("Deleting TODO with id {}", id);
        List<Todo> newList=todoList.stream().filter(t->t.getId()!=id).collect(Collectors.toList());
        todoList=newList;
    }

    public void deleteAllTodos() {
        logger.info("Deleting all TODOs");
        todoList.clear();

    }
}
