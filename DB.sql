# DB 생성
DROP DATABASE IF EXISTS SB_AM;
CREATE DATABASE SB_AM;

# DB 선택 
USE SB_AM;

# 게시물 테이블 생성
CREATE TABLE article(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    title CHAR(100) NOT NULL,
    `body` TEXT NOT NULL
);

# 게시물 테스트 데이터 생성

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목1',
`body` = '내용1';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목2',
`body` = '내용2!';
 
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목3',
`body` = '내용3!';

SELECT LAST_INSERT_ID(); 
 
SELECT * FROM article ORDER BY id DESC;

--
# 멤버 테이블 생성
CREATE TABLE `member`(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    loginId CHAR(20) NOT NULL,
    loginPw CHAR(60) NOT NULL,
    `authLevel` SMALLINT(2) UNSIGNED DEFAULT 3 COMMENT '권한 레벨 (3=일반,7=관리자)',
    `name` CHAR(20) NOT NULL, 
    nickname CHAR(20) NOT NULL,
    cellphoneNum CHAR(20) NOT NULL,
    email CHAR(50) NOT NULL,
    delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '탈퇴여부 (0=탈퇴 전,1=탈퇴 후)',
    delDate DATETIME COMMENT '탈퇴날짜'
);

# 멤버 데이터 생성 (관리자)
INSERT INTO `member`
SET regDate = NOW(),
    updateDate = NOW(),
    loginId = 'admin1',
    loginPw = 'admin1',
    `authLevel` = 7,
    `name` = '김관리',
    nickname = '관리자',
    cellphoneNum = '010-0000-0000',
    email = 'inane09@gmail.com';
    
# 멤버 데이터 생성 (일반)
INSERT INTO `member`
SET regDate = NOW(),
    updateDate = NOW(),
    loginId = 'test1',
    loginPw = 'test1',
    `name` = '김회원',
    nickname = '사용자1',
    cellphoneNum = '010-1234-5678',
    email = 'inane09@gmail.com';
    
INSERT INTO `member`
SET regDate = NOW(),
    updateDate = NOW(),
    loginId = 'test2',
    loginPw = 'test2',
    `name` = '최회원',
    nickname = '사용자2',
    cellphoneNum = '010-5678-1234',
    email = 'inane09@gmail.com';
    
        
SELECT * FROM `member`;


#게시물 테이블에 memberId 칼럼 추가
ALTER TABLE article ADD COLUMN memberId INT(10) UNSIGNED NOT NULL AFTER updateDate;

# memberId 추가
UPDATE article
SET memberId = 1
WHERE memberId = 0;


SELECT * FROM article ORDER BY id DESC;