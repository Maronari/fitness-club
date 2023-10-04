package ru.mirea.app.fitness_club.ORM;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
    @OneToOne
    @JoinColumn(name = "id_equipment_type")
    private EquipmentType equipmentType;
    
    @Id
    private int id_equipment;
    private String name;
    private int quantity;

    @OneToMany(mappedBy = "equipment")
    private List<GymsEquipment> gymsEquipments = new ArrayList<>();
}
