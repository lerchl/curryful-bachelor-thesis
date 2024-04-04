package io.github.curryful.todo.spring;

import io.github.curryful.todo.spring.Todo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TodoService {
    private final HashMap<Long, Todo> todos = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

    public List<Todo> findAll() {
        return new ArrayList<>(todos.values());
    }

    public Todo findById(long id) {
        return todos.get(id);
    }

    public Todo save(Todo todo) {
        long id = counter.incrementAndGet();
        todo.setId(id);
        todos.put(id, todo);
        return todo;
    }

    public Todo update(long id, Todo todo) {
        todo.setId(id);
        todos.put(id, todo);
        return todo;
    }

    public void deleteById(long id) {
        todos.remove(id);
    }

    public Todo toggleStatus(long id) {
        Todo todo = todos.get(id);
        if (todo != null) {
            todo.setCompleted(!todo.isCompleted());
        }
        return todo;
    }
}
