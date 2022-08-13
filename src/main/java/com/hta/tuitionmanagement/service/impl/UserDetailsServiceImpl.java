package com.hta.tuitionmanagement.service.impl;

import com.hta.tuitionmanagement.model.UserEntity;
import com.hta.tuitionmanagement.repo.UserEntityRepository;
import com.hta.tuitionmanagement.utils.DataUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userEntityRepository.findByUsername(username);
        User newUser = null;
        if (user != null) {
            if (!"ACTIVE".equalsIgnoreCase(user.getStatus())) {
                throw new UsernameNotFoundException("User not found with username: ");
            }
            List<String> role = new ArrayList<String>();
            role.add("USER");
            List<Long> listRole = new ArrayList<Long>();
            newUser = new User(user.getUsername(), user.getPassword(), buildSimpleGrantedAuthorities(role));
        }
        return newUser;
    }

    private static List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(List<String> roleList) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (DataUtils.notNullOrEmpty(roleList)) {
            for (String role : roleList) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
        }
        return authorities;
    }
}
