package com.arpit.ecommerce.api.model;

import jakarta.validation.constraints.*;
import lombok.Data;


@Data
public class RegistrationBody {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String username;

    @Email
    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 6, max = 32)
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$")
    private String password;

    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;
}
