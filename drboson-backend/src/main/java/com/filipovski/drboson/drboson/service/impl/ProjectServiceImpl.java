package com.filipovski.drboson.drboson.service.impl;

import com.filipovski.drboson.drboson.model.Project;
import com.filipovski.drboson.drboson.model.User;
import com.filipovski.drboson.drboson.repository.ProjectRepository;
import com.filipovski.drboson.drboson.repository.UserRepository;
import com.filipovski.drboson.drboson.service.ProjectService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository,
                              UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Project> getUserProjects(User user) {
        return projectRepository.findProjectsByOwnerId(user.getId());
    }

    @Override
    public Project createUserProject(User user, String name, String description, String repository) throws Exception {
        User projectOwner = userRepository.findByUsername(user.getUsername()).orElseThrow(Exception::new);
        Project project = new Project();
        project.setOwner(projectOwner);
        project.setName(name);
        project.setDescription(description);
        project.setRepository(repository);

        projectRepository.save(project);

        return project;
    }

    @Override
    public Project getProject(UUID projectId) throws Exception {
        return projectRepository.findById(projectId).orElseThrow(Exception::new);
    }

    @Override
    public Project getUserProject(UUID userId, UUID projectId) throws Exception {
        return projectRepository.findUserProject(userId, projectId).orElseThrow(Exception::new);
        // Possibly Unauthorized
    }

    @Override
    public Project updateProject(UUID projectId, String name, String description, String repository) throws Exception {
        Project project = projectRepository.findById(projectId).orElseThrow(Exception::new);

        project.setName(name);
        project.setDescription(description);
        project.setRepository(repository);
        projectRepository.save(project);

        return project;
    }

    @Override
    public Project updateUserProject(UUID userId, UUID projectId, String name, String description, String repository) throws Exception {
        Project project = projectRepository.findUserProject(userId, projectId).orElseThrow(Exception::new);
        // Possibly Unauthorized

        project.setName(name);
        project.setDescription(description);
        project.setRepository(repository);
        projectRepository.save(project);

        return project;
    }

    @Override
    public void deleteProject(UUID projectId) {
        projectRepository.deleteById(projectId);
    }

    @Override
    public void deleteUserProject(UUID userId, UUID projectId) throws Exception {
        projectRepository.findUserProject(userId, projectId).orElseThrow(Exception::new); // Possibly Unauthorized
        projectRepository.deleteUserProject(userId, projectId);
    }

}
