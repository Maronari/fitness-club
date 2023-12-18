package ru.mirea.app.fitness_club.ORM;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "id_staff")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "club_name")
    private Clubs club;

    private Date date;
    private int shift;
}
