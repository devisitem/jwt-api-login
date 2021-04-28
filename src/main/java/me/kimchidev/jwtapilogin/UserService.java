package me.kimchidev.jwtapilogin;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface UserService extends UserDetailsService {
    Collection<GrantedAuthority> getAuthorities(String userId);
    public Account findUser(String userId);

}
