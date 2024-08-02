CREATE SEQUENCE FOOD_SEQUENCE
INCREMENT BY 1
START WITH 1
MINVALUE 1
MAXVALUE 99999
NOCYCLE
NOCACHE
NOORDER;

CREATE TABLE ORDERS (
    ORDERID NUMBER NOT NULL PRIMARY KEY,
    ID VARCHAR2(255),
    CODE NUMBER(10) NOT NULL,
    QUANTITY number,
    TOTALPRICE number, 
    ORDERDATE DATE
);


CREATE TABLE FOOD_ACCOUNT (
ID VARCHAR2(30) NOT NULL PRIMARY KEY, --회원_아이디
PWD VARCHAR2(50) NOT NULL,    -- 회원_비밀번호
NAME VARCHAR2(15), -- 회원_이름
CODE NUMBER(10) NOT NULL -- 회원_코드
);

CREATE TABLE RESTAURANT (
CODE NUMBER(10) NOT NULL PRIMARY KEY , -- 식당_코드
NAME VARCHAR2(30) NOT NULL,  -- 식당_이름
PNUMBER VARCHAR2(30), -- 식당_전화번호(010-1111-1111)
ADDRESS VARCHAR2(100), -- 식당_ 주소
KIND NUMBER(10) -- 식당_종류(1. 한식당, 2. 중식당, 3. 양식당, 4. 양식당)
);

CREATE TABLE FOODMENU ( 
CODE NUMBER(10) NOT NULL PRIMARY KEY  , -- 푸드_코드
NAME VARCHAR2(30) NOT NULL, --푸드_네임
PRICE NUMBER(30), --푸드_가격
KIND NUMBER(10) --푸드_종류 구분(1. 한식, 2. 중식, 3. 양식, 4. 양식)
);