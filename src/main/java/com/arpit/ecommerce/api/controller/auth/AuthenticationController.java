package com.arpit.ecommerce.api.controller.auth;

import com.arpit.ecommerce.api.model.LoginBody;
import com.arpit.ecommerce.api.model.LoginResponse;
import com.arpit.ecommerce.api.model.RegistrationBody;
import com.arpit.ecommerce.exception.UserAlreadyExistsException;
import com.arpit.ecommerce.service.JWTService;
import com.arpit.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    UserService userService;


    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody RegistrationBody registrationBody){
        try {
            userService.registerUser(registrationBody);
            return ResponseEntity.ok().build();
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginBody loginBody)
    {
        String jwt = userService.loginUser(loginBody);
        if(jwt == null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else {
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setJwt(jwt);
            return ResponseEntity.ok(loginResponse);
        }
    }
}
