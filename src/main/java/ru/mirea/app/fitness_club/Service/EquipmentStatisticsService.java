package ru.mirea.app.fitness_club.Service;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.mirea.app.fitness_club.Repository.EquipmentStatisticsRepository;

@Service
@AllArgsConstructor
public class EquipmentStatisticsService {
    private final EquipmentStatisticsRepository equipmentStatisticsRepository;

    public int getNumberOfApproaches(int statisticId) {
        return equipmentStatisticsRepository
            .findById(statisticId)
            .orElse(null)
            .getApproaches();
    }
    
    public int getAmountOfKilocalories(int statisticId) {
        return equipmentStatisticsRepository
            .findById(statisticId)
            .orElse(null)
            .getKilocalories();
    }

    public String getActivityName(int statisticId) {
        return equipmentStatisticsRepository
            .findById(statisticId)
            .orElse(null)
            .getActivityType()
            .getActivity_name();
    }
}
