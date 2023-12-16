package ru.mirea.app.fitness_club.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import ru.mirea.app.fitness_club.ORM.Achievements;
import ru.mirea.app.fitness_club.ORM.EquipmentStatistics;
import ru.mirea.app.fitness_club.ORM.Event;
import ru.mirea.app.fitness_club.ORM.Members;
import ru.mirea.app.fitness_club.ORM.News;
import ru.mirea.app.fitness_club.ORM.Staff;
import ru.mirea.app.fitness_club.ORM.StaffSchedule;
import ru.mirea.app.fitness_club.ORM.Trainers;
import ru.mirea.app.fitness_club.ORM.TrainingSchedule;
import ru.mirea.app.fitness_club.ORM.TrainingType;
import ru.mirea.app.fitness_club.ORM.Accounts.UserDetailsServiceImpl;
import ru.mirea.app.fitness_club.Service.MembersService;
import ru.mirea.app.fitness_club.Service.NewsService;
import ru.mirea.app.fitness_club.Service.PersonalTrainingForm;
import ru.mirea.app.fitness_club.Service.StaffScheduleService;
import ru.mirea.app.fitness_club.Service.StaffService;
import ru.mirea.app.fitness_club.Service.TrainersService;
import ru.mirea.app.fitness_club.Service.TrainingForm;
import ru.mirea.app.fitness_club.Service.TrainingScheduleService;
import ru.mirea.app.fitness_club.Service.TrainingTypeService;
import ru.mirea.app.fitness_club.Service.AddTraining;
import ru.mirea.app.fitness_club.Service.ClubsService;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/")
public class FitnessController {
    private final MembersService membersService;
    private final ClubsService clubsService;
    private final TrainingScheduleService trainingScheduleService;
    private final StaffScheduleService staffScheduleService;
    private final TrainersService trainersService;
    private final StaffService staffService;
    private final TrainingTypeService trainingTypeService;
    private final NewsService newsService;
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping("/profile/{role}/{id}")
    public String profile(@PathVariable Integer id, @PathVariable String role, Model model) {
        model.addAttribute("role", role);
        switch (role) {
            case "member":
                Members member = membersService.getMember(id);
                model.addAttribute("memberId", id);
                model.addAttribute("memberClub", member.getClub());
                model.addAttribute("roleName", member.getMembershipRole().getRole_name());
                model.addAttribute("member", member);
                model.addAttribute("achievements", membersService.getListOfMemberAchievements(id));
                model.addAttribute("workouts", membersService.getListOfTrainingSchedule(id)
                        .stream()
                        .filter(workout -> workout.getSession_date().after(new Date()))
                        .limit(3)
                        .collect(Collectors.toList()));
                model.addAttribute("workoutsCount", membersService.getListOfTrainingSchedule(id)
                        .stream()
                        .filter(workout -> workout.getSession_date().after(new Date()))
                        .count());
                model.addAttribute("photoURL", membersService.getPhotoUrl(id));
                model.addAttribute("allNews", newsService.getListOfClubNews());
                break;
            case "trainer":
                model.addAttribute("trainerId", id);
                Trainers trainer = trainersService.getTrainer(id);
                model.addAttribute("trainer", trainer);
                model.addAttribute("workouts", trainersService.getListOfTrainingSchedule(id)
                        .stream()
                        .filter(workout -> workout.getSession_date().after(new Date()))
                        .limit(3)
                        .collect(Collectors.toList()));
                model.addAttribute("workoutsCount", trainersService.getListOfTrainingSchedule(id)
                        .stream()
                        .filter(workout -> workout.getSession_date().after(new Date()))
                        .count());
                model.addAttribute("photoURL", trainersService.getPhotoUrl(id));
                break;
            case "staff":
                Staff staff = staffService.getStaff(id);
                model.addAttribute("staffId", id);
                model.addAttribute("staff", staff);
                model.addAttribute("photoURL", staffService.getPhotoUrl(id));
                model.addAttribute("staffSchedule", staffService.getListOfStaffSchedule(id)
                        .stream()
                        .filter(work -> work.getDate().after(new Date()))
                        .limit(3)
                        .collect(Collectors.toList()));
                break;
            default:
                break;
        }
        return "html/profile";
    }

    @GetMapping("/news")
    @ResponseBody
    public String news(@RequestParam(value = "club", required = false) String clubId) {
        String jsonMsg = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            if (clubId != null) {
                List<News> newsList = clubsService.getListOfClubNews(clubId);
                jsonMsg = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(newsList);
                return jsonMsg;
            }
            List<News> newsList = newsService.getListOfClubNews();
            jsonMsg = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(newsList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonMsg;
    }

    @GetMapping("/calendar/{role}/{id}")
    public String calendar(@PathVariable Integer id, @PathVariable String role, Model model) {
        model.addAttribute("role", role);
        model.addAttribute("TrainersList", trainersService.getListOfTrainers());
        model.addAttribute("TrainingTypeList", trainingScheduleService.getTrainingTypes());

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = ((UserDetails) principal).getUsername();
        switch (role) {
            case "member":
                Integer memberId = userDetailsService.getUserId(name);
                Members member = membersService.getMember(memberId);
                model.addAttribute("member", member);
                model.addAttribute("memberId", memberId);
                break;
            case "trainer":
                Integer trainerId = userDetailsService.getUserId(name);
                Trainers trainer = trainersService.getTrainer(trainerId);
                model.addAttribute("trainer", trainer);
                model.addAttribute("trainerId", trainerId);
                model.addAttribute("AddTraining", new AddTraining());
                break;
            case "staff":
                Integer staffId = userDetailsService.getUserId(name);
                Staff staff = staffService.getStaff(staffId);
                model.addAttribute("staff", staff);
                model.addAttribute("staffId", staffId);
            default:
                break;
        }

        return "html/calendar";
    }

    @GetMapping("/calendar/training/{id}")
    public String training(@PathVariable Integer id, Model model) {
        TrainingSchedule workout = trainingScheduleService.getTraining(id);
        model.addAttribute("training", workout);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd-MM, EE");
        String date = sdf.format(workout.getSession_date());
        model.addAttribute("session_date", date);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = ((UserDetails) principal).getUsername();
        String role = userDetailsService.getUserRole(name);
        switch (role) {
            case "member":
                Integer memberId = userDetailsService.getUserId(name);
                Members member = membersService.getMember(memberId);
                boolean isSignedUp = member.getMemberTrainingSchedules().contains(workout);
                model.addAttribute("trainer", trainingScheduleService.getTrainers(id));
                model.addAttribute("isSignedUp", isSignedUp);
                model.addAttribute("member", member);
                model.addAttribute("memberId", memberId);
                break;
            case "trainer":
                Integer trainerId = userDetailsService.getUserId(name);
                Trainers trainer = trainersService.getTrainer(trainerId);
                model.addAttribute("trainer", trainer);
                model.addAttribute("trainerId", trainerId);
                break;
            case "staff":
                Integer staffId = userDetailsService.getUserId(name);
                Staff staff = staffService.getStaff(staffId);
                model.addAttribute("staff", staff);
                model.addAttribute("staffId", staffId);
            default:
                break;
        }
        model.addAttribute("role", role);
        return "html/training";
    }

    @PostMapping("/calendar/training/subscribe")
    public String trainingSignup(@ModelAttribute TrainingForm form, Model model) {
        int memberId = form.getMemberId();
        int trainingId = form.getTrainingId();

        Members member = membersService.getMember(memberId);
        TrainingSchedule training = trainingScheduleService.getTraining(trainingId);

        member.getMemberTrainingSchedules().add(training);

        membersService.save(member);

        return "redirect:/calendar/training/" + String.valueOf(trainingId);
    }

    @PostMapping("/calendar/training/unsubscribe")
    public String trainingUnsubscribe(@ModelAttribute TrainingForm form, Model model) {
        int memberId = form.getMemberId();
        int trainingId = form.getTrainingId();

        Members member = membersService.getMember(memberId);
        TrainingSchedule training = trainingScheduleService.getTraining(trainingId);

        member.getMemberTrainingSchedules().remove(training);

        membersService.save(member);

        if (training.getTrainingType().getId_training_type() == 5) {
            return "redirect:/calendar/member/" + memberId;
        }

        return "redirect:/calendar/training/" + trainingId;
    }

    @PostMapping("/calendar/training/add")
    public String trainingAdd(@ModelAttribute AddTraining form, BindingResult result, Model model)
            throws ParseException {

        Integer id_trainer = Integer.parseInt(form.getId_trainer());
        Integer id_training_type = Integer.parseInt(form.getId_training_type());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date session_date = sf.parse(form.getSession_date());
        Integer session_time = Integer.parseInt(form.getSession_time());

        Trainers trainer = trainersService.getTrainer(id_trainer);
        TrainingType trainingType = trainingTypeService.getTrainingType(id_training_type);

        TrainingSchedule training = new TrainingSchedule(trainer,
                trainingType,
                session_date,
                session_time,
                new ArrayList<Members>());

        trainingScheduleService.saveAndFlush(training);
        System.out.println("training added");

        return "redirect:/calendar/training/" + trainingScheduleService.getIdOfTraining(training);
    }

    @GetMapping("/statistic/{role}/{id}")
    public String statistic(@PathVariable Integer id, @PathVariable String role, Model model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = ((UserDetails) principal).getUsername();
        switch (role) {
            case "member":
                Integer memberId = userDetailsService.getUserId(name);
                Members member = membersService.getMember(memberId);
                List<EquipmentStatistics> statistics = membersService.getListOfEquipmentStatistics(id);
                model.addAttribute("statistics", statistics);
                List<Achievements> achievements = membersService.getListOfMemberAchievements(id);
                model.addAttribute("achievements", achievements);
                model.addAttribute("member", member);
                model.addAttribute("memberId", memberId);
                break;
            case "trainer":
                Integer trainerId = userDetailsService.getUserId(name);
                Trainers trainer = trainersService.getTrainer(trainerId);
                model.addAttribute("trainer", trainer);
                model.addAttribute("trainerId", trainerId);
                break;
            case "staff":
                Integer staffId = userDetailsService.getUserId(name);
                Staff staff = staffService.getStaff(staffId);
                model.addAttribute("staff", staff);
                model.addAttribute("staffId", staffId);
            default:
                break;
        }
        model.addAttribute("role", role);

        return "html/statistic";
    }

    @GetMapping("/trainers")
    public String trainers(Model model) {
        // get the loggined member
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = ((UserDetails) principal).getUsername();
        String role = userDetailsService.getUserRole(name);
        switch (role) {
            case "member":
                Integer memberId = userDetailsService.getUserId(name);
                Members member = membersService.getMember(memberId);
                model.addAttribute("member", member);
                model.addAttribute("memberId", memberId);
                break;
            case "trainer":
                Integer trainerId = userDetailsService.getUserId(name);
                Trainers trainer = trainersService.getTrainer(trainerId);
                model.addAttribute("trainer", trainer);
                model.addAttribute("trainerId", trainerId);
                break;
            case "staff":
                Integer staffId = userDetailsService.getUserId(name);
                Staff staff = staffService.getStaff(staffId);
                model.addAttribute("staff", staff);
                model.addAttribute("staffId", staffId);
            default:
                break;
        }
        model.addAttribute("role", role);
        model.addAttribute("trainers", trainersService.getListOfTrainers());
        return "html/trainers";
    }

    @PostMapping("/trainers/subscribe")
    public String subscribe(@ModelAttribute PersonalTrainingForm form, Model model) throws ParseException {

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date sessionDate = sf.parse(form.getTrainingDate());
        Trainers trainer = trainersService.getTrainer(form.getTrainerId());
        TrainingType trainingType = trainingTypeService.getTrainingType(5);

        TrainingSchedule personaltraining = new TrainingSchedule(trainer,
                trainingType,
                sessionDate,
                60,
                new ArrayList<Members>());

        personaltraining = trainingScheduleService.saveAndFlush(personaltraining);

        Members member = membersService.getMember(form.getMemberId());
        member.addTrainingSchedule(personaltraining);
        membersService.save(member);

        return "redirect:/calendar/training/" + trainingScheduleService.getIdOfTraining(personaltraining);
    }

    @GetMapping("/login")
    String login() {
        return "html/login";
    }

    @GetMapping("/logout")
    String logout() {
        return "redirect:/login?logout";
    }

    @GetMapping("/trainings")
    @ResponseBody
    public String getTrainings(@RequestParam(value = "id_trainer", required = false) List<Integer> trainerId,
            @RequestParam(value = "id_training_type", required = false) List<Integer> trainingTypeId,
            @RequestParam(value = "session_date_start", required = false) String sessionDateStart,
            @RequestParam(value = "session_date_end", required = false) String sessionDateEnd,
            @RequestParam(value = "session_time_start", required = false, defaultValue = "30") Integer sessionTimeStart,
            @RequestParam(value = "session_time_end", required = false, defaultValue = "120") Integer sessionTimeEnd,
            @RequestParam(value = "trainer_schedule", required = false, defaultValue = "0") Integer trainerSchedule)
            throws ParseException {

        Date startDate = null;
        Date endDate = null;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        if (sessionDateStart != null) {
            try {
                startDate = sf.parse(sessionDateStart);
            } catch (ParseException e) {
                startDate = null;
            }
        }

        if (sessionDateEnd != null) {
            try {
                endDate = sf.parse(sessionDateEnd);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(endDate);
                calendar.set(Calendar.HOUR_OF_DAY, 23);
                endDate = calendar.getTime();
            } catch (ParseException e) {
                endDate = null;
            }
        }

        if (trainerSchedule == null) {
            trainerSchedule = 0;
        }

        List<TrainingSchedule> trainingScheduleList;
        trainingScheduleList = trainingScheduleService.getTrainingList(trainerId,
                trainingTypeId,
                startDate,
                endDate,
                sessionTimeStart,
                sessionTimeEnd);

        List<Event> eventsList = trainingScheduleService.trainingScheduleToEventList(trainingScheduleList,
                trainerSchedule);
        String jsonMsg = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            jsonMsg = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(eventsList);
        } catch (IOException ioex) {
            System.out.println(ioex.getMessage());
        }
        return jsonMsg;
    }

    @GetMapping("/calendar/work/events")
    @ResponseBody
    public String getWorks() {
        List<StaffSchedule> staffScheduleList;
        staffScheduleList = staffScheduleService.getStaffScheduleList();
        List<Event> eventsList = staffScheduleService.staffScheduleToEvents(staffScheduleList);
        String jsonMsg = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            jsonMsg = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(eventsList);
        } catch (IOException ioex) {
            System.out.println(ioex.getMessage());
        }
        return jsonMsg;
    }

}
