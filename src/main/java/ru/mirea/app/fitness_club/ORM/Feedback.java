package ru.mirea.app.fitness_club.ORM;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "feedback")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Feedback {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_member")
    private MemberAccounts id_member;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "member_username")
    private MemberAccounts member_username;

    private int feedback_id;
    private String feedback_text;
    private String feedback_date;
    private int rating;
}
