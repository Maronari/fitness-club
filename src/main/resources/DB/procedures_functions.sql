USE `fitness_club_db`;
/*добавление данных в существующую таблицу после обновления данных*/
DELIMITER $$
CREATE DEFINER = CURRENT_USER TRIGGER
`fitness_club_db`.`achievements_AFTER_UPDATE`
AFTER UPDATE ON `achievements` FOR EACH ROW
BEGIN
  INSERT INTO members_have_achievements (receipt_date)
  VALUES (NOW());
END$$


/*добавление данных в создаваемую таблицу после обновления данных*/
create table equipment_supplies(
id_supply int,
date_supply datetime);

CREATE DEFINER = CURRENT_USER TRIGGER
`fitness_club_db`.`equipment_AFTER_UPDATE`
AFTER UPDATE ON `equipment` FOR EACH ROW
BEGIN
INSERT INTO `fitness_club_db`.`equipment_supplies`
(`id_supply`, `date_supply`)
  VALUES (NEW.id_equipment, NOW());
END$$


/*проверка добавляемых данных на корректность перед добавлением*/
CREATE DEFINER = CURRENT_USER TRIGGER `fitness_club_db`.`members_BEFORE_INSERT` BEFORE INSERT ON `members` FOR EACH ROW
BEGIN
IF NEW.end_trial_date < NEW.start_trial_date THEN 
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Cannot insert a member with an end trial date earlier start date.';
  END IF;
END$$


/*сравнение добавляемого имени с существующими перед добавлением*/
CREATE DEFINER = CURRENT_USER TRIGGER 
`fitness_club_db`.`members_accounts_BEFORE_INSERT` BEFORE INSERT ON `members_accounts` FOR EACH ROW
BEGIN
IF EXISTS (SELECT 1 FROM `fitness_club_db`.`members_accounts` WHERE username = NEW.username) THEN 
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Cannot insert a new member with an existing username.';
  END IF;
END$$


/*сравнение добавляемого имени с существующими перед изменением*/
CREATE DEFINER = CURRENT_USER TRIGGER `fitness_club_db`.`members_accounts_BEFORE_UPDATE` BEFORE UPDATE ON `members_accounts` FOR EACH ROW
BEGIN
IF EXISTS (SELECT 1 FROM `fitness_club_db`.`members_accounts` WHERE username = NEW.username) THEN 
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Cannot change to an existing username.';
  END IF;
END$$


/*добавление данных в создаваемую таблицу после обновления данных*/
create table news_audit(
id int,
date_changed datetime);

CREATE DEFINER = CURRENT_USER TRIGGER `fitness_club_db`.`news_AFTER_UPDATE` AFTER UPDATE ON `news` FOR EACH ROW
BEGIN
INSERT INTO news_audit (id,date_changed)
  VALUES (NEW.id_news, NOW());
END$$


/*проверка добавляемых данных на корректность перед добавлением*/
CREATE DEFINER = CURRENT_USER TRIGGER `fitness_club_db`.`nutrition_plan_BEFORE_INSERT` BEFORE INSERT ON `nutrition_plan` FOR EACH ROW
BEGIN
IF NEW.end_date < NEW.start_date THEN 
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Cannot insert a plan with a date in the past.';
  END IF;
END$$


/*сравнение добавляемого имени с существующими перед добавлением*/
CREATE DEFINER = CURRENT_USER TRIGGER `fitness_club_db`.`staff_accounts_BEFORE_INSERT` BEFORE INSERT ON `staff_accounts` FOR EACH ROW
BEGIN
IF EXISTS (SELECT 1 FROM `fitness_club_db`.`staff_accounts` WHERE username = NEW.username) THEN 
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Cannot make a new account with an existing username.';
  END IF;
END$$


/*сравнение добавляемого имени с существующими перед изменением*/
CREATE DEFINER = CURRENT_USER TRIGGER `fitness_club_db`.`staff_accounts_BEFORE_UPDATE` BEFORE UPDATE ON `staff_accounts` FOR EACH ROW
BEGIN
IF EXISTS (SELECT 1 FROM `fitness_club_db`.`staff_accounts` WHERE username = NEW.username) THEN 
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Cannot change to an existing username.';
  END IF;
END$$


/*удаление связаных данных после удаления данных*/
CREATE DEFINER = CURRENT_USER TRIGGER `fitness_club_db`.`trainers_AFTER_DELETE` AFTER DELETE ON `trainers` FOR EACH ROW
BEGIN
DELETE FROM `fitness_club_db`.`training_schedule` WHERE id_trainer = OLD.id_trainer;
END$$


/*сравнение добавляемого имени с существующими перед добавлением*/
CREATE DEFINER = CURRENT_USER TRIGGER `fitness_club_db`.`trainers_accounts_BEFORE_INSERT` BEFORE INSERT ON `trainers_accounts` FOR EACH ROW
BEGIN
IF EXISTS (SELECT 1 FROM `fitness_club_db`.`trainers_accounts` WHERE username = NEW.username) THEN 
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Cannot make a new account with an existing username.';
  END IF;
END$$


/*сравнение добавляемого имени с существующими перед изменением*/
CREATE DEFINER = CURRENT_USER TRIGGER `fitness_club_db`.`trainers_accounts_BEFORE_UPDATE` BEFORE UPDATE ON `trainers_accounts` FOR EACH ROW
BEGIN
IF EXISTS (SELECT 1 FROM `fitness_club_db`.`trainers_accounts` WHERE username = NEW.username) THEN 
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Cannot change to an existing username.';
  END IF;
END$$


/*сравнение добавляемой даты с настоящей перед добавлением*/
CREATE DEFINER = CURRENT_USER TRIGGER
`fitness_club_db`.`training_schedule_BEFORE_INSERT`
BEFORE INSERT ON `training_schedule` FOR EACH ROW
BEGIN
IF NEW.session_date < CURDATE() THEN 
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Cannot insert a session with a date in the past.';
  END IF;
END$$


/*добавление данных в создаваемую таблицу после обновления данных*/
create table training_schedule_audit(
id_session int, 
changed_at date);

CREATE DEFINER = CURRENT_USER TRIGGER 
`fitness_club_db`.`training_schedule_AFTER_UPDATE`
AFTER UPDATE ON `training_schedule` FOR EACH ROW
BEGIN
  INSERT INTO training_schedule_audit (`id_session`, `changed_at`)
  VALUES (NEW.id_session, NOW());
END$$


/*сравнение добавляемой даты с настоящей перед добавлением*/
CREATE DEFINER = CURRENT_USER TRIGGER `fitness_club_db`.`visits_history_BEFORE_INSERT` BEFORE INSERT ON `visits_history` FOR EACH ROW
BEGIN
IF NEW.visit_date > CURDATE() THEN 
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Cannot insert a visit with a date in the past.';
  END IF;
END$$


/*удаление удаленных данных*/
CREATE TRIGGER training_schedule_AFTER_DELETE after delete on members_have_training_schedule FOR EACH ROW
BEGIN
DELETE FROM training_schedule WHERE id_session = OLD.id_session;
END$$


/*удаление удаленных данных*/
CREATE TRIGGER visits_history_AFTER_DELETE after delete on members_have_visits_history FOR EACH ROW
BEGIN
DELETE FROM visits_history WHERE id_visit = OLD.id_visit;
END$$


/*удаление удаленных данных*/
CREATE TRIGGER achievements_AFTER_DELETE after delete on members_have_achievements FOR EACH ROW
BEGIN
DELETE FROM achievements WHERE id_achievement = OLD.id_achievement;
END$$


/*удаление удаленных данных*/
CREATE TRIGGER inbody_analyses_AFTER_DELETE after delete on members_have_inbody_analyses FOR EACH ROW
BEGIN
DELETE FROM inbody_analyses WHERE id_inbody_analys = OLD.id_inbody_analys;
END$$


/*удаление удаленных данных*/
CREATE TRIGGER equipment_statistics_AFTER_DELETE after delete on members_have_equipment_statistics FOR EACH ROW
BEGIN
DELETE FROM equipment_statistics WHERE id_statistics = OLD.id_statistics;
END$$



CREATE PROCEDURE members_have_equipment_statistics_delete()
BEGIN
delete from members_have_equipment_statistics where id_statistics=1;
select *from equipment_statistics;
END$$

CREATE PROCEDURE members_have_inbody_analyses_delete()
BEGIN
delete from members_have_inbody_analyses where id_inbody_analys=1;
select *from inbody_analyses;
END$$

CREATE PROCEDURE members_have_achievements_delete()
BEGIN
delete from members_have_achievements where id_achievement=1;
select *from achievements;
END$$

CREATE PROCEDURE members_have_visits_history_delete()
BEGIN
delete from members_have_visits_history where id_visit=1;
select *from visits_history;
END$$

CREATE PROCEDURE members_have_training_schedule_delete()
BEGIN
delete from members_have_training_schedule where id_session=1;
select *from training_schedule;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `visits_history_add`()
BEGIN
insert into visits_history(visit_date) values
('2024-09-11');
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `staff_add`()
BEGIN
insert into staff (id_position,first_name,second_name,phone_number,email,hire_date,staff_about,gender) values
(3, "Сергей", "Михайлов", "222", "dgfg.p@gmail.com", "2020-10-2", "Какой-то мужик", 1);
END$$


CREATE DEFINER=`root`@`localhost` PROCEDURE `training_type_add`()
BEGIN
insert into training_type(training_type_name, workout_description) values
("Прыжки", "Групповые прыжки вверх с двух ног. Отличный способ бесполезно провести время. Развлекайтесь!");
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `visits_history_6_delete`()
BEGIN
DELETE FROM visits_history WHERE id_visit = 6;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `staff_5_delete`()
BEGIN
DELETE FROM staff WHERE id_staff = 5;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `training_type_delete`()
BEGIN
DELETE FROM training_type WHERE id_training_type = 5;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `members_feedback`()
BEGIN
select first_name,second_name,username,feedback_text,rating,feedback_date
from members
join members_accounts using (id_member)
join feedback using (username)
order by feedback_date asc;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `members_inbody_analyses`()
BEGIN
select first_name,second_name,height,weight,bmi,fat_percent,muscle_persent
from members
join members_have_inbody_analyses using (id_member)
join inbody_analyses using (id_inbody_analys);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `members_nutrition_plan`()
BEGIN
select first_name,second_name,nutrition_description,start_date
from members
join nutrition_plan using (id_member)
order by start_date asc;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `members_roles`()
BEGIN
select role_name,first_name,second_name
from membership_role
join members using(id_role);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `members_trainings`()
BEGIN
select username,first_name,second_name,session_date,session_time
from members_accounts join members using (id_member)
join members_have_training_schedule using (id_member)
join training_schedule using (id_session)
join training_type using (id_training_type)
order by session_date asc;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `members_visits`()
BEGIN
select first_name,second_name,visit_date
from members
join members_have_visits_history using(id_member)
join visits_history using(id_visit)
order by visit_date asc;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `staff_info`()
BEGIN
select first_name,second_name,username,role_name
from staff_accounts
join staff using(id_staff)
join position using(id_position);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `staff_schedule`()
BEGIN
select first_name,second_name,staff_schedule.club_name,shift,weekday
from staff,staff_schedule,clubs
where staff.id_staff=staff_schedule.id_staff
and staff_schedule.club_name=clubs.club_name;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `trainers_trainings`()
BEGIN
select username,first_name,second_name,session_date,session_time
from trainers_accounts join trainers using (id_trainer)
join training_schedule using (id_trainer)
join training_type using (id_training_type)
order by session_date asc;
END$$



CREATE FUNCTION TotalMembersWithNutritionPlan(id_plan INT) RETURNS INT
BEGIN
    DECLARE total INT;
    SELECT COUNT(*) INTO total FROM `fitness_club_db`.`nutrition_plan` WHERE `id_plan` = id_plan;
    RETURN total;
END$$

CREATE FUNCTION TotalTrainersWithTrainingType(id_training_type INT) RETURNS INT
BEGIN
    DECLARE total INT;
    SELECT COUNT(DISTINCT `id_trainer`) INTO total FROM `fitness_club_db`.`training_schedule` WHERE `id_training_type` = id_training_type;
    RETURN total;
END$$

CREATE FUNCTION TotalStaffInPosition(id_position INT) RETURNS INT
BEGIN
    DECLARE total INT;
    SELECT COUNT(*) INTO total FROM `fitness_club_db`.`staff` WHERE `id_position` = id_position;
    RETURN total;
END$$

CREATE FUNCTION TotalNewsForClub(club_name VARCHAR(45)) RETURNS INT
BEGIN
    DECLARE total INT;
    SELECT COUNT(*) INTO total FROM `fitness_club_db`.`clubs_have_news` WHERE `club_name` = club_name;
    RETURN total;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `AverageRatingFeedbackForMember`(username VARCHAR(45)) RETURNS FLOAT
BEGIN
DECLARE avg_rating FLOAT;
    SELECT AVG(`rating`) INTO avg_rating FROM `fitness_club_db`.`feedback` WHERE `username` = username;
    RETURN avg_rating;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `members_amount_1`() RETURNS int
BEGIN
declare a int;
select count(members.first_name) into a 
from members,members_have_training_schedule,training_schedule
where training_schedule.id_session=1
and members_have_training_schedule.id_member=members.id_member
and training_schedule.id_session=members_have_training_schedule.id_session;
RETURN a;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `TotalMemberOnTrainingDate`(dateon DATE) RETURNS int
BEGIN
DECLARE total INT;
    SELECT COUNT(*) INTO total FROM `fitness_club_db`.`training_schedule` WHERE DATE(`session_date`) = dateon;
    RETURN total;
END$$

CREATE FUNCTION TotalVisitsOnDate(dateon DATE) RETURNS INT
BEGIN
    DECLARE total INT;
    SELECT COUNT(*) INTO total FROM `fitness_club_db`.`visits_history` WHERE `visit_date` = dateon;
    RETURN total;
END$$

CREATE FUNCTION TotalEquipmentInGym(id INT) RETURNS INT
BEGIN
    DECLARE total INT;
    SELECT COUNT(*) INTO total FROM `fitness_club_db`.`gyms_have_equipment` WHERE `id_gym` = id;
    RETURN total;
END$$

CREATE FUNCTION TotalEquipmentOfType(id INT) RETURNS INT
BEGIN
    DECLARE total INT;
    SELECT COUNT(*) INTO total FROM `fitness_club_db`.`equipment` WHERE `id_equipment_type` = id;
    RETURN total;
END$$

CREATE FUNCTION TotalSessionsForTrainer(id INT) RETURNS INT
BEGIN
    DECLARE total INT;
    SELECT COUNT(*) INTO total FROM `fitness_club_db`.`training_schedule` WHERE `id_trainer` = id;
    RETURN total;
END$$

CREATE FUNCTION TotalGymsInClub(nameon VARCHAR(45)) RETURNS INT
BEGIN
    DECLARE total INT;
    SELECT COUNT(*) INTO total FROM `fitness_club_db`.`gyms` WHERE `club_name` = nameon;
    RETURN total;
END$$

CREATE FUNCTION TotalAchievementsForMember(id INT) RETURNS INT
BEGIN
    DECLARE total INT;
    SELECT COUNT(*) INTO total FROM `fitness_club_db`.`members_have_achievements` WHERE `id_member` = id;
    RETURN total;
END$$

CREATE FUNCTION TotalMembersInClub(nameon VARCHAR(45)) RETURNS INT
BEGIN
    DECLARE total INT;
    SELECT COUNT(*) INTO total FROM `fitness_club_db`.`members` WHERE `club_name` = nameon;
    RETURN total;
END$$

CREATE FUNCTION TotalMembersWithAchievement(id INT) RETURNS INT
BEGIN
    DECLARE total INT;
    SELECT COUNT(*) INTO total FROM `fitness_club_db`.`members_have_achievements` WHERE `id_achievement` = id;
    RETURN total;
END$$
DELIMITER ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
