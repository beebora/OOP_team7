package controller;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import model.MemberThread;

public class ServerMain {
	public static void main(String args[]){
		try {
			new ServerMain();		//���� �⵿ ����!
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	ArrayList<MemberThread> memberArr = new ArrayList<MemberThread>();
	
	final int port = 5555;
	
	public ServerMain() throws Exception{
		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(port);
		while(true){
			Socket socket = serverSocket.accept();	//������ ����� ��ٸ�.
			//������ �߻��ϸ�?
			MemberThread member = new MemberThread(socket, this);
			memberArr.add(member);
			member.start();
		}
	}
	
	public ArrayList<MemberThread> getMemberArr(){
		return memberArr;
	}

}
