package com.todo_manager.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "jpa_todo")
public class Todo {

    @Id
    private int id;

    @Column(name = "todo_title")
    private String title;

    @Column(name = "todo_content")
    private String content;

    @Column(name = "todo_status")
    private String status;

    @Column(name = "added_date")
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    private Date addedDate;

    @Column(name = "todo_date")
    @JsonFormat(pattern = "dd/MM/yyyy")


    private Date todoDate;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Todo(int id, String title, String content, String status, Date addedDate, Date todoDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.status = status;
        this.addedDate = addedDate;
        this.todoDate = todoDate;
    }

    public Todo(){

    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Date getTodoDate() {
        return todoDate;
    }

    public void setTodoDate(Date todoDate) {
        this.todoDate = todoDate;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                ", addedDate=" + addedDate +
                ", todoDate=" + todoDate +
                '}';
    }
}
