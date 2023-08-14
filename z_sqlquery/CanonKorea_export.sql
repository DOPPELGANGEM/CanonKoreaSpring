--------------------------------------------------------
--  파일이 생성됨 - 월요일-8월-14-2023   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table MEMBER_TBL
--------------------------------------------------------

  CREATE TABLE "CANONKOREA"."MEMBER_TBL" 
   (	"MEMBER_ID" VARCHAR2(100 BYTE), 
	"MEMBER_PW" VARCHAR2(100 BYTE), 
	"MEMBER_NAME" VARCHAR2(50 BYTE), 
	"MEMBER_AGE" NUMBER, 
	"MEMBER_GENDER" VARCHAR2(10 BYTE), 
	"MEMBER_EMAIL" VARCHAR2(100 BYTE), 
	"MEMBER_PHONE" VARCHAR2(100 BYTE), 
	"MEMBER_ADDRESS" VARCHAR2(100 BYTE), 
	"MEMBER_HOBBY" VARCHAR2(50 BYTE), 
	"MEMBER_DATE" TIMESTAMP (6) DEFAULT SYSTIMESTAMP, 
	"UPDATE_DATE" TIMESTAMP (6) DEFAULT SYSTIMESTAMP, 
	"MEMBER_YN" VARCHAR2(1 BYTE) DEFAULT 'Y'
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
REM INSERTING into CANONKOREA.MEMBER_TBL
SET DEFINE OFF;
Insert into CANONKOREA.MEMBER_TBL (MEMBER_ID,MEMBER_PW,MEMBER_NAME,MEMBER_AGE,MEMBER_GENDER,MEMBER_EMAIL,MEMBER_PHONE,MEMBER_ADDRESS,MEMBER_HOBBY,MEMBER_DATE,UPDATE_DATE,MEMBER_YN) values ('khuser01','pass01','일용자',11,'M','khuser01@kh.com','01011111111','서울시 중구 ','축구',to_timestamp('23/08/13 11:47:52.515000000','RR/MM/DD HH24:MI:SSXFF'),to_timestamp('23/08/13 11:47:52.515000000','RR/MM/DD HH24:MI:SSXFF'),'Y');
Insert into CANONKOREA.MEMBER_TBL (MEMBER_ID,MEMBER_PW,MEMBER_NAME,MEMBER_AGE,MEMBER_GENDER,MEMBER_EMAIL,MEMBER_PHONE,MEMBER_ADDRESS,MEMBER_HOBBY,MEMBER_DATE,UPDATE_DATE,MEMBER_YN) values ('khuser02','pass02','이용자',22,'F','khuser02@kh.com','010222222222','서울시 서대문구','물놀이',to_timestamp('23/08/13 11:52:53.444000000','RR/MM/DD HH24:MI:SSXFF'),to_timestamp('23/08/13 11:52:53.444000000','RR/MM/DD HH24:MI:SSXFF'),'Y');
Insert into CANONKOREA.MEMBER_TBL (MEMBER_ID,MEMBER_PW,MEMBER_NAME,MEMBER_AGE,MEMBER_GENDER,MEMBER_EMAIL,MEMBER_PHONE,MEMBER_ADDRESS,MEMBER_HOBBY,MEMBER_DATE,UPDATE_DATE,MEMBER_YN) values ('khuser03','pass03','삼용자',33,'M','khuser03@kh.com','01033333333','경기도 군포시','체스',to_timestamp('23/08/13 15:13:50.283000000','RR/MM/DD HH24:MI:SSXFF'),to_timestamp('23/08/13 15:13:50.283000000','RR/MM/DD HH24:MI:SSXFF'),'Y');
Insert into CANONKOREA.MEMBER_TBL (MEMBER_ID,MEMBER_PW,MEMBER_NAME,MEMBER_AGE,MEMBER_GENDER,MEMBER_EMAIL,MEMBER_PHONE,MEMBER_ADDRESS,MEMBER_HOBBY,MEMBER_DATE,UPDATE_DATE,MEMBER_YN) values ('khuser04','pass04','사용자',44,'M','khuser04@kh.com','01044444444','서울시 유리동','사물놀이',to_timestamp('23/08/13 20:16:23.227000000','RR/MM/DD HH24:MI:SSXFF'),to_timestamp('23/08/13 20:16:23.227000000','RR/MM/DD HH24:MI:SSXFF'),'Y');
Insert into CANONKOREA.MEMBER_TBL (MEMBER_ID,MEMBER_PW,MEMBER_NAME,MEMBER_AGE,MEMBER_GENDER,MEMBER_EMAIL,MEMBER_PHONE,MEMBER_ADDRESS,MEMBER_HOBBY,MEMBER_DATE,UPDATE_DATE,MEMBER_YN) values ('khuser05','pass05','오용자',55,'M','khuser05@kh.com','01055555555','서울시 홍제동','라면끌이기',to_timestamp('23/08/14 15:48:39.708000000','RR/MM/DD HH24:MI:SSXFF'),to_timestamp('23/08/14 15:48:39.708000000','RR/MM/DD HH24:MI:SSXFF'),'Y');
--------------------------------------------------------
--  DDL for Index SYS_C007624
--------------------------------------------------------

  CREATE UNIQUE INDEX "CANONKOREA"."SYS_C007624" ON "CANONKOREA"."MEMBER_TBL" ("MEMBER_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  Constraints for Table MEMBER_TBL
--------------------------------------------------------

  ALTER TABLE "CANONKOREA"."MEMBER_TBL" MODIFY ("MEMBER_PW" NOT NULL ENABLE);
  ALTER TABLE "CANONKOREA"."MEMBER_TBL" MODIFY ("MEMBER_NAME" NOT NULL ENABLE);
  ALTER TABLE "CANONKOREA"."MEMBER_TBL" ADD PRIMARY KEY ("MEMBER_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
