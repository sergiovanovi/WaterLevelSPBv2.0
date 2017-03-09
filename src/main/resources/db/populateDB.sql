USE wlspb;
DELETE FROM user_roles;
DELETE FROM meters;
DELETE FROM users;

-- user
INSERT INTO users (name, email, password, min, max, util)
VALUES ('User', 'sergiovanovi@mail.ru', '123123', -5, 5, 0);

-- admin
INSERT INTO users (name, email, password, min, max, util)
VALUES ('Admin', 'inbox@sergiovanovi.com', '123456', -10, 10, 0);

INSERT INTO user_roles (email, role) VALUES
  ('sergiovanovi@mail.ru', 'ROLE_USER'),
  ('inbox@sergiovanovi.com', 'ROLE_ADMIN'),
  ('inbox@sergiovanovi.com', 'ROLE_USER');

INSERT INTO meters (level, date_time) VALUES
  ('0', '2017-03-1 10:00:00'),
  ('12.5', '2017-03-1 18:00:00'),
  ('35.6', '2017-03-2 11:00:00'),
  ('0', '2017-03-2 19:00:00'),
  ('-12.0', '2017-03-3 12:00:00'),
  ('-41.9', '2017-03-3 20:00:00'),
  ('0', '2017-03-4 13:00:00');