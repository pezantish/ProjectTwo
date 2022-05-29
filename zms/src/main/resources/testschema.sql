DROP TABLE IF EXISTS animal CASCADE;

CREATE TABLE IF NOT EXISTS animal(
	id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    species VARCHAR(25) NOT NULL,
    age INT NOT NULL,
    enclosureId VARCHAR(3) NOT NULL,
    PRIMARY KEY(`id`)
);

--CREATE TABLE IF NOT EXISTS `animals`(
--	`id` INT NOT NULL AUTO_INCREMENT,
    -- `enclosure_id` INT NOT NULL,
--    `enclosure_id` CHAR(3) NOT NULL,
--    `name` VARCHAR(20),
--    `species` VARCHAR(40) NOT NULL,
--    `age` INT NOT NULL,
--    `start_date` DATE NOT NULL DEFAULT "2020-04-04",
--    PRIMARY KEY(`id`),
--   FOREIGN KEY(`enclosure_id`) REFERENCES `enclosures`(`id`)
--);