package com.sergiovanovi.repository;

import com.sergiovanovi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer>{

    @Override
    List<User> findAll();

    @Override
    User save(User user);

    @Modifying
    @Transactional
    void delete(int id);

    @Override
    User getOne(Integer id);
}
