package com.todo_manager.dao;

import com.todo_manager.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class todoDao {

    Logger logger= LoggerFactory.getLogger(todoDao.class);
    private JdbcTemplate template;

    public todoDao(@Autowired JdbcTemplate template) {
        this.template = template;
        String createTableQuery = "CREATE TABLE IF NOT EXISTS todos (id INT PRIMARY KEY, title VARCHAR(100) not null, content VARCHAR(500), status VARCHAR(50) not null, addedDate TIMESTAMP, todoDate TIMESTAMP)";

        int update = template.update(createTableQuery);
        logger.info("Table created successfully, update count: {}", update);

    }

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public Todo insertQuery(Todo todo){
        String insert="insert into todos(id,title,content,status,addedDate,todoDate) values(?,?,?,?,?,?)";
        int result=template.update(insert,todo.getId(),todo.getTitle(),todo.getContent(),todo.getStatus(),todo.getAddedDate(),todo.getTodoDate());
        logger.info("Result of insert query {}", result);
        return todo;
    }

    public void getTodo(int id){
        String Query="select * from todos where id=?";
        Map<String,Object> data= template.queryForMap(Query, id);
       logger.info("Data from DB {}", data);

    }
}
