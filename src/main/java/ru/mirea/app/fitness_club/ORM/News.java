package ru.mirea.app.fitness_club.ORM;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    @OneToMany(mappedBy = "clubs_has_news")
    private List<ClubNews> clubNews = new ArrayList<>();
}
