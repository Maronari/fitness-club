package ru.mirea.app.fitness_club.ORM;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "trainers_accounts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TrainersAccounts {
    
    @Id
    @OneToOne
    @JoinColumn(name = "id_trainer")
    private Trainers trainers;
    
    private String trainer_username;
    private String password;
    private String last_login;
    private String account_creation_date;

    @OneToOne(mappedBy = "trainers")
    private List<Trainers> trainers2 = new ArrayList<>();
}