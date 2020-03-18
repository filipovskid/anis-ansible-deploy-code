package com.filipovski.drboson.drboson.service;

import com.filipovski.drboson.drboson.model.Project;
import com.filipovski.drboson.drboson.model.User;

import java.util.List;
import java.util.UUID;

public interface ProjectService {
    List<Project> getUserProjects(User user);

    Project createUserProject(User user, String name, String description, String repository) throws Exception;

    Project getProject(UUID projectId) throws Exception;

    Project updateProject(UUID projectId, String name, String description, String repository) throws Exception;

    void deleteProject(UUID projectId);
}
