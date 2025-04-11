package com.todo_manager;

import com.todo_manager.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.todo_manager.dao.TodoDao;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class TodoManagerApplication implements CommandLineRunner {

	@Autowired
	private TodoDao todoDao;

	Logger logger= LoggerFactory.getLogger(TodoManagerApplication.class);

	public static void main(String[] args) {


		SpringApplication.run(TodoManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		JdbcTemplate template=todoDao.getTemplate();
//		logger.info("JdbcTemplate is {}",template);

//		Todo todo=new Todo();
//		todo.setId(1);
//		todo.setTitle("Creation");
//		todo.setContent("Creating a new todo");
//		todo.setStatus("Pending");
//		todo.setAddedDate(new java.util.Date());
//		todo.setTodoDate(new java.util.Date());
//
//		todoDao.insertQuery(todo);
//
//		todo.setId(2);
//		todo.setTitle("Creation");
//		todo.setContent("Creating a new todo");
//		todo.setStatus("Pending");
//		todo.setAddedDate(new java.util.Date());
//		todo.setTodoDate(new java.util.Date());
//
//
//
//		todoDao.insertQuery(todo);
//
//		logger.info("Inserted  todo is {}", todo);

//

//		todoDao.deleteMultipleTodos(new int[]{1, 2, 3});

//		Todo todo1=todoDao.getTodo(0);

//		List<Todo> todo=todoDao.getAllTodos();
//
//
//		logger.info(" todo is {}", todo);
	}
}
