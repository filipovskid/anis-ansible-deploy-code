package com.filipovski.drboson.drboson.repository;

import com.filipovski.drboson.drboson.model.Run;
import com.filipovski.drboson.drboson.service.dtos.RunNameProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RunRepository extends JpaRepository<Run, String> {
    List<Run> findRunsByProjectId(UUID projectId);

    List<RunNameProjection> findRunNamesByProjectId(UUID projectId);

    RunNameProjection findRunNameById(String runId);

//    @Query("SELECT run.name FROM Run run WHERE run.project.id = :projectId")
//    List<String> getProjectRunNames(UUID projectId);

//    @Query("SELECT run.name FROM Run run WHERE run")
//    String getRunName(String runId);
}
