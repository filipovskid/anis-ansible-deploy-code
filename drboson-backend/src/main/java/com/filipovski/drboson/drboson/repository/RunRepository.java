package com.filipovski.drboson.drboson.repository;

import com.filipovski.drboson.drboson.model.Run;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RunRepository extends JpaRepository<Run, UUID> {
    List<Run> findRunsByProjectId(UUID projectId);
}
