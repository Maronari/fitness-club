DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `clubs_equipment`()
BEGIN
select clubs.club_name,gym_name,name,quantity,type_name
from clubs
join gyms using(club_name)
join gyms_have_equipment using(id_gym)
join equipment using(id_equipment)
join equipment_type using(id_equipment_type);
END$$
DELIMITER ;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `clubs_gyms`()
BEGIN
select clubs.club_name,gym_name,capacity
from clubs
join gyms using(club_name);
END$$
DELIMITER ;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `clubs_news`()
BEGIN
select clubs.club_name, news_title,news_text
from clubs
join clubs_have_news using(club_name)
join news using(id_news);
END$$
DELIMITER ;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `members_achievements`()
BEGIN
select first_name,second_name,achievement_title,achievement_description,receipt_date
from members
join members_have_achievements using (id_member)
join achievements using (id_achievement)
order by receipt_date asc;
END$$
DELIMITER ;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `members_activities`()
BEGIN
select first_name,second_name,approaches,kilocalories,activity_name
from members
join members_have_equipment_statistics using (id_member)
join equipment_statistics using (id_statistics)
join activity_type using(id_activity);
END$$
DELIMITER ;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `members_clubs`()
BEGIN
select first_name,second_name,club_name
from members
join clubs using (club_name);
END$$
DELIMITER ;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `members_feedback`()
BEGIN
select first_name,second_name,username,feedback_text,rating,feedback_date
from members
join members_accounts using (id_member)
join feedback using (username)
order by feedback_date asc;
END$$
DELIMITER ;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `members_inbody_analyses`()
BEGIN
select first_name,second_name,height,weight,bmi,fat_percent,muscle_persent
from members
join members_have_inbody_analyses using (id_member)
join inbody_analyses using (id_inbody_analys);
END$$
DELIMITER ;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `members_nutrition_plan`()
BEGIN
select first_name,second_name,nutrition_description,start_date
from members
join nutrition_plan using (id_member)
order by start_date asc;
END$$
DELIMITER ;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `members_roles`()
BEGIN
select role_name,first_name,second_name
from membership_role
join members using(id_role);
END$$
DELIMITER ;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `members_trainings`()
BEGIN
select username,first_name,second_name,session_date,session_time
from members_accounts join members using (id_member)
join members_have_training_schedule using (id_member)
join training_schedule using (id_session)
join training_type using (id_training_type)
order by session_date asc;
END$$
DELIMITER ;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `members_visits`()
BEGIN
select first_name,second_name,visit_date
from members
join members_have_visits_history using(id_member)
join visits_history using(id_visit)
order by visit_date asc;
END$$
DELIMITER ;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `staff_info`()
BEGIN
select first_name,second_name,username,role_name
from staff_accounts
join staff using(id_staff)
join position using(id_position);
END$$
DELIMITER ;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `staff_schedule`()
BEGIN
select first_name,second_name,club_name,shift,weekday
from staff,staff_schedule,clubs
where staff.id_staff=staff_schedule.id_staff
and staff_schedule.clubs_name=clubs.club_name;
END$$
DELIMITER ;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `trainers_trainings`()
BEGIN
select username,first_name,second_name,session_date,session_time
from trainers_accounts join trainers using (id_trainer)
join training_schedule using (id_trainer)
join training_type using (id_training_type)
order by session_date asc;
END$$
DELIMITER ;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` FUNCTION `amount_achievements_1`() RETURNS int
BEGIN
declare a int;
select count(members.first_name) into a 
from members,members_have_achievements,achievements
where members.id_member=1
and members_have_achievements.id_member=members.id_member
and members_have_achievements.id_achievement=achievements.id_achievement;
RETURN a;
END$$
DELIMITER ;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` FUNCTION `amount_achievements_2`() RETURNS int
BEGIN
declare a int;
select count(members.first_name) into a 
from members,members_have_achievements,achievements
where members.id_member=2
and members_have_achievements.id_member=members.id_member
and members_have_achievements.id_achievement=achievements.id_achievement;
RETURN a;
END$$
DELIMITER ;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` FUNCTION `amount_achievements_3`() RETURNS int
BEGIN
declare a int;
select count(members.first_name) into a 
from members,members_have_achievements,achievements
where members.id_member=3
and members_have_achievements.id_member=members.id_member
and members_have_achievements.id_achievement=achievements.id_achievement;
RETURN a;
END$$
DELIMITER ;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` FUNCTION `amount_achievements_4`() RETURNS int
BEGIN
declare a int;
select count(members.first_name) into a 
from members,members_have_achievements,achievements
where members.id_member=4
and members_have_achievements.id_member=members.id_member
and members_have_achievements.id_achievement=achievements.id_achievement;
RETURN a;
END$$
DELIMITER ;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` FUNCTION `amount_achievements_5`() RETURNS int
BEGIN
declare a int;
select count(members.first_name) into a 
from members,members_have_achievements,achievements
where members.id_member=5
and members_have_achievements.id_member=members.id_member
and members_have_achievements.id_achievement=achievements.id_achievement;
RETURN a;
END$$
DELIMITER ;
DELIMITER $$
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
DELIMITER ;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` FUNCTION `members_amount_2`() RETURNS int
BEGIN
declare a int;
select count(members.first_name) into a 
from members,members_have_training_schedule,training_schedule
where training_schedule.id_session=2
and members_have_training_schedule.id_member=members.id_member
and training_schedule.id_session=members_have_training_schedule.id_session;
RETURN a;
END$$
DELIMITER ;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` FUNCTION `members_amount_3`() RETURNS int
BEGIN
declare a int;
select count(members.first_name) into a 
from members,members_have_training_schedule,training_schedule
where training_schedule.id_session=3
and members_have_training_schedule.id_member=members.id_member
and training_schedule.id_session=members_have_training_schedule.id_session;
RETURN a;
END$$
DELIMITER ;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` FUNCTION `members_amount_4`() RETURNS int
BEGIN
declare a int;
select count(members.first_name) into a 
from members,members_have_training_schedule,training_schedule
where training_schedule.id_session=4
and members_have_training_schedule.id_member=members.id_member
and training_schedule.id_session=members_have_training_schedule.id_session;
RETURN a;
END$$
DELIMITER ;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` FUNCTION `members_amount_5`() RETURNS int
BEGIN
declare a int;
select count(members.first_name) into a 
from members,members_have_training_schedule,training_schedule
where training_schedule.id_session=5
and members_have_training_schedule.id_member=members.id_member
and training_schedule.id_session=members_have_training_schedule.id_session;
RETURN a;
END$$
DELIMITER ;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` FUNCTION `members_amount_6`() RETURNS int
BEGIN
declare a int;
select count(members.first_name) into a 
from members,members_have_training_schedule,training_schedule
where training_schedule.id_session=6
and members_have_training_schedule.id_member=members.id_member
and training_schedule.id_session=members_have_training_schedule.id_session;
RETURN a;
END$$
DELIMITER ;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` FUNCTION `members_amount_Авиапарк`() RETURNS int
BEGIN
declare a int;
select count(members.first_name) into a from clubs,members 
where clubs.club_name='Авиапарк'
and clubs.club_name=members.club_name;
RETURN a;
END$$
DELIMITER ;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` FUNCTION `members_amount_Аквамолл`() RETURNS int
BEGIN
declare a int;
select count(members.first_name) into a from clubs,members 
where clubs.club_name='Аквамолл'
and clubs.club_name=members.club_name;
RETURN a;
END$$
DELIMITER ;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` FUNCTION `members_amount_Первомайский`() RETURNS int
BEGIN
declare a int;
select count(members.first_name) into a from clubs,members 
where clubs.club_name='Первомайский'
and clubs.club_name=members.club_name;
RETURN a;
END$$
DELIMITER ;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` FUNCTION `members_amount_София`() RETURNS int
BEGIN
declare a int;
select count(members.first_name) into a from clubs,members 
where clubs.club_name='София'
and clubs.club_name=members.club_name;
RETURN a;
END$$
DELIMITER ;
