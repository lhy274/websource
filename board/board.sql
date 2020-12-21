create table board(
	bno number(8), --글번호
	name nvarchar2(10) not null, --작성자
	password varchar2(15) not null, --비밀번호
	title nvarchar2(50) not null, --글제목
	content nvarchar2(1000) not null, --내용
	attach nvarchar2(100), --파일첨부
	re_ref number(8) not null, --답변글 작성시 참조되는 글 번호
	re_lev number(8) not null, -- 답변글의 레벨
	re_seq number(8) not null, -- 답변글의 순서
	readcount number(8) default 0, -- 조회수
	regdate date default sysdate); -- 작성날짜
	
alter table board add constraint pk_board primary key(bno);

create SEQUENCE board_seq;