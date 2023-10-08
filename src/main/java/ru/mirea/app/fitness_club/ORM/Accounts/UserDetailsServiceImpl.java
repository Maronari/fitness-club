package ru.mirea.app.fitness_club.ORM.Accounts;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    private AllAccounts allAccounts;

    @Autowired
    public UserDetailsServiceImpl(AllAccounts allAccounts) {
        this.allAccounts = allAccounts;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = allAccounts.getAccounts().get(username);
        if (password == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new User(username, password, new ArrayList<>());
    }
    
}
