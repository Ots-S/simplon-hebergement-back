package com.simplon.simplontest.controller;

import com.simplon.simplontest.entity.Project;
import com.simplon.simplontest.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/api/project")
    public ResponseEntity<List<Project>> getProjects() {
        return new ResponseEntity<>(projectRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/api/project/{id}")
    public ResponseEntity<Optional<Project>> getProject(@PathVariable Long id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            return new ResponseEntity<>(projectRepository.findById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/api/project")
    public void saveProject(@RequestBody Project project) {
        System.out.println(project.getStartingDate());
        projectRepository.save(project);
    }

    @PutMapping("/api/project")
    public void updateProject(@RequestBody Project project) {
        projectRepository.save(project);
    }

    @DeleteMapping("/api/project/{id}")
    public void deleteProject(@PathVariable Long id) {
        Optional<Project> project = projectRepository.findById(id);
        projectRepository.delete(project.get());
    }
}

