package com.filipovski.drboson.drboson.web.controllers;

import com.filipovski.drboson.drboson.model.Dataset;
import com.filipovski.drboson.drboson.service.DatasetService;
import org.springframework.data.repository.query.Param;
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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Dataset createDataset(@PathVariable UUID projectId,
                                 String name,
                                 @RequestParam(required = false, defaultValue = "") String description,
                                 MultipartFile file) throws Exception {

        return datasetService.createDataset(projectId, name, description, file);
    }
}
