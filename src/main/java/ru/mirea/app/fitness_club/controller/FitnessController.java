package ru.mirea.app.fitness_club.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import ru.mirea.app.fitness_club.ORM.Achievements;
import ru.mirea.app.fitness_club.ORM.EquipmentStatistics;
import ru.mirea.app.fitness_club.ORM.Event;
import ru.mirea.app.fitness_club.ORM.Members;
import ru.mirea.app.fitness_club.Service.MembersService;
import ru.mirea.app.fitness_club.Service.TrainingScheduleService;
import ru.mirea.app.fitness_club.Service.ClubsService;
@Controller
@AllArgsConstructor
@RequestMapping(value = "/")
public class FitnessController {
    private final MembersService membersService;
    private final ClubsService clubsService;
    private final TrainingScheduleService trainingScheduleService;

    @GetMapping("/profile/{role}/{id}")
    public String profile(@PathVariable Integer id, @PathVariable String role, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("role", role);
        Members member = membersService.getMember(id);
        model.addAttribute("memberClub", member.getClub());
        model.addAttribute("roleName", member.getMembershipRole().getRole_name());
        model.addAttribute("member", member);
        model.addAttribute("achievements", membersService.getListOfMemberAchievements(id));
        model.addAttribute("workouts", membersService.getListOfTrainingSchedule(id));
        model.addAttribute("photoURL", membersService.getPhotoUrl(id));
        model.addAttribute("news", clubsService.getListOfClubNews(member.getClub().getClub_name()));
        return "html/profile";
    }

    @GetMapping("/calendar/{role}/{id}")
    public String calendar(@PathVariable Integer id, @PathVariable String role, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("role", role);
        Members member = membersService.getMember(id);
        model.addAttribute("member", member);
        return "html/calendar";
    }

    @GetMapping("/calendar/training/{id}")
    public String training(@PathVariable Integer id, Model model) {
        model.addAttribute("training",trainingScheduleService.getTraining(id));
        model.addAttribute("trainers",trainingScheduleService.getTrainers(id));
        return "html/training";
    }

    @GetMapping("/statistic/{role}/{id}")
    public String statistic(@PathVariable Integer id, @PathVariable String role, Model model) {
        List<EquipmentStatistics> statistics = membersService.getListOfEquipmentStatistics(id);
        model.addAttribute("statistics", statistics);
        List<Achievements> achievements = membersService.getListOfMemberAchievements(id);
        model.addAttribute("achievements",achievements);
        return "html/statistic";
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
