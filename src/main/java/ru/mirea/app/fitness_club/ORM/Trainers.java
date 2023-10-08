package ru.mirea.app.fitness_club.ORM;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.mirea.app.fitness_club.ORM.Accounts.TrainersAccounts;

@Entity
@Table(name = "trainers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Trainers {
    @Id
    private int id_trainer;
    
    private String speciality;
    private int experience;
    private int certifications;

    @OneToOne(mappedBy = "trainers")
    private TrainersAccounts trainerAccounts;
}
