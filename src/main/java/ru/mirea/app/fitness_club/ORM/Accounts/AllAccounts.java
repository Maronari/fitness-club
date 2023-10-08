package ru.mirea.app.fitness_club.ORM.Accounts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.Getter;
import ru.mirea.app.fitness_club.Repository.MembersAccountsRepository;
import ru.mirea.app.fitness_club.Repository.StaffAccountsRepository;
import ru.mirea.app.fitness_club.Repository.TrainersAccountsRepository;

@Component
public class AllAccounts {
    @Getter
    private Map<String, String> accounts = new HashMap<String, String>();

    private MembersAccountsRepository membersAccountsRepository;
    private TrainersAccountsRepository trainersAccountsRepository;
    private StaffAccountsRepository staffAccountsRepository;

    AllAccounts(MembersAccountsRepository membersAccountsRep,
            TrainersAccountsRepository trainersAccountsRep,
            StaffAccountsRepository staffAccountsRep) {

        this.membersAccountsRepository = membersAccountsRep;
        this.staffAccountsRepository = staffAccountsRep;
        this.trainersAccountsRepository = trainersAccountsRep;
        List<String> usernames = new ArrayList<String>();
        List<String> passwords = new ArrayList<String>();

        usernames.addAll(membersAccountsRepository.getUsernames());
        usernames.addAll(trainersAccountsRepository.getUsernames());
        usernames.addAll(staffAccountsRepository.getUsernames());

        passwords.addAll(membersAccountsRepository.getPasswords());
        passwords.addAll(trainersAccountsRepository.getPasswords());
        passwords.addAll(staffAccountsRepository.getPasswords());

        for (int i = 0; i < usernames.size(); i++) {
            accounts.put(usernames.get(i), passwords.get(i)); // is there a clearer way?
        }
    }
}
