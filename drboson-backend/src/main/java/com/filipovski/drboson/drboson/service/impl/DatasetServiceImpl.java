package com.filipovski.drboson.drboson.service.impl;

import com.filipovski.drboson.drboson.model.Dataset;
import com.filipovski.drboson.drboson.repository.DatasetRepository;
import com.filipovski.drboson.drboson.repository.FileStore;
import com.filipovski.drboson.drboson.service.DatasetService;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.UUID;

@Service
public class DatasetServiceImpl implements DatasetService {

    private final DatasetRepository datasetRepository;
    private final FileStore fileStore;

    public DatasetServiceImpl(DatasetRepository datasetRepository, FileStore fileStore) {
        this.datasetRepository = datasetRepository;
        this.fileStore = fileStore;
    }

    @Override
    public List<Dataset> getProjectDatasets(UUID projectId) {
        return datasetRepository.findDatasetsByProjectId(projectId);
    }

    @Override
    public Dataset getDataset(UUID datasetId) throws Exception {
        return datasetRepository.findById(datasetId).orElseThrow(Exception::new);
    }

    @Override
    public Dataset updateDataset(UUID datasetId, String name, String description) throws Exception {
        Dataset dataset = datasetRepository.findById(datasetId).orElseThrow(Exception::new);

        dataset.setName(name);
        dataset.setDescription(description);
        datasetRepository.save(dataset);

        return dataset;
    }

    @Override
    public void deleteDataset(UUID datasetId) {
        datasetRepository.deleteById(datasetId);
    }
}
