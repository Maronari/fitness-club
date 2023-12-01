package ru.mirea.app.fitness_club.ORM;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "staff_photo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StaffPhoto {
    @Id
    private int id_staff_photo;
    private String image_url;
}
