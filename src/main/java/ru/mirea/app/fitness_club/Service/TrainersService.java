package ru.mirea.app.fitness_club.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.mirea.app.fitness_club.ORM.Trainers;
import ru.mirea.app.fitness_club.ORM.TrainingSchedule;
import ru.mirea.app.fitness_club.ORM.Accounts.TrainersAccounts;
import ru.mirea.app.fitness_club.Repository.TrainersRepository;

@Service
@AllArgsConstructor
public class TrainersService {
    private final TrainersRepository trainersRepository;

    public Trainers getTrainers(Integer id) {
        return trainersRepository.findById(id).orElse(null);
    }

    public List<TrainingSchedule> getTrainingSchedules(int trainersid) {
        Trainers trainers = trainersRepository.findById(trainersid).orElse(null); 
        return trainers.getTrainingSchedules();
    }

    public TrainersAccounts getListOfTrainersAccounts(int trainersid) {
        Trainers trainers = trainersRepository.findById(trainersid).orElse(null);
        return trainers.getTrainerAccounts();
    }
}
