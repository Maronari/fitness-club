package ru.mirea.app.fitness_club.ORM;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "equipment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Equipment {
    
    @Id
    private int id_equipment;

    @OneToOne
    @JoinColumn(name = "id_equipment_type", nullable = false)
    private EquipmentType equipmentType;

    private String name;
    private int quantity;

    @ManyToMany(mappedBy = "gymEquipments")
    private List<Gyms> gyms = new ArrayList<>();
}
