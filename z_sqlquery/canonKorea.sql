--CREATE USER CANONKOREA IDENTIFIED BY CANONKOREA;
--GRANT CONNECT, RESOURCE TO CANONKOREA;
show user;

-- 댓글테이블
-- 댓글번호, 내용, 작성자, 작성날짜, 수정날짜, 사용여부
CREATE TABLE REPLY_TBL(
  REPLY_NO NUMBER PRIMARY KEY,
  REF_BOARD_NO NUMBER REFERENCES BOARD_TBL(BOARD_NO),
  REPLY_CONTENT VARCHAR2(500) NOT NULL,
  REPLY_WRITER VARCHAR2(60) NOT NULL,
  R_CREATE_DATE DATE DEFAULT SYSDATE,
  R_UPDATE_DATE DATE DEFAULT SYSDATE,
  UPDATE_YN CHAR(1) DEFAULT 'N',
  R_STATUS CHAR(1) DEFAULT 'Y'
);
DROP TABLE REPLY_TBL;

CREATE SEQUENCE SEQ_REPLY_NO;
DROP SEQUENCE SEQ_REPLY_NO;

SELECT * FROM USER_SEQUENCES;



-- 보드테이블
-- 보드번호, 제목, 내용, 작성자, 파일이름, 파일리네임,파일경로, 파일크기, 조회수, 작성날짜,수정날짜 ,사용여부(STATUS)
CREATE TABLE BOARD_TBL(
  BOARD_NO NUMBER PRIMARY KEY,
  BOARD_TITLE VARCHAR2(50) NOT NULL,
  BOARD_CONTENT VARCHAR2(2000) NOT NULL,
  BOARD_WRITER VARCHAR2(30) NOT NULL,
  BOARD_FILENAME VARCHAR2(100),
  BOARD_FILERENAME VARCHAR2(200),
  BOARD_FILEPATH VARCHAR2(300),
  BOARD_FILELENGTH NUMBER,
  BOARD_COUNT NUMBER,
  B_CREATE_DATE DATE DEFAULT SYSDATE,
  B_UPDATE_DATE DATE DEFAULT SYSDATE,
  B_STATUS CHAR(1) DEFAULT 'Y'
);
DROP TABLE BOARD_TBL;


CREATE SEQUENCE SEQ_BOARD_NO
START WITH 1
INCREMENT BY 1
MINVALUE 1
MAXVALUE 1000000
NOCYCLE
NOCACHE;

DROP SEQUENCE SEQ_BOARD_NO;





BEGIN
  FOR N IN 1..200
  LOOP
   INSERT INTO BOARD_TBL 
   VALUES(SEQ_BOARD_NO.NEXTVAL, '캐논공지사항'||N||'번째', '캐논공지사항내용'||N||'번째 입니다.~', 'admin',NULL, NULL, NULL, NULL, 0, DEFAULT,DEFAULT,DEFAULT);
 END LOOP;
  COMMIT;
END;
/
COMMIT;






-- 멤버테이블
CREATE TABLE MEMBER_TBL (
  MEMBER_ID VARCHAR2(100) PRIMARY KEY,
  MEMBER_PW VARCHAR2(100) NOT NULL,
  MEMBER_NAME VARCHAR2(50) NOT NULL,
  MEMBER_AGE NUMBER,
  MEMBER_GENDER VARCHAR2(10),
  MEMBER_EMAIL VARCHAR2(100),
  MEMBER_PHONE VARCHAR2(100),
  MEMBER_ADDRESS VARCHAR2(100),
  MEMBER_HOBBY VARCHAR2(50),
  MEMBER_DATE TIMESTAMP DEFAULT SYSTIMESTAMP,
  UPDATE_DATE TIMESTAMP DEFAULT SYSTIMESTAMP,
  MEMBER_YN VARCHAR2(1) DEFAULT 'Y'
);


SELECT * FROM MEMBER_TBL;
DROP TABLE MEMBER_TBL;

SELECT * FROM  MEMBER_TBL WHERE MEMBER_PW = '1' AND MEMBER_EMAIL = '1' 
AND MEMBER_PHONE= '1' AND MEMBER_ADDRESS= '1' AND MEMBER_HOBBY= '1' AND MEMBER_ID='1' AND MEMBER_YN = 'Y';

SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = '1' AND MEMBER_YN = 'Y';

-- 공지사항 테이블
-- 공지번호, 제목, 내용, 작성자, 작성날짜, 수정날짜, 파일이름1, 파일경로1, 파일크기1
CREATE TABLE NOTICE_TBL(
  NOTICE_NO NUMBER PRIMARY KEY,
  NOTICE_SUBJECT VARCHAR2(100) NOT NULL,
  NOTICE_CONTENT VARCHAR2(2000) NOT NULL,
  NOTICE_WRITER VARCHAR2(50) NOT NULL,
  N_CREATE_DATE TIMESTAMP DEFAULT SYSTIMESTAMP,
  N_UPDATE_DATE TIMESTAMP DEFAULT SYSTIMESTAMP,
  NOTICE_FILENAME VARCHAR2(50),
  NOTICE_FILERENAME VARCHAR2(50),
  NOTICE_FILEPATH VARCHAR2(500),
  NOTICE_FILELENGTH NUMBER
);
CREATE SEQUENCE SEQ_NOTICE_NO;

DROP TABLE NOTICE_TBL;
DROP SEQUENCE SEQ_NOTICE_NO;

COMMIT;
-- 공지사항 파일 테이블
-- 공지번호, 파일이름, 파일경로, 파일크기

BEGIN
  FOR N IN 1..216
  LOOP
    INSERT INTO NOTICE_TBL 
    VALUES(SEQ_NOTICE_NO.NEXTVAL, '캐논공지사항'||N||'번째', '캐논공지사항내용'||N||'번째 입니다.~', 'admin', DEFAULT, DEFAULT, NULL, NULL, NULL, NULL);
  END LOOP;
  COMMIT;
END;
/


SELECT * FROM NOTICE_TBL;
SELECT COUNT(*) FROM NOTICE_TBL;

SELECT COUNT(*) FROM NOTICE_TBL WHERE NOTICE_WRITER LIKE '%admin%' 
OR NOTICE_SUBJECT LIKE '%공지사항%' 
OR NOTICE_CONTENT LIKE '%공지사항내용';
SELECT COUNT(*) FROM NOTICE_TBL WHERE NOTICE_WRITER LIKE '%일용자%'; 



SELECT * FROM NOTICE_TBL WHERE NOTICE_WRITER LIKE '%일용자%';






















