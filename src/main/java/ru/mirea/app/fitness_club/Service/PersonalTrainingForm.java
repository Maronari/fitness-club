package ru.mirea.app.fitness_club.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PersonalTrainingForm {
    private int memberId;
    private int trainerId;
    private String trainingDate;

}
