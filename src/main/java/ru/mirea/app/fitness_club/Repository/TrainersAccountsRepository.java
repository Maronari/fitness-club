package ru.mirea.app.fitness_club.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.mirea.app.fitness_club.ORM.Accounts.TrainersAccounts;

public interface TrainersAccountsRepository extends JpaRepository<TrainersAccounts, String> {
    
}
