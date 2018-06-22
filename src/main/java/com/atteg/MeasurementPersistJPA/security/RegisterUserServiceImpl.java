/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atteg.MeasurementPersistJPA.security;

import com.atteg.MeasurementPersistJPA.model.User;
import com.atteg.MeasurementPersistJPA.repository.UserRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Attegates
 */
@Service
public class RegisterUserServiceImpl implements RegisterUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired  // Bean in SecurityConfig
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(String username, String password) throws UsernameExistsException {

        if (usernameExists(username)) {
            throw new UsernameExistsException("Username \"" + username + "\" already exists.");
        }
        
        User user = new User();
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);

    }

    @Override
    public boolean usernameExists(String username) {
        
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return true;
        }
        return false;
        
    }

}
