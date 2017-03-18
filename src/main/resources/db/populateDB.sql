USE wlspb;
DELETE FROM user_roles;
DELETE FROM meters;
DELETE FROM users;

-- user
INSERT INTO users (email, password, min, max, util, enabled)
VALUES ('sergiovanovi@mail.ru', '$2a$10$0P4JB2CNMEIodboE7xnnzOQ5.ngroEgHAWp0pSU7spFno8kHLlZ/i', -5, 5, 0, TRUE);

-- admin
INSERT INTO users (email, password, min, max, util, enabled)
VALUES ('inbox@sergiovanovi.com', '$2a$10$3A9Tu7JgdXWaOF.zmjZNje4XDj7Ngw9EyY1DhU8rwWL8huBY67LzW', -10, 10, 0, TRUE);

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