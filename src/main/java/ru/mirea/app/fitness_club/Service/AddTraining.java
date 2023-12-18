package ru.mirea.app.fitness_club.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AddTraining {
    private String id_trainer;
    private String id_training_type;
    private String session_date;
    private String session_time;
}
