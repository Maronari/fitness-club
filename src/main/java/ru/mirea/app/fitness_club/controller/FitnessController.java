package ru.mirea.app.fitness_club.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.AllArgsConstructor;
import ru.mirea.app.fitness_club.ORM.Clubs;
import ru.mirea.app.fitness_club.ORM.Members;
import ru.mirea.app.fitness_club.Service.MembersService;

@Controller
@AllArgsConstructor
public class FitnessController {
    private final MembersService membersService;

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable Integer id, Model model) {
        Members member = membersService.getMember(id);
        String roleName = membersService.getMembershipRoleById(id);
        Clubs memberClub = membersService.getMemberClub(id);
        model.addAttribute("memberClub", memberClub);
        model.addAttribute("roleName", roleName);
        model.addAttribute("member", member);
        return "html/profile";
    }

}
