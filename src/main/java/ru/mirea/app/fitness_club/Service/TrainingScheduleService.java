package ru.mirea.app.fitness_club.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.mirea.app.fitness_club.ORM.Event;
import ru.mirea.app.fitness_club.ORM.TrainingSchedule;
import ru.mirea.app.fitness_club.Repository.TrainingScheduleRepository;

@Service
@AllArgsConstructor
public class TrainingScheduleService {
    private final TrainingScheduleRepository trainingScheduleRepository;

    public List<TrainingSchedule> getTrainingList() {
        return trainingScheduleRepository.findAll();
    }

    public List<Event> TrainingScheduleToEventList() {
        List<Event> eventsList = new ArrayList<>();
        List<TrainingSchedule> trainingScheduleList = getTrainingList();
        for (TrainingSchedule trainingSchedule : trainingScheduleList) {
            eventsList.add(new Event(trainingSchedule.getTrainingType().getTraining_type_name(),
                    trainingSchedule.getSession_date().toString(),
                    trainingSchedule.getSession_date().toString()));
        }
        return eventsList;
    }

}
