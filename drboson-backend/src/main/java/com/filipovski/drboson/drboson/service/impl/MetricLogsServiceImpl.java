package com.filipovski.drboson.drboson.service.impl;

import com.filipovski.drboson.drboson.model.MetricLogs;
import com.filipovski.drboson.drboson.repository.MetricLogsRepository;
import com.filipovski.drboson.drboson.service.MetricLogsService;
import com.filipovski.drboson.drboson.service.dtos.ProjectMetricLogs;
import com.filipovski.drboson.drboson.service.dtos.RunMetricLogs;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MetricLogsServiceImpl implements MetricLogsService {

    private final MetricLogsRepository metricLogsRepository;

    public MetricLogsServiceImpl(MetricLogsRepository metricLogsRepository) {
        this.metricLogsRepository = metricLogsRepository;
    }

    @Override
    public ProjectMetricLogs getProjectMetricLogs(UUID projectId) {
        List<RunMetricLogs> runMetricLogs =
                metricLogsRepository.findMetricLogsByProjectId(projectId.toString())
                .stream()
                .collect(Collectors.groupingBy(MetricLogs::getRunId))
                .entrySet().stream()
                .map(entry -> RunMetricLogs.of(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        return ProjectMetricLogs.of(projectId.toString(), runMetricLogs);
    }

    @Override
    public RunMetricLogs getRunMetricLogs(UUID projectId, UUID runId) {
        List<MetricLogs> metricLogs =
                metricLogsRepository.findMetricLogsByProjectIdAndRunId(projectId.toString(), runId.toString());

        return RunMetricLogs.of(runId.toString(), metricLogs);
    }
}
