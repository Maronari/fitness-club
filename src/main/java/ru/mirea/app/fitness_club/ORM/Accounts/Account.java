package ru.mirea.app.fitness_club.ORM.Accounts;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Account {
    private String username;
    private String password;
    private int id;
    private String role;


}
