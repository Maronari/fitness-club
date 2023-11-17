package ru.mirea.app.fitness_club.ORM;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public TrainingSchedule(Trainers trainers, TrainingType trainingType, Date session_date, int session_time,
            List<Members> members) {
        this.trainers = trainers;
        this.trainingType = trainingType;
        this.session_date = session_date;
        this.session_time = session_time;
        this.members = members;
    }

}
