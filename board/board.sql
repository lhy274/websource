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

select * from board;
select count(*) from board;

-- 더미 데이터(페이지 나누기)
INSERT INTO board(bno,name,password,title,content,re_ref,re_lev,re_seq)
(SELECT board_seq.nextval,name,password,title,content,board_seq.currval,re_lev,re_seq
FROM board);

-- 내림 차순
select * from board order by bno desc;
-- bno가장 마지막 행
select * from board where bno=(select max(bno) from board);


-- 가장 최신글의 댓글 달기
-- re_ref(그룹번호 : 원본글의 re_ref번호)
-- re_lev(원본글의 댓글인지 혹은 댓글의 댓글인지 : 원본글의 re_lev+1)
-- re_seq(댓글 순서 : 원본글의 re_seq+1)
insert into board(bno,name,password,title,content,attach,re_ref,re_lev,re_seq)
values(board_seq.nextval,'hong','1234','댓글연습1','댓글 연습중',null,3355,1,1);

-- 원본글과 댓글 가져오기
select bno,name,title,re_ref,re_lev,re_seq from BOARD 
where re_ref=3355 order by re_seq;

-- 댓글작업하기
-- 1. 기존 댓글의 re_seq 값 변경하기(기존댓글이 존재한다면 re_seq+1)
update board set re_seq = re_seq+1 where re_ref=3355 and re_seq > 0;

-- 원본글과 댓글 가져오기
select bno,name,title,re_ref,re_lev,re_seq from BOARD 
where re_ref=3355 order by re_seq;

-- 2. 댓글 삽입
-- 두번째 댓글
insert into board(bno,name,password,title,content,attach,re_ref,re_lev,re_seq)
values(board_seq.nextval,'kang','1234','댓글연습2','댓글 연습중',null,3355,1,1);

-- 3. 원본글과 댓글 가져오기
select bno,name,title,re_ref,re_lev,re_seq from BOARD 
where re_ref=3355 order by re_seq;


-- 댓들의 댓글 달기(원본글의 re_ref,re_seq,re_lev)
-- 댓글의 댓글 달기
-- 1. 기존 댓글의 re_seq 값 변경하기(기존댓글이 존재한다면 re_seq+1)
-- 원본글의 re_seq 보다 큰거
update board set re_seq = re_seq+1 where re_ref=3355 and re_seq > 1;
-- 2. 댓글 삽입
-- re_ref(그룹번호 : 원본글의 re_ref번호)
-- re_lev(원본글의 댓글인지 혹은 댓글의 댓글인지 : 원본글의 re_lev+1)
-- re_seq(댓글 순서 : 원본글의 re_seq+1)
insert into board(bno,name,password,title,content,attach,re_ref,re_lev,re_seq)
values(board_seq.nextval,'choi','1234','Re:댓글연습','댓글 연습중',null,3355,2,2);
-- 3. 원본글과 댓글 가져오기
select bno,name,title,re_ref,re_lev,re_seq from BOARD 
where re_ref=3355 order by re_seq;


-- 진심!!! 정신 똑바로 좀 차려라 뭐하는거야!!!!!!!!!!!!!!!11
DELETE FROM board;



-- 페이지 나누기


-- rownum : order by절과 사용할 때 order by 를 적용할 컬럼이 index 가 아니라면
--			제대로 결과를 만들어 주지 않음

-- bno : pk(자동으로 인덱스 처리)
select rownum,bno,title from board order by bno desc;
select rownum,bno,title from board order by re_seq desc, re_seq asc;

-- 출력행을 10개로 제한
select rownum,bno,title from board where rownum<=10 order by bno desc;
select rownum,bno,title from board where rownum<=10 order by re_seq desc, re_seq asc;

-- 인덱스 컬럼이 아닌 경우 해결
select rownum,bno,title
from(select bno,title from board where bno>0 order by re_ref desc, re_seq asc)
where rownum<=10;

-- 1 page => 가장 최신글 10개 가져오기
select bno,title
from(select rownum rnum,bno,title
	from(select bno,title 
		from board 
		where bno>0 order by re_ref desc, re_seq asc)
	where rownum<=10)
where rnum > 0;


-- 2 page => 가장 최신글 11~20 가져오기

select bno,title
from(select rownum rnum,bno,title
	from(select bno,title 
		from board 
		where bno>0 order by re_ref desc, re_seq asc)
	where rownum<=20)
where rnum > 10;

-- 10 : 한 페이지에 보여줄 값
-- 1 => 0, 10(1~10)    (1-1)*10,1*10
-- 2 => 10, 20(11~20)  (2-1)*10,2*10
-- 3 => 20, 30(21~30)  (3-1)*10,3*10



select bno,title,name,regdate,readcount,re_lev
from(select rownum rnum,bno,title,name,regdate,readcount,re_lev
	from(select bno,title,name,regdate,readcount,re_lev
		from board 
		where bno>0 order by re_ref desc, re_seq asc)
	where rownum<=20)
where rnum > 10;


--
--전체 행수
select count(*) from board;
--검색 조전에 맞는 전체 행수가 필요해
select count(*) from board where criteria=title and keyword='파이썬';


