package com.arpit.ecommerce.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    /** The JWT token to be used for authentication. */
    private String jwt;

}
