package me.kimchidev.jwtapilogin;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public class AuthenticationToken {

    private String userId;
    private Collection authorities;
    private String token;

    public AuthenticationToken(String userId, Collection collection, String token) {
        this.userId = userId;
        this.authorities = collection;
        this.token = token;
    }
}
