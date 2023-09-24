package ru.mirea.app.fitness_club.Service;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.mirea.app.fitness_club.ORM.Clubs;
import ru.mirea.app.fitness_club.ORM.Members;
import ru.mirea.app.fitness_club.ORM.MembershipRole;
import ru.mirea.app.fitness_club.Repository.ClubsRepository;
import ru.mirea.app.fitness_club.Repository.MembersRepository;
import ru.mirea.app.fitness_club.Repository.membershipRoleRepository;

@Service
@AllArgsConstructor
public class MembersService {
    private final MembersRepository membersRepository;
    private final membershipRoleRepository membershipRoleRepository;
    private final ClubsRepository clubsRepository;

    public Members getMember(Integer id) {
        return membersRepository.findById(id).orElse(null);
    }

    public String getMembershipRoleById(Integer id) {
        MembershipRole role = membershipRoleRepository.findById(id).orElse(null);
        return role.getRole_name();
    }

    public Clubs getMemberClub(Integer id) {
        Clubs club = clubsRepository.findById(id).orElse(null);
        return club;
    }
}
