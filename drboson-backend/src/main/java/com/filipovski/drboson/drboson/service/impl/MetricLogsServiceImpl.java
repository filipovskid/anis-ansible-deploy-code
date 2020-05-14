package com.filipovski.drboson.drboson.service.impl;

import com.filipovski.drboson.drboson.model.MetricLogs;
import com.filipovski.drboson.drboson.repository.MetricLogsRepository;
import com.filipovski.drboson.drboson.repository.RunRepository;
import com.filipovski.drboson.drboson.service.MetricLogsService;
import com.filipovski.drboson.drboson.service.dtos.RunNameProjection;
import com.filipovski.drboson.drboson.service.dtos.ProjectMetricLogs;
import com.filipovski.drboson.drboson.service.dtos.RunMetricLogs;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MetricLogsServiceImpl implements MetricLogsService {

    private final MetricLogsRepository metricLogsRepository;
    private final RunRepository runRepository;

    public MetricLogsServiceImpl(MetricLogsRepository metricLogsRepository, RunRepository runRepository) {
        this.metricLogsRepository = metricLogsRepository;
        this.runRepository = runRepository;
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

        Map<String, String> runNames = runRepository.findRunNamesByProjectId(projectId).stream()
                .collect(Collectors.toMap(RunNameProjection::getId, RunNameProjection::getName));

        runMetricLogs.forEach(log -> log.setRunName(runNames.get(log.getRunId())));

        return ProjectMetricLogs.of(projectId.toString(), runMetricLogs);
    }

    @Override
    public RunMetricLogs getRunMetricLogs(UUID projectId, UUID runId) {
        List<MetricLogs> metricLogs =
                metricLogsRepository.findMetricLogsByProjectIdAndRunId(projectId.toString(), runId.toString());

        String runName = runRepository.findRunNameById(runId.toString()).getName();

        return RunMetricLogs.of(runId.toString(), runName, metricLogs);
    }
}
