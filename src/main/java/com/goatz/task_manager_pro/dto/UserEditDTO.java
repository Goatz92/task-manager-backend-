package com.goatz.task_manager_pro.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserEditDTO {

    @NotNull(message = "Username can not be null.")
    @Size(min = 2, message = "Username size must be at least 2 characters long.")
    private String username;
}
