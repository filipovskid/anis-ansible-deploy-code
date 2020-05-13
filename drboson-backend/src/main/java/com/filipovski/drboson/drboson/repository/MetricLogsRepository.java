package com.filipovski.drboson.drboson.repository;

import com.filipovski.drboson.drboson.model.MetricLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetricLogsRepository extends JpaRepository<MetricLogs, String> {
    List<MetricLogs> findMetricLogsByProjectId(String projectId);

    List<MetricLogs> findMetricLogsByProjectIdAndRunId(String projectId, String runId);
}
