package helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;

import model.Member;

import org.json.JSONArray;
import org.json.JSONObject;

public class ConnectHelper {
	static ConnectHelper singleton;

	Socket mSocket;
	BufferedReader mReader;
	BufferedWriter mWriter;
	String serverMsg;
	Thread serverListener;
	
	String mId=null;
	String mName=null;
	
	final int port = 5555;
	
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
	
	static public ConnectHelper getInstance(){
		if(singleton==null) singleton = new ConnectHelper();
		return singleton;
	}
	
	public interface GetFriendsListener{
		public void receiveFriends(DefaultMutableTreeNode friends);
	}
	
	public interface BooleanListener{
		public void receiveResult(Boolean success, String msg);
	}
	
	public interface MessageListener{
		public void receiveMessage(String id, String name, String msg);
	}
	
	GetFriendsListener mGetFriendsListener = null;
	BooleanListener mLoginListener = null;
	BooleanListener mJoinListener = null;
	BooleanListener mLogoutListener = null;
	BooleanListener mMsgResultListener = null;
	BooleanListener mChatResultListener = null;
	MessageListener mMessageListener = null;
	MessageListener mChatListener = null;
	GetFriendsListener mFriendsChangedListener = null;
	
	public void connect(String ip) throws Exception{
		mSocket = new Socket(ip, port);
		mReader = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
		mWriter = new BufferedWriter(new OutputStreamWriter(mSocket.getOutputStream()));
		
		serverListener = new Thread(){
			public void run(){
				try{
					while( (serverMsg = mReader.readLine()) != null ){
						JSONObject ret = new JSONObject(serverMsg);
						SocketEvent socketEvent = SocketEvent.valueOf(ret.getString("socket_event"));
						switch(socketEvent){
						case FRIENDS_CHANGED:{
							refreshFriends(ret, mFriendsChangedListener);
							break;}
						case GET_FRIENDS:{
							refreshFriends(ret, mGetFriendsListener);
							break;}
						case JOIN:{
							Boolean success = ret.getBoolean("result");
							String msg = ret.getString("message");
							mJoinListener.receiveResult(success, msg);
							break;}
						case LOGIN:{
							Boolean success = ret.getBoolean("result");
							String msg = ret.getString("message");
							String name = ret.getString("name");
							mName = name;
							mLoginListener.receiveResult(success, msg);
							break;}
						case RECEIVE_CHAT:{
							String id = ret.getString("from");
							String name = ret.getString("name");
							String msg = ret.getString("msg");
							mChatListener.receiveMessage(id, name, msg);
							break;}
						case RECEIVE_MESSAGE:{
							String id = ret.getString("from");
							String name = ret.getString("name");
							String msg = ret.getString("msg");
							mMessageListener.receiveMessage(id, name, msg);
							break;}
						case SEND_CHAT:{
							Boolean success = ret.getBoolean("result");
							String msg = ret.getString("message");
							mChatResultListener.receiveResult(success, msg);
							break;}
						case SEND_MESSAGE:{
							Boolean success = ret.getBoolean("result");
							String msg = ret.getString("message");
							mMsgResultListener.receiveResult(success, msg);
							break;}
						default:
							System.out.println("socketEvt error : ");
							System.out.println(socketEvent);
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		};
		serverListener.start();
	}
	
	public void disconnect() throws Exception{
		mSocket.close();
		mWriter.close();
		mReader.close();
	}
	
	private void refreshFriends(JSONObject ret, GetFriendsListener listener) throws Exception{
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Á¶Á÷µµ");
		JSONArray memberArr = ret.getJSONArray("members");
		for(int i=0; i<memberArr.length(); i++){
			JSONObject memberObj = (JSONObject) memberArr.get(i);
			Member member = new Member(
					memberObj.getString("id")
					, memberObj.getString("name")
					, memberObj.getBoolean("is_online")
					, memberObj.getBoolean("is_dep"));
			String dep_id = memberObj.getString("dep_id");
			Enumeration<?> en = root.depthFirstEnumeration();
			while(en.hasMoreElements()){
				DefaultMutableTreeNode parent = (DefaultMutableTreeNode) en.nextElement();
				if(dep_id.equals( ((Member)parent.getUserObject()).getName() )){
					DefaultMutableTreeNode node = new DefaultMutableTreeNode(member);
					parent.add(node);
					break;
				}
			}
		}
		listener.receiveFriends(root);
	}
	
	public void join(String id, String name, String pw, String dep_id, BooleanListener joinListener){
		mJoinListener = joinListener;
		try{
			JSONObject arg = new JSONObject();
			arg.put("socket_event", SocketEvent.JOIN.toString());
			arg.put("id", id);
			arg.put("name", name);
			arg.put("pw", pw);
			arg.put("dep_id", dep_id);
			mWriter.write(arg.toString()+"\n");
			mWriter.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void login(String id, String pw,
			MessageListener messageListener,
			MessageListener chatListener,
			GetFriendsListener friendsChangedListener,
			BooleanListener loginListener
		){
		mLoginListener = loginListener;
		mMessageListener = messageListener;
		mChatListener = chatListener;
		mFriendsChangedListener = friendsChangedListener;
		
		try{
			JSONObject arg = new JSONObject();
			arg.put("socket_event", SocketEvent.LOGIN.toString());
			arg.put("id", id);
			arg.put("pw", pw);
			mWriter.write(arg.toString()+"\n");
			mWriter.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void logout(){
		try{
			JSONObject arg = new JSONObject();
			arg.put("socket_event", SocketEvent.LOGOUT.toString());
//			arg.put("id", );
			mWriter.write(arg.toString()+"\n");
			mWriter.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void getFriends(GetFriendsListener getFriendsListener){
		mGetFriendsListener = getFriendsListener;
		try{
			JSONObject arg = new JSONObject();
			arg.put("socket_event", SocketEvent.GET_FRIENDS.toString());
			mWriter.write(arg.toString()+"\n");
			mWriter.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

	public void sendMessage(String toId, String msg, BooleanListener msgResultListener){
		mMsgResultListener = msgResultListener;
		try{
			JSONObject arg = new JSONObject();
			arg.put("socket_event", SocketEvent.SEND_MESSAGE.toString());
			arg.put("to", toId);
			arg.put("name", mName);
			arg.put("msg", msg);
			mWriter.write(arg.toString()+"\n");
			mWriter.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
	}	

	public void sendChat(String toId, String msg, BooleanListener chatResultListener){
		mChatResultListener = chatResultListener;
		try{
			JSONObject arg = new JSONObject();
			arg.put("socket_event", SocketEvent.SEND_CHAT.toString());
			arg.put("to", toId);
			arg.put("name", mName);
			arg.put("msg", msg);
			mWriter.write(arg.toString()+"\n");
			mWriter.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String getId(){
		return mId;
	}
	
	public String getName(){
		return mName;
	}
}