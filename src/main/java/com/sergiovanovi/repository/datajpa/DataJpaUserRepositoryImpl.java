package com.sergiovanovi.repository.datajpa;

import com.sergiovanovi.model.User;
import com.sergiovanovi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class DataJpaUserRepositoryImpl implements UserRepository{
    private static final Sort SORT_BY_EMAIL = new Sort("email");

    @Autowired
    private CRUDUserRepository crudUserRepository;

    @Override
    public User save(User user) {
        return crudUserRepository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return crudUserRepository.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        return crudUserRepository.findOne(id);
    }

    @Override
    public User getByEmail(String email) {
        return crudUserRepository.findOneByEmail(email);
    }

    @Override
    public Collection<User> getAll() {
        return crudUserRepository.findAll(SORT_BY_EMAIL);
    }
}
