package ru.mirea.app.fitness_club.ORM;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "nutrition_plan")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class NutritionPlan {
    @Id
    @OneToOne
    @JoinColumn(name = "id_member")
    private Members member;

    private int plan_id;
    private String nutrition_description;
    private String start_date;

    @OneToOne(mappedBy = "members")
    private Set<Members> members;
}
