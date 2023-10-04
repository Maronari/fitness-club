package ru.mirea.app.fitness_club.ORM;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "achievements")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Achievements {
    @Id
    private int id_achievement;
    private String achievement_description;
    private String achievement_title;
    private String achievement_icon_url;

    @OneToMany(mappedBy = "achievement")
    private List<MemberAchievements> membersAchievements = new ArrayList<MemberAchievements>();
}
