package com.filipovski.drboson.drboson.service;

import com.filipovski.drboson.drboson.model.Run;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface RunService {
    List<Run> getProjectRuns(UUID projectId);

    Run createProjectRun(UUID projectId, UUID datasetId, String name, String description) throws Exception;

    Run getRun(UUID runId) throws Exception;

    Run updateRun(UUID runId, String name, String description) throws Exception;

    void deleteRun(UUID runId);

    void startRun(UUID projectId, UUID runId) throws Exception;
}
