package ru.mirea.app.fitness_club.ORM;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clubs")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Clubs {
    @Id
    private String club_name;
    private String address;

    @OneToMany(mappedBy = "club")
    private Set<Members> members;
}
