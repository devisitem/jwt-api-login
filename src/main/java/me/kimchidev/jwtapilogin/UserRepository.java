package me.kimchidev.jwtapilogin;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository implements UserService {

    private final PasswordEncoder passwordEncoder;

    @Override
    public Collection<GrantedAuthority> getAuthorities(String userId) {
        List<GrantedAuthority> string_authorities = new ArrayList<>();

        if(userId.equals("tgjeon")){
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
    public PasswordEncoder passwordEncoder() {
        return this.passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account user = createUser();
        if(s.equals(user.getUserId())){
            user.setAuthorities(getAuthorities(user.getUserId()));
        }
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
