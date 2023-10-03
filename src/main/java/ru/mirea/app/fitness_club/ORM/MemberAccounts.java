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

    @OneToOne(mappedBy = "members")
    private List<Members> members = new ArrayList<>();

    @OneToOne(mappedBy = "users_photo")
    private List<UserPhoto> userPhotos = new ArrayList<>();

    @OneToMany(mappedBy = "feedback")
    private List<Feedback> feedbacks = new ArrayList<>();
}
