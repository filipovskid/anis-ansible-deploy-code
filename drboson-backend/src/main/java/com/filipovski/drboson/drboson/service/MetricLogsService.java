package com.filipovski.drboson.drboson.service;

import com.filipovski.drboson.drboson.model.MetricLogs;
import com.filipovski.drboson.drboson.service.dtos.ProjectMetricLogs;
import com.filipovski.drboson.drboson.service.dtos.RunMetricLogs;

import java.util.List;
import java.util.UUID;

public interface MetricLogsService {
    ProjectMetricLogs getProjectMetricLogs(UUID projectId);

    RunMetricLogs getRunMetricLogs(UUID projectId, UUID runId);
}

