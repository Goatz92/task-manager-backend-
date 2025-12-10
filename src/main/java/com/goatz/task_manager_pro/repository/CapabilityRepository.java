package com.goatz.task_manager_pro.repository;

import com.goatz.task_manager_pro.model.auth.Capability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
/**
 * Repository for Capability entity data access.
 * Extends JpaRepository for CRUD operations on capabilities.
 */
public interface CapabilityRepository extends JpaRepository<Capability, Long> {

}
