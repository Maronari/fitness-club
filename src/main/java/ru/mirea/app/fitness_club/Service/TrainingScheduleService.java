package ru.mirea.app.fitness_club.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.mirea.app.fitness_club.ORM.Event;
import ru.mirea.app.fitness_club.ORM.Members;
import ru.mirea.app.fitness_club.ORM.Trainers;
import ru.mirea.app.fitness_club.ORM.TrainingSchedule;
import ru.mirea.app.fitness_club.ORM.Accounts.UserDetailsServiceImpl;
import ru.mirea.app.fitness_club.Repository.TrainingScheduleRepository;

@Service
@AllArgsConstructor
public class TrainingScheduleService {
    private final TrainingScheduleRepository trainingScheduleRepository;
    private final MembersService membersService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public List<TrainingSchedule> getTrainingList() {
        return trainingScheduleRepository.findAll();
    }

    public List<Event> TrainingScheduleToEventList() {
        List<Event> eventsList = new ArrayList<>();
        List<TrainingSchedule> trainingScheduleList = getTrainingList();

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = ((UserDetails) principal).getUsername();
        Integer memberId = userDetailsService.getUserId(name);
        Members member = membersService.getMember(memberId);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        for (TrainingSchedule training : trainingScheduleList) {
            String color;
            if (member.getMemberTrainingSchedules().contains(training)) {
                color = "#3e4684";
            } else {
                color = "#b2b4d4";
            }

            String trainingType = training.getTrainingType().getTraining_type_name();
            String trainingDate = sdf.format(training.getSession_date()).toString();
            int sessionId = training.getId_session();

            eventsList.add(new Event(trainingType, trainingDate, trainingDate, sessionId, color));
        }
        return eventsList;
    }

    public TrainingSchedule getTraining(int scheduleId) {
        return trainingScheduleRepository.findById(scheduleId).orElse(null);
    }

    public Trainers getTrainers(int scheduleId) {
        TrainingSchedule training = trainingScheduleRepository.findById(scheduleId).orElse(null);
        return training.getTrainers();
    }

}
