package com.example.demo;

public class Todo {

    private long id;
    private String content;
    private boolean done;

    public Todo(long id, String content, boolean done) {
        this.id = id;
        this.content = content;
        this.done = done;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
