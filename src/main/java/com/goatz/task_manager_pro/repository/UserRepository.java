package com.goatz.task_manager_pro.repository;

import com.goatz.task_manager_pro.model.User;
import com.goatz.task_manager_pro.model.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for User entity data access.
 * Provides query methods for finding users by role and username, and counting by role.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Finds a user by their role.
     * @param role Role entity
     * @return Optional containing the user if found
     */
    Optional<User> findByRole(Role role);

    /**
     * Finds a user by their username.
     * @param username Username string
     * @return Optional containing the user if found
     */
    Optional<User> findByUsername(String username);

    /**
     * Counts the number of users with a given role.
     * @param role Role entity
     * @return Number of users with the specified role
     */
    Long countByRole(Role role);
}
