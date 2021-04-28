package me.kimchidev.jwtapilogin;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class UserRepository implements UserService {


    @Override
    public Collection<GrantedAuthority> getAuthorities(String userId) {
        List<GrantedAuthority> string_authorities = new ArrayList<>();
        System.out.println("UserRepository.getAuthorities");
        if(userId.equals("tgjeon")){
            System.out.println("UserRepository.getAuthorities : 권한부여");
            string_authorities.add(new SimpleGrantedAuthority("USER"));
            string_authorities.add(new SimpleGrantedAuthority("ADMIN"));
        }

        return string_authorities;
    }

    @Override
    public Account findUser(String userId) {
        return createUser();
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account user = createUser();
        System.out.println("user = " + user);
        if(s.equals(user.getUserId())){
            user.setAuthorities(getAuthorities(user.getUserId()));
        }
        System.out.println("user.getAuthorities() = " + user.getAuthorities());
        return user;
    }

    public static Account createUser(){
        Account account = Account.builder()
                .userId("tgjeon")
                .password("password486")
                .build();
        return account;
    }
}
