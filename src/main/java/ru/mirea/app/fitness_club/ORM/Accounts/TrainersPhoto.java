package ru.mirea.app.fitness_club.ORM.Accounts;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "trainers_photo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TrainersPhoto {
    @Id
    private int id_photo;
    private String image_url;
}
