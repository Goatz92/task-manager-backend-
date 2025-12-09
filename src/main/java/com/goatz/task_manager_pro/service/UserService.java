package com.goatz.task_manager_pro.service;

import com.goatz.task_manager_pro.dao.UserRepository;
import com.goatz.task_manager_pro.dto.UserInsertDTO;
import com.goatz.task_manager_pro.exceptions.EntityAlreadyExistsException;
import com.goatz.task_manager_pro.mapper.Mapper;
import com.goatz.task_manager_pro.model.User;
import com.goatz.task_manager_pro.model.auth.Role;
import com.goatz.task_manager_pro.repository.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Mapper mapper;
    private final RoleRepository roleRepository;

    @Transactional(rollbackOn = Exception.class)
    public void saveUser(UserInsertDTO userInsertDTO) throws EntityAlreadyExistsException {
        try {
            if (userRepository.findByUsername(userInsertDTO.getUsername()).isPresent()) {
                throw new EntityAlreadyExistsException("User", "User with username " + userInsertDTO.getUsername() + " already exists.");
            }
            User user = mapper.mapToUserEntity(userInsertDTO);
            user.setPassword(passwordEncoder.encode(user.getPassword()));   // Encrypt password
            Role role = roleRepository.findById(userInsertDTO.getRoleId())
                    .orElse(null);
            user.setRole(role); // does not check for null, since UI restricts entry
            userRepository.save(user);
            log.info("Save succeeded for user with username={}", userInsertDTO.getUsername());
        } catch (EntityAlreadyExistsException e) {
            log.error("Save failed for user with username={}. User already exists", userInsertDTO.getUsername(), e);
            throw e;
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
