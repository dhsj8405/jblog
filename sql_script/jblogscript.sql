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

delete from category where no = '12';


-- 카테고리 리스트 ( 포스트수 포함)
select c.no, c.name, c.`desc`, c.blog_id ,p.post_count
  from category c LEFT JOIN
  (select count(*) as post_count, category_no from post
  group by category_no) p ON  
  p.category_no =c.no
  where c.blog_id = 'dhsj8405';
  
  -- 카테고리 select(카테고리 이름입력했을때 비교하기)
  select no,name,`desc` as description ,blog_id as blogId from category
  where category.name = '미분류'
  and blog_id = 'dhsj8405';
select * from category;  
  
  			select no,name,`desc` as description ,blog_id as blogId 
			  from category 
			 where name = '미분류'
			   and blog_id =  'dhsj8405';
  
  
-- 카테고리 출력 ( 포스트수 x)
select c.no,c.name,  c.`desc`, c.blog_id  from category c, post p
where c.blog_id = 'dhsj8405'
and  c.no = p.category_no;

-- 카테고리 추가
select * from category;
insert into category values(null, 'inserttest01' , 'inserttest01', 'dhsj8405' );



select * from post;
		   
select c.no, c.name, c.`desc`, c.blog_id ,p.post_count
  from category c LEFT JOIN
  (select count(*) as post_count, category_no from post
  group by category_no) p ON  
  p.category_no =c.no;

-- 카테고리 삭제 (포스트없이)
delete from post where category_no = '4';
delete from category where no = '27' and category.name != '미분류';
select * from category;




-- 카테고리 삭제(포스트 포함 구현중)
delete from p, c
USING category as c LEFT JOIN post as p on c.no = p.category_no
where c.no = '9';

delete c, p from category c LEFT JOIN post p ON c.no = p.category_no where c.no ='9';

-- 테스트 데이터 추가

-- 포스트 추가 (마지막 :카테고리 넘버)
select * from post;
insert into post values(null, 'inserttest01포스트타이틀' ,'inserttest01포스트본문', now(),'9');
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
insert into category values(null, '미분류', '','babo') ;
insert into category values(null, '카테고리2', '본문2','dhsj8405') ;


-- 포스트 불러오기

select no, title, contents, reg_date as regDateTime, category_no as categoryNo from post 
where category_no ='7';

select p.no, p.title, p.contents, p.reg_date as regDateTime, p.category_no as categoryNo, c.name as categoryName from post p, category c 
where p.category_no ='7'
  and p.category_no = c.no
  and c.blog_id = 'ehdgus8405';

-- 포스트 삭제
delete from post where no = '1';

