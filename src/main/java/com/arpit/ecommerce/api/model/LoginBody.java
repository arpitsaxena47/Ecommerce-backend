package com.arpit.ecommerce.api.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginBody {

    @NotNull
    @NotBlank
    private String username;
    /** The password to log in with. */
    @NotNull
    @NotBlank
    private String password;

}
