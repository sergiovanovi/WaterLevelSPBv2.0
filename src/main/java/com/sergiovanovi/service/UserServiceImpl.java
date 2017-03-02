package com.sergiovanovi.service;

import com.sergiovanovi.model.User;
import com.sergiovanovi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(int id) {
        userRepository.delete(id);
    }

    @Override
    public User get(int id) {
        return userRepository.get(id);
    }

    @Override
    public Collection<User> getAll() {
        return userRepository.getAll();
    }
}
