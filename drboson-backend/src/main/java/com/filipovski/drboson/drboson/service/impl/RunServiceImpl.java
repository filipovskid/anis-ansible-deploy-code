package com.filipovski.drboson.drboson.service.impl;

import com.filipovski.drboson.drboson.avro.RunRecord;
import com.filipovski.drboson.drboson.common.RunStatus;
import com.filipovski.drboson.drboson.common.Utils;
import com.filipovski.drboson.drboson.config.AmazonS3Config;
import com.filipovski.drboson.drboson.model.DRBosonFile;
import com.filipovski.drboson.drboson.model.Dataset;
import com.filipovski.drboson.drboson.model.Project;
import com.filipovski.drboson.drboson.model.Run;
import com.filipovski.drboson.drboson.repository.*;
import com.filipovski.drboson.drboson.service.RunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Service
public class RunServiceImpl implements RunService {

    private final RunRepository runRepository;
    private final ProjectRepository projectRepository;
    private final DatasetRepository datasetRepository;
    private final RunFilesRepository runFilesRepository;
    private final FileStore fileStore;
    private final AmazonS3Config amazonS3Config;

    private final KafkaTemplate<String, RunRecord> kafkaTemplate;

    public RunServiceImpl(RunRepository runRepository,
                          ProjectRepository projectRepository,
                          DatasetRepository datasetRepository,
                          RunFilesRepository runFilesRepository,
                          FileStore fileStore,
                          AmazonS3Config amazonS3Config,
                          KafkaTemplate<String, RunRecord> kafkaTemplate) {

        this.runRepository = runRepository;
        this.projectRepository = projectRepository;
        this.datasetRepository = datasetRepository;
        this.runFilesRepository = runFilesRepository;
        this.fileStore = fileStore;
        this.amazonS3Config = amazonS3Config;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public List<Run> getProjectRuns(UUID projectId) {
        return runRepository.findRunsByProjectId(projectId);
    }

    @Override
    public Run createProjectRun(UUID projectId, UUID datasetId, String name, String description) throws Exception {
        Project project = projectRepository.findById(projectId).orElseThrow(Exception::new);
        Dataset dataset = datasetRepository.findProjectDataset(projectId, datasetId).orElseThrow(Exception::new);

        Run run = Run.builder()
                .project(project)
                .dataset(dataset)
                .name(name)
                .description(description)
                .status(RunStatus.PENDING)
                .build();
        runRepository.save(run);

        startRun(projectId, UUID.fromString(run.getId()));

        return run;
    }

    @Override
    public Run getRun(UUID runId) throws Exception {
        return runRepository.findById(runId.toString()).orElseThrow(Exception::new);
    }

    @Override
    public List<DRBosonFile> getRunFiles(UUID runId) {
        return runFilesRepository.findFilesByRunId(runId.toString());
    }

    @Override
    public StreamingResponseBody downloadRunFile(UUID runId, UUID fileId) throws Exception {
        DRBosonFile file = runFilesRepository.findFileByRunIdAndId(runId.toString(), fileId.toString())
                .orElseThrow(Exception::new);

        return Utils.getStreamingResponseBody(fileStore, amazonS3Config.getRunFilesBucketName(), file.getLocation());
    }

    @Override
    public Run updateRun(UUID runId, String name, String description) throws Exception {
        Run run = runRepository.findById(runId.toString()).orElseThrow(Exception::new);

        run.setName(name);
        run.setDescription(description);
        runRepository.save(run);

        return run;
    }

    @Override
    public void deleteRun(UUID runId) {
        runRepository.deleteById(runId.toString());
    }

    @Override
    public void startRun(UUID runId) throws Exception {
            Run run = runRepository.findById(runId.toString()).orElseThrow(Exception::new);
    public void startRun(UUID runId) throws Exception {
    public void startRun(UUID projectId, UUID runId) throws Exception {
        Project project = projectRepository.findById(projectId).orElseThrow(Exception::new);
        Run run = runRepository.findById(runId.toString()).orElseThrow(Exception::new);

        RunRecord runRecord = RunRecord.newBuilder()
                .setId(run.getId())
                .setProjectId(run.getProject().getId().toString())
                .setDatasetLocation(run.getDataset().getLocation())
                .setRepository(project.getRepository())
                .build();

        kafkaTemplate.send("runs", String.valueOf(runRecord.getId()), runRecord);
    }
}
