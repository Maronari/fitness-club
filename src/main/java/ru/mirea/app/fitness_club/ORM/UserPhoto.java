package ru.mirea.app.fitness_club.ORM;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.mirea.app.fitness_club.ORM.Accounts.MembersAccounts;
@Entity
@Table(name = "users_photo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserPhoto {
    @Id
    private int id_photo;

    @OneToOne
    @JoinColumn(name = "id_member")
    private Members member;

    @OneToOne
    @JoinColumn(name = "member_username")
    private MembersAccounts membersAccount;
    
    private String image_url;
}
