package com.yrmb.todo.service;

import com.yrmb.todo.model.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private ArrayList<Task> tasks;

    TaskService(){

        tasks = new ArrayList<Task>();

        // mock
        Task task1 = new Task(1, "Teste 1", LocalDateTime.now(), null);
        Task task2 = new Task(2, "Teste 2", LocalDateTime.now(), null);
        Task task3 = new Task(3, "Teste 3", LocalDateTime.now(), null);

        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

    }

    public boolean validate(Task task){
        return true;
    }

    public void create(Task task){

        task.setId(newId());
        task.setCreatedDate(LocalDateTime.now());
        task.setResolvedDate(null);
        tasks.add(task);
    }

    public void update(Task task){

        Task taskRemove = findById(task.getId());

        if (taskRemove != null) {
            tasks.remove(taskRemove);
            tasks.add(task);
        }
    }

    public void deleteById(long id){
        tasks.remove(findById((id)));
    }

    public void deleteAll() {
        tasks.clear();
    }

    public List<Task> findAll() {
        return tasks;
    }

    public Task findById(long id) {
        return tasks.stream().filter(t -> t.getId() == id).collect(Collectors.toList()).get(0);
    }

    public long newId(){
        return tasks.size() + 1;
    }

}
