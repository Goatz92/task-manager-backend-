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

    @NotNull(message = "Name can not be null.")
    @Size(min = 2, message = "Name size must be at least 2 characters long.")
    private String firstname;
}
