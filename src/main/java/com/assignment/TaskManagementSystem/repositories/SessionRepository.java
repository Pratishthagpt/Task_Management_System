package com.assignment.TaskManagementSystem.repositories;

import com.assignment.TaskManagementSystem.models.Session;
import com.assignment.TaskManagementSystem.models.User;
import com.assignment.TaskManagementSystem.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SessionRepository extends JpaRepository<Session, UUID> {

    Optional<Session> findByTokenAndUser(String token, User user);
}
