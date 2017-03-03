DROP DATABASE IF EXISTS wlspb;
CREATE DATABASE wlspb;

CREATE TABLE `wlspb`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `min`INT NOT NULL,
  `max` INT NOT NULL,
  `message` VARCHAR(45) NOT NULL,
  `enabled` BIT(1) NOT NULL DEFAULT TRUE ,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC));

CREATE UNIQUE INDEX users_unique_id_email_idx ON wlspb.users (`id`, `email`);

CREATE TABLE `wlspb`.`meters` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `level` INT NOT NULL,
  `date_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`));

CREATE UNIQUE INDEX meters_unique_id_datetime_idx ON wlspb.meters (`id`, `date_time`);


CREATE TABLE `wlspb`.`user_roles`(
  `email` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45),
  CONSTRAINT user_roles_idx UNIQUE (`email`, `role`),
  FOREIGN KEY (`email`) REFERENCES wlspb.users (`email`) ON DELETE CASCADE
);