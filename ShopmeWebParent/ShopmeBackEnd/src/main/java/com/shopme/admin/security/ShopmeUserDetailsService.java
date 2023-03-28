package com.shopme.admin.security;

import com.shopme.admin.user.UserRepository;
import com.shopme.common.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class ShopmeUserDetailsService implements UserDetailsService{
   public final UserRepository userRepository;

    public ShopmeUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
