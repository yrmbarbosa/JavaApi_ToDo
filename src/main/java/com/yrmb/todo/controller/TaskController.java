package com.yrmb.todo.controller;

import com.yrmb.todo.model.Task;
import com.yrmb.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/todo", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<Task> create(@RequestBody Task task){

        try {
            if (taskService.validate(task)) {
                return ResponseEntity.ok(task);
            } else {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Task>> findAll() {

        try {
            return ResponseEntity.ok(taskService.findAll());
        } catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable long id) {

        try {
            return ResponseEntity.ok(taskService.findById(id));
        } catch (IndexOutOfBoundsException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteAll() {

        try {
            taskService.deleteAll();
            return ResponseEntity.ok().build();
        } catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable long id) {

        try {
            taskService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch(IndexOutOfBoundsException ex){
            return ResponseEntity.notFound().build();
        } catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    public ResponseEntity<Boolean> update(@RequestBody Task task) {

        try {
            taskService.update(task);
            return ResponseEntity.ok().build();
        } catch (IndexOutOfBoundsException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
