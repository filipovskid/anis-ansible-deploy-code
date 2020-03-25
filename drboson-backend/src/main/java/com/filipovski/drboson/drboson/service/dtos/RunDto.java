package com.filipovski.drboson.drboson.service.dtos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.filipovski.drboson.drboson.model.Dataset;
import com.filipovski.drboson.drboson.model.Project;
import com.filipovski.drboson.drboson.model.Run;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RunDto {
    private String name;
    private String description;
    private ObjectNode project;
    private ObjectNode dataset;

    public static RunDto of(Run run) {
        ObjectMapper objectMapper = new ObjectMapper();
        Project project = run.getProject();
        Dataset dataset = run.getDataset();

        ObjectNode projectNode = objectMapper.createObjectNode();
        projectNode.put("id", String.valueOf(project.getId()));

        ObjectNode datasetNode = objectMapper.createObjectNode();
        datasetNode.put("id", String.valueOf(dataset.getId()));
        datasetNode.put("location", dataset.getLocation());

        return RunDto.builder()
                .description(run.getDescription())
                .name(run.getName())
                .project(projectNode)
                .dataset(datasetNode)
                .build();
    }
}
