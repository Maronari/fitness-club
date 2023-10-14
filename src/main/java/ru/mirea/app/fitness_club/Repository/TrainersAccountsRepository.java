package ru.mirea.app.fitness_club.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ru.mirea.app.fitness_club.ORM.Accounts.TrainersAccounts;

public interface TrainersAccountsRepository extends JpaRepository<TrainersAccounts, String> {
    
    @Query(value = "select t.username from trainers_accounts t", nativeQuery = true)
    List<String> getUsernames();

    @Query(value = "select t.password from trainers_accounts t", nativeQuery = true)
    List<String> getPasswords();

    @Query(value = "select t.user_role from trainers_accounts t", nativeQuery = true)
    List<String> getUserRoles();
}
