-- 2021년 7월 
-- 
DROP TABLE reply;
DROP TABLE board;
DROP TABLE members;

DROP SEQUENCE reply_seq;
DROP SEQUENCE board_seq;

-- 회원 (Users)
CREATE TABLE members
(
	userid   varchar2(50) primary key,   	-- 사용자 아이디
	userpwd  varchar2(50) not null, 	-- 사용자 비밀번호
	username varchar2(50) not null,		-- 이름
	email    varchar2(100),			-- 이메일
	birth    date,				-- 생년월일
	gender   varchar2(20) default 'woman',	-- 성별
	phone    varchar2(20)			-- 전화번호
);


-- 게시판 (Board)
CREATE TABLE board
(
	boardnum number         primary key,	-- 게시글 번호
	userid   varchar2(50)   not null,	-- 작성자 아이디
	title    varchar2(200)  not null,	-- 글제목
	text     varchar2(4000) not null,	-- 글내용
	hitcount number default 0,		-- 조회수
	regdate  date default sysdate,		-- 등록일
	originalfile varchar2(300),		-- 첨부 파일명(원래이름)
	savedfile    varchar2(300)		-- 첨부 파일명(실제 파일에 저장된 이름)
);

CREATE SEQUENCE board_seq;

-- 댓글(Reply)
CREATE TABLE reply
(
	replynum  number primary key,		-- 댓글 번호
	boardnum  number not null references board(boardnum), -- 참조하는 본문글번호
	userid    varchar2(50)   not null,	-- 댓글 작성자 아이디
	replytext varchar2(1000) not null,	-- 댓글 내용
	regdate   date default sysdate		-- 댓글 작성일
);

CREATE SEQUENCE reply_seq;