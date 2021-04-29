package me.kimchidev.jwtapilogin;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository{

    @PersistenceContext
    private final EntityManager entityManager;

    public void save(Account account){
        entityManager.persist(account);
    }

    public Account findOne(Long id){
        return entityManager.find(Account.class,id);
    }

    public Account findById(String userId){
        return entityManager.createQuery("select a from Account a where a.userId = :userId",Account.class)
                .setParameter("userId",userId)
                .getSingleResult();
    }


}
