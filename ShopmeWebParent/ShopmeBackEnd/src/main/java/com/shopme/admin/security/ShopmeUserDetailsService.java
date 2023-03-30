package com.shopme.admin.security;

import com.shopme.admin.user.UserRepository;
import com.shopme.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


public class ShopmeUserDetailsService implements UserDetailsService{
    public UserRepository userRepository;

    public ShopmeUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ShopmeUserDetailsService() {
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(email);
        if (user != null){
            return new ShopmeUserDetails(user);
        }
        throw new UsernameNotFoundException("Не могу найти пользователя email = " +email);
    }
}
