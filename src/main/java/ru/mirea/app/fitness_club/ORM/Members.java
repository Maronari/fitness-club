package ru.mirea.app.fitness_club.ORM;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.mirea.app.fitness_club.ORM.Accounts.MembersAccounts;

@Entity
@Table(name = "members")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Members {
    @Id
    private int id_member;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_role", nullable = false)
    private MembershipRole membershipRole;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "club_name", nullable = false)
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

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "members_have_visits_history", joinColumns = {
            @JoinColumn(name = "id_member") }, inverseJoinColumns = { @JoinColumn(name = "id_visit") })
    private List<Visits> membersVisits = new ArrayList<>();

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "members_have_inbody_analyses", joinColumns = {
            @JoinColumn(name = "id_member") }, inverseJoinColumns = { @JoinColumn(name = "id_inbody_analys") })
    private List<InbodyAnalyses> memberInbodyAnalyses = new ArrayList<>();

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "members_have_equipment_statistic", joinColumns = {
            @JoinColumn(name = "id_member") }, inverseJoinColumns = { @JoinColumn(name = "id_statistic") })
    private List<EquipmentStatistics> memberEquipmentStatistics = new ArrayList<>();

    @OneToOne(mappedBy = "member")
    private MembersAccounts memberAccounts;

    @OneToOne(mappedBy = "member")
    private NutritionPlan nutritionPlans;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "members_have_training_schedule", joinColumns = {
            @JoinColumn(name = "id_member") }, inverseJoinColumns = { @JoinColumn(name = "id_session") })
    private List<TrainingSchedule> memberTrainingSchedules = new ArrayList<>();
}
