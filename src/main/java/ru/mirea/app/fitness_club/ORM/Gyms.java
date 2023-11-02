package ru.mirea.app.fitness_club.ORM;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "gyms")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Gyms {
    @Id
    private int id_gym;

    private String gym_name;

    @Id
    @ManyToOne
    @JoinColumn(name = "club_name", nullable = false)
    private Clubs club;

    private int capacity;
    private int available_hours;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "gyms_have_equipment",
            joinColumns = {
                @JoinColumn(name = "id_gym"),
                @JoinColumn(name = "club_name")},
            inverseJoinColumns = {
                @JoinColumn(name = "id_equipment")})
    private List<Equipment> gymEquipments = new ArrayList<>();
}
