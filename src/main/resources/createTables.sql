CREATE TABLE station (
                id integer NOT NULL AUTO_INCREMENT,
               city varchar(21), 
                state varchar(2),
                 lat_n numeric ,
                long_w numeric ,
               PRIMARY KEY (id)
                );
                
CREATE TABLE employee (
                 employee_id integer NOT NULL AUTO_INCREMENT,
                  name varchar(21), 
                 months numeric ,
                  salary numeric ,
                  PRIMARY KEY (employee_id)
                );                
                
 CREATE TABLE city (
                  id integer NOT NULL AUTO_INCREMENT,
                  name varchar(17), 
                  countryCode varchar(3) ,
                  district varchar(20) ,
                  population numeric ,
                  PRIMARY KEY (id)
                );              
                
                
 CREATE TABLE student (
                  id integer NOT NULL AUTO_INCREMENT,
                  name varchar(30), 
                  marks integer ,
                  PRIMARY KEY (id)
                );             
                
                
CREATE TABLE grades (
                  grade integer NOT NULL AUTO_INCREMENT,
                  min_mark integer,
                max_mark integer ,
                  PRIMARY KEY (grade)
                );               
                
                
CREATE TABLE hackers (
                 hacker_id integer NOT NULL AUTO_INCREMENT,
                 name varchar(30),
                 PRIMARY KEY (hacker_id)
                );              


CREATE TABLE submissions (
                  submission_id integer NOT NULL AUTO_INCREMENT,
                 submission_date date,
                  hacker_id integer ,
                  score integer ,
                  PRIMARY KEY (submission_id) ,
                  KEY hacker_id (hacker_id),
                  CONSTRAINT hacker_id_fk FOREIGN KEY (hacker_id) REFERENCES hackers (hacker_id) 
                );                
                
                
 SET @@global.time_zone = '+00:00';
                SET @@session.time_zone = '+00:00';               