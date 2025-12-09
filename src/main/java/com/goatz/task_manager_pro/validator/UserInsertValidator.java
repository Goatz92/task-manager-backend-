package com.goatz.task_manager_pro.validator;

import com.goatz.task_manager_pro.dto.UserInsertDTO;
import com.goatz.task_manager_pro.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.groups.ValidationOrder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserInsertValidator implements Validator {
    private final UserRepository userRepository;

    @Override
    public boolean supports(@NonNull Class<?> insertClass) {
        return UserInsertDTO.class == insertClass;
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        UserInsertDTO userInsertDTO = (UserInsertDTO) target;

        if (userRepository.findByUsername(userInsertDTO.getUsername()).isPresent()) {
            log.error("Save failed for user with username={}. User already exists.", userInsertDTO.getUsername());
            errors.rejectValue("username", "username.user.exists", "Username exists");
        }
    }
}
