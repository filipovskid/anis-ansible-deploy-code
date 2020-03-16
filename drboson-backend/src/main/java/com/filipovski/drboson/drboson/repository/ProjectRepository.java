package com.filipovski.drboson.drboson.repository;

import com.filipovski.drboson.drboson.model.Project;
import com.filipovski.drboson.drboson.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
    List<Project> findProjectsByOwnerId(UUID ownerId);
}
