package com.iykekscode.todolistappjsp.todo;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
@SessionAttributes("name")
public class TodoService {

    private static List<Todo> todoList = new ArrayList<>();

    private static int todosCount = 0;

    static {
        todoList.add(new Todo(++todosCount, "Iykescode", "Learn AWS", LocalDate.now().plusYears(1), false));
        todoList.add(new Todo(++todosCount, "Iykescode", "Learn DevOps", LocalDate.now().plusYears(2), false));
        todoList.add(new Todo(++todosCount, "Iykescode", "Learn Fullstack Development", LocalDate.now().plusYears(3), false));
    }

    public static List<Todo> findByUsername(String username) {
        Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
        return todoList.stream().filter(predicate).toList();
    }

    public void addTodo(String username, String description, LocalDate targetDate, Boolean done) {
        Todo todo = new Todo(++todosCount, username, description, targetDate, done);
        todoList.add(todo);
    }

    public void updateTodo(Todo todo) {
//        Todo todo = new Todo(++todosCount, username, description, targetDate, done);
        todoList.add(todo);
    }

    public void deleteById(Integer id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        todoList.removeIf(predicate);
    }

    public Todo findById(Integer id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        return todoList.stream().filter(predicate).findFirst().get();
    }
}
