package ru.mirea.app.fitness_club.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import ru.mirea.app.fitness_club.ORM.Staff;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
    
}
