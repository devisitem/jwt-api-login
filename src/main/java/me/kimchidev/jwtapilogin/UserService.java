package me.kimchidev.jwtapilogin;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account user = createUser();
        userRepository.save(user);


        System.out.println("유저 생성");

        if(s.equals(user.getUserId())){
            user.setAuthorities(getAuthorities(user.getUserId()));
            System.out.println("Authorities 설정후");
        }
        System.out.println("UserRepository.loadUserByUsername before setting for Password");
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        System.out.println("user = " + user);
        System.out.println("user.getAuthorities() = " + user.getAuthorities());
        return user;
    }

    public Collection<GrantedAuthority> getAuthorities(String userId) {
        List<GrantedAuthority> string_authorities = new ArrayList<>();
        if(userId.equals("tgjeon")){
            System.out.println("UserRepository.getAuthorities : 권한부여");
            string_authorities.add(new SimpleGrantedAuthority("USER"));
            string_authorities.add(new SimpleGrantedAuthority("ADMIN"));
        }

        return string_authorities;
    }

    public Account findOne (String userId){
        return userRepository.findById(userId);
    }




    public static Account createUser(){
        Account account = Account.builder()
                .userId("tgjeon")
                .password("password486")
                .build();
        return account;
    }
}
