use cegeka_db;

-- Weather Observation Station 7
drop table if exists station;
create table station( id bigint(20) unsigned not null auto_increment primary key,
					city varchar(21),
                    state varchar(21),
                    lat_n bigint(20),
                    long_w bigint(20));
insert into station values (1, 'Sandy Hook', 'CT', 72,148);
insert into station values (2, 'Loma Mar', 'CA', 49,131);
insert into station values (3, 'Weldona', 'CO', 33, 58);
insert into station values (4, 'Weldona', 'CO', 33, 58);
insert into station values (5, 'Lakota', 'IA', 56, 92);

select distinct city from station where city like '%a'
or city like '%e' or city like '%i' or city like '%o' or city like '%u';

select distinct city from station where city rlike '[aeiou]$';

-- Employee Salaries
drop table if exists employee;
create table employee( employee_id bigint(20) unsigned not null auto_increment primary key,
					name varchar(21),
                    months bigint(20),
                    salary bigint(20));
insert into employee values(1, 'Rose', 15, 1968);
insert into employee values(2, 'Angela', 1, 3443);
insert into employee values(3, 'Frank', 17, 1608);
insert into employee values(4, 'Michael', 6, 2017);
insert into employee values(5, 'Todd', 5, 3396);
select name from employee where salary > 2000 and months < 10 order by employee_id;
 
 -- Japan Population
drop table if exists city;
create table city( id bigint(20) unsigned not null auto_increment primary key,
					name varchar(21),
                    countrycode varchar(3),
                    district varchar(20),
                    population bigint(20));
insert into city values(1, 'Rotterdam','NLD','Zuid-Holland', 593321);
insert into city values(2, 'Tokuyama', 'JPN', 'Yamaguchi', 107078);
insert into city values(3, 'Sayama', 'JPN', 'Saitama', 162472);

select sum(population) from city where countrycode = 'JPN';

-- The Report
drop table if exists students;
create table students( id bigint(20) unsigned not null auto_increment primary key,
					name varchar(21),
                    marks varchar(21));
                    
insert into students values(1,'Julia',88);
insert into students values(2,'Samantha',68);
insert into students values(3,'Maria',99);
insert into students values(4,'Scarlet',78);
insert into students values(5,'Ashley',63);
insert into students values(6,'Jane',81);

                    
drop table if exists grades;
create table grades( grade bigint(20) unsigned not null auto_increment primary key,
					min_mark int(21),
                    max_mark int(21));
insert into grades values(1,0,9);
insert into grades values(2,10,19);
insert into grades values(3,20,29);
insert into grades values(4,30,39);
insert into grades values(5,40,49);
insert into grades values(6,50,59);
insert into grades values(7,60,69);
insert into grades values(8,70,79);
insert into grades values(9,80,89);
insert into grades values(10,90,100);

select (case g.grade >=8 when true then s.name else null end), g.grade, s.marks
		from students s inner join grades g
        on s.marks between min_mark and max_mark
        order by g.grade desc, s.name, ifnull(s.name, s.marks) desc;

-- Top Competitors
drop table if exists hackers;
create table hackers( hacker_id bigint(20) unsigned not null auto_increment primary key,
					name varchar(21));
                    
insert into hackers values(5580,'Rose');
insert into hackers values(8439,'Angela');
insert into hackers values(27205,'Frank');
insert into hackers values(52243,'Patrick');
insert into hackers values(90411,'Joe');

drop table if exists difficulty;
create table difficulty( difficulty_level int(20) unsigned not null auto_increment primary key,
					score int(21));
                    
insert into difficulty values(1,20);
insert into difficulty values(2,30);
insert into difficulty values(3,40);
insert into difficulty values(4,60);
insert into difficulty values(5,80);
insert into difficulty values(6,100);
insert into difficulty values(7,120);

drop table if exists challenges;
create table challenges( challenge_id bigint(20) unsigned not null auto_increment primary key,
					hacker_id bigint(20) unsigned not null ,
                    difficulty_level int(20),
                    foreign key (hacker_id) references hackers(hacker_id));
                    
insert into challenges values(36566,5580,7);
insert into challenges values(21089,27205,1);
insert into challenges values(66730,52243,6);
insert into challenges values(71055,52243,2);

drop table if exists submissions;
create table submissions( submission_id bigint(20) unsigned not null auto_increment primary key,
					hacker_id bigint(20) unsigned not null,
                    challenge_id bigint(20) unsigned not null,
                    score bigint(20),
                    foreign key (hacker_id) references hackers(hacker_id),
                    foreign key (challenge_id) references challenges(challenge_id));

insert into submissions values(97397,90411,66730,100);
insert into submissions values(97431,90411,71055,30);
insert into submissions values(523,5580,71055,4);

select h.hacker_id, h.name from submissions s
inner join challenges c on s.challenge_id = c.challenge_id
inner join difficulty d on c.difficulty_level = d.difficulty_level
inner join hackers h on h.hacker_id = s.hacker_id
where s.score = d.score and c.difficulty_level = d.difficulty_level
group by h.hacker_id, h.name having count(s.hacker_id)>1
order by count(s.hacker_id) desc, h.hacker_id asc;

-- 15 Days of Learning SQL
SET FOREIGN_KEY_CHECKS = 0;
drop table if exists hackers;
SET FOREIGN_KEY_CHECKS = 1;
create table hackers( hacker_id bigint(20) unsigned not null auto_increment primary key,
					name varchar(21));
                    
insert into hackers values(15758,'Rose');
insert into hackers values(20703,'Angela');
insert into hackers values(36396,'Frank');
insert into hackers values(38289,'Patrick');

SET FOREIGN_KEY_CHECKS = 0;
drop table if exists submissions;
SET FOREIGN_KEY_CHECKS = 1;
create table submissions( submission_date date ,
					submission_id bigint(20) unsigned not null,
					hacker_id bigint(20) unsigned not null,
                    score bigint(20),
                    foreign key (hacker_id) references hackers(hacker_id));

insert into submissions values('2016-03-02',34928,20703,0);
insert into submissions values('2016-03-01',8494,20703,0);
insert into submissions values('2016-03-03',45440,20703,0);
insert into submissions values('2016-03-04',50344,20703,0);
insert into submissions values('2016-03-05',72852,20703,0);

insert into submissions values('2016-03-01',30173,36396,70);
insert into submissions values('2016-03-03',49050,36396,70);
insert into submissions values('2016-03-05',82439,36396,10);
insert into submissions values('2016-03-05',90006,36396,40);

select submission_date ,( select count(distinct hacker_id)  from submissions s2  
where s2.submission_date = s1.submission_date and 
(select count(distinct s3.submission_date) 
from submissions s3 where s3.hacker_id = s2.hacker_id and  
s3.submission_date < s1.submission_date) = dateDIFF(s1.submission_date , '2016-03-01')) as counts ,
(select hacker_id  from submissions s2 
where s2.submission_date = s1.submission_date group by hacker_id 
order by count(submission_id) desc , hacker_id limit 1) as hacker,
(select name from hackers where hacker_id = hacker) as Names from 
(select distinct submission_date from submissions) s1
group by submission_date order by submission_date asc;



