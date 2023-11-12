package ru.mirea.app.fitness_club.Service;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TrainingFilter {
    private Integer idTrainer;
    private Integer idTrainingType;
    private Date sessionDateStart;
    private Date sessionDateEnd;
    private Integer sessionTimeStart;
    private Integer sessionTimeEnd;
}
