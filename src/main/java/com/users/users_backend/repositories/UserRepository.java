package com.users.users_backend.repositories;

import com.users.users_backend.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Page<UserEntity> findAll(Pageable pageable);

    Optional<UserEntity> findByUsername(String username);
}
