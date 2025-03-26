package org.example.entitys.user;

import jakarta.validation.constraints.NotBlank;

public record RegisterDTO(@NotBlank String name, @NotBlank String login, @NotBlank String password, UserRole role) {
}
