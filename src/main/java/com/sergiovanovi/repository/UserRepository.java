package com.sergiovanovi.repository;

import com.sergiovanovi.model.User;

import java.util.Collection;

public interface UserRepository {

    User save(User user);

    boolean delete(int id);

    User get(int id);

    User getByEmail(String email);

    //ORDERED by id
    Collection<User> getAll();

}
