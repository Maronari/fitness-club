package ru.mirea.app.fitness_club.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.mirea.app.fitness_club.ORM.Clubs;
import ru.mirea.app.fitness_club.ORM.Equipment;
import ru.mirea.app.fitness_club.ORM.Gyms;
import ru.mirea.app.fitness_club.ORM.News;
import ru.mirea.app.fitness_club.ORM.StaffSchedule;
import ru.mirea.app.fitness_club.Repository.ClubsRepository;

@Service
@AllArgsConstructor
public class ClubsService {
    private final ClubsRepository clubsRepository;

    public Clubs getClubs(String id) {
        return clubsRepository.findById(id).orElse(null);
    }

    public List<News> getListOfClubNews(String id) {
        Clubs clubs = clubsRepository.findById(id).orElse(null); 
        return clubs.getClubNews();
    }

    public List<Gyms> getListOfGyms(String id) {
        Clubs clubs = clubsRepository.findById(id).orElse(null);
        return clubs.getGyms();
    }

    public List<Equipment> getListOfEquipments(String clubsId, int gymId) {
        List<Gyms> gyms = getListOfGyms(clubsId);
        for (Gyms gym : gyms) {
            if (gym.getId_gym() == gymId) {
                return gym.getGymEquipments();
            }
        }
        return null;
    }

    public String getEquipmentTypeName(String clubsId, int gymId, int equipmentId) {
        List<Equipment> equipments = getListOfEquipments(clubsId, gymId);
        for (Equipment equipment : equipments) {
            if (equipment.getId_equipment() == equipmentId) {
                return equipment.getEquipmentType().getType_name();
            }
        }
        return null;
    }

    public List<StaffSchedule> getListOfStaffSchedules(String clubsId) {
        Clubs clubs = clubsRepository.findById(clubsId).orElse(null);
        return clubs.getStaffSchedule();
    }
}
