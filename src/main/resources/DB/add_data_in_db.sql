create user 'springroot'@'%' identified by 'spring';
grant all on fitness_club_db.* to 'springroot'@'%';

insert into achievements values
(1, 'Вы подтянулись 20 раз за подход', 'Подтягальник', 'https://img1.pnghut.com/15/4/15/dX9fhyUbwr/neck-bodybuilding-sportart-shoulder-hand.jpg'),
(2, 'Пробежать 2км за подход', 'Начинающий бегун', 'https://i.pinimg.com/originals/ce/85/ec/ce85ec0dda46a2d173c9d703676dd7fa.png'),
(3, 'Пробежать 5км за подход', 'Марафонец', 'https://flyclipart.com/thumb2/learn-258380.png'),
(4, 'Пробежать 10км за подход', 'Беги, Форрест, беги! ', 'https://flyclipart.com/thumb2/home-23427.png');

insert into membership_role values
(1, 'Light'),
(2, 'Smart'),
(3, 'Infinity');

insert into clubs values
('София', 'Сиреневый бул., 31 (этаж 3)'),
('Авиапарк', 'Ходынский бул., 4 (этаж 3)'),
('Аквамолл', 'ул. Южнобутовская, д.2,'),
('Первомайский', 'Первомайская, д. 93\20, (этаж 2)');

insert into members values
(1, 1, 'София', 'John', 'Doe', '1234567890', 'johndoe@example.com', '1990-01-01', '2021-10-01', '2021-11-01', 1),
(2, 2, 'София', 'Jane', 'Smith', '2345678901', 'janesmith@example.com', '1995-05-05', '2021-09-01', NULL, 0),
(3, 1, 'Авиапарк', 'Mike', 'Johnson', '3456789012', 'mikejohnson@example.com', '1985-12-31', '2021-08-01', '2021-09-01', 1),
(4, 2, 'Первомайский', 'Emily', 'Brown', '4567890123', 'emilybrown@example.com', '2000-07-15', '2021-07-01',  NULL, 0),
(5, 3, 'Аквамолл', 'David', 'Lee', '5678901234', 'davidlee@example.com', '1998-03-20', '2021-06-01', '2021-07-01', 1);

insert into members_have_achievements values
(1, 1, '2021-10-01'),
(2, 3, '2021-09-15'),
(3, 2, '2021-08-20'),
(4, 1, '2021-07-10'),
(5, 4, '2021-06-05');