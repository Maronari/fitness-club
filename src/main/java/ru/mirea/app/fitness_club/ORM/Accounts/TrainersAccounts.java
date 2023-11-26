package ru.mirea.app.fitness_club.ORM.Accounts;


import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.mirea.app.fitness_club.ORM.Trainers;
import ru.mirea.app.fitness_club.ORM.TrainersPhoto;

@Entity
@Table(name = "trainers_accounts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TrainersAccounts {    
    @OneToOne
    @JoinColumn(name = "id_trainer", nullable = false)
    private Trainers trainers;
    
    @Id
    private String username;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_trainers_photo")
    private TrainersPhoto trainerPhoto;

    private String password;
    private Date last_login;
    private Date account_creation_date;
    private String user_role;
}