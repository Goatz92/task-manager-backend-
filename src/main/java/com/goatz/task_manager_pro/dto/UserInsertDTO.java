package com.goatz.task_manager_pro.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserInsertDTO {

    @NotNull(message = "Userame can not be null.")
    @Size(min = 3, max = 20, message = "userame size must be between 3 and 20 characters long.")
    private String username;

    @NotEmpty(message = "Password is required.")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "Password must contain: 1 uppercase, 1 lowercase, 1 digit, 1 special character, and no whitespace"
    )
    private String password;

    private Long roleId;
}