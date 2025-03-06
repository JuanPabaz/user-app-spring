package com.users.users_backend.services;

import com.users.users_backend.entities.User;
import com.users.users_backend.repositories.UserRepository;
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
    public List<User> findAll() {
        return List.of();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public User save(User user) {
        return null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {

    }
}
