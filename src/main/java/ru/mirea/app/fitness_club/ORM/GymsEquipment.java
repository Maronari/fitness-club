package ru.mirea.app.fitness_club.ORM;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "gyms_has_equipment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GymsEquipment {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_gym")
    private Gyms gyms;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "id_equipment")
    private Equipment equipment;
}
