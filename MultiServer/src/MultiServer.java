import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;

import javax.sql.*;

public class MultiServer{
	
	List<String> ul = new ArrayList<String>();
	ServerSocket sc;
	Vector users;
	UserThread user;
	Enumeration usersEn;
	static Statement stmt;
	static ResultSet rs;
	static Connection conn;
	

	public static void main(String args[]){
		try {
			// The newInstance() call is a work around for some
			// broken Java implementations
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			// handle the error
		}

		conn = null;
		try{
			conn = 
				DriverManager.getConnection("jdbc:mysql://localhost", "root","6409");//6807을 해당 mysql의 비번으로 변경하세요
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}



		stmt = null;
		rs = null;
		String sql;
		
		try {
			stmt = conn.createStatement();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			sql="delete from term.buddy where id=budi";
			stmt.executeUpdate(sql);
			sql="update term.buddy set ip='0'";
			stmt.executeUpdate(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		System.out.println("DB 시작");
		
		
		MultiServer ms=null;
		try{
			ms=new MultiServer();
			System.out.println("네트워크 시작");
			ms.welcomeUsers();
			
		}catch(Exception e){
			System.out.println("main:"+e);
		}
	}

	public MultiServer() throws Exception{
		sc=new ServerSocket(5555);
		users=new Vector();
	}

	public void welcomeUsers() throws Exception{
		while(true){
			Socket socket=sc.accept();
			user=new UserThread(socket, this);
			addUser(user);
			//System.out.println(socket.getInetAddress().toString()+"에서 접속했드라");
			user.setDaemon(true);
			user.start();               
		}
	}
	public synchronized void addUser(Thread userThread){
		users.add(userThread);
		System.out.println("current Users : "+users.size());
	}

	public synchronized void broadCast(String message){
		usersEn=users.elements();
		while(usersEn.hasMoreElements()){
			((UserThread)usersEn.nextElement()).sendMessage(message);
		}
	}
	public void broadUserList(){
		String re="";
		for(int i=0; i<users.size(); i++)
			re+=((UserThread)users.get(i)).nickName+"|";
		re=re.substring(0,re.length()-1);
		broadCast("001"+re);
	}

	public synchronized void deleteFromServer(UserThread userThread){
		users.remove(userThread);
		System.out.println(users.size()+" left in server");
	}
	class UserThread extends Thread{
		Socket socket;
		MultiServer server;
		BufferedReader reader;
		BufferedWriter writer;
		String userMsg;
		String nickName;
		StringTokenizer st;
		String myip;
		String tmp1,tmp2,tmp3,sql,tmp4,tmp5,id,ip;
		

		public UserThread(Socket user_socket, MultiServer main_server) throws Exception{
			socket=user_socket;
			server=main_server;

			reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		}
		String getul()
		{	
			String kk;
			kk="a1 ";
			for(int i=0;i<ul.size();i++)
			{
					kk=kk+ul.get(i);			
			}
			return kk;
		}
		public void run(){
			try{
				
				while((userMsg=reader.readLine()) != null){
					st = new StringTokenizer(userMsg);
					//System.out.println(userMsg);
					if(userMsg.startsWith("A")){
						process_login();
					}
					else if(userMsg.startsWith("B"))
					{
						process_join();
					}
					else if(userMsg.startsWith("C"))
					{
						process_find();
					}
					else if(userMsg.startsWith("D"))
					{
						process_add();
						//System.out.println("친구 추가");
					}
					else if(userMsg.startsWith("F1"))
					{
						process_confirm_added_info();
					}
					else if(userMsg.startsWith("F2"))
					{
						process_add_info_join();
					}
					else if(userMsg.startsWith("F3"))
					{
						process_find_choice();
					}
					else if(userMsg.startsWith("I"))
					{
						process_add_d_day();
					}
				}
			}catch(SocketException se){ 
				//클라이언트와의 연결 오류
				closeUser();
			}catch(Exception e){
				System.out.println("run:"+e);
				closeUser();
			}
			server.broadCast("a4 "+nickName);
			closeUser();
		}
		public void sendMessage(String server_message){
			try{
				System.out.println("sendMsg:"+server_message);
				writer.write(server_message+"\n");
				writer.flush();
			}catch(Exception e){
				System.out.println("sendMessage:"+e);
			}
		}
		public void closeUser(){
			try{
				sql="delete from term.buddy where id='"+nickName+"' and budi='"+nickName+"'";
				try
				{
					stmt.executeUpdate(sql);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				sql="update term.buddy set ip='0' where budi='"+nickName+"'";
				try
				{
					stmt.executeUpdate(sql);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				server.deleteFromServer(this);
				writer.close();
				reader.close();
				socket.close();
			}catch(Exception e){
				System.out.println("closeUser:"+e);
			}
		}
		public void process_add_d_day()
		{
			st.nextToken();
			tmp1=st.nextToken();
			tmp2=st.nextToken();
			if(tmp2.length()<2)
				tmp2="0"+tmp2;
			tmp3=st.nextToken();
			if(tmp3.length()<2)
				tmp3="0"+tmp3;
			tmp4=st.nextToken();
			tmp5=st.nextToken();
			String x7;
			x7=tmp5.replace("^s^"," ");
			tmp5=x7.replace("^nl^","\n");
			sql="insert into term.d_day(year,month,day,hour,title) values ('"+tmp1+"','"+tmp2+"','"+tmp3+"','"+tmp4+"','"+tmp5+"')";
			try
			{
				stmt.executeUpdate(sql);
				sendMessage("i1 0");
			}
			catch(Exception e){
				e.printStackTrace();
				sendMessage("i1 1");
			}
			
		}
		public void process_find_choice()
		{
			sql="select term.buddy.ip from term.match_msg,term.buddy where term.match_msg.id=term.buddy.id and term.buddy.budi=term.buddy.id";
			st.nextToken();
			tmp1=st.nextToken();
			if(!tmp1.startsWith("-1"))
			{
				sql=sql+" and term.match_msg.sex="+tmp1;
			}
			tmp1=st.nextToken();
			if(!tmp1.startsWith("-1"))
			{
				Calendar oCalendar = Calendar.getInstance( );
				int y=oCalendar.get(Calendar.YEAR);
				if(tmp1.startsWith("2"))
					sql=sql+" and term.match_msg.birth>="+(y-19)+" and term.match_msg.birth<="+(y-14);
				else if(tmp1.startsWith("3"))
					sql=sql+" and term.match_msg.birth>="+(y-21)+" and term.match_msg.birth<="+(y-19);
				else if(tmp1.startsWith("4"))
					sql=sql+" and term.match_msg.birth>="+(y-24)+" and term.match_msg.birth<="+(y-21);
				else if(tmp1.startsWith("5"))
					sql=sql+" and term.match_msg.birth>="+(y-26)+" and term.match_msg.birth<="+(y-24);
				else if(tmp1.startsWith("6"))
					sql=sql+" and term.match_msg.birth>="+(y-29)+" and term.match_msg.birth<="+(y-26);
				else if(tmp1.startsWith("7"))
					sql=sql+" and term.match_msg.birth>="+(y-34)+" and term.match_msg.birth<="+(y-29);
				else if(tmp1.startsWith("8"))
					sql=sql+" and term.match_msg.birth>="+(y-44)+" and term.match_msg.birth<="+(y-34);
				else if(tmp1.startsWith("9"))
					sql=sql+" and term.match_msg.birth>="+(y-49)+" and term.match_msg.birth<="+(y-44);
				else if(tmp1.startsWith("10"))
					sql=sql+" and term.match_msg.birth>="+(y-59)+" and term.match_msg.birth<="+(y-49);
				else if(tmp1.startsWith("11"))
					sql=sql+" and term.match_msg.birth>="+(y-99)+" and term.match_msg.birth<="+(y-59);
				else if(tmp1.startsWith("12"))
					sql=sql+" and term.match_msg.birth>="+(y-255)+" and term.match_msg.birth<="+(y-100);
				else if(tmp1.startsWith("1"))
					sql=sql+" and term.match_msg.birth>="+(y-14)+" and term.match_msg.birth<="+y;
				
			}
			tmp1=st.nextToken();
			if(!tmp1.startsWith("-1"))
			{
				sql=sql+" and term.match_msg.star="+tmp1;
			}
			tmp1=st.nextToken();
			if(!tmp1.startsWith("-1"))
			{
				//"1-140","140-150","150-155","155-160","160-165"
				//,"165-168","168-172","172-174","174-176","176-178",
				//"178-180","180-182","182-186","186-190","190-200",
				//"200-210","210-230","230-256";
				if(tmp1.startsWith("10"))
					sql=sql+" and term.match_msg.height>=176 and term.match_msg.height<=178";
				else if(tmp1.startsWith("11"))
					sql=sql+" and term.match_msg.height>=178 and term.match_msg.height<=180";
				else if(tmp1.startsWith("12"))
					sql=sql+" and term.match_msg.height>=180 and term.match_msg.height<=182";
				else if(tmp1.startsWith("13"))
					sql=sql+" and term.match_msg.height>=182 and term.match_msg.height<=186";
				else if(tmp1.startsWith("14"))
					sql=sql+" and term.match_msg.height>=186 and term.match_msg.height<=190";
				else if(tmp1.startsWith("15"))
					sql=sql+" and term.match_msg.height>=190 and term.match_msg.height<=200";
				else if(tmp1.startsWith("16"))
					sql=sql+" and term.match_msg.height>=200 and term.match_msg.height<=210";
				else if(tmp1.startsWith("17"))
					sql=sql+" and term.match_msg.height>=210 and term.match_msg.height<=230";
				else if(tmp1.startsWith("18"))
					sql=sql+" and term.match_msg.height>=230";
				else if(tmp1.startsWith("1"))
					sql=sql+" and term.match_msg.height>=1 and term.match_msg.height<=140";
				else if(tmp1.startsWith("2"))
					sql=sql+" and term.match_msg.height>=140 and term.match_msg.height<=150";
				else if(tmp1.startsWith("3"))
					sql=sql+" and term.match_msg.height>=150 and term.match_msg.height<=155";
				else if(tmp1.startsWith("4"))
					sql=sql+" and term.match_msg.height>=155 and term.match_msg.height<=160";
				else if(tmp1.startsWith("5"))
					sql=sql+" and term.match_msg.height>=160 and term.match_msg.height<=165";
				else if(tmp1.startsWith("6"))
					sql=sql+" and term.match_msg.height>=165 and term.match_msg.height<=168";
				else if(tmp1.startsWith("7"))
					sql=sql+" and term.match_msg.height>=168 and term.match_msg.height<=172";
				else if(tmp1.startsWith("8"))
					sql=sql+" and term.match_msg.height>=172 and term.match_msg.height<=174";
				else if(tmp1.startsWith("9"))
					sql=sql+" and term.match_msg.height>=174 and term.match_msg.height<=176";
				
			
			}
			tmp1=st.nextToken();
			if(!tmp1.startsWith("-1"))
			{
				sql=sql+" and term.match_msg.face="+tmp1;
			}
			tmp1=st.nextToken();
			if(!tmp1.startsWith("-1"))
			{
				sql=sql+" and term.match_msg.style="+tmp1;
			}
			tmp1=st.nextToken();
			if(!tmp1.startsWith("-1"))
			{
				sql=sql+" and term.match_msg.habi="+tmp1;
			}
			tmp1=st.nextToken();
			if(!tmp1.startsWith("-1"))
			{
				sql=sql+" and term.match_msg.local="+tmp1;
			}
			System.out.println(sql);
			try{
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				if (stmt.execute(sql)) {
					rs = stmt.getResultSet();
				}
			} catch(Exception ex) {
				System.out.println(ex.toString());
			}
			int i,j;
			i=j=0;
			String ggg;
			ggg="f3";
			try{
				while (rs.next()) {
					tmp3=rs.getString("ip");
					ggg=ggg+" "+tmp3;
				} 
			}catch(Exception ex) {
				System.out.println(ex.toString());
			}
			sendMessage(ggg);
		}
		public void process_add_info_join(){
			st.nextToken();
			tmp1=st.nextToken();
			sql="INSERT INTO term.match_msg (id,sex, birth, star,height,face, style, habi, local) values('"+tmp1+"'";
			tmp1=st.nextToken();
			sql=sql+","+tmp1;
			tmp1=st.nextToken();
			sql=sql+","+tmp1;
			tmp1=st.nextToken();
			sql=sql+","+tmp1;
			tmp1=st.nextToken();
			sql=sql+","+tmp1;
			tmp1=st.nextToken();
			sql=sql+","+tmp1;
			tmp1=st.nextToken();
			sql=sql+","+tmp1;
			tmp1=st.nextToken();
			sql=sql+","+tmp1;
			tmp1=st.nextToken();
			sql=sql+","+tmp1+")";
			try
			{
				stmt.executeUpdate(sql);
				sendMessage("f2 0");
			}
			catch(Exception e){
				e.printStackTrace();
				sendMessage("f2 1");
			}
			
			
		}
		public void process_confirm_added_info(){
			st.nextToken();
			tmp1=st.nextToken();
			sql="SELECT * FROM term.match_msg where id='"+tmp1+"'";
			try{
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				if (stmt.execute(sql)) {
					rs = stmt.getResultSet();
				}
			} catch(Exception ex) {
				System.out.println(ex.toString());
			}
			int i=0;
			try{
				while (rs.next()) {
					i++;break;
					
				} 
			}catch(Exception ex) {
				System.out.println(ex.toString());
			}
			if(i==0)
			{
				System.out.println("없군");
				sendMessage("f1 0");
			}
			else
			{
				System.out.println("있군");
				sendMessage("f1 1");
			}
			
		}
		public void process_login(){
			st.nextToken();
			tmp1=st.nextToken();
			tmp2=st.nextToken();
			StringTokenizer st2 = new StringTokenizer(socket.getInetAddress().toString(),"/");
			tmp3=st2.nextToken();
			myip=tmp3;
			sql="SELECT * FROM term.member where id='"+tmp1+"' and pass='"+tmp2+"'";
			try{
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				if (stmt.execute(sql)) {
					rs = stmt.getResultSet();
				}
			} catch(Exception ex) {
				System.out.println(ex.toString());
			}
			int i=0;
			try{
				while (rs.next()) {
					i++;break;
					
				} 
			}catch(Exception ex) {
				System.out.println(ex.toString());
			}
			if(i==0)
			{
				//System.out.println("없군");
				sendMessage("a2");
			}
			else
			{
				nickName=tmp1;
				//System.out.println("있군");
				server.broadCast("a3 "+tmp1+" "+tmp3);
				
				sql="update term.buddy set ip='"+tmp3+"' where budi='"+tmp1+"'";
				try
				{
					stmt.executeUpdate(sql);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				//sql="SELECT budi,ip FROM term.buddy where id='"+tmp1+"'";
				sql="select a.id, a.name, b.ip from term.member a, term.buddy b where a.id=b.budi and b.id='"+tmp1+"'";
				try{
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					if (stmt.execute(sql)) {
						rs = stmt.getResultSet();
					}
				} catch(Exception ex) {
					System.out.println(ex.toString());
				}
				tmp4="a1";
				String name;
				try{
					while (rs.next()) {
						id = rs.getString("a.id");
						name=rs.getString("a.name");
						ip = rs.getString("b.ip");
						tmp4=tmp4+" "+id+","+name+","+ip;
					} 
				}catch(Exception ex) {
					System.out.println(ex.toString());
				}
				sql="insert into term.buddy(id,budi,ip) values('"+tmp1+"','"+tmp1+"','"+tmp3+"')";
				try
				{
					stmt.executeUpdate(sql);
				}
				catch(Exception e){
					e.printStackTrace();
				}
				sendMessage(tmp4);
				sql="select * from term.d_day order by year DESC,month DESC,day DESC,hour DESC";
				try{
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					if (stmt.execute(sql)) {
						rs = stmt.getResultSet();
					}
				} catch(Exception ex) {
					System.out.println(ex.toString());
				}
				tmp4="i2";
				String y,m,d,h,t,amp;
				try{
					while (rs.next()) {
						y = rs.getString("year");
						m=rs.getString("month");
						d = rs.getString("day");
						h=rs.getString("hour");
						t=rs.getString("title");
						amp=t.replace(" ","^s^");
						t=amp.replace("\n", "^nl^");
						amp=t.replace(",","^cm^");
						tmp4=tmp4+" "+y+","+m+","+d+","+h+","+amp;
					} 
				}catch(Exception ex) {
					System.out.println(ex.toString());
				}
				sendMessage(tmp4);
			}
		}
		public void process_find(){
			st.nextToken();
			tmp1=st.nextToken();
			sql="SELECT id FROM term.member where name='"+tmp1+"'";
			try{
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				if (stmt.execute(sql)) {
					rs = stmt.getResultSet();
				}
			} catch(Exception ex) {
				System.out.println(ex.toString());
			}
			tmp4="c1";
			try{
				while (rs.next()) {
					id = rs.getString("id");
					tmp4=tmp4+" "+id;
				} 
			}catch(Exception ex) {
				System.out.println(ex.toString());
			}
			sendMessage(tmp4);
			
		}
		public void process_add(){
			st.nextToken();
			tmp1=st.nextToken();
			sql="insert into term.buddy(id,budi,ip) values('"+nickName+"','"+tmp1+"','0')";
			try
			{
				stmt.executeUpdate(sql);
				sql="SELECT ip FROM term.buddy where id='"+tmp1+"' and budi='"+tmp1+"'";
				try{
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					if (stmt.execute(sql)) {
						rs = stmt.getResultSet();
					}
				} catch(Exception ex) {
					System.out.println(ex.toString());
				}
				tmp4="d1";
				try{
					ip="0";
					while (rs.next()) {
						ip = rs.getString("ip");
						tmp4=tmp4+" "+ip;
					} 
					sql="update term.buddy set ip='"+ip+"' where budi='"+tmp1+"' and id='"+nickName+"'";
					try
					{
						stmt.executeUpdate(sql);
					}
					catch(Exception e){
						e.printStackTrace();
					}
				}catch(Exception ex) {
					System.out.println(ex.toString());
				}
				sendMessage(tmp4);
				System.out.println(tmp4);
			}
			catch(Exception e){
				e.printStackTrace();
				sendMessage("d2");
			}
		}
		public void process_join(){
			System.out.println("가입 요청");
			System.out.println(userMsg);
			st.nextToken();
			tmp1=st.nextToken();
			
			tmp2=st.nextToken();
			tmp3=st.nextToken();
			System.out.println("이름 "+tmp1);
			System.out.println("아디"+tmp2);
			System.out.println("비번 "+tmp3);
			sql="insert into term.member(id,name,pass) values('"+tmp2+"','"+tmp1+"','"+tmp3+"')";
			try
			{
				stmt.executeUpdate(sql);
				System.out.println("가입 성공 ㄳ");
				sendMessage("b1");
			}
			catch(Exception e){
				e.printStackTrace();
				System.out.println("가입 실패 ㅅㅂ");
				sendMessage("b2");
			}
		}
		
	}
}
