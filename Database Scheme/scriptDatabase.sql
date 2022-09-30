DROP DATABASE IF EXISTS locacao_dvds;
CREATE DATABASE IF NOT EXISTS locacao_dvds;
USE locacao_dvds;

CREATE TABLE `locacao_dvds`.`actor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `surname` VARCHAR(45) NULL,
  `premiereDate` DATE NULL,
  PRIMARY KEY (`id`));
  
  CREATE TABLE `locacao_dvds`.`dvd` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `releaseYear` VARCHAR(5) NULL,
  `mainActorFK` INT NOT NULL,
  `supportingActorFK` INT NOT NULL,
  `duration` INT NULL,
  `genderFK` INT NOT NULL,
  `ageRatingFK` INT NOT NULL,
  PRIMARY KEY (`id`));

  CREATE TABLE `locacao_dvds`.`gender` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));
  
  CREATE TABLE `locacao_dvds`.`agerating` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));