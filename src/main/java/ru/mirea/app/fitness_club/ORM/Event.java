package ru.mirea.app.fitness_club.ORM;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class Event {
    private String title;
    private String start;
    private String end;
}
