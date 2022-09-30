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
  `releaseDate` DATE NOT NULL,
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
  
  INSERT INTO actor (name, surname, premiereDate) VALUES ("Mateus", "Santos", "2022/02/03");
  INSERT INTO actor (name, surname, premiereDate) VALUES ("Joao", "Pedro", "2022/02/03");
  INSERT INTO agerating (description) VALUES ("Menores de 18 anos");
  INSERT INTO gender (description) VALUES ("Ação");
  INSERT INTO dvd (title, releaseYear, releaseDate, duration, mainActorFK, supportingActorFk, genderFK, ageRatingFK)
  VALUES ("A casa", 2022, "2022/03/03", 120, 1,2,1,1);
  
  SELECT * FROM actor;
  SELECT * FROM gender;
  SELECT * FROM agerating;
  SELECT * FROM dvd;
  
  SELECT
		d.id dvdId, 
		d.title dvdTitle, 
		d.releaseYear dvdReleaseYear,
		d.releaseDate dvdReleaseDate,
		d.duration dvdDuration, 
		d.mainActorFK dvdMainActorFk, 
		d.supportingActorFK dvdSupportingActorFk, 
		a.id mainActor_id, 
		a.id supportingActor_id, 
		a.name mainActor, 
		a.name supportingActor, 
		g.id gender_id, 
		g.description genderDescription, 
		ag.id ageClassification_id, 
		ag.description ageClassificationDescription 
	FROM
		dvd d,
		actor a,
		gender g,
		ageRating ag
    WHERE
		d.mainActorFK = a.id AND 
		d.supportingActorFK = a.id AND 
		d.genderFK= g.id AND
		d.ageRatingFK = ag.id;
    
    SELECT 
		d.id , 
        d.title, 
        d.releaseYear, 
        d.releaseDate, 
        d.duration,
        d.mainActorFK, 
        d.supportingActorFK, 
        d.genderFK, 
        d.ageRatingFK,
        g.id as genderId,
        g.description as genderDescription,
        ag.id as ageRatingId,
        ag.description as ageRatingDescription,
			(SELECT id from actor where actor.id = mainActorFK) as mainActorId,
			(SELECT name from actor where actor.id = mainActorFK) as mainActorName,
			(SELECT surname from actor where actor.id = mainActorFK) as mainActorSurname,
			(SELECT premiereDate from actor where actor.id = mainActorFK) as mainActorPremiereDate,
			(SELECT id from actor where actor.id = supportingActorFK) as supportingActorId,
			(SELECT name from actor where actor.id = supportingActorFK) as supportingActorName,
			(SELECT surname from actor where actor.id = supportingActorFK) as supportingActorSurname,
			(SELECT premiereDate from actor where actor.id = supportingActorFK) as supportingActorPremiereDate
    FROM dvd d, gender g, ageRating ag
    WHERE g.id = d.genderFK AND ag.id = d.ageRatingFK
   
    
    