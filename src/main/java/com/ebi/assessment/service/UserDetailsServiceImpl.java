package com.ebi.assessment.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ebi.assessment.model.User;
import com.ebi.assessment.repository.UserRepository;

@Component

public class UserDetailsServiceImpl implements UserDetailsService


{
	@Autowired
    private UserRepository userRepository;


    public UserDetailsServiceImpl(UserRepository userRepository)

    {

        this.userRepository = userRepository;

    }


    

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException

    {

        User user = userRepository.findByUsername(username);

        if(user == null)

        {

            throw new UsernameNotFoundException(username);
            
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.emptyList());

    }

}
