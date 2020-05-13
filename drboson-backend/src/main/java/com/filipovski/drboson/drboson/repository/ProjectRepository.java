package com.filipovski.drboson.drboson.repository;

import com.filipovski.drboson.drboson.model.Project;
import com.filipovski.drboson.drboson.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {
    List<Project> findProjectsByOwnerId(UUID ownerId);

    @Query("SELECT p FROM Project p WHERE p.owner.id = :ownerId and p.id = :projectId")
    Optional<Project> findUserProject(@Param("ownerId") UUID ownerId,
                                      @Param("projectId") UUID projectId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Project p WHERE p.owner.id = :ownerId and p.id = :projectId")
    void deleteUserProject(@Param("ownerId") UUID ownerId,
                           @Param("projectId") UUID projectId);
}
