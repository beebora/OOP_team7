package controller;

import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;

public class dbms {
	String SQL;
	Statement stmt;
	
	private void init_class()
	{
		SQL = null;
		stmt = null;
	}
	public void create_table()
	{
		
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
		SQL = "update term.member set ip = '0' where id = '"+id+"'";
		try
		{
			stmt.executeUpdate(SQL);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			return false;
		}
	}
	public boolean login(String id, String pass, String ip){
		init_class();
		// 찾아보고, 있으면 t else false
		try
		{
			///SQL = "update term.member() set "
			stmt.executeUpdate(SQL);
			
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public boolean setIP(String id, String ip){
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
	}
	
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