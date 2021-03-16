create table shop(
	goods_num number(10,0),
	goods_category varchar2(50) not null,
	goods_name varchar2(200) not null,
	goods_price number(8) not null,
	goods_color varchar2(200) not null,
	goods_size varchar2(200) not null,
	goods_content varchar2(500) not null,
	goods_date date default sysdate
);

alter table shop add constraint pk_shop primary key(goods_num);

create sequence seq_shop;

select * from shop;