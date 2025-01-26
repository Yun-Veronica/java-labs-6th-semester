package org.example.newappa.service;

import org.springframework.transaction.annotation.Transactional;
import org.example.newappa.model.Tasks;
import org.example.newappa.repository.AppaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TasksService {
    @Autowired
    private final AppaRepo tasksRepository;

    public TasksService(AppaRepo tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public List<Tasks> findAll() {
        return tasksRepository.findAll();
    }

    public Tasks findById(Long id) {
        return tasksRepository.findById(id).orElse(null);
    }
    @Transactional
    public Tasks save(Tasks task) {
        return tasksRepository.save(task);
    }

    public void deleteById(Long id) {
        tasksRepository.deleteById(id);
    }
}
