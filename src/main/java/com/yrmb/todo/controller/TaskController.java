package com.yrmb.todo.controller;

import com.yrmb.todo.model.Task;
import com.yrmb.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/todo", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> findAll() {
        return ResponseEntity.ok(taskService.findAll());
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteAll() {
        taskService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Boolean> update(@RequestBody Task task) {

        try {
            taskService.update(task);
            return ResponseEntity.ok().build();
            
        } catch (IndexOutOfBoundsException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}
