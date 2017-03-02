package com.sergiovanovi.repository.datajpa;

import com.sergiovanovi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CRUDUserRepository extends JpaRepository<User, Integer>{

    @Override
    List<User> findAll();

    @Override
    User save(User user);

    @Modifying
    @Transactional
    @Query("DELETE FROM users u WHERE u.id=:id")
    void delete(@Param("id") int id);

    @Override
    User getOne(Integer id);
}
