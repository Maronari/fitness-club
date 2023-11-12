package ru.mirea.app.fitness_club.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.mirea.app.fitness_club.ORM.TrainingSchedule;

import java.util.Date;
import java.util.List;

public interface TrainingScheduleRepository extends JpaRepository<TrainingSchedule, Integer> {

        @Query("SELECT ts FROM TrainingSchedule ts " +
                "WHERE (:id_trainer IS NULL OR ts.trainers.id IN :id_trainer) " +
                "AND (:id_training_type IS NULL OR ts.trainingType.id IN :id_training_type) " +
                "AND (:session_date_start IS NULL OR ts.session_date >= :session_date_start) " +
                "AND (:session_date_end IS NULL OR ts.session_date <= :session_date_end) " +
                "AND (:session_time_start IS NULL OR ts.session_time >= :session_time_start) " +
                "AND (:session_time_end IS NULL OR ts.session_time <= :session_time_end)")
        List<TrainingSchedule> findByFilter(@Param("id_trainer") List<Integer> id_trainer,
                                @Param("id_training_type") List<Integer> id_training_type,
                                @Param("session_date_start") Date session_date_start,
                                @Param("session_date_end") Date session_date_end,
                                @Param("session_time_start") Integer session_time_start,
                                @Param("session_time_end") Integer session_time_end);
}
