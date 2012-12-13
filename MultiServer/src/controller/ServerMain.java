package controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import model.MemberThread;

public class ServerMain {
	ArrayList<MemberThread> memberArr = new ArrayList<MemberThread>();

	final int port = 5555;
	ServerSocket serverSocket;
	ServerMain self;
	Thread thread;

	public ServerMain() throws Exception{
		serverSocket = new ServerSocket(port);
		self = this;
	}
	
	public void start() throws Exception{
		thread = new Thread(new Runnable(){
			public void run(){
				while(!thread.isInterrupted()){
					try{
						System.out.println("listen");
						Socket socket = serverSocket.accept();	//접속이 생기길 기다림.
						//접속이 발생하면?
						System.out.println("accept from " + socket.getLocalAddress().getHostAddress());
						MemberThread member = new MemberThread(socket, self);
						memberArr.add(member);
						member.start();
					}catch(SocketException se){
						System.out.println("ServerSocket destory");
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				System.out.println("terminated");
			}
		});
		thread.start();
	}

	public ArrayList<MemberThread> getMemberArr(){
		return memberArr;
	}

	public void close(){
		try {
			thread.interrupt();
			for(MemberThread m: memberArr){
				m.disconnect();
			}
			serverSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
