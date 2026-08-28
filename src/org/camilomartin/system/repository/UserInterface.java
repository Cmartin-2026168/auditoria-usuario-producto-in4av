package org.camilomartin.system.repository;

import org.camilomartin.system.model.User;

public interface UserInterface {

    void create(User user);

    User findByUser(String user);
}