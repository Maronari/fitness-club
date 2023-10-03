package ru.mirea.app.fitness_club.ORM;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @OneToMany(mappedBy = "club")
    private Set<Members> members;

    @OneToOne(mappedBy = "trainers")
    private List<TrainersAccounts> trainersAccounts = new ArrayList<TrainersAccounts>();
}
