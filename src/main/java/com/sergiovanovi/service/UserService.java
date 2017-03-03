package com.sergiovanovi.service;

import com.sergiovanovi.model.User;

import java.util.Collection;

public interface UserService {

    User save(User user);

    boolean delete(int id);

    User get(int id);

    User getByEmail(String email);

    //ORDERED by id
    Collection<User> getAll();
}
