package ru.mirea.app.fitness_club.ORM;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "members_accounts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MemberAccounts {
    @Id
    @OneToOne
    @JoinColumn(name = "id_member")
    private Members member;    

    @Id
    @OneToOne
    @JoinColumn(name = "id_photo")
    private UserPhoto userPhoto;    

    private String member_username;
    private String password;
    private String account_creation_date;
    private String last_login;

    @OneToMany(mappedBy = "id_member")
    private List<Feedback> feedbacks = new ArrayList<>();

    @OneToMany(mappedBy = "member_username")
    private List<Feedback> feedbacks2 = new ArrayList<>();
}
