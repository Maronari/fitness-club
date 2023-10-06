package ru.mirea.app.fitness_club.ORM;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "visit_history")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Visits {
    @Id
    private int id_visit;
    private Date visit_date;

    @ManyToMany(mappedBy = "membersVisits")
    private List<Members> members = new ArrayList<>();
}   