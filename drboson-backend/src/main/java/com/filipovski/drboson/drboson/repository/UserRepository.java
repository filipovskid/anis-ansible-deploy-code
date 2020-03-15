package com.filipovski.drboson.drboson.repository;

import com.filipovski.drboson.drboson.model.User;
import com.filipovski.drboson.drboson.service.dtos.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);

    boolean existsUserByUsername(String username);

    UserProjection getUserProjectionByUsername(String username);
}
