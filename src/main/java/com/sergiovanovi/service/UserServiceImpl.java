package com.sergiovanovi.service;

import com.sergiovanovi.AuthorizedUser;
import com.sergiovanovi.model.User;
import com.sergiovanovi.repository.UserRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

import static org.slf4j.LoggerFactory.getLogger;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final Logger LOG = getLogger("application");

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        User savedUser = userRepository.save(user);
        if (savedUser != null) {
            LOG.info(LocalDateTime.now() + " user successfully SAVED with email: " + user.getEmail());
        } else {
            LOG.error(LocalDateTime.now() + "user NOT SAVED with email: " + user.getEmail());
        }
        return savedUser;
    }

    @Override
    public boolean delete(int id) {
        if (userRepository.delete(id)){
            LOG.info(LocalDateTime.now() + " user successfully DELETED with id: " + id);
            return true;
        } else {
            LOG.error(LocalDateTime.now() + " user NOT DELETED with id: " + id);
            return false;
        }
    }

    @Override
    public User get(int id) {
        User user = userRepository.get(id);
        if (user != null){
            LOG.info(LocalDateTime.now() + " user successfully FOUND with id: " + id);
        } else {
            LOG.error(LocalDateTime.now() + " user NOT FOUND with id: " + id);
        }
        return user;
    }

    @Override
    public User getByEmail(String email) {
        User user = userRepository.getByEmail(email);
        if (user != null){
            LOG.info(LocalDateTime.now() + " user successfully FOUND with email: " + email);
        } else {
            LOG.error(LocalDateTime.now() + " user NOT FOUND with email: " + email);
        }
        return user;
    }

    @Override
    public Collection<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User u = userRepository.getByEmail(email.toLowerCase());
        if (u == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(u);
    }
}
