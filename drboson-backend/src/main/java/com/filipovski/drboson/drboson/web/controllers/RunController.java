package com.filipovski.drboson.drboson.web.controllers;

import com.filipovski.drboson.drboson.model.Run;
import com.filipovski.drboson.drboson.service.MetricLogsService;
import com.filipovski.drboson.drboson.service.RunService;
import com.filipovski.drboson.drboson.service.dtos.ProjectMetricLogs;
import com.filipovski.drboson.drboson.service.dtos.RunMetricLogs;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "*"}, allowCredentials = "true")
@RequestMapping(value = "/{projectId}/run", produces = MediaType.APPLICATION_JSON_VALUE)
public class RunController {

    private final RunService runService;
    private final MetricLogsService metricLogsService;

    public RunController(RunService runService, MetricLogsService metricLogsService) {
        this.runService = runService;
        this.metricLogsService = metricLogsService;
    }

    @GetMapping
    public List<Run> getProjectRuns(@PathVariable UUID projectId) {
        return runService.getProjectRuns(projectId);
    }

    @GetMapping("/{runId}")
    public Run getRun(@PathVariable UUID runId) throws Exception {
        return runService.getRun(runId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Run createRun(@PathVariable UUID projectId, @RequestParam UUID datasetId, String name,
                         @RequestParam(required = false, defaultValue = "") String description) throws Exception {
        return runService.createProjectRun(projectId, datasetId, name, description);
    }

    @PatchMapping("/{runId}")
    public Run updateRun(@PathVariable UUID runId, String name,
                         @RequestParam(required = false, defaultValue = "") String description) throws Exception {
        return runService.updateRun(runId, name, description);
    }

    @DeleteMapping("/{runId}")
    public void deleteRun(@PathVariable UUID runId) {
        runService.deleteRun(runId);
    }

    @GetMapping("/{runId}/start")
    public void startRun(@PathVariable UUID projectId,
                         @PathVariable UUID runId) throws Exception {
        runService.startRun(projectId, runId);
    }

    @GetMapping("/logs")
    public ProjectMetricLogs projectRunMetrics(@PathVariable UUID projectId) {
        return metricLogsService.getProjectMetricLogs(projectId);
    }

    @GetMapping("/{runId}/logs")
    public RunMetricLogs projectRunMetrics(@PathVariable UUID projectId, @PathVariable UUID runId) {
        return metricLogsService.getRunMetricLogs(projectId, runId);
    }
}
