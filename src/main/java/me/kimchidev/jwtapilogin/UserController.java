package me.kimchidev.jwtapilogin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/login")
    public AuthenticationToken login(@RequestBody AuthenticationRequest authenticationRequest, HttpSession session) throws Exception{
        String userName = authenticationRequest.getUserId();
        String userPassword = authenticationRequest.getPassword();

        //create Token
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName,userPassword);
        System.out.println("token = " + token);
        //Create Authentication via AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(token);

        //apply Authentication in SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //Add Context into Session
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,SecurityContextHolder.getContext());

        Account user = userService.findUser(userName);
        return new AuthenticationToken(user.getUserId(), user.getAuthorities(), session.getId());
    }


}
