package ru.mirea.app.fitness_club.ORM;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    private String workout_description;
    private String session_date;
    private int session_time;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_training_type")
    private TrainingType trainingType;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_trainer")
    private Trainers trainers;

    @OneToMany(mappedBy = "trainingSchedule")
    private List<MemberTrainingSchedule> memberTrainingSchedules = new ArrayList<>();
}
