package org.example.entitys.user;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(@NotBlank String login, @NotBlank String password) {
}
