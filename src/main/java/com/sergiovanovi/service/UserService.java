package com.sergiovanovi.service;

import com.sergiovanovi.model.User;

import java.util.Collection;

public interface UserService {

    void save(User user);

    void delete(int id);

    User get(int id);

    //ORDERED by id
    Collection<User> getAll();
}
