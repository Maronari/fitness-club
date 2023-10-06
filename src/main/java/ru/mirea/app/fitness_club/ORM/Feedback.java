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
import ru.mirea.app.fitness_club.ORM.Accounts.MembersAccounts;

@Entity
@Table(name = "feedback")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Feedback {
    @Id
    private int id_feedback;
    
    @ManyToOne
    @JoinColumn(name = "id_member")
    private Members member;
    
    @ManyToOne
    @JoinColumn(name = "member_username")
    private MembersAccounts membersAccount;

    private String feedback_text;
    private Date feedback_date;
    private int rating;
}
