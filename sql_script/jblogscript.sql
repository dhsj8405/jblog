desc blog;
delete from user;
delete from blog;
delete  from user where id = 'doolly@gmai.com';
delete  from blog where id = 'doolly@gmai.com';
delete from category where blog_id = 'doolly@gmai.com';



select * from user;
select * from blog;
select * from category;
select * from post;

select c.no, c.name, c.blog_Id, c.`desc` 
from category c 
 where blog_id = 'dhsj8405';
 
-- 블로그 타이틀 가져오기
	select title, logo, id as blogId 
		 from blog  
		 where id = 'dhsj8405';


-- 카테고리 리스트 가져오기
select * from category;
select * from post;

-- 카테고리 리스트 ( 포스트수 포함)
select c.no, c.name, c.`desc`, c.blog_id ,p.post_count
  from category c LEFT JOIN
  (select count(*) as post_count, category_no from post
  group by category_no) p ON  
  p.category_no =c.no
  where c.blog_id = 'dhsj8405';
  
-- 카테고리 출력 ( 포스트수 x)
select c.no,c.name,  c.`desc`, c.blog_id  from category c, post p
where c.blog_id = 'dhsj8405'
and  c.no = p.category_no;






insert into post values(null, '미분류포스트타이틀1' ,'미분류포스트본문1', now(),'1');
insert into post values(null, 'ehdgus8405post2title' ,'ehdgus8405post2contents', now(),'7');

insert into category values(null, 'ehdgus8405카테고리1', 'ehdgus8405본문1','ehdgus8405') ;

update user set  id = 'ehdgus8405' where name = '둘리';
update blog set  id = 'ehdgus8405' where id = 'doolly@gmai.com';
update blog set title = 'test02타이틀' ,logo ='teset02로고' where id = 'doolly@gmai.com';

select b.title, b.logo, b.id ,c.name as categoryName, c.desc as categoryDesc
from blog b ,category c
where b.id = 'dhsj8405'
and c.blog_id = b.id;

-- 계정추가
insert into user values('test','test이름','1234',now());
-- 블로그추가
insert into blog values('', '', 'dhsj8405');

-- 블로그 타이틀 로고추가
update blog set  title = 'test타이틀', logo = '/images/dolly.png'
where blog.id = 'dhsj8405';

-- id 중복체크
select id,name,join_date from user where id = 'dhsj8405';

-- 로그인
select id,name,join_date as joinDate 
from user u
where u.id = 'dhsj8405'  
and u.password = '1234';

-- 카테고리 추가
insert into category values(null, '카테고리1', '본문1','dhsj8405') ;
insert into category values(null, '카테고리2', '본문2','dhsj8405') ;







