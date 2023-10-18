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

    public Clubs getClubs(Integer id) {
        return clubsRepository.findById(id).orElse(null);
    }

    public List<News> getListOfClubNews(int clubsid) {
        Clubs clubs = clubsRepository.findById(clubsid).orElse(null); 
        return clubs.getClubNews();
    }

    public List<Gyms> getListOfGyms(int clubsid) {
        Clubs clubs = clubsRepository.findById(clubsid).orElse(null);
        return clubs.getGyms();
    }

    public List<Equipment> getListOfEquipments(int clubsid) {
        List<Gyms> gyms = getListOfGyms(clubsid);
        return gyms.get(clubsid).getGymEquipments();
    }

    public String getEquipmentTypeName(int clubsid) {
        List<Equipment> equipments = getListOfEquipments(clubsid);
        return equipments.get(clubsid).getEquipmentType().getType_name();
    }

    public List<StaffSchedule> getListOfStaffSchedules(int clubsid) {
        Clubs clubs = clubsRepository.findById(clubsid).orElse(null);
        return clubs.getStaffSchedule();
    }
}
