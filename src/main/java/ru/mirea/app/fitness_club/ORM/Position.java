package ru.mirea.app.fitness_club.ORM;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "position")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Position {
    @Id
    private int id_position;
    private String role_name;

    @OneToMany(mappedBy = "position")
    private List<Staff> staffs = new ArrayList<>();
}
