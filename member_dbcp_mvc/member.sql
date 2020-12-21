create table member1(
	userid VARCHAR2(15) primary key,
	password VARCHAR2(20) not null,
	name NVARCHAR2(10) not null,
	gender VARCHAR2(2) not null,
	email VARCHAR2(50) not null
);

insert into MEMBER1 values('hong123', 'hong123', '홍길동', '남', 'hong123@naver.com');

select * from member1;
select * from member1;