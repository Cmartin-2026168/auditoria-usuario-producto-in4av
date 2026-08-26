package org.camilomartin.system.service;

import org.camilomartin.system.model.User;
import org.camilomartin.system.repository.UserRepository;

public class UserService {

    private UserRepository userRepo = new UserRepository();

    public UserStatus createUser(String user, String name, String lastName,
            String email, String password) {
        try {
            User newUser = new User(name, lastName, email, password, user);
            userRepo.create(newUser);
        } catch (Exception e) {
        }
        return null;
        
    }
}
