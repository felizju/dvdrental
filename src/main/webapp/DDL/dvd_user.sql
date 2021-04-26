
-- User 도메인 테이블 설계
CREATE SEQUENCE seq_dvd_user;

CREATE TABLE dvd_user (
    user_number NUMBER(10),
    user_name VARCHAR2(10) NOT NULL,
    phone_number VARCHAR2(13),
    total_paying NUMBER(10) DEFAULT 0,
    grade VARCHAR2(10) DEFAULT 'BRONZE',
    CONSTRAINT pk_user_number PRIMARY KEY (user_number)
);

INSERT INTO dvd_user(user_number, user_name, phone_number)
VALUES (seq_dvd_user.nextval, '둘리', '010-2391-1170');

INSERT INTO dvd_user(user_number, user_name, phone_number)
VALUES (seq_dvd_user.nextval, '고길동', '010-1234-4450');

COMMIT;

SELECT * FROM dvd_user;