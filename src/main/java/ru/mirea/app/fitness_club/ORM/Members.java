package ru.mirea.app.fitness_club.ORM;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="members")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Members {
    @Id
    private int id_member;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_role", nullable = false)
    private MembershipRole membershipRole;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="club_name", nullable = false)
    private Clubs club; 
    
    private String first_name;
    private String second_name;
    private String phone_number;
    private String email;
    private Date birth_date;
    private Date start_trial_date;
    private Date end_trial_date;
    private int gender;

    @OneToMany(mappedBy = "member")
    private List<MemberAchievements> membersAchievements = new ArrayList<MemberAchievements>();

    @OneToMany(mappedBy = "member")
    private List<MemberVisits> membersVisits = new ArrayList<MemberVisits>();

    @OneToMany(mappedBy = "member")
    private List<MemberInbodyAnalyses> memberInbodyAnalyses = new ArrayList<MemberInbodyAnalyses>();

    @OneToMany(mappedBy = "member")
    private List<MemberEquipmentStatistics> memberEquipmentStatistics = new ArrayList<MemberEquipmentStatistics>();

    @OneToMany(mappedBy = "member")
    private List<MemberAccounts> memberAccounts = new ArrayList<MemberAccounts>();

    @OneToOne(mappedBy = "member")
    private List<NutritionPlan> nutritionPlans = new ArrayList<NutritionPlan>();

    @OneToMany(mappedBy = "member")
    private List<MemberTrainingSchedule> memberTrainingSchedules = new ArrayList<MemberTrainingSchedule>();
}
