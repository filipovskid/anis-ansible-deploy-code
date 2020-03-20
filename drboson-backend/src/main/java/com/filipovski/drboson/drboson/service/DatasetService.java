package com.filipovski.drboson.drboson.service;

import com.filipovski.drboson.drboson.model.Dataset;

import java.util.List;
import java.util.UUID;

public interface DatasetService {
    List<Dataset> getProjectDatasets(UUID projectId);

    Dataset getDataset(UUID datasetId) throws Exception;

    Dataset updateDataset(UUID datasetId, String name, String description) throws Exception;

    void deleteDataset(UUID datasetId);
}
