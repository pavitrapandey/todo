package com.todo_manager.controllers;

import com.todo_manager.models.Todo;
import com.todo_manager.services.TodoServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import com.todo_manager.services.impl.TodoServicesImpl;

import java.util.List;
import java.util.*;

@RestController
@RequestMapping("/todos")
public class todoControllers {
    Logger logger= LoggerFactory.getLogger(todoControllers.class);

    @Autowired
   private TodoServices todoServices;
    Random random=new Random();


    @PostMapping
    public ResponseEntity<Todo> createHandler(@RequestBody Todo todo){
        int Id=random.nextInt(99999999);
        todo.setId(Id);
        logger.info("Creating a new todo");
        Date currentDate = new Date();
        todo.setAddedDate(currentDate);
       logger.info("Todo added date {}", currentDate);
        logger.info("Todo date {}", todo.getTodoDate());
       Todo createdTodo = todoServices.createTodo(todo);
        return new ResponseEntity<>(todo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getAllHandler(){
        logger.info("Fetching all todos");
        List<Todo> todoList=todoServices.getAllTodos();
        return new ResponseEntity<>(todoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoByIdHandler(@PathVariable int id) {
        logger.info("Fetching todo with id {}", id);
        Todo todo=todoServices.getTodo(id);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodoHandler(@PathVariable int id, @RequestBody Todo todo) {
        logger.info("Updating todo with id {}", id);
        Todo updatedTodo = todoServices.updateTodo(id, todo);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodoHandler(@PathVariable int id) {
        logger.info("Deleting todo with id {}", id);
        todoServices.deleteTodo(id);
        return new ResponseEntity<>("Todo deleted successfully", HttpStatus.OK);
    }


}
