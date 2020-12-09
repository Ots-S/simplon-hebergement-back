package com.simplon.simplontest.controller;

import com.simplon.simplontest.entity.Project;
import com.simplon.simplontest.repository.ProjectRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ProjectController {

    @Autowired
    private ProjectRespository projectRespository;

    @GetMapping("/api/project")
    public List<Project> getProjects() {
        return projectRespository.findAll();
    }

    @GetMapping("/api/project/{id}")
    public Optional<Project> getProject(@PathVariable Long id) {
        return projectRespository.findById(id);
    }

    @PostMapping("/api/project/")
    public void saveProject(@RequestBody Project project) {
        projectRespository.save(project);
    }

    @DeleteMapping("/api/project/{id}")
    public void deleteProject(@PathVariable Long id) {
        Optional<Project> project = projectRespository.findById(id);
        projectRespository.delete(project.get());
    }
}

