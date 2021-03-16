create table productTBL(
	num number(8),
	category varchar2(20),
	name varchar2(50),
	content varchar2(3000),
	psize varchar2(10),
	color varchar2(20),
	amount number(8),
	price number(8),
	goods_date date default sysdate
);

alter table productTBL add constraint prod_seq primary key(num);

create sequence seq_productTBL;

select * from productTBL;

DROP TABLE productTBL;
DROP TABLE productTBL2;