package ru.mirea.app.fitness_club.ORM.Accounts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import ru.mirea.app.fitness_club.Repository.MembersAccountsRepository;
import ru.mirea.app.fitness_club.Repository.StaffAccountsRepository;
import ru.mirea.app.fitness_club.Repository.TrainersAccountRepository;

public class AllAccounts {
    @Getter
    private Map<String, String> accounts = new HashMap<String, String>();

    private MembersAccountsRepository membersAccountsRepository;
    private StaffAccountsRepository staffAccountsRepository;
    private TrainersAccountRepository trainersAccountRepository;

    AllAccounts() {
        List<String> usernames = new ArrayList<String>();
        List<String> passwords = new ArrayList<String>();

        usernames.addAll(membersAccountsRepository.findAllUsernames());
        usernames.addAll(trainersAccountRepository.findAllUsernames());
        usernames.addAll(staffAccountsRepository.findAllUsernames());

        passwords.addAll(membersAccountsRepository.findAllPasswords());
        passwords.addAll(trainersAccountRepository.findAllPasswords());
        passwords.addAll(staffAccountsRepository.findAllPasswords());

        for(int i = 0; i < usernames.size(); i++) {
            accounts.put(usernames.get(i), passwords.get(i));
        }
    }
}
