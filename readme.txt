���� ����� ���ؼ��� ������� ������ 9�� ��� ����� ���� �ֽ��ϴ�.


�׷��� ������ ����帮�� ������XP ȯ�濡�� �۵� �մϴ�.

������ Ŭ���̾�Ʈ ��� ���� IP �̰ų�,  �����⸦ ���� ȯ�濡���� ����/Ŭ���̾�Ʈ ��ΰ� ���� �����⿡ ���� �ִ� ȯ�濡�� ��ȣ ������ �����մϴ�.



 ecplice, JDK, MySQL -JDBC driver�� �־�� �ϰ�

MySQL 5.0(�������) �� ��ġ�Ǿ� �־�� �մϴ�.

�׸��� MySQL 5.0�� Ŀ�ǵ忡 �����ؼ� �Ʒ� ������ ���� ���� ���̺��� �ۼ��ؾ� �մϴ�.


=============================
set names euckr; 

create database term;

use term;

create table member(id varchar(20),name varchar(20), pass varchar(20), primary key(id)) ENGINE=MyISAM DEFAULT CHARSET=utf8;

create table buddy(id varchar(20),budi varchar(20), ip varchar(20),primary key(id,budi)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table match_msg(id varchar(20) ,sex BOOL, birth smallint, star tinyint,height tinyint unsigned, face tinyint, style tinyint, habi tinyint, local tinyint, primary key(id));

create table d_day(year varchar(4), month varchar(2), day varchar(2), hour varchar(5), title varchar(100))  ENGINE=InnoDB DEFAULT CHARSET=utf8;

=================== 




�׸��� ���� ������ MultiServer.java ������ 28Line���� MySQL�� ��й�ȣ�� �����ؾ� �մϴ�.

DriverManager.getConnection("jdbc:mysql://localhost", "root","6408");

���⼭ 6408�̶�� �� �κ��� �ش� PC�� ��ġ�� MySQL�� ��й�ȣ�� �����ؾ� �մϴ�.



