package ru.mirea.app.fitness_club.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.mirea.app.fitness_club.ORM.Clubs;

@Repository
public interface ClubsRepository extends JpaRepository<Clubs, Integer>{
    
}
