DROP TABLE IF EXISTS sys.users;
DROP TABLE IF EXISTS sys.meters;

CREATE TABLE `sys`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `min`INT NOT NULL,
  `max` INT NOT NULL,
  `message` VARCHAR(45) NOT NULL,
  `enadled` BIT(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC));

CREATE UNIQUE INDEX users_unique_id_email_idx ON sys.users (id, email);

CREATE TABLE `sys`.`meters` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `level` INT NOT NULL,
  `date_time` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`));

CREATE UNIQUE INDEX meters_unique_id_datetime_idx ON sys.meters (id, date_time);
