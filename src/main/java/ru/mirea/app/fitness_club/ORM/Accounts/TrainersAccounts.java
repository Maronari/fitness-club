package ru.mirea.app.fitness_club.ORM.Accounts;


import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.mirea.app.fitness_club.ORM.Trainers;

@Entity
@Table(name = "trainers_accounts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TrainersAccounts {    
    @Id
    @OneToOne
    @JoinColumn(name = "id_trainer", nullable = false)
    private Trainers trainers;
    
    @Id
    private String trainer_username;

    private String password;
    private Date last_login;
    private Date account_creation_date;
    private String user_role;
}