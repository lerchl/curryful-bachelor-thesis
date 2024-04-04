package io.github.curryful.todo.spring;

import io.github.curryful.todo.spring.Todo;
import io.github.curryful.todo.spring.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.findAll();
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        return todoService.save(todo);
    }

    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable long id) {
        return todoService.findById(id);
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable long id, @RequestBody Todo todo) {
        return todoService.update(id, todo);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable long id) {
        todoService.deleteById(id);
    }

    @PostMapping("/{id}/toggle")
    public Todo toggleTodoStatus(@PathVariable long id) {
        return todoService.toggleStatus(id);
    }
}
