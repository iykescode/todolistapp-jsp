package com.iykekscode.todolistappjsp.todo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    @Id
    @GeneratedValue
    private Integer id;

    private String username;

    @Size(min = 10, message = "Please enter at least 10 characters")
    private String description;

    private LocalDate targetDate;

    private Boolean done;
}
