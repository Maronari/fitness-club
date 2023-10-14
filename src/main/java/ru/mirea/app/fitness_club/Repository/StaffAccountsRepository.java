package ru.mirea.app.fitness_club.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ru.mirea.app.fitness_club.ORM.Accounts.StaffAccounts;

public interface StaffAccountsRepository extends JpaRepository<StaffAccounts, String> {
    
    @Query(value = "select s.username from staff_accounts s", nativeQuery = true)
    List<String> getUsernames();

    @Query(value = "select s.password from staff_accounts s", nativeQuery = true)
    List<String> getPasswords();

    @Query(value = "select s.user_role from staff_accounts s", nativeQuery = true)
    List<String> getUserRoles();
}
