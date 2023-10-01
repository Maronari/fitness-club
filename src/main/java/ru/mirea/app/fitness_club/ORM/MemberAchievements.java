package ru.mirea.app.fitness_club.ORM;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "members_have_achievements")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemberAchievements {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_member")
    private Members member;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "id_achievement")
    private Achievements achievement;

    private Date receipt_date;

}
