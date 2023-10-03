package ru.mirea.app.fitness_club.ORM;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.weaver.Position;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "staff")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Staff {
    @Id
    @OneToOne
    @JoinColumn(name = "id_position")
    private Position position;
    
    @Id
    private int id_staff;
    private String first_name;
    private String second_name;
    private int phone_number;
    private String email;
    private String hire_date;    
    private String staff_about;
    private int gender;

    @OneToOne(mappedBy = "staff_schedule")
    private List<StaffSchedule> staffSchedules = new ArrayList<>();
}
