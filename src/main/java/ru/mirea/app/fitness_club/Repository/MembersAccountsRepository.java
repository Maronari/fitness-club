package ru.mirea.app.fitness_club.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.mirea.app.fitness_club.ORM.Accounts.MembersAccounts;

@Repository
public interface MembersAccountsRepository extends JpaRepository<MembersAccounts, String> {
    
    @Query("SELECT member_username FROM members_accounts")
    List<String> findAllUsernames();

    @Query("SELECT password FROM members_accounts")
    List<String> findAllPasswords();
}
