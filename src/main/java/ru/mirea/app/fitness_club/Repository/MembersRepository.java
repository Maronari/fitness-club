package ru.mirea.app.fitness_club.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.app.fitness_club.ORM.Members;

@Repository
public interface MembersRepository extends JpaRepository<Members, Integer>  {
    
}
