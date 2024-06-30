CREATE TABLE IF NOT EXISTS  `candidates` (
  `id` serial PRIMARY KEY,
  `name` varchar(200),
  `last_name` varchar(200),
  `email` varchar(200),
  `age` integer,
  `salary_expected` integer,
  `gender` varchar(50),
  `create_at` datetime DEFAULT CURRENT_TIMESTAMP
);


INSERT INTO candidates
(name, last_name, email, age, salary_expected, gender) VALUES
('name candidate 1', 'last name candiodate 1', 'candidate1@email.com', 28, 1000, 'Masculino'),
('name candidate 2', 'last name candiodate 2', 'candidate2@email.com', 25, 1300, 'Masculino'),
('name candidate 3', 'last name candiodate 3', 'candidate3@email.com', 26, 1400, 'Femenino'),
('name candidate 4', 'last name candiodate 4', 'candidate4@email.com', 22, 1100, 'Masculino'),
('name candidate 5', 'last name candiodate 5', 'candidate5@email.com', 32, 1200, 'Femenino'),
('name candidate 6', 'last name candiodate 6', 'candidate6@email.com', 37, 1700, 'Masculino');

