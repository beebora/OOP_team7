실행 방법에 대해서는 최종결과 보고서의 9쪽 사용 방법에 나와 있습니다.


그래도 간략히 설명드리면 윈도우XP 환경에서 작동 합니다.

서버와 클라이언트 모두 고정 IP 이거나,  공유기를 쓰는 환경에서는 서버/클라이언트 모두가 같은 공유기에 물려 있는 환경에서 상호 접속이 가능합니다.



 ecplice, JDK, MySQL -JDBC driver가 있어야 하고

MySQL 5.0(윈도우용) 이 설치되어 있어야 합니다.

그리고 MySQL 5.0에 커맨드에 접속해서 아래 쿼리를 통해 사전 테이블을 작성해야 합니다.


=============================
set names euckr; 

create database term;

use term;

create table member(id varchar(20),name varchar(20), pass varchar(20), primary key(id)) ENGINE=MyISAM DEFAULT CHARSET=utf8;

create table buddy(id varchar(20),budi varchar(20), ip varchar(20),primary key(id,budi)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table match_msg(id varchar(20) ,sex BOOL, birth smallint, star tinyint,height tinyint unsigned, face tinyint, style tinyint, habi tinyint, local tinyint, primary key(id));

create table d_day(year varchar(4), month varchar(2), day varchar(2), hour varchar(5), title varchar(100))  ENGINE=InnoDB DEFAULT CHARSET=utf8;

=================== 




그리고 서버 파일인 MultiServer.java 파일의 28Line에서 MySQL의 비밀번호를 설정해야 합니다.

DriverManager.getConnection("jdbc:mysql://localhost", "root","6408");

여기서 6408이라고 된 부분을 해당 PC에 설치된 MySQL의 비밀번호로 변경해야 합니다.



