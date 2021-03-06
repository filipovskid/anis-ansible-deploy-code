package com.filipovski.drboson.drboson.web.controllers;

import com.filipovski.drboson.drboson.model.Project;
import com.filipovski.drboson.drboson.model.User;
import com.filipovski.drboson.drboson.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "*"}, allowCredentials = "true")
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<Project> getUserProjects(@AuthenticationPrincipal User user) {
        return projectService.getUserProjects(user);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Project createProject(String name, String description, String repository,
                                 @AuthenticationPrincipal User user) throws Exception {
        return projectService.createUserProject(user, name, description, repository);
    }

    @GetMapping("/{projectId}")
    public Project getProject(@PathVariable UUID projectId, @AuthenticationPrincipal User user) throws Exception {
        return projectService.getUserProject(user.getId(), projectId);
    }

    @PatchMapping("/{projectId}")
    public Project updateProject(@PathVariable UUID projectId, String name, String description, String repository,
                                 @AuthenticationPrincipal User user) throws Exception {
        return projectService.updateUserProject(user.getId(), projectId, name, description, repository);
    }

    @DeleteMapping("/{projectId}")
    public void deleteProject(@PathVariable UUID projectId, @AuthenticationPrincipal User user) throws Exception {
        projectService.deleteUserProject(user.getId(), projectId);
    }
}
