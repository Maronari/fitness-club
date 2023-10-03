package ru.mirea.app.fitness_club.ORM;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "staff_schedule")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class StaffSchedule {

    @Id
    @ManyToOne
    @JoinColumn(name = "clubs_name")
    private Clubs clubs;
    
    @Id
    @OneToOne
    @JoinColumn(name = "id_staff")
    private Staff staff;

    private int id_schedule;
    private int weekday;
    private int shift;
}
