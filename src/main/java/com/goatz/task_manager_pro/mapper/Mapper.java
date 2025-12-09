package com.goatz.task_manager_pro.mapper;

import com.goatz.task_manager_pro.dto.UserInsertDTO;
import com.goatz.task_manager_pro.dto.UserReadOnlyDTO;
import com.goatz.task_manager_pro.model.User;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public User mapToUserEntity(UserInsertDTO userInsertDTO) {
        return User.builder().username(userInsertDTO.getUsername())
                .password(userInsertDTO.getPassword()).build();
    }

    public UserReadOnlyDTO mapUserToReadOnlyDTO(User user) {
        return new UserReadOnlyDTO(user.getUsername(), user.getRole().getName());
    }
}
