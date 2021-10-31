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

select no,name, blog_Id, `desc`,post_count
from category
 where blog_id = 'dhsj8405'
 and category.no = post.category_no;

select * from post,category
where post.category_no = category.no;


select * from post;
select count(*) from post
				where post.category_no = '1';                

insert into post values(null, '미분류포스트타이틀1' ,'미분류포스트본문1', now(),'1');
insert into post values(null, 'post2title' ,'post2contents', now(),'4');

insert into category values(null, '카테고리1', '본문1','dhsj8405') ;

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







