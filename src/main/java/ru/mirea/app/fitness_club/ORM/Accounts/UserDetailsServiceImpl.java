package ru.mirea.app.fitness_club.ORM.Accounts;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private AllAccounts allAccounts;

    @Autowired
    public UserDetailsServiceImpl(AllAccounts allAccounts) {
        this.allAccounts = allAccounts;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String password = allAccounts.getPasswordByUsername(username);
        if (password == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new User(username, password, new ArrayList<>());
    }

    public UserDetails loadUserById(Integer userId) throws UsernameNotFoundException {
        String username = allAccounts.getUsernameById(userId);
        if (username == null) {
            throw new UsernameNotFoundException("User not found");
        }
        String password = allAccounts.getPasswordByUsername(username);
        return new User(username, password, new ArrayList<>());
    }

    public Integer getUserId(String username) {
        return allAccounts.getIdByUsername(username);
    }

    public String getUserRole(String username) {
        String role = allAccounts.getRoleByUsername(username);
        switch (role) {
            case "MEMBER":
                return "member";
            case "TRAINER":
                return "trainer";
            case "STAFF":
                return "staff";
            default:
                return null;
        }
    }

}
