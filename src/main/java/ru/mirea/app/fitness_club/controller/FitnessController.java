package ru.mirea.app.fitness_club.controller;

import java.io.IOException;
import java.util.List;

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
import ru.mirea.app.fitness_club.ORM.Event;
import ru.mirea.app.fitness_club.ORM.Members;
import ru.mirea.app.fitness_club.ORM.TrainingSchedule;
import ru.mirea.app.fitness_club.ORM.Accounts.UserDetailsServiceImpl;
import ru.mirea.app.fitness_club.Service.MembersService;
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

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping("/profile/{role}/{id}")
    public String profile(@PathVariable Integer id, @PathVariable String role, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("role", role);
        Members member = membersService.getMember(id);
        model.addAttribute("memberClub", member.getClub());
        model.addAttribute("roleName", member.getMembershipRole().getRole_name());
        model.addAttribute("member", member);
        model.addAttribute("achievements", membersService.getMemberAchievements(id));
        model.addAttribute("workouts", membersService.getListOfTrainingSchedule(id));
        model.addAttribute("photoURL", membersService.getPhotoUrl(id));
        model.addAttribute("news", clubsService.getListOfClubNews(member.getClub().getClub_name()));
        return "html/profile";
    }

    @GetMapping("/calendar/{role}/{id}")
    public String calendar(@PathVariable Integer id, @PathVariable String role, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("role", role);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String name = ((UserDetails) principal).getUsername();
            Integer memberId = userDetailsService.getUserId(name);
            Members member = membersService.getMember(memberId);
            model.addAttribute("member", member);
        }

        return "html/calendar";
    }

    @GetMapping("/calendar/training/{id}")
    public String training(@PathVariable Integer id, Model model) {
        TrainingSchedule workout = trainingScheduleService.getTraining(id);
        model.addAttribute("training", workout);
        model.addAttribute("trainers", trainingScheduleService.getTrainers(id));

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String name = ((UserDetails) principal).getUsername();
            Integer memberId = userDetailsService.getUserId(name);
            Members member = membersService.getMember(memberId);
            String role = userDetailsService.getUserRole(name);

            boolean isSignedUp = member.getMemberTrainingSchedules().contains(workout);
            model.addAttribute("isSignedUp", isSignedUp);
            model.addAttribute("role", role);
            model.addAttribute("memberId", memberId);
        }
        return "html/training";
    }

    @PostMapping("/calendar/training/{id}")
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
