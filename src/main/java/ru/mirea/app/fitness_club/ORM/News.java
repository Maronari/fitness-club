package ru.mirea.app.fitness_club.ORM;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "news")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class News {
    @Id
    private int id_news;
    
    private String news_title;
    private String news_text;

    @JsonIgnore
    @ManyToMany(mappedBy = "clubNews")
    private List<Clubs> clubs = new ArrayList<>();
}
