package ru.mirea.app.fitness_club.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import ru.mirea.app.fitness_club.ORM.Accounts.StaffAccounts;

public interface StaffAccountsRepository extends JpaRepository<StaffAccounts, String> {
}
