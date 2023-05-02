package com.iykekscode.todolistappjsp.todo;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;

//@Controller
@RequiredArgsConstructor
@SessionAttributes("name")
public class TodoController {

    private final TodoService todoService;

    @RequestMapping(value = "/list-todos", method = { RequestMethod.GET })
    public String listAllTodos(Model model) {
        String username = (String) model.getAttribute("name");
        model.addAttribute("todos", todoService.findByUsername(username));
        model.addAttribute("title", "Todo List");
        return "listTodos";
    }

    @RequestMapping(value = "/add-todo", method = { RequestMethod.GET })
    public String displayAddTodo(Model model) {
        String username = (String) model.getAttribute("name");
        model.addAttribute("todo", new Todo(0, username, "", LocalDate.now().plusYears(1), false));
        model.addAttribute("title", "Add Todo");
        return "todoForm";
    }

    @RequestMapping(value = "/add-todo", method = { RequestMethod.POST })
    public String addTodo(@Valid Todo todo, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "todoForm";
        }

        String username = (String) model.getAttribute("name");
        todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), false);
        return "redirect:/list-todos";
    }

    @RequestMapping(value = "/update-todo/{id}", method = { RequestMethod.GET })
    public String displayUpdateTodo(Model model, @PathVariable Integer id) {
        Todo todo = todoService.findById(id);
        model.addAttribute("todo", todo);
        model.addAttribute("title", "Update Todo");
        return "todoForm";
    }

    @RequestMapping(value = "/update-todo/{id}", method = { RequestMethod.POST })
    public String updateTodo(@Valid Todo todo, BindingResult result) {
        if(result.hasErrors()) {
            return "todoForm";
        }

        todo.setUsername("Iykescode");
        todoService.updateTodo(todo);
        return "redirect:/list-todos";
    }

    @RequestMapping("/delete-todo/{id}")
    public String deleteTodo(@PathVariable Integer id) {
        todoService.deleteById(id);
        return "redirect:/list-todos";
    }
}
