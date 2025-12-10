package com.goatz.task_manager_pro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object for exposing read-only user information.
 * Used for displaying user details without sensitive fields.
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserReadOnlyDTO {

    private String username;
    private String role;
}
