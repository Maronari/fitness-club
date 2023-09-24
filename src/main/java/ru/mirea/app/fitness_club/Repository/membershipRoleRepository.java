package ru.mirea.app.fitness_club.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.app.fitness_club.ORM.MembershipRole;

@Repository
public interface membershipRoleRepository extends JpaRepository<MembershipRole, Integer> {
    MembershipRole findById(int id);
}
