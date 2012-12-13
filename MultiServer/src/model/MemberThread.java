package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;

import controller.ServerMain;
import controller.dbms;
import controller.dbms.member;

public class MemberThread extends Thread{
	String mId = null;
	ServerMain mParent;
	Socket mSocket;
	BufferedReader mReader;
	BufferedWriter mWriter;
	dbms db = dbms.getInstance();
	public enum SocketEvent{
		GET_FRIENDS
		, JOIN
		, LOGIN
		, LOGOUT
		, SEND_MESSAGE
		, SEND_CHAT
		, FRIENDS_CHANGED
		, RECEIVE_MESSAGE
		, RECEIVE_CHAT
	}

	public String getUid(){
		return mId;
	}

	public MemberThread(Socket socket, ServerMain parent) throws Exception{
		this.mSocket = socket;
		this.mParent = parent;
		this.mReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.mWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	}
	
	@Override
	public void run() {
		String clientMsg;
		try{
			while( (clientMsg = mReader.readLine()) != null ){
				JSONObject arg = new JSONObject(clientMsg);
				SocketEvent socketEvent = SocketEvent.valueOf(arg.getString("socket_event"));
				switch(socketEvent){
				case GET_FRIENDS:{
					//Get Friends List process
					this.sendFriendsList(SocketEvent.GET_FRIENDS);
					break;}
				case JOIN:{
					String id = arg.getString("id");
					String name = arg.getString("name");
					String pw = arg.getString("pw");
					String dept_id = arg.getString("dept_id");
					
					//TODO: Join process
					
					JSONObject ret = new JSONObject();
					ret.put("socket_event", SocketEvent.JOIN.toString());
					ret.put("result", true);
					ret.put("message", "success");
					mWriter.write(ret.toString()+"\n");
					mWriter.flush();
					break;}
				case LOGIN:{
					String id = arg.getString("id");
					String pw = arg.getString("pw");
					
					//TODO: Login process
					
					this.mId = id;
					JSONObject ret = new JSONObject();
					ret.put("socket_event", SocketEvent.LOGIN.toString());
					ret.put("result", true);
					//TODO: ret.put name from DB!
//					ret.put("name", )
					ret.put("message", "success");
					mWriter.write(ret.toString()+"\n");
					mWriter.flush();
					break;}
				case LOGOUT:{ //there is no data to response. just close the conn!
					logout();
					break;}
				case SEND_CHAT:{
					String id = arg.getString("to");
					String msg = arg.getString("msg");
					String name = arg.getString("name");	//from who
					
					for(MemberThread m : mParent.getMemberArr()){
						if(m.getUid().equals(id)) m.onReceiveChat(this.mId, name, msg);
					}
					
					JSONObject ret = new JSONObject();
					ret.put("socket_event", SocketEvent.SEND_CHAT.toString());
					ret.put("result", true);
					ret.put("message", "success");
					mWriter.write(ret.toString()+"\n");
					mWriter.flush();
					break;}
				case SEND_MESSAGE:{
					String id = arg.getString("to");
					String msg = arg.getString("msg");
					String name = arg.getString("name");	//from who
					
					for(MemberThread m : mParent.getMemberArr()){
						if(m.getUid().equals(id)) m.onReceiveMessage(this.mId, name, msg);
					}
					
					JSONObject ret = new JSONObject();
					ret.put("socket_event", SocketEvent.SEND_MESSAGE.toString());
					ret.put("result", true);
					ret.put("message", "success");
					mWriter.write(ret.toString()+"\n");
					mWriter.flush();
					break;}
				default:
					System.out.println("socketEvt error : ");
					System.out.println(socketEvent);
				}
			}
		}catch(SocketException se){
			logout();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void logout(){
		try{
			disconnect();
			mParent.getMemberArr().remove(this);
			for(MemberThread m : mParent.getMemberArr()){
				if(!m.equals(this)) m.sendFriendsList(SocketEvent.FRIENDS_CHANGED); //broadcast new friends list
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void onReceiveMessage(String from, String name, String msg) throws Exception{
		JSONObject ret = new JSONObject();
		ret.put("socket_event", SocketEvent.RECEIVE_MESSAGE.toString());
		ret.put("from", from);
		ret.put("name", name);
		ret.put("msg", msg);
		mWriter.write(ret.toString()+"\n");
		mWriter.flush();
	}

	public void onReceiveChat(String from, String name, String msg) throws Exception{
		JSONObject ret = new JSONObject();
		ret.put("socket_event", SocketEvent.RECEIVE_CHAT.toString());
		ret.put("from", from);
		ret.put("name", name);
		ret.put("msg", msg);
		mWriter.write(ret.toString()+"\n");
		mWriter.flush();
	}
	
	public void sendFriendsList(SocketEvent socketEvent) throws Exception{
		JSONObject ret = new JSONObject();
		ret.put("socket_event", socketEvent.toString());
		
		JSONArray friends = new JSONArray();
		
		
		ArrayList<member>  mem;
		mem = db.getList();
		int i = 0;
		while(i < mem.size())
		{
			JSONObject a = new JSONObject();
			a.put("id", mem.get(i).id);
			a.put("name", mem.get(i).name);
			a.put("dept_id", mem.get(i).parent);
			a.put("is_online", mem.get(i).logon);
			a.put("is_dept", mem.get(i).is_dept);
			i++;
			friends.put(a);
		}
		


		//TODO: send friends list
		
		ret.put("members", friends);
		ret.put("result", true);
		ret.put("message", "success");
		
		mWriter.write(ret.toString()+"\n");
		mWriter.flush();
	}

	public void disconnect() throws Exception{
		mSocket.close();
		mWriter.close();
		mReader.close();
	}
}
