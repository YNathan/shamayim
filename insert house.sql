insert into shamayim.house (state, city, street, house_number, house_kind, number_of_rooms, number_of_living_rooms, number_of_kitchens, number_of_bedrooms, number_of_bathrooms, location_kind, comments) 
values ('Newark', 'N.J', '13th', '718', '1', 7, 1, 1, 2, 1, 2,  'comments');

insert into shamayim.house (state, city, street, house_number, house_kind, number_of_rooms, number_of_living_rooms, number_of_kitchens, number_of_bedrooms, number_of_bathrooms, location_kind, comments)
values ('Washington', 'Baltimor', 'Prince', '12', '1', 7, 1, 1, 2, 1, 2,  'אחלה של בית');

CREATE DATABASE `shamayim` /*!40100 DEFAULT CHARACTER SET utf8 */

CREATE TABLE `house` (
  `house_id` int(11) NOT NULL AUTO_INCREMENT,
  `state` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `house_number` int(11) DEFAULT NULL,
  `house_kind` int(11) DEFAULT NULL,
  `number_of_rooms` int(11) DEFAULT NULL,
  `number_of_living_rooms` int(11) DEFAULT NULL,
  `number_of_kitchens` int(11) DEFAULT NULL,
  `number_of_bedrooms` int(11) DEFAULT NULL,
  `number_of_bathrooms` double DEFAULT NULL,
  `location_kind` int(11) DEFAULT NULL,
  `comments` varchar(20000) DEFAULT NULL,
  `purchase_price` double DEFAULT NULL,
  `treatment_fees` double DEFAULT NULL,
  `renovation_fees` double DEFAULT NULL,
  `divers_fees` double DEFAULT NULL,
  PRIMARY KEY (`house_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8


CREATE TABLE `users` (
  `user_name` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `birthdate` varchar(45) NOT NULL,
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8