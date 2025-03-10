package com.users.users_backend.repositories;

import com.users.users_backend.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Role,Integer> {

    Optional<Role> findByRole(String name);
}
