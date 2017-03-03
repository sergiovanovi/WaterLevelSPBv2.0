package com.sergiovanovi.service;

import com.sergiovanovi.model.User;
import mock.MockUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

import static org.junit.Assert.*;

public class UserServiceTest extends AbstractServiceTest{

    @Autowired
    private UserService userService;

    @Test
    public void saveAndGetByEmail() throws Exception {
        User expected = MockUserService.expected;
        userService.save(expected);
        User actual = userService.getByEmail(expected.getEmail());
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void delete() throws Exception {
        User expected = MockUserService.expected;
        userService.save(expected);
        assertTrue(userService.delete(expected.getId()));
        assertFalse(userService.delete(0));
    }

    @Test
    public void get() throws Exception {
        User expected = MockUserService.expected;
        userService.save(expected);
        assertTrue(expected.toString().equals(userService.get(expected.getId()).toString()));
        assertFalse(MockUserService.admin.toString().equals(userService.get(expected.getId()).toString()));
    }

    @Test
    public void getAll() throws Exception {
        Collection<User> actual =  userService.getAll();
        actual.forEach(meter -> meter.setId(0));
        assertEquals(MockUserService.users.toString(), actual.toString());
    }

}