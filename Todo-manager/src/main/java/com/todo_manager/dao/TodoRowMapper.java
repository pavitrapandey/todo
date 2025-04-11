package com.todo_manager.dao;

import com.todo_manager.models.Todo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class TodoRowMapper implements RowMapper<Todo> {
    @Override
    public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Todo todo=new Todo();
        todo.setId( rs.getInt("id"));
        todo.setTitle( rs.getString("title"));
        todo.setContent(rs.getString("content"));
        todo.setStatus( rs.getString("status"));
        todo.setAddedDate( rs.getDate("addedDate"));
        todo.setTodoDate( rs.getDate("todoDate"));


        return todo;
    }
}
