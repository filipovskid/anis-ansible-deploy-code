package com.filipovski.drboson.drboson.web.controllers;

import com.filipovski.drboson.drboson.model.Dataset;
import com.filipovski.drboson.drboson.service.DatasetService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/{projectId}/dataset")
public class DatasetController {

    private final DatasetService datasetService;

    public DatasetController(DatasetService datasetService) {
        this.datasetService = datasetService;
    }

    @GetMapping("{datasetId}")
    public Dataset getDataset(@PathVariable UUID projectId, @PathVariable UUID datasetId) throws Exception {
        return datasetService.getProjectDataset(projectId, datasetId);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Dataset createDataset(@PathVariable UUID projectId, String name,
                                 @RequestParam(required = false, defaultValue = "") String description,
                                 MultipartFile file) throws Exception {

        return datasetService.createDataset(projectId, name, description, file);
    }

    @PatchMapping("/{datasetId}")
    public Dataset updateDataset(@PathVariable UUID datasetId, String name,
                                 @RequestParam(required = false, defaultValue = "") String description) throws Exception {
        return datasetService.updateDataset(datasetId, name, description);
    }

    @DeleteMapping("/{datasetId}")
    public void deleteDataset(@PathVariable UUID datasetId) throws Exception {
        datasetService.deleteDataset(datasetId);
    }

}