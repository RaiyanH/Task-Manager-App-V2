package com.tmav2.controller;

import com.tmav2.model.Task;
import com.tmav2.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:3000") // Allow CORS for local host with port 3000 origins
public class TaskController {

    @Autowired
    private TaskRepo taskRepo;

//    @Autowired
//    private UserRepo userRepo;

    @PostMapping("/addTask")
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        Task taskCreated = taskRepo.save(task);
        return new ResponseEntity<>(taskCreated, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskRepo.findAll();
        if (tasks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/getTaskById/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable long id) {

        try {
            Optional<Task> task = taskRepo.findById(id);

            if (task.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(task.get(), HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTaskById(@PathVariable long id, @RequestBody Task newTask) {
        try {
            Optional<Task> oldTask = taskRepo.findById(id);
            if (oldTask.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            Task updatedTask = oldTask.get();
            updatedTask.setTaskStatus(newTask.getTaskStatus());
            updatedTask.setTaskTitle(newTask.getTaskTitle());
            updatedTask.setTaskDescription(newTask.getTaskDescription());
            updatedTask.setTaskPriority(newTask.getTaskPriority());
            //updatedTask.setTaskAssignee(newTask.getTaskAssignee());
            updatedTask.setTaskDeadline(newTask.getTaskDeadline());

            Task task = taskRepo.save(updatedTask);
            return new ResponseEntity<>(task, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteTaskById/{id}")
    public ResponseEntity<Task> deleteTaskById(@PathVariable long id) {
        taskRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
