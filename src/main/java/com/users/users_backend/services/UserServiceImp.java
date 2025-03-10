package com.users.users_backend.services;

import com.users.users_backend.entities.UserEntity;
import com.users.users_backend.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements IUserService {

    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<UserEntity> update(UserEntity userEntity, Long id) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            UserEntity updatedUserEntity = optionalUser.get();
            updatedUserEntity.setName(userEntity.getName());
            updatedUserEntity.setLastName(userEntity.getLastName());
            updatedUserEntity.setUsername(userEntity.getUsername());
            updatedUserEntity.setEmail(userEntity.getEmail());
            updatedUserEntity.setPassword(userEntity.getPassword());
            return Optional.of(userRepository.save(updatedUserEntity));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Page<UserEntity> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
