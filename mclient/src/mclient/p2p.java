package mclient;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.*;

public class p2p {
	

}
class p2p_listen extends Thread{
	p2p_server myp;
	Socket socket;
	BufferedReader reader;
	BufferedWriter writer;
	String userMsg;
	String token;
	String your_ip;
	String your_name;
	NewSwingApp mypp;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JProgressBar jProgressBar2;
	private JLabel jLabel6;
	private JLabel jLabel5;
	private JLabel jLabel4;
	private JDialog jDialog2;
	StringTokenizer st2;
	private JButton jButton15;
	private JButton jButton17;
	private JButton jButton16;
	private JButton jButton14;
	private JScrollPane jScrollPane5;
	private JScrollPane jScrollPane6;
	private JTextArea jTextArea3;
	private JTextArea jTextArea4;
	private JTextField jTextField3;
	private JDialog _i_get_msg;
	private JDialog one_to_one;
	int times;
	String my_name;
	File f;
	static FileOutputStream out;
	//InputStream ins = null;
	BufferedInputStream ins=null; 
	
	
	public p2p_listen(Socket cs, p2p_server pp) throws IOException{
		socket=cs;
		myp=pp;
		reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		st2 = new StringTokenizer(socket.getInetAddress().toString(),"/");
		your_ip=st2.nextToken();
		mypp=myp.getp();
		my_name=mypp.get_name();
		times=0;
		
	}
	private JDialog getOne_to_one() {
		if(one_to_one == null) {
			one_to_one = new JDialog();
			one_to_one.setLayout(null);
			one_to_one.setTitle("1:1 \ub300\ud654");
			one_to_one.getContentPane().add(getJScrollPane6());
			one_to_one.getContentPane().add(getJTextField3());
			one_to_one.getContentPane().add(getJButton17());
			one_to_one.getContentPane().add(getJButton16());
			one_to_one.setSize(280, 351);
			one_to_one.setBounds(280, 351,280, 351);
			one_to_one.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent evt) {
					one_to_oneWindowClosing(evt);
				}
			});
		}
		return one_to_one;
	}
	private void one_to_oneWindowClosing(WindowEvent evt) {
		System.out.println("one_to_one.windowClosing, event="+evt);
		//TODO add your code for one_to_one.windowClosing
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private JTextArea getJTextArea4() {
		if(jTextArea4 == null) {
			jTextArea4 = new JTextArea();
			jTextArea4.setBounds(12, 12, 248, 239);
		}
		return jTextArea4;
	}
	
	private JScrollPane getJScrollPane6() {
		if(jScrollPane5 == null) {
			jScrollPane5 = new JScrollPane();
			jScrollPane5.setBounds(12, 12, 248, 239);
			jScrollPane5.setViewportView(getJTextArea4());
		}
		return jScrollPane5;
	}
	
	private JTextField getJTextField3() {
		if(jTextField3 == null) {
			jTextField3 = new JTextField();
			jTextField3.setText("대화 내용을 입력하세요");
			jTextField3.setBounds(12, 264, 179, 22);
			jTextField3.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent evt) {
					jTextField3KeyPressed(evt);
				}
			});
		}
		return jTextField3;
	}
	
	private JButton getJButton17() {
		if(jButton15 == null) {
			jButton15 = new JButton();
			jButton15.setText("\ub300\ud654 \ub05d\ub0b4\uae30");
			jButton15.setBounds(59, 298, 132, 22);
			jButton15.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					jButton17MouseClicked(evt);
				}
			});
		}
		return jButton15;
	}
	
	private JButton getJButton16() {
		if(jButton16 == null) {
			jButton16 = new JButton();
			jButton16.setText("\uc785\ub825");
			jButton16.setBounds(197, 264, 64, 22);
			jButton16.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					jButton16MouseClicked(evt);
				}
			});
		}
		return jButton16;
	}
	
	private void jButton16MouseClicked(MouseEvent evt) {
		System.out.println("jButton16.mouseClicked, event="+evt);
		//TODO add your code for jButton16.mouseClicked
		String kk=jTextField3.getText();
		String xx;
		xx=kk.replace(" ", "^s^");
		kk=xx.replace("\n","^nl^");
		jTextArea4.setText(jTextArea4.getText()+"\n"+"["+my_name+"] "+jTextField3.getText());
		try {
			writer.write("G ["+my_name+"] "+kk+"\n");
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jTextField3.setText("");

	}
	private void jTextField3KeyPressed(KeyEvent evt) {
		System.out.println("jTextField3.keyPressed, event="+evt);
		//TODO add your code for jTextField3.keyPressed
		if(evt.getKeyCode()==10)
		{
			jButton16MouseClicked(null);
		}
	}
	private void jButton17MouseClicked(MouseEvent evt) {
		System.out.println("jButton15.mouseClicked, event="+evt);
		//TODO add your code for jButton15.mouseClicked
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		one_to_one.setVisible(false);
	}

	private JDialog get_i_get_msg() {
		if(_i_get_msg == null) {
			_i_get_msg = new JDialog();
			_i_get_msg.setLayout(null);
			_i_get_msg.setTitle("\ubc1b\uc740 \uba54\uc2dc\uc9c0");
			_i_get_msg.getContentPane().add(getJLabel2());
			_i_get_msg.getContentPane().add(getJLabel3());
			_i_get_msg.getContentPane().add(getJScrollPane5());
			_i_get_msg.getContentPane().add(getJButton14());
			_i_get_msg.getContentPane().add(getJButton15());
			_i_get_msg.setSize(350, 240);
			_i_get_msg.setBounds(350, 240,350,240);
		}
		return _i_get_msg;
	}
	
	private JLabel getJLabel2() {
		if(jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setBounds(12, 12, 318, 15);
		}
		return jLabel2;
	}
	
	private JLabel getJLabel3() {
		if(jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("jLabel3");
			jLabel3.setBounds(12, 33, 318, 15);
		}
		return jLabel3;
	}
	
	private JTextArea getJTextArea3() {
		if(jTextArea3 == null) {
			jTextArea3 = new JTextArea();
			jTextArea3.setText("jTextArea3");
			jTextArea3.setBounds(12, 60, 318, 121);
		}
		return jTextArea3;
	}
	
	private JScrollPane getJScrollPane5() {
		if(jScrollPane5 == null) {
			jScrollPane5 = new JScrollPane();
			jScrollPane5.setBounds(12, 60, 318, 121);
			jScrollPane5.setViewportView(getJTextArea3());
		}
		return jScrollPane5;
	}
	
	private JButton getJButton14() {
		if(jButton14 == null) {
			jButton14 = new JButton();
			jButton14.setText("\ub2f5\uc7a5");
			jButton14.setBounds(33, 187, 112, 22);
			jButton14.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					jButton14MouseClicked(evt);
				}
			});
		}
		return jButton14;
	}
	private void jButton14MouseClicked(MouseEvent evt) {
		//JOptionPane.showMessageDialog(null, "지랄! 염병!", "come", JOptionPane.WARNING_MESSAGE);
		mypp.Set_receiver(your_name);
		mypp.Set_dest_ip(your_ip);
	}
	private JButton getJButton15() {
		if(jButton15 == null) {
			jButton15 = new JButton();
			jButton15.setText("\ub2eb\uae30");
			jButton15.setBounds(185, 187, 116, 22);
			jButton15.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					jButton15MouseClicked(evt);
				}
			});
		}
		return jButton15;
	}
	private void jButton15MouseClicked(MouseEvent evt) {
		//JOptionPane.showMessageDialog(null, "지랄! 염병!", "come", JOptionPane.WARNING_MESSAGE);
		_i_get_msg.setVisible(false);
	}
	private JDialog getJDialog2() {
		if(jDialog2 == null) {
			jDialog2 = new JDialog();
			jDialog2.setLayout(null);
			jDialog2.setTitle("\ud30c\uc77c \ubc1b\uae30");
			jDialog2.getContentPane().add(getJLabel4());
			jDialog2.getContentPane().add(getJLabel5());
			jDialog2.getContentPane().add(getJLabel6());
			jDialog2.getContentPane().add(getJProgressBar2());
			jDialog2.setSize(303, 208);
			jDialog2.setBounds(303, 208,303, 208);
		}
		return jDialog2;
	}
	
	private JLabel getJLabel4() {
		if(jLabel4 == null) {
			jLabel4 = new JLabel();
			jLabel4.setText("\ud30c\uc77c\uba85");
			jLabel4.setBounds(30, 25, 228, 15);
		}
		return jLabel4;
	}
	
	private JLabel getJLabel5() {
		if(jLabel5 == null) {
			jLabel5 = new JLabel();
			jLabel5.setText("\uc800\uc7a5 \uacbd\ub85c(\uace0\uc815\uc784)");
			jLabel5.setBounds(30, 57, 248, 15);
		}
		return jLabel5;
	}
	
	private JLabel getJLabel6() {
		if(jLabel6 == null) {
			jLabel6 = new JLabel();
			jLabel6.setText("99234/123213Bytes");
			jLabel6.setBounds(32, 91, 221, 15);
		}
		return jLabel6;
	}
	
	private JProgressBar getJProgressBar2() {
		if(jProgressBar2 == null) {
			jProgressBar2 = new JProgressBar();
			jProgressBar2.setBounds(30, 123, 246, 21);
		}
		return jProgressBar2;
	}
	public void run(){
		try {
			while((userMsg=reader.readLine()) != null){
				//JOptionPane.showMessageDialog(null, userMsg, "come", JOptionPane.WARNING_MESSAGE);
				st2 = new StringTokenizer(userMsg," ");
				token=st2.nextToken();
				if(token.startsWith("E"))
				{
					String gg;
					token=st2.nextToken();
					
					Date today=new Date();
					_i_get_msg=get_i_get_msg();
					your_name=token;
					jLabel2.setText(token+"에게 받은 쪽지");
					jLabel3.setText("받은 시각 : "+today);
					token=st2.nextToken();
					
					gg=token.replace("^s^"," ");
					token=gg.replace("^nl^", "\n");
					
					jTextArea3.setText(token);
					_i_get_msg.setVisible(true);
				}
				else if(token.startsWith("S"))
				{
					String gg1;
					token=st2.nextToken();
					
					Date today=new Date();
					_i_get_msg=get_i_get_msg();
					your_name=token;
					jLabel2.setText(token+"에게 받은 보안 쪽지");
					jLabel3.setText("받은 시각 : "+today);
					token=st2.nextToken();
					
					gg1=token.replace("^s^"," ");
					token=gg1.replace("^nl^", "\n");
					
					char []gg2;
					char []gg;
					int i;
					gg2=new char [token.length()];
					gg=new char [token.length()];
					for(i=0;i<token.length();i++)
						gg2[token.length()-1-i]=token.charAt(i);
					gg1="";
					for(i=0;i<token.length();i++)
					{
						gg[i]=(char) (gg2[i]-((50+i)%127));
						gg1=gg1+gg[i];
					}
					jTextArea3.setText(gg1);
					_i_get_msg.setVisible(true);
					
				}
				else if(token.startsWith("G"))
				{
					if(times==0)
					{
						times=1;
						one_to_one=getOne_to_one();
						one_to_one.setVisible(true);
						jTextArea4.setText("");
					}
					String gg1,gg4;
					gg4=st2.nextToken();
					token=st2.nextToken();
					
					gg1=token.replace("^s^"," ");
					token=gg1.replace("^nl^", "\n");
					jTextArea4.setText(jTextArea4.getText()+"\n"+gg4+" "+gg1);
					
				}
				else if(token.startsWith("H"))
				{
					String sender,name,size;
					sender=st2.nextToken();
					name=st2.nextToken();
					size=st2.nextToken();
					int check = JOptionPane.showConfirmDialog(null, sender+ " 님이 파일 : "+name+"("+Integer.parseInt(size)/1024+"KB)을 전송하려고 합니다.\n파일을 수신하시겠습니까?\n저장경로(c:)","파일수신",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if(check == 0){
						JFileChooser jfc = new JFileChooser(name);
						
						jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
						int y=jfc.showSaveDialog(null);
						//
						
						//f =new File(name);
						//JOptionPane.showMessageDialog(null, jfc.getSelectedFile().getPath(), "come", JOptionPane.WARNING_MESSAGE);
						
						//f=jfc.getSelectedFile();
						String k=jfc.getSelectedFile().getPath()+"\\"+name;
						f=new File(k);
						System.out.println(name);
						ins = new BufferedInputStream(socket.getInputStream());
						out = new FileOutputStream(f);
						writer.write("h1 0\n");
						writer.flush();
						jDialog2=getJDialog2();
						jDialog2.setVisible(true);
						jLabel4.setText("받는 파일 : "+name);
						jLabel5.setText("저장 경로 : "+jfc.getCurrentDirectory());
						byte[] buffer = new byte[128];
						int size2 = (int)(Integer.parseInt(size)/128+0.5);
						jProgressBar2.setMaximum(size2);
						jLabel6.setText("0 / "+size+"Bytes");
						int cnt = 0;
						int temp;
						while((temp =ins.read(buffer)) != -1){
							out.write(buffer,0,temp);
							jLabel6.setText((cnt*128+temp)+" / "+size+"Bytes");
							cnt++;
							jProgressBar2.setValue(cnt);
						}
						
						out.close();
						JOptionPane.showMessageDialog(null, "Transmission complete", "ㄱㄳ", JOptionPane.WARNING_MESSAGE);
						jDialog2.setVisible(false);
					}
					else
					{
						writer.write("h1 1\n");
						writer.flush();
					}
				}

				
				System.out.println(userMsg);
			}
			if(times==1)
			{
				jTextArea4.setText(jTextArea4.getText()+"\n** 상대방이 나갔습니다. **");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
class p2p_server extends Thread{
	NewSwingApp myp;
	ServerSocket sc;
	p2p_listen user;
	NewSwingApp getp()
	{
		return myp;
	}
	public p2p_server(NewSwingApp nn) throws IOException{
		myp=nn;
		sc=new ServerSocket(9999);
	}
	public void run(){
		while(true){
			Socket socket;
			try {
				socket = sc.accept();
				user=new p2p_listen(socket, this);
				//JOptionPane.showMessageDialog(null, socket.getInetAddress().toString(), "accepted", JOptionPane.WARNING_MESSAGE);
				user.start();  
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			             
		}
	}
}