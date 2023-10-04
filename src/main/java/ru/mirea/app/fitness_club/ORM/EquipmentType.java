package ru.mirea.app.fitness_club.ORM;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "equipment_type")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EquipmentType {
    @Id
    private int id_equipment_type;
    private String type_name;

    @OneToOne(mappedBy = "equipmentType")
    private List<Equipment> equipments = new ArrayList<>();
}
