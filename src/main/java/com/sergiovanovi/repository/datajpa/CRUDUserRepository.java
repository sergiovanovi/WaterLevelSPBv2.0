package com.sergiovanovi.repository.datajpa;

import com.sergiovanovi.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CRUDUserRepository extends JpaRepository<User, Integer>{

    @Override
    List<User> findAll(Sort sort);

    @Override
    @Transactional
    User save(User user);

    @Modifying
    @Transactional
    @Query(value = "DELETE u FROM users u WHERE u.id=:id", nativeQuery = true)
    int delete(@Param("id") int id);

    @Override
    User findOne(Integer id);

    @Query(value = "SELECT * FROM users u WHERE u.email=:email", nativeQuery = true)
    User findOneByEmail(@Param("email") String email);
}
