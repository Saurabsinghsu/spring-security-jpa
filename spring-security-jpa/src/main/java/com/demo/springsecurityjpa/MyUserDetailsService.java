package com.demo.springsecurityjpa;

import com.demo.springsecurityjpa.model.MyUserDetails;
import com.demo.springsecurityjpa.model.User;
import com.demo.springsecurityjpa.repsitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUserName(userName);

        user.orElseThrow(()-> new UnsupportedOperationException("User "+userName+" is not a valid user"));

        //Converting optional type to MyUserDetails type
        return user.map(MyUserDetails::new).get();
    }
}
