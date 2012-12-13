package model;

public class Member {
	String id;
	String name;
	Boolean isOnline;
	String dept_id;
	NodeType nodetype;
	
	public enum NodeType{
		DEPARTMENT, PERSON
	}

	public Member(String id, String name, Boolean isOnline, String dept_id, Boolean isDepartment){
		this.id = id;
		this.name = name;
		this.isOnline = isOnline;
		this.dept_id = dept_id;
		this.nodetype = (isDepartment)? NodeType.DEPARTMENT : NodeType.PERSON;
	}
	
	public String getName(){
		return name;
	}
	
	public NodeType getType(){
		return nodetype;
	}
	
	@Override
	public String toString(){
		return id + "(" + name + ")";
	}
	
	public String getId(){
		return id;
	}
	
	public String getDeptId(){
		return dept_id;
	}
}
