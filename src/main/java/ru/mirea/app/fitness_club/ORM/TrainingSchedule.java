package ru.mirea.app.fitness_club.ORM;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "training_schedule")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TrainingSchedule {
    @Id
    private int id_session;

    @ManyToOne
    @JoinColumn(name = "id_trainer")
    private Trainers trainers;

    @ManyToOne
    @JoinColumn(name = "id_training_type")
    private TrainingType trainingType;

    private Date session_date;
    private int session_time;

    @ManyToMany(mappedBy = "memberTrainingSchedules")
    private List<Members> members = new ArrayList<>();
}
