package ru.mirea.app.fitness_club.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.mirea.app.fitness_club.ORM.Accounts.StaffAccounts;

@Repository
public interface StaffAccountsRepository extends JpaRepository<StaffAccounts, String> {
    
    @Query("SELECT staff_username FROM staff_accounts")
    List<String> findAllUsernames();

    @Query("SELECT password FROM staff_accounts")
    List<String> findAllPasswords();
}
