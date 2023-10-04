package ru.mirea.app.fitness_club.ORM;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "staff_accounts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StaffAccounts {
    @Id
    private int staff_username;
    private String password;
    private String last_login;
    private String account_creation_date;

    @Id
    @OneToOne
    @JoinColumn(name = "id_staff")
    private Staff staff;
}
