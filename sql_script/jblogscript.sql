desc blog;
delete from user;
delete from blog;


select * from user;
select * from blog;
select * from category;


-- id 중복체크
select id,name,join_date from user where id = 'dhsj8405';

-- 로그인
select id,name,join_date as joinDate 
from user u
where u.id = 'dhsj8405'  
and u.password = '1234';





insert into user values('test','test이름','1234',now());


insert into blog values('title', 'logo',  
						(select id
						   from user
						  where id = 'dhsj8405')) ;
                        



insert into blog values('', '', 'dhsj8405');