package ru.mirea.app.fitness_club.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import ru.mirea.app.fitness_club.ORM.Achievements;
import ru.mirea.app.fitness_club.ORM.EquipmentStatistics;
import ru.mirea.app.fitness_club.ORM.Event;
import ru.mirea.app.fitness_club.ORM.Members;
import ru.mirea.app.fitness_club.ORM.Staff;
import ru.mirea.app.fitness_club.ORM.Trainers;
import ru.mirea.app.fitness_club.ORM.TrainingSchedule;
import ru.mirea.app.fitness_club.ORM.Accounts.UserDetailsServiceImpl;
import ru.mirea.app.fitness_club.Service.MembersService;
import ru.mirea.app.fitness_club.Service.StaffService;
import ru.mirea.app.fitness_club.Service.TrainersService;
import ru.mirea.app.fitness_club.Service.TrainingForm;
import ru.mirea.app.fitness_club.Service.TrainingScheduleService;
import ru.mirea.app.fitness_club.Service.ClubsService;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/")
public class FitnessController {
    private final MembersService membersService;
    private final ClubsService clubsService;
    private final TrainingScheduleService trainingScheduleService;
    private final TrainersService trainersService;
    private final StaffService staffService;

    @Autowired
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
                        .stream().limit(3).collect(Collectors.toList()));
                model.addAttribute("workoutsCount", membersService.getListOfTrainingSchedule(id).size());
                model.addAttribute("photoURL", membersService.getPhotoUrl(id));
                model.addAttribute("news", clubsService.getListOfClubNews(member.getClub().getClub_name()));
                break;
            case "trainer":
                model.addAttribute("trainerId", id);
                Trainers trainer = trainersService.getTrainer(id);
                model.addAttribute("trainer", trainer);
                model.addAttribute("workouts", trainersService.getListOfTrainingSchedule(id)
                        .stream().limit(3).collect(Collectors.toList()));
                model.addAttribute("workoutsCount", membersService.getListOfTrainingSchedule(id).size());
                model.addAttribute("photoURL", trainersService.getPhotoUrl(id));
                model.addAttribute("news", clubsService.getListOfClubNews("София"));
                break;
            default:
                break;
        }
        return "html/profile";
    }

    @GetMapping("/calendar/{role}/{id}")
    public String calendar(@PathVariable Integer id, @PathVariable String role, Model model) {
        model.addAttribute("role", role);

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
        model.addAttribute("trainers", trainingScheduleService.getTrainers(id));

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

        return "redirect:/calendar/training/" + String.valueOf(trainingId);
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

    @GetMapping("/login")
    String login() {
        return "html/login";
    }

    @GetMapping("/logout")
    String logout() {
        return "redirect:/login";
    }

    @GetMapping("/trainings")
    @ResponseBody
    public String getTrainings() {
        List<Event> eventsList = trainingScheduleService.TrainingScheduleToEventList();
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
