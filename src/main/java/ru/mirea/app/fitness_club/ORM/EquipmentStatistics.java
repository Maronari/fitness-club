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
@Table(name = "equipment_statistics")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EquipmentStatistics {
    @Id
    @OneToOne
    @JoinColumn(name = "id_activity")
    private ActivityType id_activity;

    private int id_statistics;
    private int approaches;
    private int kilocalories;

    @OneToMany(mappedBy = "equipment_statistics")
    private List<MemberEquipmentStatistics> membersEquipmentStatistics = new ArrayList<>();
}
