package ru.mirea.app.fitness_club.ORM;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clubs_has_news")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ClubNews {

    @Id
    @ManyToOne
    @JoinColumn(name = "club_name")
    private Clubs clubs;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "id_news")
    private News news;
}
