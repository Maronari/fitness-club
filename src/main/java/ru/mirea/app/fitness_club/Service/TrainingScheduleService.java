package ru.mirea.app.fitness_club.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import ru.mirea.app.fitness_club.ORM.TrainingType;
import ru.mirea.app.fitness_club.ORM.Accounts.UserDetailsServiceImpl;
import ru.mirea.app.fitness_club.Repository.TrainingScheduleRepository;
import ru.mirea.app.fitness_club.Repository.TrainingTypeRepository;

@Service
@AllArgsConstructor
public class TrainingScheduleService {
    private final TrainingScheduleRepository trainingScheduleRepository;
    private final MembersService membersService;
    private final TrainingTypeRepository trainingTypeRepository;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public List<TrainingSchedule> getTrainingList(List<Integer> id_trainer,
            List<Integer> id_training_type,
            Date session_date_start,
            Date session_date_end,
            Integer session_time_start,
            Integer session_time_end) {

        return trainingScheduleRepository.findByFilter(id_trainer, id_training_type, session_date_start,
                session_date_end, session_time_start, session_time_end);
    }

    public List<Event> trainingScheduleToEventList(List<TrainingSchedule> trainingScheduleList) {
        List<Event> eventsList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = ((UserDetails) principal).getUsername();
        String role = userDetailsService.getUserRole(name);
        switch (role) {
            case "member":
                Integer memberId = userDetailsService.getUserId(name);
                Members member = membersService.getMember(memberId);

                for (TrainingSchedule training : trainingScheduleList) {

                    if ((training.getTrainingType().getId_training_type() == 5)
                            && !(member.getMemberTrainingSchedules().contains(training))) {
                                continue;
                    }

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
                break;
            case "trainer":
                for (TrainingSchedule training : trainingScheduleList) {
                    String color = "#3e4684";

                    String trainingType = training.getTrainingType().getTraining_type_name();
                    String trainingDate = sdf.format(training.getSession_date()).toString();
                    int sessionId = training.getId_session();

                    eventsList.add(new Event(trainingType, trainingDate, trainingDate, sessionId, color));
                }
                break;
            default:
                break;
        }

        return eventsList;
    }

    public List<TrainingType> getTrainingTypes() {
        return trainingTypeRepository.findAll();
    }

    public TrainingSchedule getTraining(int scheduleId) {
        return trainingScheduleRepository.findById(scheduleId).orElse(null);
    }

    public Trainers getTrainers(int scheduleId) {
        TrainingSchedule training = trainingScheduleRepository.findById(scheduleId).orElse(null);
        return training.getTrainers();
    }

    public TrainingSchedule saveAndFlush(TrainingSchedule training) {
        return trainingScheduleRepository.saveAndFlush(training);
    }

    public Integer getIdOfTraining(TrainingSchedule training) {
        TrainingSchedule foundTraining = trainingScheduleRepository.findById(training.getId_session()).orElse(null);
        if (foundTraining != null) {
            return foundTraining.getId_session();
        }
        return null;
    }
}
