package ru.mirea.app.fitness_club.Service;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.mirea.app.fitness_club.ORM.Staff;
import ru.mirea.app.fitness_club.ORM.StaffSchedule;
import ru.mirea.app.fitness_club.ORM.Accounts.StaffAccounts;
import ru.mirea.app.fitness_club.Repository.StaffRepository;

@Service
@AllArgsConstructor
public class StaffService {
    private final StaffRepository staffRepository;

    public Staff getStaff(Integer staffId) {
        return staffRepository.findById(staffId).orElse(null);
    }

    public List<StaffSchedule> getStaffSchedule(int staffId) {
        Staff staff = staffRepository.findById(staffId).orElse(null); 
        return staff.getStaffSchedules();
    }

    public StaffAccounts getStaffAccount(int staffId) {
        Staff staff = staffRepository.findById(staffId).orElse(null); 
        return staff.getStaffAccounts();
    }

    public String getPositionName(int staffId) {
        Staff staff = staffRepository.findById(staffId).orElse(null); 
        return staff.getPosition().getRole_name();
    }

    public String getPhotoUrl(Integer staffId) {
        StaffAccounts staffAccount = getStaffAccount(staffId);
        return staffAccount.getStaffPhoto().getImage_url();
    }

    public List<StaffSchedule> getListOfStaffSchedule(Integer staffId) {
        Staff staff = staffRepository.findById(staffId).orElse(null);
        List<StaffSchedule> staffSchedule = staff.getStaffSchedules();
        staffSchedule.sort(Comparator.comparing(StaffSchedule::getDate));
        return staffSchedule;
    }
}