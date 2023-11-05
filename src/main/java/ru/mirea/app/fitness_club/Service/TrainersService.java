package ru.mirea.app.fitness_club.Service;

import java.util.Comparator;
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

    public List<TrainingSchedule> getTrainingSchedules(int trainerId) {
        Trainers trainers = trainersRepository.findById(trainerId).orElse(null); 
        return trainers.getTrainingSchedules();
    }

    public TrainersAccounts getTrainerAccount(int trainerId) {
        Trainers trainers = trainersRepository.findById(trainerId).orElse(null);
        return trainers.getTrainerAccounts();
    }

    public List<TrainingSchedule> getListOfTrainingSchedule(int trainerId) {
        Trainers trainer = trainersRepository.findById(trainerId).orElse(null);
        List<TrainingSchedule> trainerTrainings = trainer.getTrainingSchedules();
        trainerTrainings.sort(Comparator.comparing(TrainingSchedule::getSession_date));
        return trainerTrainings;
    }

    public String getPhotoUrl(int trainersId) {
        TrainersAccounts trainerAccount = getTrainerAccount(trainersId);
        return trainerAccount.getTrainerPhoto().getImage_url();
    }
}
