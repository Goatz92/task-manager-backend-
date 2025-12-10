
package com.goatz.task_manager_pro.service;

import java.util.List;
import com.goatz.task_manager_pro.model.User;

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

    /**
     * Returns a list of all users in the system.
     * @return List of User entities
     */
    List<User> getAllUsers();
}
