package ru.mirea.app.fitness_club.ORM;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "activity_type")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ActivityType {
    @Id
    private int id_activity;
    
    private String activity_name;

    @OneToOne(mappedBy = "activityType")
    private EquipmentStatistics equipmentStatistics;
}
