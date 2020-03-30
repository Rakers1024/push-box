package com.aldrich_lin.pushbox.service;

import com.aldrich_lin.pushbox.entity.User;
import com.aldrich_lin.pushbox.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DatabaseDetailService implements UserDetailsService {

    protected final Log logger = LogFactory.getLog(this.getClass());


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("登录账号:"+s);
        User myUser = null;
        for(User user : userRepository.findAll()){
            if(s.equals(user.getUsername())) {
                myUser = user;
                break;
            }
        }
//        List<User> users = userRepository.findByName(s);
        UserDetails user = null;
        if (myUser == null) {
            this.logger.debug("Query returned no results for user '" + s + "'");
            throw new UsernameNotFoundException("Username  not found");
        } else {

            List<GrantedAuthority> dbAuths = new ArrayList<>();
            PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            user = new org.springframework.security.core.userdetails.User(s,encoder.encode(myUser.getPassword()),dbAuths);

        }

        return user;
    }
}
