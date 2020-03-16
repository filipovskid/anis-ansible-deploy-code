package com.filipovski.drboson.drboson.web.controllers;

import com.filipovski.drboson.drboson.model.Project;
import com.filipovski.drboson.drboson.model.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/projects")
public class ProjectController {
    public List<Project> getUserProjects(User user) {
        return null;
    }
}
