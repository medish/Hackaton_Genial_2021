INSERT INTO department(id,name) VALUES (0,'Informatique');
INSERT INTO department(id,name) VALUES (1,'Mathématiques');
INSERT INTO department(id,name) VALUES (2,'Bio');

INSERT INTO room_type(id,name) VALUES (1,'CM');
INSERT INTO room_type(id,name) VALUES (2,'TD');
INSERT INTO room_type(id,name) VALUES (3,'TP');

INSERT INTO room (id,room_department_id,capacity,room_type_id) VALUES ('1A',0,120,1);
INSERT INTO room (id,room_department_id,capacity,room_type_id) VALUES ('2A',0,120,1);
INSERT INTO room (id,room_department_id,capacity,room_type_id) VALUES ('3B',0,120,1);
INSERT INTO room (id,room_department_id,capacity,room_type_id) VALUES ('4C',0,120,1);
INSERT INTO room (id,room_department_id,capacity,room_type_id) VALUES ('5C',0,120,1);
INSERT INTO room (id,room_department_id,capacity,room_type_id) VALUES ('6C',0,120,1);
INSERT INTO room (id,room_department_id,capacity,room_type_id) VALUES ('7C',0,120,1);
INSERT INTO room (id,room_department_id,capacity,room_type_id) VALUES ('8C',0,120,1);
INSERT INTO room (id,room_department_id,capacity,room_type_id) VALUES ('9E',0,120,1);
INSERT INTO room (id,room_department_id,capacity,room_type_id) VALUES ('10E',0,120,1);
INSERT INTO room (id,room_department_id,capacity,room_type_id) VALUES ('11E',0,120,1);
INSERT INTO room (id,room_department_id,capacity,room_type_id) VALUES ('12E',0,120,1);
INSERT INTO room (id,room_department_id,capacity,room_type_id) VALUES ('13E',0,120,1);
INSERT INTO room (id,room_department_id,capacity,room_type_id) VALUES ('2035',0,40,2);
INSERT INTO room (id,room_department_id,capacity,room_type_id) VALUES ('2036',0,40,2);
INSERT INTO room (id,room_department_id,capacity,room_type_id) VALUES ('2005',0,20,3);
INSERT INTO room (id,room_department_id,capacity,room_type_id) VALUES ('2006',0,20,3);
INSERT INTO room (id,room_department_id,capacity,room_type_id) VALUES ('2007',0,20,3);
INSERT INTO room (id,room_department_id,capacity,room_type_id) VALUES ('2008',0,20,3);
INSERT INTO room (id,room_department_id,capacity,room_type_id) VALUES ('027C',0,40,2);
INSERT INTO room (id,room_department_id,capacity,room_type_id) VALUES ('125C',0,40,2);
INSERT INTO room (id,room_department_id,capacity,room_type_id) VALUES ('123C',0,40,2);


INSERT INTO degree(id,name) VALUES (1,'Master GENIAL M1');
INSERT INTO degree(id,name) VALUES (2,'Master GENIAL M2');
INSERT INTO degree(id,name) VALUES (3,'Master DATA M1');
INSERT INTO degree(id,name) VALUES (4,'Master DATA M2');
INSERT INTO degree(id,name) VALUES (5,'Master IMPAIR M1');
INSERT INTO degree(id,name) VALUES (6,'Master IMPAIR M2');
INSERT INTO degree(id,name) VALUES (7,'Licence 1 Informatique');
INSERT INTO degree(id,name) VALUES (8,'Licence 2 Informatique');
INSERT INTO degree(id,name) VALUES (9,'Licence 3 Informatique');

INSERT INTO course(id,name) VALUES (1,'Algorithmique');
INSERT INTO course(id,name) VALUES (2,'Base de données avancée');
INSERT INTO course(id,name) VALUES (3,'Génie Logiciel');
INSERT INTO course(id,name) VALUES (4,'Droit de l informatique');
INSERT INTO course(id,name) VALUES (5,'Anglais M1');
INSERT INTO course(id,name) VALUES (6,'Composants mobiles');
INSERT INTO course(id,name) VALUES (7,'Langage a objets avancé');
INSERT INTO course(id,name) VALUES (8,'Protocoles réseaux');
INSERT INTO course(id,name) VALUES (9,'Sécurité');
INSERT INTO course(id,name) VALUES (10,'Initiation a la programmation');
INSERT INTO course(id,name) VALUES (11,'Internet et outils');
INSERT INTO course(id,name) VALUES (12,'Concepts informatiques');
INSERT INTO course(id,name) VALUES (13,'Anglais L1');
INSERT INTO course(id,name) VALUES (14,'Programmation orientée objets');
INSERT INTO course(id,name) VALUES (15,'Langage C');
INSERT INTO course(id,name) VALUES (16,'Outils logiques');
INSERT INTO course(id,name) VALUES (17,'Mathématiques 4');
INSERT INTO course(id,name) VALUES (18,'Mathématiques discrètes');
INSERT INTO course(id,name) VALUES (19,'Compléments POO');
INSERT INTO course(id,name) VALUES (20,'Programmation fonctionelle');
INSERT INTO course(id,name) VALUES (21,'Base de données');

INSERT INTO course_degree(course_id,degree_id) VALUES (1,1);
INSERT INTO course_degree(course_id,degree_id) VALUES (2,1);
INSERT INTO course_degree(course_id,degree_id) VALUES (3,1);
INSERT INTO course_degree(course_id,degree_id) VALUES (4,1);
INSERT INTO course_degree(course_id,degree_id) VALUES (5,1);
INSERT INTO course_degree(course_id,degree_id) VALUES (6,1);
INSERT INTO course_degree(course_id,degree_id) VALUES (7,1);
INSERT INTO course_degree(course_id,degree_id) VALUES (8,1);
INSERT INTO course_degree(course_id,degree_id) VALUES (9,1);
INSERT INTO course_degree(course_id,degree_id) VALUES (1,3);
INSERT INTO course_degree(course_id,degree_id) VALUES (2,3);
INSERT INTO course_degree(course_id,degree_id) VALUES (3,3);
INSERT INTO course_degree(course_id,degree_id) VALUES (4,3);
INSERT INTO course_degree(course_id,degree_id) VALUES (5,3);
INSERT INTO course_degree(course_id,degree_id) VALUES (6,3);
INSERT INTO course_degree(course_id,degree_id) VALUES (7,3);
INSERT INTO course_degree(course_id,degree_id) VALUES (8,3);
INSERT INTO course_degree(course_id,degree_id) VALUES (1,5);
INSERT INTO course_degree(course_id,degree_id) VALUES (2,5);
INSERT INTO course_degree(course_id,degree_id) VALUES (3,5);
INSERT INTO course_degree(course_id,degree_id) VALUES (4,5);
INSERT INTO course_degree(course_id,degree_id) VALUES (5,5);
INSERT INTO course_degree(course_id,degree_id) VALUES (6,5);
INSERT INTO course_degree(course_id,degree_id) VALUES (7,5);
INSERT INTO course_degree(course_id,degree_id) VALUES (8,5);
INSERT INTO course_degree(course_id,degree_id) VALUES (12,7);
INSERT INTO course_degree(course_id,degree_id) VALUES (11,7);
INSERT INTO course_degree(course_id,degree_id) VALUES (10,7);
INSERT INTO course_degree(course_id,degree_id) VALUES (13,7);
INSERT INTO course_degree(course_id,degree_id) VALUES (14,8);
INSERT INTO course_degree(course_id,degree_id) VALUES (15,8);
INSERT INTO course_degree(course_id,degree_id) VALUES (16,8);
INSERT INTO course_degree(course_id,degree_id) VALUES (17,8);
INSERT INTO course_degree(course_id,degree_id) VALUES (18,9);
INSERT INTO course_degree(course_id,degree_id) VALUES (19,9);
INSERT INTO course_degree(course_id,degree_id) VALUES (20,9);
INSERT INTO course_degree(course_id,degree_id) VALUES (21,9);

INSERT INTO customer (id,name,first_name,email,is_admin,password) VALUES ('admin','Admin','UFR','admin@u-paris.fr',true,'motdepasse');
INSERT INTO customer (id,name,first_name,email,is_admin,password) VALUES ('prof1','Pierre','Dupont','dupont@u-paris.fr',false,'motdepasse');
INSERT INTO customer (id,name,first_name,email,is_admin,password) VALUES ('prof2','Jean','Dupond','dupond@u-paris.fr',false,'motdepasse');
INSERT INTO customer (id,name,first_name,email,is_admin,password) VALUES ('prof3','Philippe','Loiseau','loiseau@u-paris.fr',false,'motdepasse');
INSERT INTO customer (id,name,first_name,email,is_admin,password) VALUES ('prof4','Henri','Duval','duval@u-paris.fr',false,'motdepasse');
INSERT INTO customer (id,name,first_name,email,is_admin,password) VALUES ('prof5','Cloé','Vignac','vignac@u-paris.fr',false,'motdepasse');
INSERT INTO customer (id,name,first_name,email,is_admin,password) VALUES ('prof6','Laura','Delarue','larue@u-paris.fr',false,'motdepasse');
INSERT INTO customer (id,name,first_name,email,is_admin,password) VALUES ('prof7','Emmanuel','Macron','macron@u-paris.fr',false,'motdepasse');


INSERT INTO professor(customer_id) VALUES ('prof1');
INSERT INTO professor(customer_id) VALUES ('prof2');
INSERT INTO professor(customer_id) VALUES ('prof3');
INSERT INTO professor(customer_id) VALUES ('prof4');
INSERT INTO professor(customer_id) VALUES ('prof5');
INSERT INTO professor(customer_id) VALUES ('prof6');
INSERT INTO professor(customer_id) VALUES ('prof7');


INSERT INTO lesson (lesson_id,duration,group_size,course_id,room_type_id) VALUES ('Cours Algorithmique',7200000000000,80,1,1);
INSERT INTO lesson (lesson_id,duration,group_size,course_id,room_type_id) VALUES ('TD Algorithmique G1',7200000000000,25,1,2);
INSERT INTO lesson (lesson_id,duration,group_size,course_id,room_type_id) VALUES ('TD Algorithmique G2',7200000000000,30,1,2);
INSERT INTO lesson (lesson_id,duration,group_size,course_id,room_type_id) VALUES ('TD Algorithmique G3',7200000000000,25,1,2);
INSERT INTO lesson (lesson_id,duration,group_size,course_id,room_type_id) VALUES ('Cours Base de données avancées',7200000000000,80,2,1);
INSERT INTO lesson (lesson_id,duration,group_size,course_id,room_type_id) VALUES ('TD Base de données avancées G1',7200000000000,25,2,2);
INSERT INTO lesson (lesson_id,duration,group_size,course_id,room_type_id) VALUES ('TD Base de données avancées G2',7200000000000,30,2,2);
INSERT INTO lesson (lesson_id,duration,group_size,course_id,room_type_id) VALUES ('TD Base de données avancées G3',7200000000000,25,2,2);
INSERT INTO lesson (lesson_id,duration,group_size,course_id,room_type_id) VALUES ('Cours Génie logiciel',7200000000000,80,3,1);
INSERT INTO lesson (lesson_id,duration,group_size,course_id,room_type_id) VALUES ('TD Génie logiciel',7200000000000,80,3,2);
INSERT INTO lesson (lesson_id,duration,group_size,course_id,room_type_id) VALUES ('Cours Droit de l informatique',7200000000000,80,4,1);
INSERT INTO lesson (lesson_id,duration,group_size,course_id,room_type_id) VALUES ('Anglais M1 G1',7200000000000,20,5,2);
INSERT INTO lesson (lesson_id,duration,group_size,course_id,room_type_id) VALUES ('Anglais M1 G2',7200000000000,20,5,2);

INSERT INTO lesson_professor (lesson_id,professor_id) VALUES ('Cours Algorithmique','prof1');
INSERT INTO lesson_professor (lesson_id,professor_id) VALUES ('TD Algorithmique G1','prof2');
INSERT INTO lesson_professor (lesson_id,professor_id) VALUES ('TD Algorithmique G2','prof1');
INSERT INTO lesson_professor (lesson_id,professor_id) VALUES ('TD Algorithmique G3','prof3')
INSERT INTO lesson_professor (lesson_id,professor_id) VALUES ('Cours Base de données avancées','prof2');
INSERT INTO lesson_professor (lesson_id,professor_id) VALUES ('TD Base de données avancées G1','prof1');
INSERT INTO lesson_professor (lesson_id,professor_id) VALUES ('TD Base de données avancées G2','prof2');
INSERT INTO lesson_professor (lesson_id,professor_id) VALUES ('TD Base de données avancées G3','prof3');
INSERT INTO lesson_professor (lesson_id,professor_id) VALUES ('Cours Génie logiciel','prof4');
INSERT INTO lesson_professor (lesson_id,professor_id) VALUES ('TD Génie logiciel','prof4');
INSERT INTO lesson_professor (lesson_id,professor_id) VALUES ('Cours Droit de l informatique','prof5');
INSERT INTO lesson_professor (lesson_id,professor_id) VALUES ('Anglais M1 G1','prof6');
INSERT INTO lesson_professor (lesson_id,professor_id) VALUES ('Anglais M1 G1','prof7');


