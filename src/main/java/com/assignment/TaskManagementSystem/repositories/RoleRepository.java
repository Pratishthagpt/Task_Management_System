package com.assignment.TaskManagementSystem.repositories;

import com.assignment.TaskManagementSystem.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<UserRole, UUID> {

    Optional<UserRole> findByRole (String roleName);
}
