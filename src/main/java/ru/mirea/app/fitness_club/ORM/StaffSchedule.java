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
    private int id_schedule;

    @OneToOne
    @JoinColumn(name = "id_staff")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "clubs_name")
    private Clubs clubs;

    private int weekday;
    private int shift;
}
