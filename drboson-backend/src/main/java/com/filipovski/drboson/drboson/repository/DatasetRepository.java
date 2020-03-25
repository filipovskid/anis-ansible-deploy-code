package com.filipovski.drboson.drboson.repository;

import com.filipovski.drboson.drboson.model.Dataset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DatasetRepository extends JpaRepository<Dataset, UUID> {
    List<Dataset> findDatasetsByProjectId(UUID projectId);

    @Query("SELECT d FROM Dataset d WHERE d.project.id = :projectId and d.id = :datasetId")
    Optional<Dataset> findProjectDataset(@Param("projectId") UUID projectId,
                                         @Param("datasetId") UUID datasetId);
}
