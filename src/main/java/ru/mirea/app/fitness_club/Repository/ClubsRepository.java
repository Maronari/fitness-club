package ru.mirea.app.fitness_club.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import ru.mirea.app.fitness_club.ORM.Clubs;
public interface ClubsRepository extends JpaRepository<Clubs, Integer>{
    
}
