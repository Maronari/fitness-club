package ru.mirea.app.fitness_club.ORM;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
    @OneToOne
    @JoinColumn(name = "id_member")
    private MemberAccounts id_member;
    
    @Id
    @OneToOne
    @JoinColumn(name = "member_username")
    private MemberAccounts member_username;

    private int feedback_id;
    private String feedback_text;
    private String feedback_date;
    private int rating;


    @OneToOne(mappedBy = "users_photo")
    private Set<MemberAccounts> memberAccounts;
}
