package com.example.demo;

import com.example.demo.exception.TodoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class TodoController {

    private List<Todo> todos = new ArrayList<>();
    private AtomicInteger atomicInteger = new AtomicInteger(1);

    public TodoController() {
    }

    @GetMapping("/todo")
    public List<Todo> getTodos() {
        return todos;
    }

    // http://localhost:8080/todo/{id}
    @GetMapping("/todo/{id}")
    public Todo getTodoByID(@PathVariable long id) {
        return todos.stream().filter(todo -> todo.getId() == id).findFirst()
                .orElseThrow(() -> new TodoNotFoundException(id));
    }

    // http://localhost:8080/todo/search/?id=1
    @GetMapping("/todo/search")
    public Todo getTodoByName(@RequestParam(defaultValue = "1") long id) {
        return todos.stream().filter(todo -> todo.getId() == id).findFirst()
                .orElseThrow(() -> new  TodoNotFoundException(id));
    }

    // http://localhost:8080/todo/1
    // @PathVariable annotation
    @GetMapping("/todo/{id}/{content}/{done}")
    public Todo todoPathVariable(@PathVariable("id") long id,
                                       @PathVariable("content") String content,
                                 @PathVariable("done") boolean done) {
        return new Todo(id, content, done);
    }

    // build rest API to handle query parameters
    // http://localhost:8080/todo/query?firstName=Ramesh&lastName=Fadatare
    @GetMapping("/todo/query")
    public Todo todoQueryParam(
            @RequestParam(name = "id") long id,
            @RequestParam(name = "content") String content) {
        return new Todo(id, content, false);
    }

    // POST - contains elements in body
    // http://localhost:8080/todo/
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/todo")
    public void addTodoWithBody(@RequestBody Todo todo) {
        todos.add(new Todo(atomicInteger.getAndIncrement() , todo.getContent() , todo.isDone()));
    }

    // PUI - contains elements in body
    // http://localhost:8080/todo/{id}
    @PutMapping("/todo/{id}")
    public void editTodo(@RequestBody Todo todo, @PathVariable long id) {
        todos.stream().filter(result -> result.getId() == id).findFirst().ifPresentOrElse(result -> {
            result.setContent(todo.getContent());
        }, () -> {
            throw new TodoNotFoundException(id);
        });
    }

    // DEL
    // http://localhost:8080/todo/{id}
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/todo/{id}")
    public void deleteMap(@PathVariable long id) {
        todos.stream().filter(result -> result.getId() == id).findFirst().ifPresentOrElse(result -> {
           todos.remove(result);
        }, () -> {
            throw new TodoNotFoundException(id);
        });
    }
}
