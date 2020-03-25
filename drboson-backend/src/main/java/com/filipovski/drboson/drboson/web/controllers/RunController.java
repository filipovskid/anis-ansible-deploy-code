package com.filipovski.drboson.drboson.web.controllers;

import com.filipovski.drboson.drboson.model.Run;
import com.filipovski.drboson.drboson.service.RunService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/{projectId}/run")
public class RunController {

    private final RunService runService;

    public RunController(RunService runService) {
        this.runService = runService;
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
}
