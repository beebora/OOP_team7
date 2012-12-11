package model;

public class Member {
	String id;
	String name;
	Boolean isOnline;
	NodeType nodetype;
	
	public Member parent;
	
	public enum NodeType{
		DEPARTMENT, PERSON
	}

	
	public Member(String id, String name, Boolean isOnline, Boolean isDepartment){
		this.id = id;
		this.name = name;
		this.isOnline = isOnline;
		this.nodetype = (isDepartment)? NodeType.DEPARTMENT : NodeType.PERSON;
	}
	
	public String getName(){
		return name;
	}
	
	public NodeType getType(){
		return nodetype;
	}
}
