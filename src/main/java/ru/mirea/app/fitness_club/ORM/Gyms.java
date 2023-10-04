package ru.mirea.app.fitness_club.ORM;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    @OneToMany(mappedBy = "gyms")
    private List<GymsEquipment> gymsEquipments = new ArrayList<>();
    
    private int id_gym;
    private int amount_of_equipment;
}
