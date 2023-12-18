package ru.mirea.app.fitness_club.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.mirea.app.fitness_club.ORM.StaffSchedule;

public interface StaffScheduleRepository extends JpaRepository<StaffSchedule, Integer>{
    
}
