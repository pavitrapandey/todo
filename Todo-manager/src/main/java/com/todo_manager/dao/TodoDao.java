package com.todo_manager.dao;

import com.todo_manager.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class TodoDao {

    Logger logger= LoggerFactory.getLogger(TodoDao.class);
    private JdbcTemplate template;



    public TodoDao(@Autowired JdbcTemplate template) {
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


    // INSERTION OF DATA
    public Todo insertQuery(Todo todo){
        String insert="insert into todos(id,title,content,status,addedDate,todoDate) values(?,?,?,?,?,?)";
        int result=template.update(insert,todo.getId(),todo.getTitle(),todo.getContent(),todo.getStatus(),todo.getAddedDate(),todo.getTodoDate());
        logger.info("Result of insert query {}", result);
        return todo;
    }

    //GET A TODO WITH THE ID
    public Todo getTodo(int id){
        String Query="select * from todos where id=?";

       Todo todo= template.queryForObject(Query,new TodoRowMapper(),id);


//        Todo todo=new Todo();
//        todo.setId((int) data.get("id"));
//        todo.setTitle((String) data.get("title"));
//        todo.setContent((String) data.get("content"));
//        todo.setStatus((String) data.get("status"));
//        todo.setAddedDate((Date) data.get("addedDate"));
//        todo.setTodoDate((Date) data.get("todoDate"));
//
//        logger.info("Todo from DB {}", todo);

        return todo;
    }


    //GET ALL TODO FROM DATABASE
    public List<Todo> getAllTodos(){
        String Query="select * from todos";
//        List<Map<String, Object>> data = template.queryForList(Query);

        List<Todo> collectTodos =template.query(Query,new TodoRowMapper());


//                    Todo todo=new Todo();
//                    todo.setId((int) map.get("id"));
//                    todo.setTitle((String) map.get("title"));
//                    todo.setContent((String) map.get("content"));
//                    todo.setStatus((String) map.get("status"));
//                    todo.setAddedDate((Date) map.get("addedDate"));
//                    todo.setTodoDate((Date) map.get("todoDate"));
//
//                    logger.info("Todo from DB {}", todo);
//                    return todo;
//                }).collect(Collectors.toList());


        return collectTodos;
    }


    //UPDATE THE TODO IN DATABASE
    public Todo updateTodo(int id, Todo todo){
        String Query="update todos set title=?,content=?,status=?,todoDate=? where id=?";

        int update=template.update(Query,todo.getTitle(),todo.getContent(),todo.getStatus(),todo.getTodoDate(),id);
        logger.info("Result of update query {}", update);
        todo.setId(id);
        return todo;
    }

    //DELETE A TODO USING ID ON DATABSE
    public Todo deleteTodo(int id){
        String Query="delete from todos where id=?";
        int update=template.update(Query, id);
        logger.info("Result of delete query {}", update);
        return null;
    }


    //DELETE MULTIPLE TODOS USING IDS
    public void deleteMultipleTodos(int[] ids) {
        String query = "DELETE FROM todos WHERE id=?";

        int[] results = template.batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, ids[i]); // ✅ Use correct index
            }

            @Override
            public int getBatchSize() {
                return ids.length;
            }
        });

        for (int result : results) {
            logger.info("Result of delete query: {}", result);
        }
    }
}
