DROP DATABASE IF EXISTS wlspb;
CREATE DATABASE wlspb;
USE wlspb;

CREATE TABLE users (
  id       INT         NOT NULL AUTO_INCREMENT,
  email    VARCHAR(128) NOT NULL,
  password VARCHAR(128) NOT NULL,
  min      DOUBLE      NOT NULL,
  max      DOUBLE      NOT NULL,
  util     INT         NOT NULL,
  enabled  BOOLEAN      NOT NULL DEFAULT FALSE,
  PRIMARY KEY (id),
  UNIQUE INDEX id_UNIQUE (id ASC),
  UNIQUE INDEX email_UNIQUE (email ASC)
);
CREATE UNIQUE INDEX users_unique_id_email_idx ON users (id, email);

CREATE TABLE meters (
  id        INT UNSIGNED NOT NULL AUTO_INCREMENT,
  level     DOUBLE       NOT NULL,
  date_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);
CREATE UNIQUE INDEX meters_unique_id_datetime_idx ON meters (id, date_time);


CREATE TABLE user_roles (
  id INT NOT NULL,
  role  VARCHAR(128),
  CONSTRAINT user_roles_idx UNIQUE (id, role),
  FOREIGN KEY (id) REFERENCES users (id) ON DELETE CASCADE
);