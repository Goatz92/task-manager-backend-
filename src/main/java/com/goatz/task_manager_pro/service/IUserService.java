package com.goatz.task_manager_pro.service;

import com.goatz.task_manager_pro.dto.UserInsertDTO;
import com.goatz.task_manager_pro.core.exceptions.EntityAlreadyExistsException;

/**
 * Interface for user-related service operations.
 * Defines contract for saving a new user.
 */
public interface IUserService {
    /**
     * Registers a new user in the system.
     * @param userInsertDTO DTO containing user registration data
     * @throws EntityAlreadyExistsException if username already exists
     */
    void saveUser(UserInsertDTO userInsertDTO) throws EntityAlreadyExistsException;
}
