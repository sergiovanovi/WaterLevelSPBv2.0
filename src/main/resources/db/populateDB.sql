USE wlspb;
DELETE FROM user_roles;
DELETE FROM meters;
DELETE FROM users;

-- user
INSERT INTO users (name, email, password, min, max, message)
VALUES ('User', 'user@yandex.ru', '123123', -40, 30, 'greetings from user');

-- admin
INSERT INTO users (name, email, password, min, max, message)
VALUES ('Admin', 'admin@gmail.com', '123456', -10, 10, 'greetings from admin');

INSERT INTO user_roles (email, role) VALUES
  ('user@yandex.ru', 'ROLE_USER'),
  ('admin@gmail.com', 'ROLE_ADMIN'),
  ('admin@gmail.com', 'ROLE_USER');

INSERT INTO meters (level, date_time) VALUES
  ('0','2017-03-1 10:00:00'),
  ('12','2017-03-1 18:00:00'),
  ('35','2017-03-2 11:00:00'),
  ('0','2017-03-2 19:00:00'),
  ('-12','2017-03-3 12:00:00'),
  ('-41','2017-03-3 20:00:00'),
  ('0','2017-03-4 13:00:00');