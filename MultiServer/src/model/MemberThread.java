package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;

import org.json.JSONArray;
import org.json.JSONObject;

import controller.ServerMain;

public class MemberThread extends Thread{
	String mId = null;
	ServerMain mParent;
	Socket mSocket;
	BufferedReader mReader;
	BufferedWriter mWriter;

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
					
//					JSONObject ret = new JSONObject();
//					ret.put("socket_event", SocketEvent.GET_FRIENDS.toString());
//					//TODO: ret.put Friends List
//					mWriter.write(ret.toString()+"\n");
//					mWriter.flush();
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
		
		JSONObject a = new JSONObject();
		a.put("id", "skku");
		a.put("name", "¼º±Õ°ü´ë");
		a.put("dept_id", "");
		a.put("is_online", false);
		a.put("is_dept", true);
		
		JSONObject b = new JSONObject();
		b.put("id", "blain");
		b.put("name", "ÃÖÀ±¼·");
		b.put("dept_id", "skku");
		b.put("is_online", true);
		b.put("is_dept", false);
		
		friends.put(a);
		friends.put(b);
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
