package com.filipovski.drboson.drboson.service.impl;

import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.filipovski.drboson.drboson.config.AmazonS3Config;
import com.filipovski.drboson.drboson.model.Dataset;
import com.filipovski.drboson.drboson.model.Project;
import com.filipovski.drboson.drboson.repository.DatasetRepository;
import com.filipovski.drboson.drboson.repository.FileStore;
import com.filipovski.drboson.drboson.repository.ProjectRepository;
import com.filipovski.drboson.drboson.service.DatasetService;
import com.sun.org.apache.xpath.internal.operations.Mult;
import org.apache.tika.Tika;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypes;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class DatasetServiceImpl implements DatasetService {

    private final DatasetRepository datasetRepository;
    private final ProjectRepository projectRepository;
    private final FileStore fileStore;
    private final AmazonS3Config amazonS3Config;

    public DatasetServiceImpl(DatasetRepository datasetRepository,
                              ProjectRepository projectRepository,
                              FileStore fileStore,
                              AmazonS3Config amazonS3Config) {
        this.datasetRepository = datasetRepository;
        this.projectRepository = projectRepository;
        this.fileStore = fileStore;
        this.amazonS3Config = amazonS3Config;
    }

    @Override
    public List<Dataset> getAllProjectDatasets(UUID projectId) {
        return datasetRepository.findDatasetsByProjectId(projectId);
    }

    @Override
    public Dataset getProjectDataset(UUID projectId, UUID datasetId) throws Exception {
        return datasetRepository.findProjectDataset(projectId, datasetId).orElseThrow(Exception::new);
    }

    @Override
    public Dataset createDataset(UUID projectId, String name, String description, MultipartFile file) throws Exception {
        performChecks(file);

        Project project = projectRepository.findById(projectId).orElseThrow(Exception::new);

        Dataset dataset = new Dataset();
        dataset.setName(name);
        dataset.setDescription(description);
        dataset.setProject(project);

        String filename = String.format("%s--%s", UUID.randomUUID(), name);
        Map<String, String> metadata = extractMetadata(file);

        try {
            fileStore.save(amazonS3Config.getDatasetBucketName(), filename, file.getInputStream(), Optional.of(metadata));
            dataset.setLocation(filename);
            datasetRepository.save(dataset);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        return dataset;
    }

    private void performChecks(MultipartFile file) {
        isEmpty(file);
        checkContentType(file);
    }

    private void isEmpty(MultipartFile file) {
        if(file.isEmpty())
            throw new IllegalStateException("Cannot upload empty file [ " + file.getSize() + "]");
    }

    private void checkContentType(MultipartFile file) {
        List<String> allowedTypes = Arrays.asList("application/zip", "text/plain", "text/csv");
        String mimeType = getContentType(file);

        if(allowedTypes.stream().noneMatch(mimeType::equals))
            throw new IllegalStateException("Illegal content type !");
    }

    private String getContentType(MultipartFile file) {
        Tika tika = new Tika();

        try {
            return tika.detect(file.getBytes());
        } catch (IOException e) {
            throw new IllegalStateException("Can't get content type !");
        }
    }

    private Map<String, String> extractMetadata(MultipartFile file) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", this.getContentType(file));
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        return metadata;
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
    public void deleteDataset(UUID datasetId) throws Exception {
        Dataset dataset = datasetRepository.findById(datasetId).orElseThrow(Exception::new);
        fileStore.delete(amazonS3Config.getDatasetBucketName(), dataset.getLocation());

        datasetRepository.deleteById(datasetId);
    }

    @Override
    public StreamingResponseBody downloadDataset(UUID datasetId) throws Exception {
        Dataset dataset = datasetRepository.findById(datasetId).orElseThrow(Exception::new);
        S3Object object = fileStore.download(amazonS3Config.getDatasetBucketName(), dataset.getLocation())
                .orElseThrow(Exception::new);

        InputStream inputStream = object.getObjectContent().getDelegateStream();

        StreamingResponseBody responseBody = outputStream -> {

            int numberOfBytesToWrite;
            byte[] data = new byte[1024];
            while ((numberOfBytesToWrite = inputStream.read(data, 0, data.length)) != -1) {
                outputStream.write(data, 0, numberOfBytesToWrite);
            }

            inputStream.close();
            object.close();
        };

        return responseBody;
    }
}
