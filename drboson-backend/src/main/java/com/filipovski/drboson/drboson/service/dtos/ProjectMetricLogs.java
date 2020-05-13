package com.filipovski.drboson.drboson.service.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@AllArgsConstructor
@Builder
public class ProjectMetricLogs {
    @JsonProperty("project_id")
    String projectId;

    @JsonProperty("logs")
    List<RunMetricLogs> runMetricLogs;

    public static ProjectMetricLogs of(String projectId, List<RunMetricLogs> runMetricLogs) {
        return new ProjectMetricLogs(projectId, runMetricLogs);
    }
}
