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
    @ManyToOne
    @JoinColumn(name = "club_name")
    private Clubs clubs;

    @Id
    private int id_gym;
    private int amount_of_equipment;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "gyms_has_equipment", joinColumns = {
            @JoinColumn(name = "id_gym") }, inverseJoinColumns = { @JoinColumn(name = "id_equipment") })
    private List<Equipment> gymEquipments = new ArrayList<>();

}
