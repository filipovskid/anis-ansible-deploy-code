package com.filipovski.drboson.drboson.repository;

import com.filipovski.drboson.drboson.model.DRBosonFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RunFilesRepository extends JpaRepository<DRBosonFile, String> {
    List<DRBosonFile> findFilesByRunId(String runId);

    Optional<DRBosonFile> findFileByRunIdAndId(String runId, String fileId);
}
