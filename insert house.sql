CREATE DATABASE `shamayim` /*!40100 DEFAULT CHARACTER SET utf8 */

drop table shamayim.house;
CREATE TABLE `house` (
  `house_id` int(11) NOT NULL AUTO_INCREMENT,
  `zip_code` int(11) DEFAULT NULL,
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
  `renovation_fees_for_sale` double DEFAULT NULL,
  `renovation_fees_for_renting` double DEFAULT NULL,
  `divers_fees` double DEFAULT NULL,
  `housecol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`house_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*
-- Query: SELECT * FROM shamayim.house
LIMIT 0, 1000

-- Date: 2016-12-31 17:56
*/
INSERT INTO `house` (`house_id`,`zip_code`,`state`,`city`,`street`,`house_number`,`house_kind`,`number_of_rooms`,`number_of_living_rooms`,`number_of_kitchens`,`number_of_bedrooms`,`number_of_bathrooms`,`location_kind`,`comments`,`purchase_price`,`treatment_fees`,`renovation_fees_for_sale`,`renovation_fees_for_renting`,`divers_fees`,`housecol`) VALUES (1,NULL,'Newark','N.J','Prince',283,1,7,1,1,3,2.5,1,'בית מאד גדול למשפחה ובמצב מצויין לאחר שיפוץ',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `house` (`house_id`,`zip_code`,`state`,`city`,`street`,`house_number`,`house_kind`,`number_of_rooms`,`number_of_living_rooms`,`number_of_kitchens`,`number_of_bedrooms`,`number_of_bathrooms`,`location_kind`,`comments`,`purchase_price`,`treatment_fees`,`renovation_fees_for_sale`,`renovation_fees_for_renting`,`divers_fees`,`housecol`) VALUES (2,NULL,'Newark','N.J','13th',718,1,7,1,1,2,1,2,'בית פינתי כניסה לכל דירה מרחב אחר בנוסף יש בייסמנט שנוכל לשפץ לעוד יחידה החנייה ברחוב קטן ושקט, אין ג\'ארז פרטי הוצאות לפי 2 אופציות',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `house` (`house_id`,`zip_code`,`state`,`city`,`street`,`house_number`,`house_kind`,`number_of_rooms`,`number_of_living_rooms`,`number_of_kitchens`,`number_of_bedrooms`,`number_of_bathrooms`,`location_kind`,`comments`,`purchase_price`,`treatment_fees`,`renovation_fees_for_sale`,`renovation_fees_for_renting`,`divers_fees`,`housecol`) VALUES (3,NULL,'israel','jerusalem','yirmiyahu',32,3,1,1,65,3,5,5,'nice area have fun',0,0,98,0,0,NULL);

CREATE TABLE `users` (
  `user_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_manager` int(11) NOT NULL DEFAULT '1',
  `permission_view` int(11) DEFAULT '1',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*
-- Query: SELECT * FROM shamayim.users
LIMIT 0, 1000

-- Date: 2016-12-31 17:39
*/
INSERT INTO `users` (`user_name`,`email`,`telephone`,`password`,`user_id`,`permission_manager`,`permission_view`) VALUES ('Y.Nathan','yaacovisraelnathan@gmail.com','525','a',1,0,0);


drop table shamayim.dictionary;
CREATE TABLE `dictionary` (
  `house_language` varchar(255) NOT NULL,
  `house_id` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `state` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `street` varchar(255) NOT NULL,
  `house_number` varchar(255) NOT NULL,
  `house_kind` varchar(255) NOT NULL,
  `number_of_rooms` varchar(255) NOT NULL,
  `number_of_living_rooms` varchar(255) NOT NULL,
  `number_of_kitchens` varchar(255) NOT NULL,
  `number_of_bedrooms` varchar(255) NOT NULL,
  `number_of_bathrooms` varchar(255) NOT NULL,
  `location_kind` varchar(255) NOT NULL,
  `score` varchar(255) NOT NULL,
  `comments` varchar(255) NOT NULL,
  `purchase_price` varchar(255) NOT NULL,
  `treatment_fees` varchar(255) NOT NULL,
  `renovation_fees_for_sale` varchar(255) NOT NULL,
  `renovation_fees_for_renting` varchar(255) NOT NULL,
  `divers_fees` varchar(255) NOT NULL,
  `general_house_details` varchar(255) NOT NULL,
  `financial_house_details` varchar(255) NOT NULL,
  `upload_house_files` varchar(255) DEFAULT NULL,
  `drag_or_drop_files_here` varchar(255) DEFAULT NULL,
  `house_table` varchar(255) DEFAULT NULL,
  `menu` varchar(255) DEFAULT NULL,
  `information` varchar(255) DEFAULT NULL,
  `house` varchar(255) DEFAULT NULL,
  `manage_houses` varchar(255) DEFAULT NULL,
  `new_house` varchar(255) DEFAULT NULL,
  `copyright` varchar(255) DEFAULT NULL,
  `wellcom_to_your_account` varchar(255) DEFAULT NULL,
  `select_a_house` varchar(255) DEFAULT NULL,
  `area_on_the_map` varchar(255) DEFAULT NULL,
  `houses_list` varchar(255) DEFAULT NULL,
  `save` varchar(255) DEFAULT NULL,
  `send_me_this_house_to_my_mail` varchar(255) DEFAULT NULL,
  `direction_html` varchar(255) DEFAULT 'ltr',
  `logout` varchar(255) DEFAULT 'Logout',
  `system_manager` varchar(255) DEFAULT 'System Manager',
  `upload_house_profile_picture` varchar(255) DEFAULT 'Upload House Profile Picture',
  `upload_house_pictures` varchar(255) DEFAULT 'Upload House Pictures',
  `back_to_the_catalog` varchar(255) DEFAULT 'Back To The Catalog',
  PRIMARY KEY (`house_language`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*
-- Query: SELECT * FROM shamayim.dictionary
LIMIT 0, 1000

-- Date: 2016-12-31 17:50
*/
INSERT INTO `dictionary` (`house_language`,`house_id`,`address`,`state`,`city`,`street`,`house_number`,`house_kind`,`number_of_rooms`,`number_of_living_rooms`,`number_of_kitchens`,`number_of_bedrooms`,`number_of_bathrooms`,`location_kind`,`score`,`comments`,`purchase_price`,`treatment_fees`,`renovation_fees_for_sale`,`renovation_fees_for_renting`,`divers_fees`,`general_house_details`,`financial_house_details`,`upload_house_files`,`drag_or_drop_files_here`,`house_table`,`menu`,`information`,`house`,`manage_houses`,`new_house`,`copyright`,`wellcom_to_your_account`,`select_a_house`,`area_on_the_map`,`houses_list`,`save`,`send_me_this_house_to_my_mail`,`direction_html`,`logout`,`system_manager`,`upload_house_profile_picture`,`upload_house_pictures`) VALUES ('English','Number Of House That Recording In The System','Address','State','City','Street','House Number','Kind Of House','Number Of Rooms','Number Of Living Rooms','Number Of Kitchens','Number Of Bedrooms','Number Of Bathrooms','Location Kind','Score','Comments','Purchase Price','Treatment Fees','Renovation Fees For Sale','Renovation Fees For Reinting','Divers Fees','General Details','Financial Details','Upload House Files','Drag & Drop Files Here','House Details Table','Menu','Information','House','Manage Houses','New House','About','Wellcom To Your Account','Select A House','Area On The Map','Houses List','Save','Send Me This House To My Mail','ltr','Logout','System Manager','Upload House Profile Picture','House Pictures');
INSERT INTO `dictionary` (`house_language`,`house_id`,`address`,`state`,`city`,`street`,`house_number`,`house_kind`,`number_of_rooms`,`number_of_living_rooms`,`number_of_kitchens`,`number_of_bedrooms`,`number_of_bathrooms`,`location_kind`,`score`,`comments`,`purchase_price`,`treatment_fees`,`renovation_fees_for_sale`,`renovation_fees_for_renting`,`divers_fees`,`general_house_details`,`financial_house_details`,`upload_house_files`,`drag_or_drop_files_here`,`house_table`,`menu`,`information`,`house`,`manage_houses`,`new_house`,`copyright`,`wellcom_to_your_account`,`select_a_house`,`area_on_the_map`,`houses_list`,`save`,`send_me_this_house_to_my_mail`,`direction_html`,`logout`,`system_manager`,`upload_house_profile_picture`,`upload_house_pictures`) VALUES ('Francais','Numero De Maison Enregistrer Dans Le System','Address','Pays','Ville','Rue','Numero De Maison','Style De Maison','Nombre De Chambres','Numbre De Salle A Manger','Numbre De cuisins','Nombre De Chambre A Cousher','Nombre De Chambre De Toilette','Environnment','Score','Commentaires','Prix Dachat','Depance De Traitment','Depance De Renovation Pour Vente','Depance De Renovation Pour Location','Autre Depance','Detailles Generals','Detailles Financier','Enregistrer Des Fichier Concernant La Maison','Tirez Ou Jeter Des Fichier Ici','Tableau détaillé de la maison','Menu Principal','Information','Maison','gerer les maisons','Nouvel Maison','A Propos','Bienvenu A Votre Compte','Choisissez Une Maison','Sur La Carte','List De Maisons','Sauvegarder','Envoi Moi Cette Maison Sur Ma Boite Mail','ltr','Se Déconnecter','Gestionnaire Du Système','Photo De Profile De La Maison','Photo De La Maison');
INSERT INTO `dictionary` (`house_language`,`house_id`,`address`,`state`,`city`,`street`,`house_number`,`house_kind`,`number_of_rooms`,`number_of_living_rooms`,`number_of_kitchens`,`number_of_bedrooms`,`number_of_bathrooms`,`location_kind`,`score`,`comments`,`purchase_price`,`treatment_fees`,`renovation_fees_for_sale`,`renovation_fees_for_renting`,`divers_fees`,`general_house_details`,`financial_house_details`,`upload_house_files`,`drag_or_drop_files_here`,`house_table`,`menu`,`information`,`house`,`manage_houses`,`new_house`,`copyright`,`wellcom_to_your_account`,`select_a_house`,`area_on_the_map`,`houses_list`,`save`,`send_me_this_house_to_my_mail`,`direction_html`,`logout`,`system_manager`,`upload_house_profile_picture`,`upload_house_pictures`) VALUES ('עברית','מספר רישום הבית במערכת','כתובת','מדינה','עיר','רחוב','מספר בית','סוג הבית','מספר חדרים','מספר חדרי סלון','מספר חדרי מטבח','מספר חדרי שינה','מספר חדרי שירותים/ מקלחת','מיקום','ציון','הערות','מחיר קניה','הוצאות טיפול','הוצאות שיפוץ למכירה','הוצאות שיפוץ להשכרה','הוצאות אחרות','נתונים כלליים','נתונים כלכליים','העלאת קבצים','גרור או זרוק קבצים לפה','טבלת נתוני הבית','תפריט','פרטים על משתמש','בית','רשימת כלל הבתים','בית חדש','אודות','ברוך הבא לחשבונך','בחר בית',' על המפה','רשימת הבתים','שמור','שלח לי בית זה למייל','rtl','צא','ניהול המשתמשים','תמונת פרופיל של הבית','תמונות הבית');


