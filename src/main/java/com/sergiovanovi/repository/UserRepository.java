package com.sergiovanovi.repository;

import com.sergiovanovi.model.User;

import java.util.Collection;

public interface UserRepository {

    void save(User user);

    void delete(int id);

    User get(int id);

    //ORDERED by id
    Collection<User> getAll();

}
