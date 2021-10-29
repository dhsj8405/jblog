desc blog;
delete from user;
delete from blog;


select * from user;
select * from blog;






insert into user values('test','test이름','1234',now());


insert into blog values('title', 'logo',  
						(select id
						   from user
						  where id = 'dhsj8405')) ;
                        



insert into blog values('', '', 'dhsj8405');