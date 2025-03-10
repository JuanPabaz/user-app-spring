package com.users.users_backend.services;

import com.users.users_backend.entities.Role;
import com.users.users_backend.entities.UserEntity;
import com.users.users_backend.models.UserRequest;
import com.users.users_backend.repositories.RolRepository;
import com.users.users_backend.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RolRepository rolRepository;

    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder, RolRepository rolRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.rolRepository = rolRepository;
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
        List<Role> roles = new ArrayList<>();
        Optional<Role> optionalRole = rolRepository.findByRole("ROLE_USER");
        optionalRole.ifPresent(roles::add);
        userEntity.setRoles(roles);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<UserEntity> update(UserRequest userRequest, Long id) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            UserEntity updatedUserEntity = optionalUser.get();
            updatedUserEntity.setName(userRequest.getName());
            updatedUserEntity.setLastName(userRequest.getLastName());
            updatedUserEntity.setUsername(userRequest.getUsername());
            updatedUserEntity.setEmail(userRequest.getEmail());
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
