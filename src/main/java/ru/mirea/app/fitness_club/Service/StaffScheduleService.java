package ru.mirea.app.fitness_club.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.mirea.app.fitness_club.ORM.Event;
import ru.mirea.app.fitness_club.ORM.Staff;
import ru.mirea.app.fitness_club.ORM.StaffSchedule;
import ru.mirea.app.fitness_club.ORM.Accounts.UserDetailsServiceImpl;
import ru.mirea.app.fitness_club.Repository.StaffScheduleRepository;

@Service
@AllArgsConstructor
public class StaffScheduleService {
    private final StaffScheduleRepository staffScheduleRepository;
    private UserDetailsServiceImpl userDetailsService;
    private final StaffService staffService;

    public List<StaffSchedule> getStaffScheduleList() {
        return staffScheduleRepository.findAll();
    }

    public List<Event> staffScheduleToEvents(List<StaffSchedule> staffScheduleList) {
        List<Event> eventsList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = ((UserDetails) principal).getUsername();
        Integer staffId = userDetailsService.getUserId(name);
        Staff staff = staffService.getStaff(staffId);

        for (StaffSchedule work : staffScheduleList) {
            String color;
            if (staff.getStaffSchedules().contains(work)) {
                color = "#3e4684";
            } else {
                color = "#b2b4d4";
            }

            String staffName = work.getStaff().getFirst_name() + " " + work.getStaff().getSecond_name() + " cмена - "
                    + work.getShift();
            String startDate = sdf.format(work.getDate()).toString();
            String endDate = sdf.format(work.getDate()).toString();
            switch (work.getShift()) {
                case 1:
                    startDate += "T06:00:00";
                    endDate += "T12:00:00";
                    break;
                case 2:
                    startDate += "T12:00:00";
                    endDate += "T18:00:00";
                    break;
                case 3:
                    startDate += "T18:00:00";
                    endDate += "T23:59:00";
                    break;
                default:
                    break;
            }
            int workId = work.getId_schedule();

            eventsList.add(new Event(staffName, startDate, endDate, workId, color));
        }
        return eventsList;
    }
}
