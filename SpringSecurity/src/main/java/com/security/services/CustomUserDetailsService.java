package com.security.services;

import com.security.model.CustomUserDetails;
import com.security.model.User;
import com.security.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final User user = this.userRepository.findByUsername(username);

        if (user==null){
            throw new UsernameNotFoundException("User not found!!");
        }
        else{
            return new CustomUserDetails(user);
        }


        //user database

//        if (username.equals("Pranav")){
//            return new User("Pranav","Pranav123", new ArrayList<>());
//        }
//        else {
//            throw new UsernameNotFoundException("User not found !!");
//        }

    }
}