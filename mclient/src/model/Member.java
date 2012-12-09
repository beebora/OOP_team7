package model;

public class Member {
	String id;
	String name;
	Boolean isOnline;
	
	public Member parent;
	
	public Member(String id, String name, Boolean isOnline){
		this.id = id;
		this.name = name;
		this.isOnline = isOnline;
	}
	
	public String getName(){
		return name;
	}
}
