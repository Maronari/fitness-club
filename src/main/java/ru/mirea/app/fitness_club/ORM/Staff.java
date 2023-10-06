package ru.mirea.app.fitness_club.ORM;

import java.sql.Date;

import org.aspectj.weaver.Position;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.mirea.app.fitness_club.ORM.Accounts.StaffAccounts;

@Entity
@Table(name = "staff")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Staff {
    @Id
    private int id_staff;    
    
    @ManyToOne
    @JoinColumn(name = "id_position")
    private Position position;
    
    private String first_name;
    private String second_name;
    private int phone_number;
    private String email;
    private Date hire_date;    
    private String staff_about;
    private int gender;

    @OneToOne(mappedBy = "staff")
    private StaffSchedule staffSchedules;

    @OneToOne(mappedBy = "staff")
    private StaffAccounts staffAccounts;
}
