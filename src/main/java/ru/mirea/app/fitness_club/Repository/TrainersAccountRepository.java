package ru.mirea.app.fitness_club.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.mirea.app.fitness_club.ORM.Accounts.TrainersAccounts;

@Repository
public interface TrainersAccountRepository extends JpaRepository<TrainersAccounts, String> {
    
    @Query("SELECT trainer_username FROM trainers_accounts")
    List<String> findAllUsernames();

    @Query("SELECT password FROM trainers_accounts")
    List<String> findAllPasswords();
}
