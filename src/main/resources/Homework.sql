#1
select distinct city from station where upper(substr(city,-1)) in ('A','E','I','O','U');

#2
SELECT name
FROM Employee
WHERE months < 10 
  AND salary > 2000;


#3
SELECT SUM(POPULATION) FROM CITY  WHERE COUNTRYCODE ='JPN';


#4
select (case when grade <8 THEN NULL ELSE name END) name, grade, marks
from
(select name, grade, marks
from students, grades
where marks between min_Mark and Max_Mark)
order by grade desc, name, decode(name,NULL,marks);

#6
select big_1.submission_date, big_1.hkr_cnt, big_2.hacker_id, h.name
from
(select submission_date, count(distinct hacker_id) as hkr_cnt
from 
(select s.*, dense_rank() over(order by submission_date) as date_rank, 
dense_rank() over(partition by hacker_id order by submission_date) as hacker_rank 
from submissions s ) a 
where date_rank = hacker_rank 
group by submission_date) big_1 
join 
(select submission_date,hacker_id, 
 rank() over(partition by submission_date order by sub_cnt desc, hacker_id) as max_rank 
from (select submission_date, hacker_id, count(*) as sub_cnt 
      from submissions 
      group by submission_date, hacker_id) b ) big_2
on big_1.submission_date = big_2.submission_date and big_2.max_rank = 1 
join hackers h on h.hacker_id = big_2.hacker_id 
order by 1;
