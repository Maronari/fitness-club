package ru.mirea.app.fitness_club.ORM.Accounts;

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
import ru.mirea.app.fitness_club.ORM.Feedback;
import ru.mirea.app.fitness_club.ORM.Members;
import ru.mirea.app.fitness_club.ORM.UserPhoto;

@Entity
@Table(name = "members_accounts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MembersAccounts {
    @Id
    @OneToOne
    @JoinColumn(name = "id_member")
    private Members member;    

    @OneToOne
    @JoinColumn(name = "id_photo")
    private UserPhoto userPhoto;    

    @Id
    private String username;
    private String password;
    private String account_creation_date;
    private String last_login;
    private String user_role;

    @OneToMany(mappedBy = "id_member")
    private List<Feedback> feedbacks = new ArrayList<>();
}
