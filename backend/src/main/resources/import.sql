INSERT INTO department(name) VALUES ('Informatique');
INSERT INTO department(name) VALUES ('Mathématiques');
INSERT INTO department(name) VALUES ('Bio');

INSERT INTO room (name,capacity,department_id) VALUES ('1A',120,1);
INSERT INTO room (name,capacity,department_id) VALUES ('2A',120,1);
INSERT INTO room (name,capacity,department_id) VALUES ('3B',120,1);
INSERT INTO room (name,capacity,department_id) VALUES ('4C',120,1);
INSERT INTO room (name,capacity,department_id) VALUES ('5C',120,1);
INSERT INTO room (name,capacity,department_id) VALUES ('6C',120,1);
INSERT INTO room (name,capacity,department_id) VALUES ('7C',120,1);
INSERT INTO room (name,capacity,department_id) VALUES ('8C',120,1);
INSERT INTO room (name,capacity,department_id) VALUES ('9E',120,1);
INSERT INTO room (name,capacity,department_id) VALUES ('10E',120,1);
INSERT INTO room (name,capacity,department_id) VALUES ('11E',120,1);
INSERT INTO room (name,capacity,department_id) VALUES ('12E',120,1);
INSERT INTO room (name,capacity,department_id) VALUES ('13E',120,1);
INSERT INTO room (name,capacity,department_id) VALUES ('2035',40,1);
INSERT INTO room (name,capacity,department_id) VALUES ('2036',40,1);
INSERT INTO room (name,capacity,department_id) VALUES ('2005',20,1);
INSERT INTO room (name,capacity,department_id) VALUES ('2006',20,1);
INSERT INTO room (name,capacity,department_id) VALUES ('2007',20,1);
INSERT INTO room (name,capacity,department_id) VALUES ('2008',20,1);
INSERT INTO room (name,capacity,department_id) VALUES ('027C',40,1);
INSERT INTO room (name,capacity,department_id) VALUES ('125C',40,1);
INSERT INTO room (name,capacity,department_id) VALUES ('123C',40,1);

INSERT INTO room_room_types (room_id,room_types) VALUES (1,'CM');
INSERT INTO room_room_types (room_id,room_types) VALUES (2,'CM');
INSERT INTO room_room_types (room_id,room_types) VALUES (3,'CM');
INSERT INTO room_room_types (room_id,room_types) VALUES (4,'CM');
INSERT INTO room_room_types (room_id,room_types) VALUES (5,'CM');
INSERT INTO room_room_types (room_id,room_types) VALUES (6,'CM');
INSERT INTO room_room_types (room_id,room_types) VALUES (7,'CM');
INSERT INTO room_room_types (room_id,room_types) VALUES (8,'CM');
INSERT INTO room_room_types (room_id,room_types) VALUES (9,'CM');
INSERT INTO room_room_types (room_id,room_types) VALUES (10,'CM');
INSERT INTO room_room_types (room_id,room_types) VALUES (11,'CM');
INSERT INTO room_room_types (room_id,room_types) VALUES (12,'CM');
INSERT INTO room_room_types (room_id,room_types) VALUES (13,'CM');
INSERT INTO room_room_types (room_id,room_types) VALUES (14,'TD');
INSERT INTO room_room_types (room_id,room_types) VALUES (15,'TD');
INSERT INTO room_room_types (room_id,room_types) VALUES (16,'TP');
INSERT INTO room_room_types (room_id,room_types) VALUES (17,'TP');
INSERT INTO room_room_types (room_id,room_types) VALUES (18,'TP');
INSERT INTO room_room_types (room_id,room_types) VALUES (19,'TP');
INSERT INTO room_room_types (room_id,room_types) VALUES (20,'TD');
INSERT INTO room_room_types (room_id,room_types) VALUES (21,'TD');
INSERT INTO room_room_types (room_id,room_types) VALUES (22,'TD');


INSERT INTO degree(name) VALUES ('Licence 1');
INSERT INTO degree(name) VALUES ('Licence 2');
INSERT INTO degree(name) VALUES ('Licence 3');
INSERT INTO degree(name) VALUES ('Master 1');
INSERT INTO degree(name) VALUES ('Master 2');

INSERT INTO major(name) VALUES ('GENIAL');
INSERT INTO major(name) VALUES ('DATA');
INSERT INTO major(name) VALUES ('IMPAIR');
INSERT INTO major(name) VALUES ('Informatique');

INSERT INTO major_degree(degree_id,major_id) VALUES (4,1);
INSERT INTO major_degree(degree_id,major_id) VALUES (5,1);
INSERT INTO major_degree(degree_id,major_id) VALUES (4,2);
INSERT INTO major_degree(degree_id,major_id) VALUES (5,2);
INSERT INTO major_degree(degree_id,major_id) VALUES (4,3);
INSERT INTO major_degree(degree_id,major_id) VALUES (5,3);
INSERT INTO major_degree(degree_id,major_id) VALUES (1,4);
INSERT INTO major_degree(degree_id,major_id) VALUES (2,4);
INSERT INTO major_degree(degree_id,major_id) VALUES (3,4);

INSERT INTO course(name, color, degree_id) VALUES ('Algorithmique','DD33DD',1);
INSERT INTO course(name, color, degree_id) VALUES ('Base de données avancée', 'DE0000', 2);
INSERT INTO course(name, color, degree_id) VALUES ('Génie Logiciel', '000FDE', 3);
INSERT INTO course(name, color, degree_id) VALUES ('Droit de l informatique', '0A4000', 1);
INSERT INTO course(name, color, degree_id) VALUES ('Anglais', '000000', 2);
INSERT INTO course(name, color, degree_id) VALUES ('Composants mobiles', '0000E0', 2);
INSERT INTO course(name, color, degree_id) VALUES ('Langage a objets avancé', '7E0000', 3);
INSERT INTO course(name, color, degree_id) VALUES ('Protocoles réseaux', '000DE0', 2);
INSERT INTO course(name, color, degree_id) VALUES ('Sécurité', '000000', 2);
INSERT INTO course(name, color, degree_id) VALUES ('Initiation a la programmation', '000000', 2);
INSERT INTO course(name, color, degree_id) VALUES ('Internet et outils', '000000', 2);
INSERT INTO course(name, color, degree_id) VALUES ('Concepts informatiques', '000000', 2);
INSERT INTO course(name, color, degree_id) VALUES ('Programmation orientée objets', '000000', 2);
INSERT INTO course(name, color, degree_id) VALUES ('Langage C', '000000', 2);
INSERT INTO course(name, color, degree_id) VALUES ('Outils logiques', '000000', 2);
INSERT INTO course(name, color, degree_id) VALUES ('Mathématiques', '000000', 2);
INSERT INTO course(name, color, degree_id) VALUES ('Mathématiques discrètes', '000000', 1);
INSERT INTO course(name, color, degree_id) VALUES ('Compléments POO', '000000', 2);
INSERT INTO course(name, color, degree_id) VALUES ('Programmation fonctionelle', '000000', 2);
INSERT INTO course(name, color, degree_id) VALUES ('Base de données', '000000', 2);


INSERT INTO major_course(major_id,course_id) VALUES (1,1);
INSERT INTO major_course(major_id,course_id) VALUES (2,2);
INSERT INTO major_course(major_id,course_id) VALUES (2,3);
INSERT INTO major_course(major_id,course_id) VALUES (1,4);
INSERT INTO major_course(major_id,course_id) VALUES (2,5);
INSERT INTO major_course(major_id,course_id) VALUES (1,3);
INSERT INTO major_course(major_id,course_id) VALUES (2,4);
INSERT INTO major_course(major_id,course_id) VALUES (2,1);
INSERT INTO major_course(major_id,course_id) VALUES (3,4);


INSERT INTO users (last_name,first_name,email,password,role) VALUES ('Admin','UFR','admin@u-paris.fr','oui','ADMIN');
INSERT INTO users (last_name,first_name,email,password,role) VALUES ('Pierre','Dupont','dupont@u-paris.fr','oui','PROFESSOR');
INSERT INTO users (last_name,first_name,email,password,role) VALUES ('Jean','Dupond','dupond@u-paris.fr','oui','PROFESSOR');
INSERT INTO users (last_name,first_name,email,password,role) VALUES ('Philippe','Loiseau','loiseau@u-paris.fr','oui','PROFESSOR');
INSERT INTO users (last_name,first_name,email,password,role) VALUES ('Henri','Duval','duval@u-paris.fr','oui','PROFESSOR');
INSERT INTO users (last_name,first_name,email,password,role) VALUES ('Cloé','Vignac','vignac@u-paris.fr','oui','PROFESSOR');
INSERT INTO users (last_name,first_name,email,password,role) VALUES ('Laura','Delarue','larue@u-paris.fr','oui','PROFESSOR');
INSERT INTO users (last_name,first_name,email,password,role) VALUES ('Emmanuel','Macron','macron@u-paris.fr','oui','PROFESSOR');


INSERT INTO professor(id) VALUES (2);
INSERT INTO professor(id) VALUES (3);
INSERT INTO professor(id) VALUES (4);
INSERT INTO professor(id) VALUES (5);
INSERT INTO professor(id) VALUES (6);
INSERT INTO professor(id) VALUES (7);
INSERT INTO professor(id) VALUES (8);


INSERT INTO course_group (course_id,group_id,duration,size,room_type) VALUES (1,1,7200000000000,80,'CM');
INSERT INTO course_group (course_id,group_id,duration,size,room_type) VALUES (1,2,7200000000000,25,'TD');
INSERT INTO course_group (course_id,group_id,duration,size,room_type) VALUES (1,3,7200000000000,30,'TD');
INSERT INTO course_group (course_id,group_id,duration,size,room_type) VALUES (1,4,7200000000000,25,'TD');
INSERT INTO course_group (course_id,group_id,duration,size,room_type) VALUES (2,1,7200000000000,80,'CM');
INSERT INTO course_group (course_id,group_id,duration,size,room_type) VALUES (2,2,7200000000000,25,'TD');
INSERT INTO course_group (course_id,group_id,duration,size,room_type) VALUES (2,3,7200000000000,30,'TD');
INSERT INTO course_group (course_id,group_id,duration,size,room_type) VALUES (2,4,7200000000000,25,'TD');
INSERT INTO course_group (course_id,group_id,duration,size,room_type) VALUES (3,1,7200000000000,80,'CM');
INSERT INTO course_group (course_id,group_id,duration,size,room_type) VALUES (3,2,7200000000000,80,'TD');
INSERT INTO course_group (course_id,group_id,duration,size,room_type) VALUES (4,1,7200000000000,80,'CM');
INSERT INTO course_group (course_id,group_id,duration,size,room_type) VALUES (5,1,7200000000000,20,'CM');
INSERT INTO course_group (course_id,group_id,duration,size,room_type) VALUES (5,2,7200000000000,20,'CM');

INSERT INTO professor_course (professor_id,course_id) VALUES (2,1);
INSERT INTO professor_course (professor_id,course_id) VALUES (3,1);
INSERT INTO professor_course (professor_id,course_id) VALUES (4,1);
INSERT INTO professor_course (professor_id,course_id) VALUES (2,2);
INSERT INTO professor_course (professor_id,course_id) VALUES (3,2);
INSERT INTO professor_course (professor_id,course_id) VALUES (4,2);
INSERT INTO professor_course (professor_id,course_id) VALUES (5,3);
INSERT INTO professor_course (professor_id,course_id) VALUES (6,4);
INSERT INTO professor_course (professor_id,course_id) VALUES (7,5);
INSERT INTO professor_course (professor_id,course_id) VALUES (8,5);

