package com.schoolmgmt.application.dto;

public record CreateUserCommand(
        // Solo pedimos lo estrictamente necesario para el registro
        String username,
        String email,
        String password,
        String firstName,
        String lastName,
        String roleId
) {}