package ru.mirea.app.fitness_club.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ru.mirea.app.fitness_club.ORM.Accounts.MembersAccounts;

public interface MembersAccountsRepository extends JpaRepository<MembersAccounts, String> {

    @Query(value = "select m.username from members_accounts m", nativeQuery = true)
    List<String> getUsernames();

    @Query(value = "select m.password from members_accounts m", nativeQuery = true)
    List<String> getPasswords();

    @Query(value = "select m.user_role from members_accounts m", nativeQuery = true)
    List<String> getUserRoles();
}
