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
	
	final int port = 5555;
	
	public enum ApiType{
		GET_FRIENDS
		, JOIN
		, LOGIN
		, LOGOUT
		, SEND_MESSAGE
		, SEND_CHAT
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
	MessageListener mMessageListener = null;
	MessageListener mChatListener = null;
	BooleanListener mMsgResultListener = null;
	BooleanListener mChatResultListener = null;
	
	
	public void connect(String ip) throws Exception{
		mSocket = new Socket(ip, port);
		mReader = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
		mWriter = new BufferedWriter(new OutputStreamWriter(mSocket.getOutputStream()));
		
		serverListener = new Thread(){
			public void run(){
				try{
					while( (serverMsg = mReader.readLine()) != null ){
						JSONObject ret = new JSONObject(serverMsg);
						ApiType apiType = ApiType.valueOf(ret.getString("apiType"));
						switch(apiType){
						case GET_FRIENDS:
							if(mGetFriendsListener!=null){
								DefaultMutableTreeNode root = new DefaultMutableTreeNode("조직도");
								JSONArray memberArr = ret.getJSONArray("members");
								for(int i=0; i<memberArr.length(); i++){
									JSONObject memberObj = (JSONObject) memberArr.get(i);
									Member member = new Member(
											memberObj.getString("id")
											, memberObj.getString("name")
											, memberObj.getBoolean("isOnline"));
									String parentId = memberObj.getString("parent");
									Enumeration<?> en = root.depthFirstEnumeration();
									while(en.hasMoreElements()){
										DefaultMutableTreeNode parent = (DefaultMutableTreeNode) en.nextElement();
										if(parentId.equals( ((Member)parent.getUserObject()).getName() )){
											DefaultMutableTreeNode node = new DefaultMutableTreeNode(member);
											parent.add(node);
											break;
										}
									}
								}
								mGetFriendsListener.receiveFriends(root);
							}
							break;
						case JOIN:{
							Boolean success = ret.getBoolean("result");
							String msg = ret.getString("message");
							mJoinListener.receiveResult(success, msg);
							break;}
						case LOGIN:{
							Boolean success = ret.getBoolean("result");
							String msg = ret.getString("message");
							mLoginListener.receiveResult(success, msg);
							break;}
						case RECEIVE_CHAT:{
							String id = ret.getString("id");
							String msg = ret.getString("message");
							String name = ret.getString("name");
							mChatListener.receiveMessage(id, name, msg);
							break;}
						case RECEIVE_MESSAGE:{
							String id = ret.getString("id");
							String msg = ret.getString("message");
							String name = ret.getString("name");
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
							System.out.println("ApiType error : ");
							System.out.println(apiType);
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
	
	public void join(String id, String pw, String parentId, BooleanListener joinListener){
		mJoinListener = joinListener;
		try{
			JSONObject arg = new JSONObject();
			arg.put("apiType", ApiType.JOIN.toString());
			arg.put("id", id);
			arg.put("pw", pw);
			arg.put("parent", parentId);
			mWriter.write(arg.toString()+"\n");
			mWriter.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void login(String id, String pw, String ip, BooleanListener loginListener){
		mLoginListener = loginListener;
		try{
			JSONObject arg = new JSONObject();
			arg.put("apiType", ApiType.LOGIN.toString());
			arg.put("id", id);
			arg.put("pw", pw);
			arg.put("ip", ip);
			mWriter.write(arg.toString()+"\n");
			mWriter.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void logout(){
		try{
			JSONObject arg = new JSONObject();
			arg.put("apiType", ApiType.LOGOUT.toString());
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
			arg.put("apiType", ApiType.GET_FRIENDS.toString());
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
			arg.put("apiType", ApiType.SEND_MESSAGE.toString());
			arg.put("to", toId);
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
			arg.put("apiType", ApiType.SEND_CHAT.toString());
			arg.put("to", toId);
			arg.put("msg", msg);
			mWriter.write(arg.toString()+"\n");
			mWriter.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}