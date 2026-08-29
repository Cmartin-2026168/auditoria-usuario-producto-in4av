package org.camilomartin.system.service;

import org.camilomartin.system.model.User;
import org.camilomartin.system.repository.UserRepository;

public class UserService {

    private UserRepository userRepo = new UserRepository();

    public UserStatus createUser(String user, String name, String lastName,
            String email, String password) {

        if (user.isBlank() || name.isBlank() || lastName.isBlank()
                || email.isBlank() || password.isBlank()) {
            return UserStatus.EMPTY_FIELDS;
        }

        try {
            User newUser = new User(name, lastName, email, password, user);  
            userRepo.create(newUser);
            return UserStatus.USER_CREATED;
        } catch (Exception e) {
            return UserStatus.ERROR_USER_CREATED;
        }
    }
    public LoginStatus login(String user, String password) {

    if (user.isBlank() || password.isBlank()) {
        return LoginStatus.EMPTY_FIELDS;
    }

    try {
        User userFound = userRepo.findByUser(user);

        if (userFound == null) {
            return LoginStatus.USER_NOT_FOUND;
        }

        if (!userFound.getPassword().equals(password)) {
            return LoginStatus.WRONG_PASSWORD;
        }

        return LoginStatus.LOGIN_SUCCESS;

    } catch (Exception e) {
        return LoginStatus.LOGIN_ERROR;
    }
}
}