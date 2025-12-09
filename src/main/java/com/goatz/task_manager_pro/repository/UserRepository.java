package com.goatz.task_manager_pro.repository;

import com.goatz.task_manager_pro.model.User;
import com.goatz.task_manager_pro.model.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByRole(Role role);
    Optional<User> findByUsername(String username);
    Long countByRole(Role role);
}
