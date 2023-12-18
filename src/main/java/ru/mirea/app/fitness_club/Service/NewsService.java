package ru.mirea.app.fitness_club.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.mirea.app.fitness_club.ORM.News;
import ru.mirea.app.fitness_club.Repository.NewsRepository;

@Service
@AllArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;

    public List<News> getListOfClubNews() {
        return newsRepository.findAll();
    }
}
