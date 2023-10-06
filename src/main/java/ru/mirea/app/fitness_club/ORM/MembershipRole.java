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
@Table(name = "membership_role")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MembershipRole {
    @Id
    private int id_role;
    
    private String role_name;
    
    @OneToMany(mappedBy = "membershipRole")
    private Set<Members> members;
}
