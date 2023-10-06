package ru.mirea.app.fitness_club.ORM;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inbody_analyses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class InbodyAnalyses {
    @Id
    private int id_inbody_analyses;
    private float height;
    private float weight;
    private float bmi;
    private float fat_percent;
    private float muscle_percent;

    @ManyToMany(mappedBy = "memberInbodyAnalyses")
    private List<Members> members = new ArrayList<>();
}