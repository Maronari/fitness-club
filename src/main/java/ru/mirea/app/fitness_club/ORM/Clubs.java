package ru.mirea.app.fitness_club.ORM;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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
    private List<Members> members;

    @OneToMany(mappedBy = "club")
    private List<StaffSchedule> staffSchedule;

    @OneToMany(mappedBy = "club")
    private List<Gyms> gyms;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "clubs_have_news", 
            joinColumns = { @JoinColumn(name = "club_name") }, 
            inverseJoinColumns = { @JoinColumn(name = "id_news") })
    private List<News> clubNews = new ArrayList<>();
}
