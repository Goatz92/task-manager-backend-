package com.goatz.task_manager_pro.repository;

import com.goatz.task_manager_pro.model.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Role entity data access.
 * Extends JpaRepository for CRUD operations on roles.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
