package com.filipovski.drboson.drboson.service.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.filipovski.drboson.drboson.model.MetricLogs;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Builder
@Data
public class RunMetricLogs {
    @JsonProperty("run_id")
    private String runId;

    @JsonProperty("run_name")
    private String runName;

    @JsonProperty("logs")
    @JsonRawValue
    private List<String> metricLogs;

    public static RunMetricLogs of(String runId, List<MetricLogs> metricLogs) {
        List<String> logs = metricLogs.stream().map(MetricLogs::getLog).collect(Collectors.toList());

        return new RunMetricLogs(runId, "", logs);
    }

    public static RunMetricLogs of(String runId, String runName, List<MetricLogs> metricLogs) {
        List<String> logs = metricLogs.stream().map(MetricLogs::getLog).collect(Collectors.toList());

        return new RunMetricLogs(runId, runName, logs);
    }
}
