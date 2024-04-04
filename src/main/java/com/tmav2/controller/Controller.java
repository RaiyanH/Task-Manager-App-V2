package com.tmav2.controller;
//
import com.tmav2.model.Task;
import com.tmav2.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class Controller {

    @GetMapping("/")
    public String textMsg(){
        return "Hello World! This is the Task App";
    }

    @Autowired
    private TaskRepo taskRepo;

    @PostMapping("/createTask")
    public ResponseEntity<Task> addTask(@RequestBody Task task){
            //Task task = new Task();
            Task taskCreated = taskRepo.save(task);
            return new ResponseEntity<>(taskCreated, HttpStatus.OK);
    }

    @GetMapping("/GetListOfTasks")
    public ResponseEntity<List<Task>> getListOfTasks(){

        try {
            List<Task> listOfTasks = new ArrayList<>();
            //Implement the findAll() method from Crud Operation. Also,
            taskRepo.findAll().forEach(listOfTasks::add);

            // checks if the list is empty in case no book was added
            if (listOfTasks.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(listOfTasks, HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/getTaskById/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable long id) {

        try {
            Optional<Task> task = taskRepo.findById(id);

            if (task.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(task.get(), HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PutMapping("/updateTaskById/{id}")
    public ResponseEntity<Task> updateTaskById(@PathVariable long id, @RequestBody Task newTask) {

        try {
            Optional<Task> oldTask = taskRepo.findById(id);

            if (oldTask.isEmpty()){

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            } else {

                Task updatedTask = oldTask.get();
                updatedTask.setTaskTitle(newTask.getTaskTitle());
                updatedTask.setTaskDescription(newTask.getTaskDescription());
                updatedTask.setTaskPriority(newTask.getTaskPriority());

                Task task = taskRepo.save(updatedTask);

                return new ResponseEntity<>(task, HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @DeleteMapping("/deleteTaskById/{id}")
    public ResponseEntity<Task> deleteTaskById(@PathVariable long id){
        /*
        try {
            Optional<Task> oldTask = taskRepo.findById(id);

            if (oldTask.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                taskRepo.delete(oldTask.get());
                return new ResponseEntity<>(HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        } */
        taskRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
