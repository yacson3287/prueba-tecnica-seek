CREATE TABLE IF NOT EXISTS `users` (
  `id` serial PRIMARY KEY,
  `username` varchar(100) UNIQUE,
  `password` varchar(500),
  `role` varchar(500)
);

INSERT INTO users(username, password, `role`) VALUES( 'admin', '$2a$10$8It0HGXAFHgXLDG8QRBkV.KoC5c8gJH9Gcie7wrmwSRXWC7Q63lp2', 'ADMIN');
