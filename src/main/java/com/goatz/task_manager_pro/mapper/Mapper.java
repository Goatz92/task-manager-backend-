package com.goatz.task_manager_pro.mapper;

import com.goatz.task_manager_pro.dto.UserInsertDTO;
import com.goatz.task_manager_pro.dto.UserReadOnlyDTO;
import com.goatz.task_manager_pro.model.User;
import org.springframework.stereotype.Component;

@Component
/**
 * Utility class for mapping between DTOs and entity objects.
 * Provides conversion methods for user registration and read-only views.
 */
public class Mapper {

    /**
     * Maps a UserInsertDTO to a User entity (for registration).
     * @param userInsertDTO DTO containing user registration data
     * @return User entity with username and password set
     */
    public User mapToUserEntity(UserInsertDTO userInsertDTO) {
        return User.builder().username(userInsertDTO.getUsername())
                .password(userInsertDTO.getPassword()).build();
    }

    /**
     * Maps a User entity to a UserReadOnlyDTO (for display).
     * @param user User entity
     * @return DTO with username and role name
     */
    public UserReadOnlyDTO mapUserToReadOnlyDTO(User user) {
        return new UserReadOnlyDTO(user.getUsername(), user.getRole().getName());
    }
}
