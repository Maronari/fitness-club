package ru.mirea.app.fitness_club.Service;

//import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.mirea.app.fitness_club.ORM.Achievements;
import ru.mirea.app.fitness_club.ORM.Members;
import ru.mirea.app.fitness_club.ORM.TrainingSchedule;
import ru.mirea.app.fitness_club.ORM.Accounts.MembersAccounts;
import ru.mirea.app.fitness_club.Repository.MembersRepository;

@Service
@AllArgsConstructor
public class MembersService {
    private final MembersRepository membersRepository;

    public Members getMember(Integer id) {
        return membersRepository.findById(id).orElse(null);
    }

    public List<Achievements> getMemberAchievements(int memberId) {
        Members member = membersRepository.findById(memberId).orElse(null); 
        return member.getMemberAchievements().stream()
                //.map(MemberAchievements::getAchievement)
                .collect(Collectors.toList());
    }

    public List<TrainingSchedule> getListOfTrainingSchedule(int memberId) {
        Members member = membersRepository.findById(memberId).orElse(null);
        return member.getMemberTrainingSchedules();
    }

    public MembersAccounts getMembersAccounts(int memberId) {
        Members member = membersRepository.findById(memberId).orElse(null);
        return member.getMemberAccounts();
    }

    public String getPhotoUrl(int memberId) {
        MembersAccounts memberAccounts = getMembersAccounts(memberId);
        return memberAccounts.getUserPhoto().getImage_url();
    }

    // public List<Date> getReceiptDates(int memberId) {
    //     Members member = membersRepository.findById(memberId).orElse(null);
    //     return member.getMemberAchievements().stream()
    //             .map(MemberAchievements::getReceipt_date)
    //             .collect(Collectors.toList());
    // }
}
