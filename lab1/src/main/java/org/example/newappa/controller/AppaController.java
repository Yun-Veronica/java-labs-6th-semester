package org.example.newappa.controller;

import jakarta.validation.Valid;
import org.example.newappa.model.Tasks;
import org.example.newappa.repository.AppaRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class AppaController {
    private final AppaRepo tasksRepository;

    public AppaController(AppaRepo tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    // GET all tasks
    @GetMapping
    public List<Tasks> getAllTasks() {
        return tasksRepository.findAll();
    }

    // GET a task by ID
    @GetMapping("/{id}")
    public Tasks getTaskById(@PathVariable Long id) {
        return tasksRepository.findById(id).orElse(null);
    }

    // CREATE
    @PostMapping("/create")
    @Transactional
    public ResponseEntity<Tasks> createTask(@Valid @RequestBody Tasks task) {
        try {
            if (task.getId() != null) {
                System.out.println(task.getId()+" "+ task.getTaskText()+" "+task.getName());
                return ResponseEntity.badRequest().body(null); // ID must be null for new tasks
            }
            Tasks savedTask = tasksRepository.save(task);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
        } catch (Exception e) {
            e.printStackTrace(); // Log exception details
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



    // UPDATE
    @PutMapping("/{id}")
    public Tasks updateTask(@PathVariable Long id, @RequestBody Tasks updatedTask) {
        Tasks task = tasksRepository.findById(id).orElse(null);
        if (task != null) {
            task.setName(updatedTask.getName());
            task.setTaskText(updatedTask.getTaskText());
            return tasksRepository.save(task);
        }
        return null;
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        tasksRepository.deleteById(id);
    }
}
