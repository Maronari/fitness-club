package ru.mirea.app.fitness_club.ORM.Accounts;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Getter;
import ru.mirea.app.fitness_club.Repository.MembersAccountsRepository;
import ru.mirea.app.fitness_club.Repository.StaffAccountsRepository;
import ru.mirea.app.fitness_club.Repository.TrainersAccountsRepository;

@Component
@Getter
public class AllAccounts {
    private List<Account> accounts = new ArrayList<Account>();

    AllAccounts(MembersAccountsRepository membersAccountsRep, TrainersAccountsRepository trainersAccountsRep,
            StaffAccountsRepository staffAccountsRep) {

        for (MembersAccounts memberAccount : membersAccountsRep.findAll()) {
            accounts.add(new Account(memberAccount.getUsername(),
                    memberAccount.getPassword(),
                    memberAccount.getMember().getId_member(),
                    memberAccount.getUser_role()));
        }

        for (TrainersAccounts trainerAccount : trainersAccountsRep.findAll()) {
            accounts.add(new Account(trainerAccount.getUsername(),
                    trainerAccount.getPassword(),
                    trainerAccount.getTrainers().getId_trainer(),
                    trainerAccount.getUser_role()));
        }

        for (StaffAccounts staffAccount : staffAccountsRep.findAll()) {
            accounts.add(new Account(staffAccount.getUsername(),
                    staffAccount.getPassword(),
                    staffAccount.getStaff().getId_staff(),
                    staffAccount.getUser_role()));
        }
    }

    String getPasswordByUsername(String username) {
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                return account.getPassword();
            }
        }
        return null;
    }

    String getRoleByUsername(String username) {
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                return account.getRole();
            }
        }
        return null;
    }

    Integer getIdByUsername(String username) {
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                return account.getId();
            }
        }
        return null;
    }

    public String getUsernameById(Integer userId) {
        for (Account account : accounts) {
            if (account.getId() == userId) {
                return account.getUsername();
            }
        }
        return null;
    }
}
