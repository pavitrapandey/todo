package com.todo_manager.services.impl;

import com.todo_manager.models.Todo;
import com.todo_manager.services.TodoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.*;
import com.todo_manager.dao.TodoDao;
import java.util.List;


@Service

public class TodoDaoServicesImpl implements TodoServices{

    @Autowired
    private TodoDao todoDao;

    @Override
    public Todo createTodo(Todo todo) {
        return todoDao.insertQuery(todo);
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoDao.getAllTodos();
    }

    @Override
    public Todo getTodo(int id) {
        return todoDao.getTodo(id);
    }

    @Override
    public Todo updateTodo(int id, Todo todo) {
        return todoDao.updateTodo(id, todo);
    }

    @Override
    public void deleteTodo(int id) {
        todoDao.deleteTodo(id);
    }

}
