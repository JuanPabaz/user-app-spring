package com.users.users_backend.services;

import com.users.users_backend.entities.UserEntity;
import com.users.users_backend.models.UserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<UserEntity> findAll();
    Optional<UserEntity> findById(Long id);
    UserEntity save(UserEntity userEntity);
    void deleteById(Long id);
    Optional<UserEntity> update(UserRequest userRequest, Long id);
    Page<UserEntity> findAllUsers(Pageable pageable);
}
