package ru.mirea.app.fitness_club.ORM;

import java.sql.Date;

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
    private int id_plan;   

    @OneToOne
    @JoinColumn(name = "id_member")
    private Members member;

    private String nutrition_description;
    private Date start_date;
}
