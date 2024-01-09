create user if not exists 'springroot'@'%' identified by 'spring';
grant all on fitness_club_db.* to 'springroot'@'%';

insert into achievements (achievement_description,achievement_title,achievement_icon_url) values
('Вы подтянулись 20 раз за подход', 'Подтягальник', 'https://i.postimg.cc/xTx9Grmw/icons8-pull-up-64.png'),
('Пробежать 2км за подход', 'Начинающий бегун', 'https://i.postimg.cc/3Nymr2Z4/icons8-64.png'),
('Вы первые посетили наш клуб', 'Начало положено', 'https://i.postimg.cc/RF2qRych/icons8-1-64-1.png'),
('Вы сходили 10 раз на занятия зумбой', 'Звезда зумбы', 'https://i.postimg.cc/c4nLsFfM/icons8-64-2.png');

insert into membership_role(role_name) values
('Light'),
('Smart'),
('Infinity');

insert into clubs values
('София', 'Сиреневый бул., 31 (этаж 3)'),
('Авиапарк', 'Ходынский бул., 4 (этаж 3)'),
('Аквамолл', 'ул. Южнобутовская, д.2,'),
('Первомайский', 'Первомайская, д. 93\20, (этаж 2)');

insert into members (id_role,clubs_name,first_name,second_name,phone_number,email,birth_date,start_trial_date,end_trial_date,gender) values
(1, 'София', 'Алексей', 'Иванов', '1234567890', 'alex_will@example.com', '1990-01-01', '2021-10-01', '2021-11-01', 1),
(2, 'София', 'Екатерина', 'Смирнова', '2345678901', 'sm.kate@example.com', '1995-05-05', '2021-09-01', NULL, 0),
(1, 'Авиапарк', 'Дмитрий', 'Козлов', '3456789012', 'dmitry_k@example.com', '1985-12-31', '2021-08-01', '2021-09-01', 1),
(2, 'Первомайский', 'Наталья', 'Кузнецова', '4567890123', 'nat_kozlova228@example.com', '2000-07-15', '2021-07-01',  NULL, 0),
(3, 'Аквамолл', 'Стас', 'Михайлов', '5678901234', 'everything_for_you@example.com', '1998-03-20', '2021-06-01', '2021-07-01', 1);

insert into members_have_achievements values
(1, 1, '2021-10-01'),
(1, 3, '2021-09-15'),
(1, 2, '2021-08-20'),
(4, 1, '2021-07-10'),
(5, 4, '2021-06-05');

insert into trainers (first_name, second_name, speciality, experience, certifications, phone_number, email, hire_date)  values
("Андрей", "Качалкин", "тренер по бодибилдингу", 3, 1, "89284434099", "andrei.kach@xxd.ru", "2022-12-03"),
("Иван", "Степанов", "фитнес-инструктор", 5, 2, "12345678976", "stepanov@mail.ru", "2020-11-15"),
("Мария", "Васильева", "тренер по йоге", 6, 3, "45678904387", "maria@yandex.ru", "2021-03-10"),
("Анна", "Смирнова", "инструктор по танцам", 4, 2, "44455667788", "ann@ann.com", "2022-09-21"),
("Ольга", "Фёдорова", "тренер по аэробике", 2, 1, "22233447634", "olga@ol.com", "2023-01-12"),
("Евгений", "Орлов", "тренер по бегу", 8, 4, "11122334455", "evgeniy@mail.com", "2021-06-23");

insert into trainers_photo (image_url) values
("https://i.postimg.cc/XqbfNDBH/i.jpg"),
("https://i.postimg.cc/Wbznd0qn/1674365371-3-34.jpg"),
("https://i.postimg.cc/pTTTfQbW/f3ff0d04-c6fd-4162-9b67-78cdcbbf5fd7.jpg"),
("https://i.postimg.cc/cH4mfzLv/Jacked-Nordic-Chad-Side-View.webp"),
("https://i.postimg.cc/Wbznd0qn/1674365371-3-34.jpg"),
("https://i.postimg.cc/Wbznd0qn/1674365371-3-34.jpg");

insert into trainers_accounts (username, password, id_trainers_photo, last_login, account_creation_date, user_role)  values
("andrew.kachalkin", "$2y$10$z1VSMQ36/jxstCVWOG5A0uqlLMOIDRTeatfPzkQX6bWNICY9At2ba", 1 , current_date(), "2020-12-23", "TRAINER"),
("ivan.stepanov", "$2y$10$z1VSMQ36/jxstCVWOG5A0uqlLMOIDRTeatfPzkQX6bWNICY9At2ba", 2, current_date(), "2020-11-23", "TRAINER"),
("maria.vasilieva", "$2y$10$z1VSMQ36/jxstCVWOG5A0uqlLMOIDRTeatfPzkQX6bWNICY9At2ba", 3, current_date(), "2020-03-23", "TRAINER"),
("anna.smirnova", "$2y$10$z1VSMQ36/jxstCVWOG5A0uqlLMOIDRTeatfPzkQX6bWNICY9At2ba", 4, current_date(), "2020-09-23", "TRAINER"),
("olga.smirnova", "$2y$10$z1VSMQ36/jxstCVWOG5A0uqlLMOIDRTeatfPzkQX6bWNICY9At2ba", 5, current_date(), "2020-01-23", "TRAINER"),
("evgeniy.orlov", "$2y$10$z1VSMQ36/jxstCVWOG5A0uqlLMOIDRTeatfPzkQX6bWNICY9At2ba", 6, current_date(), "2020-06-23", "TRAINER");

insert into position (role_name) values
("Администратор"),
('Менеджер'),
('Бугхалтер');

insert into staff (id_position, first_name, second_name, phone_number, email, hire_date, staff_about, gender) values
(1, "Павел", "Петров", "12345678", "pavel.p@gmail.com", "2019-10-2", "Какой-то мужик", 1),
(2, 'Никита', 'Козлов', '13467912', 'nikk@example.com', '2018-10-27', 'Менеджер по продажам', 1),
(3, 'Яна', 'Кузнецова', '85246931', 'yaniik@example.com', '2018-10-26', 'Какая-то девушка', 0);


insert into staff_photo (image_url) values
("https://i.postimg.cc/7YdqTc0k/d0276bfac31e1954601d1409c76d8085.jpg"),
("https://i.postimg.cc/Fz2cqpdJ/giga.jpg"),
("https://i.postimg.cc/q7bGw8L2/photo-2023-11-26-15-43-47.jpg");

insert into staff_accounts (id_staff, username, password, id_staff_photo, last_login, account_creation_date, user_role) values
(1, "pavelP", "$2y$10$AWFx1TzUBsvOeRw17pDYeeDR.V50yDQvqH4moueRUzZecQ0kg0hBa", 1, current_date(), "2019-10-3", "STAFF"),
(2, "nikitaM", "$2y$10$AWFx1TzUBsvOeRw17pDYeeDR.V50yDQvqH4moueRUzZecQ0kg0hBa", 2, current_date(), "2019-11-3", "STAFF"),
(3, "yanaB", "$2y$10$AWFx1TzUBsvOeRw17pDYeeDR.V50yDQvqH4moueRUzZecQ0kg0hBa", 3, current_date(), "2019-02-3", "STAFF");

insert into users_photo (image_url) values
("https://pushinka.top/uploads/posts/2023-03/thumbs/1679913315_pushinka-top-p-avatarki-s-gigachadom-vkontakte-58.webp"),
("https://i.postimg.cc/ZKw8Znfd/kiss-cut-stickers-5-5x5-5-default-63866219a9dc4-1200x.webp");


insert into members_accounts values
("JohnDoe", 1, 1, "$2y$10$uXzTcs9nQcNo62JgoBzXJOjhyeuV5.kBUEt8l5l88XYVNtFnRPuGi", "2021-10-01", "2023-10-5", "MEMBER"),
("KatyaS", 2, 2, "$2y$10$/B5.o6InFg3f4p/MqWb51.rUzvDJqjR1h13Q.kWmn3t8DGMkgmrvW", "2021-11-05", "2023-11-4", "MEMBER");

insert into training_type(training_type_name, workout_description) values
("BODYPUMP", "Групповая тренировка со штангой. Отличный способ снижения веса. Развивает силу, повышает выносливость."),
("STRETCHING", "Программа для комплексного снятия усталости мышц, улучшения гибкости и подвижности суставов. Лучший способой раслабиться."),
("Зумба", "Танцевальная фитнес-программа, популярная во всем мире. Зумба - один из видов высокоинтенсивных аэробных уроков."),
("Smart Start Fullbody", "Авторская программа XXD Fitness, которая включает в себя управжения на основные группы мышц."),
("Персональная тренировка", "Ваша тренировка с личным тренером");

insert into training_schedule(id_trainer, id_training_type, session_date, session_time)  values 
(2, 2, "2023-12-05 10:30:00", 55),
(1, 1, '2023-12-01 10:00:00', 60),
(2, 2, '2023-12-02 11:00:00', 90),
(3, 3, '2023-12-03 12:00:00', 120),
(1, 2, '2023-12-03 10:00:00', 60),
(2, 2, '2023-12-03 11:00:00', 90),
(3, 3, '2023-12-04 12:00:00', 120),
(1, 1, '2023-12-04 15:00:00', 60),
(3, 2, '2023-12-05 17:00:00', 90),
(3, 4, '2023-12-09 18:00:00', 120),
(3, 4, '2023-12-10 20:00:00', 60),
(2, 2, '2023-12-10 21:00:00', 90),
(3, 3, '2023-12-12 10:00:00', 120),
(3, 1, '2023-12-12 12:00:00', 60),
(2, 3, '2023-12-12 11:00:00', 90),
(2, 3, '2023-12-12 13:00:00', 120),
(1, 4, '2023-12-13 10:00:00', 60),
(2, 2, '2023-12-13 11:00:00', 90),
(3, 3, '2023-12-14 12:00:00', 120),
(5, 3, '2023-12-15 10:00:00', 60),
(3, 2, '2023-12-17 11:00:00', 90),
(1, 3, '2023-12-18 11:00:00', 90),
(2, 3, '2023-12-18 13:00:00', 120),
(4, 4, '2023-12-18 10:00:00', 60),
(1, 2, '2023-12-19 11:00:00', 90),
(1, 3, '2023-12-20 12:00:00', 120),
(3, 3, '2023-12-20 10:00:00', 60),
(4, 3, '2023-12-21 11:00:00', 90),
(1, 3, '2023-12-22 13:00:00', 120),
(4, 4, '2023-12-23 10:00:00', 60),
(3, 2, '2023-12-23 11:00:00', 90),
(4, 3, '2023-12-24 12:00:00', 120),
(5, 3, '2023-12-25 10:00:00', 60);

insert into members_have_training_schedule values
(1, 1),
(1, 2);

insert into inbody_analyses(height,weight,bmi,fat_percent,muscle_persent) values
(175, 75, 24.49, 18.5, 40.2),
(160, 60, 23.44, 20.2, 37.8),
(185, 85, 24.84, 17.3, 42.1),
(170, 70, 24.22, 19.0, 38.9),
(165, 62, 22.77, 21.5, 36.4);

insert into gyms (club_name,gym_name,capacity,available_hours) values
('София', 'Тренажерный зал', 50, 14),
('Авиапарк', 'Зал для пилатес', 30, 17),
('Аквамолл', 'Зал для групповых занятий', 25, 12),
('Первомайский', 'Зал для пилатес', 20, 13);

insert into nutrition_plan(id_member,nutrition_description,start_date) values
(1, 'Ежедневный план питания для набора мышечной массы', '2021-10-01'),
(2, 'План питания для выработки энергии перед тренировкой', '2021-09-05'),
(3, 'План питания для поддержания физической активности', '2021-08-01'),
(4, 'План питания для оптимального восстановления после тренировки', '2021-07-02'),
(5, 'План питания для снижения жира и поддержания мышечной формы', '2021-06-01');

insert into visits_history(visit_date) values
('2023-11-28'),
('2023-11-29'),
('2023-11-30'),
('2023-12-1'),
('2023-11-28');

insert into equipment_type(type_name) values
('Гантели'),
('Брусья'),
('Тренажеры для ног'),
('Скакалки'),
('Эластичные ленты');

insert into equipment(id_equipment_type,name,quantity) values
(1, 'Гантели 10 кг', 10),
(2, 'Брусья для отжиманий', 5),
(3, 'Тренажер для ног "Легс-Пресс"', 2),
(4, 'Скакалки с пластиковыми ручками', 15),
(5, 'Эластичные ленты с разной жесткостью', 20);

insert into gyms_have_equipment values
(1,1),
(1,2),
(2,4),
(4,4),
(3,5);

insert into members_have_inbody_analyses values
(5,1),
(4,2),
(3,4),
(2,4),
(1,5);

insert into members_have_visits_history values
(1,1),
(2,2),
(3,3),
(4,4),
(5,5);

insert into activity_type(activity_name) values
('Йога'),
('Зумба'),
('Пилатес'),
('Бег'),
('Аэробика');

insert into staff_schedule(id_staff, club_name, date, shift) values
(1, 'Авиапарк', "2023-12-01", '1'),
(1, 'Авиапарк', "2023-12-02", '2'),
(2, 'Первомайский', '2023-12-01', '3'),
(3, 'Первомайский', '2023-12-02', '1'),
(3, 'Первомайский', '2023-12-01', '2');

insert into news(news_title,news_text) values
('Новый фитнесс клуб открыт', 'С радостью сообщаем, что у нас открылся новый фитнесс клуб!'),
('Второй фитнесс клуб открылся!', 'С радостью сообщаем, что у нас открылся второй фитнесс клуб!'),
('Третий фитнесс клуб открылся!', 'С радостью сообщаем, что у нас открылся третий фитнесс клуб!'),
('Четвертый фитнесс клуб открылся!', 'С радостью сообщаем, что у нас открылся четвертый фитнесс клуб!');

insert into clubs_have_news (id_news, club_name) values
(1,'Авиапарк'),
(2,'Аквамолл'),
(3,'Первомайский'),
(4,'София');

insert into equipment_statistics(id_activity,approaches,kilocalories) values
(1, 1, 300),
(2, 1, 500),
(3, 1, 300),
(4, 1, 700),
(5, 1, 500);

insert into feedback(username,feedback_text,feedback_date,rating) values
("JohnDoe", "Вы давно не ходили в зал", "2021-10-29", 5),
("JohnDoe", "Вы зарегистрировались на сайте", "2021-10-01", 5),
("JohnDoe", "Что-то с чем-то", "2021-10-15", 5);

insert into members_have_equipment_statistics values
(1,1),
(2,2),
(3,3);