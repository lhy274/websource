-- bookTBL
-- code 숫자 4 pk
-- title 문자 50
-- writer 문자 20
-- price 숫자 8

CREATE TABLE bookTBL(
	code number(4) PRIMARY KEY,
	title NVARCHAR2(50) not null,
	writer NVARCHAR2(20) not null,
	price number(8) not null
);

insert into bookTBL values(1001, '이것이 자바다', '신용균', 28000);
insert into bookTBL values(1002, '자바의 신', '강신용', 29000);
insert into bookTBL values(1003, '자바 1000제', '김용만', 27000);
insert into bookTBL values(1004, '오라클로 배우는 데이터베이스 입문', '김진수', 32000);

select * from bookTBL;






