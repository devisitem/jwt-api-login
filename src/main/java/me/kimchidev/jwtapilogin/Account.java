package me.kimchidev.jwtapilogin;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@ToString
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Account implements UserDetails {
    @Id @GeneratedValue
    private Long id;

    @Setter
    @Column(unique = true)
    private String userId;

    private String password;


    private  boolean isAccountNonExpired;
    private  boolean isAccountNonLocked;
    private  boolean isCredentialsNonExpired;
    private  boolean isEnabled;
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println("Account.getAuthorities 조회 id = "+this.userId);
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities){
        this.authorities = authorities;
    }


    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonLocked() {
        System.out.println("Account.isAccountNonLocked");
        return true;
    }

    @Override
    public boolean isEnabled() {
        System.out.println("Account.isEnabled");
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        System.out.println("Account.isAccountNonExpired");
        return true;
    }

    @Builder
    public Account (String userId,String password){
        this.userId = userId;
        this.password = password;
    }


}
