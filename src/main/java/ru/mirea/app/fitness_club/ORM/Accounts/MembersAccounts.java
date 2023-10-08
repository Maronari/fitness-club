package ru.mirea.app.fitness_club.ORM.Accounts;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
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
    private String username;

    @OneToOne
    @JoinColumn(name = "id_member", nullable = false)
    private Members member;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_photo")
    private UserPhoto userPhoto;

    private String password;
    private Date account_creation_date;
    private Date last_login;
    private String user_role;

    @OneToMany(mappedBy = "memberAccount")
    private List<Feedback> feedbacks;
}
