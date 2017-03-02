package com.sergiovanovi.repository.datajpa;

import com.sergiovanovi.model.User;
import com.sergiovanovi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class DataJpaUserRepositoryImpl implements UserRepository{

    @Autowired
    private CRUDUserRepository crudUserRepository;

    @Override
    public void save(User user) {
        crudUserRepository.save(user);
    }

    @Override
    public void delete(int id) {
        crudUserRepository.delete(id);
    }

    @Override
    public User get(int id) {
        return crudUserRepository.getOne(id);
    }

    @Override
    public Collection<User> getAll() {
        return crudUserRepository.findAll();
    }
}
