package me.kimchidev.jwtapilogin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthenticationToken login(@RequestBody AuthenticationRequest authenticationRequest, HttpSession session) throws Exception{
        String userName = authenticationRequest.getUserId();
        String userPassword = authenticationRequest.getPassword();

        //create Token
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName,userPassword);

        //Create Authentication via AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(token);

        //

    }


}
