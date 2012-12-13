package controller;

import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;

public class dbms {

	static dbms singleton;

	static public dbms getInstance(){
		if(singleton==null) singleton = new dbms();
		return singleton;
	}
	
	String SQL;
	Statement stmt;
	ResultSet rs;
	public class member
	{
		String id, name, pass, parent;
		boolean logon, is_dept;
		
	}
	private void init_class()
	{
		SQL = null;
		stmt = null;
		
	}
	public boolean create_table()
	{	
		init_class();

		SQL="create table member(id varchar(20),name varchar(20), pass varchar(20),  parent varchar(20), logon bool, is_dept bool, primary key(id), foregin key(parent) references member) ENGINE=MyISAM DEFAULT CHARSET=utf8	";
			
		try
		{
			stmt.executeUpdate(SQL);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}

		
	}
	public ArrayList<member> getList()
	{
		init_class();

		ArrayList<member> mem;
		mem = new ArrayList<member>();
		SQL="SELECT * FROM member";
		try
		{
			
			rs = stmt.executeQuery(SQL);
			while(rs.next())
			{
				member me = new member();
				me.id = rs.getString("id");
				me.is_dept = rs.getBoolean("is_dept");
				me.logon = rs.getBoolean("logon");
				me.name = rs.getString("name");
				me.parent = rs.getString("parent");
				me.pass = rs.getString("pass");
				mem.add(me);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return mem;
	}
	
	public boolean join(String name, String id, String pass, String dept){
		
		init_class();

		SQL="insert into term.member(id,name,pass,dept_name) values('"+id+"','"+name+"','"+pass+","+dept+"')";
		try
		{
			stmt.executeUpdate(SQL);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public boolean logout(String id){
		init_class();
		SQL = "update term.member set logon = 'false' where id = '"+id+"'";
		try
		{
			stmt.executeUpdate(SQL);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public boolean login(String id, String pass, String ip){
		init_class();			
		SQL="SELECT * FROM term.member where id='"+id+"' and pass='"+pass+"'";
		try
		{

			rs = stmt.executeQuery(SQL);
			if (stmt.execute(SQL)) 
			{
				rs = stmt.getResultSet();
			}
		} 
		catch(Exception ex) 
		{
			System.out.println(ex.toString());
			return false;
		}
			
		int i=0;
		try
		{
			while (rs.next())
			{
					i++;break;
			} 
		}
		catch(Exception ex)
		{
				System.out.println(ex.toString());
		}
		if(i==0)
		{
			return false;
		}
		else
		{
			SQL = "update term.member set logon = 'true' where id = '"+id+"'";
			try
			{
				stmt.executeUpdate(SQL);
				return true;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return false;
			}	
		}
	}
		
	public boolean getMember(String id)
	{		
		init_class();
		SQL = "update term.member set logon = 'false' where id = '"+id+"'";
		try
		{
			stmt.executeUpdate(SQL);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	/*public boolean setIP(String id, String ip){
		init_class();
		SQL = "update term.member set ip = '"+ip+"' where id = '"+id+"'";
		try
		{
			stmt.executeUpdate(SQL);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}*/
	
//getmember -- dept/name
	//input = dept id
	//	output = 전체 하위 dept 멤버의 id
				
	public boolean folder_add(String id, String fold_name){
		init_class();
		try
		{
			stmt.executeUpdate(SQL);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}

	}
	public boolean folder_delete(){
		init_class();
		try
		{
			stmt.executeUpdate(SQL);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean auth_add(){
		init_class();
		try
		{
			stmt.executeUpdate(SQL);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public boolean auth_delete(){
		init_class();
		try
		{
			stmt.executeUpdate(SQL);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean file_delete(){
		init_class();
		try
		{
			stmt.executeUpdate(SQL);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public boolean file_add(){
		init_class();
		try
		{
			stmt.executeUpdate(SQL);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
