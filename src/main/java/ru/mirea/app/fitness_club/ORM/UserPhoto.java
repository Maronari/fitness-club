package ru.mirea.app.fitness_club.ORM;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users_photo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserPhoto {
    @Id
    private int id_photo;
    private String image_url;

    @OneToOne(mappedBy = "userPhoto")
    private Set<MemberAccounts> memberAccounts;
}
