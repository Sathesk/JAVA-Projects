create database course_reg_sym;
use course_reg_sym;
show tables;
describe course_registry;
describe course;
select * from course;
select * from course_registry;
insert into course_registry(course_name,email_id,name) values
("Python for ML", "saif@gmail.com","saif");
insert into course values
("100","Java Essentials",4,"Balaji"),
("101","Python for ML",6,"Karthick"),
("102","Spring Boot",8,"Balaji");