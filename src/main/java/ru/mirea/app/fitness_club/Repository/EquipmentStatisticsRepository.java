package ru.mirea.app.fitness_club.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import ru.mirea.app.fitness_club.ORM.EquipmentStatistics;
public interface EquipmentStatisticsRepository extends JpaRepository<EquipmentStatistics, Integer>{
    
}