package com.filipovski.drboson.drboson.service;

import com.filipovski.drboson.drboson.model.Dataset;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface DatasetService {
    List<Dataset> getAllProjectDatasets(UUID projectId);

    Dataset getProjectDataset(UUID projectId, UUID datasetId) throws Exception;

    Dataset createDataset(UUID projectId, String name, String description, MultipartFile file) throws Exception;

    Dataset updateDataset(UUID datasetId, String name, String description) throws Exception;

    void deleteDataset(UUID datasetId);
}
