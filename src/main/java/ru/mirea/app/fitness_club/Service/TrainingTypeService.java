package ru.mirea.app.fitness_club.Service;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.mirea.app.fitness_club.ORM.TrainingType;
import ru.mirea.app.fitness_club.Repository.TrainingTypeRepository;

@Service
@AllArgsConstructor
public class TrainingTypeService {
    private final TrainingTypeRepository trainingTypeRepository;
    
    public TrainingType getTrainingType(Integer trainingTypeId) {
        return trainingTypeRepository.findById(trainingTypeId).orElse(null);
    }
}
