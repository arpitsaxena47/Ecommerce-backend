package com.arpit.ecommerce.service;

import com.arpit.ecommerce.api.model.LoginBody;
import com.arpit.ecommerce.api.model.RegistrationBody;
import com.arpit.ecommerce.exception.UserAlreadyExistsException;
import com.arpit.ecommerce.model.LocalUser;
import com.arpit.ecommerce.model.dao.LocalUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private LocalUserDAO localUserDAO;

    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    JWTService jwtService;

    public LocalUser registerUser(RegistrationBody registrationBody) throws UserAlreadyExistsException
    {
        if(localUserDAO.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent() ||
                localUserDAO.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        LocalUser user=  new LocalUser();
        user.setEmail(registrationBody.getEmail());
        user.setFirstName(registrationBody.getFirstName());
        user.setLastName(registrationBody.getLastName());
        user.setUsername(registrationBody.getUsername());
        //Todo:encrypt password
        user.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));
        return localUserDAO.save(user);
    }

    public String loginUser(LoginBody loginBody) {
        Optional<LocalUser> opUser = localUserDAO.findByUsernameIgnoreCase(loginBody.getUsername());
        if (opUser.isPresent()) {
            LocalUser user = opUser.get();
            if (encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())) {
                return jwtService.generateJWT(user);
            }
        }
        return null;
    }
}
