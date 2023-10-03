package ru.mirea.app.fitness_club.ORM;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "training_type")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TrainingType {
    @Id
    private int id_training_type;
    private String training_type_name;

    @OneToMany(mappedBy = "club")
    private Set<Members> members;
}
