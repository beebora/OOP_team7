package mclient;
import java.awt.LayoutManager;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;


/**
 * This code was edited or generated using CloudGarden's Jigloo
 * SWT/Swing GUI Builder, which is free for non-commercial
 * use. If Jigloo is being used commercially (ie, by a corporation,
 * company or business for any purpose whatever) then you
 * should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details.
 * Use of Jigloo implies acceptance of these licensing terms.
 * A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
 * THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
 * LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
 */

public class NewSwingApp extends javax.swing.JFrame {

	private JMenuItem helpMenuItem;
	private JMenu jMenu5;
	private JPanel jPanel2;
	private JButton jButton3;
	private JButton jButton2;
	private JButton jButton1;
	private JTextField jTextField2;
	private JTextField jTextField1;
	private JPanel jPanel1;
	private JMenuItem exitMenuItem;
	private JSeparator jSeparator2;
	private JMenuItem closeFileMenuItem;
	private JMenuItem saveAsMenuItem;
	private JMenuItem saveMenuItem;
	private JButton jButton10;
	private JScrollPane jScrollPane2;
	private JList jList2;
	private JButton jButton9;
	private JTextField jTextField8;
	private JDialog add;
	private JButton jButton8;
	private JButton jButton7;
	private JTextField jTextField5;
	private JTextField jTextField6;
	private JScrollPane jScrollPane6;
	private JList jList3;
	private JDialog notice;
	private JMenuItem jMenuItem3;
	private JButton jButton19;
	private JTextField jTextField13;
	private JTextField jTextField12;
	private JTextField jTextField11;
	private JTextField jTextField7;
	private JDialog d_day_input;

	private JProgressBar jProgressBar1;
	private JFileChooser jFileChooser1;
	private JButton jButton18;
	private JButton jButton17;
	private JLabel jLabel3;
	private JTextField jTextField4;
	private JDialog join;
	private JButton jButton6;
	private JButton jButton5;
	private JButton jButton4;
	private JScrollPane jScrollPane1;
	private JList jList1;
	private JMenuItem openFileMenuItem;
	private JMenuItem newFileMenuItem;
	private JPasswordField jPasswordField1;
	private JPasswordField jPasswordField2;
	private JDialog jDialog1;
	private JMenuItem jMenuItem2;
	private JButton jButton16;
	private JButton jButton15;
	private JTextField jTextField3;
	private JScrollPane jScrollPane5;
	private JTextArea jTextArea3;
	private JDialog one_to_one;
	private JButton jButton14;
	private JLabel jLabel2;
	private JPasswordField jPasswordField3;
	private JButton jButton13;
	private JComboBox jComboBox14;
	private JComboBox jComboBox13;
	private JComboBox jComboBox12;
	private JComboBox jComboBox11;
	private JTextField jTextField10;
	private JComboBox jComboBox10;
	private JTextField jTextField9;
	private JComboBox jComboBox9;
	private JDialog add_info;
	private JScrollPane jScrollPane4;
	private JMenuItem jMenuItem1;
	private JMenu jMenu1;
	private JButton jButton12;
	private JComboBox jComboBox8;
	private JComboBox jComboBox7;
	private JComboBox jComboBox6;
	private JComboBox jComboBox5;
	private JComboBox jComboBox4;
	private JComboBox jComboBox3;
	private JComboBox jComboBox2;
	private JComboBox jComboBox1;
	private JDialog match;
	private JScrollPane jScrollPane3;
	private JTextArea jTextArea2;
	private JDialog get_msg;
	private JButton jButton11;
	private JTextArea jTextArea1;
	private JLabel jLabel1;
	private JDialog sms;
	private JMenu jMenu3;
	private JMenuBar jMenuBar1;
	private DefaultListModel listModel;
	private DefaultListModel listModel3;
	private DefaultListModel listModel2;
	private StringTokenizer st,st2;
	static List<String> ul;
	private File file;
	FileInputStream fis = null;
	DataInputStream dis = null;
	Thread listenThread;
	Thread listenThread2;
	Socket socket,p2p_socket;
	BufferedReader reader,p2p_reader;
	BufferedWriter writer,p2p_writer;
	String server_msg;
	String my_name;
	String your_name;
	String dest_ip;
	String my;
	p2p_server myserver;
	public String get_name()
	{
		return my_name;
	}

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public void Set_your_name(String x)
	{
		jLabel2.setText("jLabel2");
	}
	public void Set_your_msg(String x)
	{
		jTextArea1.setText("\uba54\uc2dc\uc9c0");
	}
	public void closeAll(){
		try{

			reader.close();
			writer.close();
			socket.close();
			System.exit(0);
		}catch(IOException ie){
			System.out.println(ie.getMessage());
		}
	}
	public void listenMessage(){
		listenThread=new Thread(){
			public void run(){
				try{
					while( (server_msg=reader.readLine()) != null ){
						System.out.println(server_msg);
						if(server_msg.startsWith("a1")){
							my_name=jTextField2.getText();
							jPanel1.setVisible(false);
							jPanel2.setVisible(true);
							jButton4.setEnabled(false);
							jMenuItem1.setEnabled(true);
							jMenuItem3.setEnabled(true);
							listModel.removeAllElements();
							ul.removeAll(ul);
							st = new StringTokenizer(server_msg);
							st.nextToken();
							String kk,cc,zz;
							while(st.hasMoreTokens()){
								//JOptionPane.showMessageDialog(null, st.nextToken(), "ㅅㅂ", JOptionPane.WARNING_MESSAGE);
								kk=st.nextToken();
								st2 = new StringTokenizer(kk,",");
								kk=st2.nextToken();
								cc=kk;
								zz=kk;
								kk=st2.nextToken();
								cc=cc+"("+kk+")";
								kk=st2.nextToken();
								zz=zz+" "+kk;
								if(kk.length()<3)
									cc=cc+" - 오프라인";
								else
									cc=cc+" - 온라인";
								listModel.addElement(cc);
								
								ul.add(zz);
							}
						}
						else if(server_msg.startsWith("i2")){
							//listModel3.removeAllElements();
							st = new StringTokenizer(server_msg);
							st.nextToken();
							String kk,cc,zz,amp;
							notice=getNotice();
							notice.setVisible(true);
							//JOptionPane.showMessageDialog(null, "개새기야", "ㅅㅂ", JOptionPane.WARNING_MESSAGE);
							while(st.hasMoreTokens()){
								cc="[";
								kk=st.nextToken();
								st2 = new StringTokenizer(kk,",");
								zz=st2.nextToken();
								cc=cc+zz;
								zz=st2.nextToken();
								cc=cc+"-"+zz;
								zz=st2.nextToken();
								cc=cc+"-"+zz;
								zz=st2.nextToken();
								cc=cc+" ("+zz+") 까지] - ";
								zz=st2.nextToken();
								amp=zz.replace("^s^"," ");
								zz=amp.replace("^nl^","\n" );
								amp=zz.replace("^cm^",",");
								cc=cc+amp;
								listModel3.addElement(cc);
							}
							
						}
						else if(server_msg.startsWith("a2")){
							JOptionPane.showMessageDialog(null, "아뒤나 비번이 틀렸슴", "ㅅㅂ", JOptionPane.WARNING_MESSAGE);
						}
						else if(server_msg.startsWith("a3")){
							//JOptionPane.showMessageDialog(null, server_msg, "ㅅㅂ", JOptionPane.WARNING_MESSAGE);
							st = new StringTokenizer(server_msg);
							String kk;
							String xx,x2,x3;
							st.nextToken();
							kk=st.nextToken();
							x3=st.nextToken();
							for(int i=0;i<listModel.size();i++)
							{
								xx=listModel.getElementAt(i).toString();
								st2 = new StringTokenizer(xx,"(");
								x2=st2.nextToken();
								if(kk.length()==x2.length())
								{
									int j;
									for(j=0;j<kk.length();j++)
									{
										if(kk.charAt(j)!=x2.charAt(j)) break;
									}
									if(j==kk.length())
									{
										for(j=0;j<ul.size();j++)
										{
											st2 = new StringTokenizer(ul.get(j)," ");
											kk=st2.nextToken();
											//JOptionPane.showMessageDialog(null, x2+","+kk+",", "ㅅㅂ", JOptionPane.WARNING_MESSAGE);
											if(x2.length()==kk.length())
											{
												int k;
												for(k=0;k<x2.length();k++)
												{
													if(x2.charAt(k)!=kk.charAt(k))break;													
												}
												if(k==x2.length())
												{
													ul.set(j, x2+" "+x3);
													//JOptionPane.showMessageDialog(null, ul.get(j), "ㅅㅂ", JOptionPane.WARNING_MESSAGE);
													break;
												}
											}

										}
										//JOptionPane.showMessageDialog(null, kk+","+x2+",", "ㅅㅂ", JOptionPane.WARNING_MESSAGE);
										st2 = new StringTokenizer(xx," ");
										kk=st2.nextToken();
										xx=kk+" - 온라인";
										//JOptionPane.showMessageDialog(null, xx, "ㅅㅂ", JOptionPane.WARNING_MESSAGE);
										listModel.setElementAt(xx, i);

										break;
									}
								}

							}					
						}
						else if(server_msg.startsWith("a4")){
							st = new StringTokenizer(server_msg);
							String kk;
							String xx,x2,x3;
							st.nextToken();
							kk=st.nextToken();
							for(int i=0;i<listModel.size();i++)
							{
								xx=listModel.getElementAt(i).toString();
								st2 = new StringTokenizer(xx,"(");
								x2=st2.nextToken();
								if(kk.length()==x2.length())
								{
									int j;
									for(j=0;j<kk.length();j++)
									{
										if(kk.charAt(j)!=x2.charAt(j)) break;
									}
									if(j==kk.length())
									{
										for(j=0;j<ul.size();j++)
										{
											st2 = new StringTokenizer(ul.get(j)," ");
											x3=st2.nextToken();
											//JOptionPane.showMessageDialog(null, x2+","+kk+",", "ㅅㅂ", JOptionPane.WARNING_MESSAGE);
											if(x2.length()==x3.length())
											{
												int k;
												for(k=0;k<x2.length();k++)
												{
													if(x2.charAt(k)!=x3.charAt(k))break;													
												}
												if(k==x2.length())
												{
													//JOptionPane.showMessageDialog(null, x2+" "+x3, "ㅅㅂ", JOptionPane.WARNING_MESSAGE);
													ul.set(j, x2+" 0");
													//JOptionPane.showMessageDialog(null, ul.get(j), "ㅅㅂ", JOptionPane.WARNING_MESSAGE);
													break;
												}
											}

										}
										//JOptionPane.showMessageDialog(null, kk+","+x2+",", "ㅅㅂ", JOptionPane.WARNING_MESSAGE);
										st2 = new StringTokenizer(xx," ");
										kk=st2.nextToken();
										xx=kk+" - 오프라인";
										//JOptionPane.showMessageDialog(null, xx, "ㅅㅂ", JOptionPane.WARNING_MESSAGE);
										listModel.setElementAt(xx, i);
										//jList1.updateUI();
										break;
									}
								}

							}	
						}
						else if(server_msg.startsWith("b1")){
							JOptionPane.showMessageDialog(null, "가입성공", "ㅊㅋ", JOptionPane.WARNING_MESSAGE);
						}
						else if(server_msg.startsWith("b2")){
							JOptionPane.showMessageDialog(null, "이미 가입된 ID 입니다.", "ㅅㅂ", JOptionPane.WARNING_MESSAGE);
						}
						else if(server_msg.startsWith("c1")){
							st = new StringTokenizer(server_msg);
							String kk;
							st.nextToken();
							while(st.hasMoreTokens()){
								kk=st.nextToken()+"("+jTextField8.getText()+")";
								listModel2.addElement(kk);
							}
							if(listModel2.size()>0)
							{
							}
							else
							{
								JOptionPane.showMessageDialog(null, "검색된 회원이 없다.", "ㅅㅂ", JOptionPane.WARNING_MESSAGE);
							}
						}
						else if(server_msg.startsWith("d1")){
							JOptionPane.showMessageDialog(null, "추가에 성공했습니다..", "ㄳ", JOptionPane.WARNING_MESSAGE);
							st = new StringTokenizer(server_msg);
							String kk="0";
							String ax;
							st.nextToken();
							if(st.hasMoreElements())
								kk=st.nextToken();
							ax=kk;
							if(kk.length()>2) kk="- 온라인";
							else kk="- 오프라인";
							kk=jList2.getSelectedValue().toString()+" "+kk;
							listModel.addElement(kk);
							st = new StringTokenizer(kk,"(");
							kk=st.nextToken();
							ul.add(kk+" "+ax);
					
						}else if(server_msg.startsWith("d2")){
							JOptionPane.showMessageDialog(null, "추가에 실패햇음.", "ㅅㅂ", JOptionPane.WARNING_MESSAGE);
						}
						else if(server_msg.startsWith("f1"))
						{
							st = new StringTokenizer(server_msg);
							st.nextToken();
							String kk;
							kk=st.nextToken();
							if(kk.startsWith("0"))
							{

								if(JOptionPane.showConfirmDialog(null, "추가정보를 등록하지 않았습니다.\n 추가정보를 등록하지 않으면 선택 쪽지 보내기를 할 수 없습니다.\n 추가 정보를 등록겠습니까?", "제목표시줄", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)==0)
								{
									process_add_info();
								}
							}
							else
							{
								process_find_match();
							}
						}
						else if(server_msg.startsWith("f2"))
						{
							st = new StringTokenizer(server_msg);
							st.nextToken();
							String kk;
							kk=st.nextToken();
							if(kk.startsWith("0"))
							{
								JOptionPane.showMessageDialog(null, "등록에 성공 했습니다.", "ㅊㅋ", JOptionPane.WARNING_MESSAGE);
							}
							else
							{
								JOptionPane.showMessageDialog(null, "등록에 실패 했습니다.", "ㅅㅂ", JOptionPane.WARNING_MESSAGE);							
							}

						}
						else if(server_msg.startsWith("f3"))
						{
							st = new StringTokenizer(server_msg);
							st.nextToken();
							String kk;
							String xx,yy;
							xx=jTextArea1.getText();
							yy=xx.replace(" ", "^s^");
							xx=yy.replace("\n", "^nl^");
							String kx="E "+my_name+" "+xx;
							int i=0;
							while(st.hasMoreTokens())
							{
								kk=st.nextToken();
								Send_to_p2p(kk,kx);
								i++;
							}
							JOptionPane.showMessageDialog(null, "조건에 맞는 사용자 중에서 접속한 "+i+"명에게 쪽지를 전송했습니다.", "ㅊㅋ", JOptionPane.WARNING_MESSAGE);
						}
						else if(server_msg.startsWith("i1"))
						{
							st = new StringTokenizer(server_msg);
							st.nextToken();
							String kk;
							kk=st.nextToken();
							if(kk.startsWith("0"))
							{
								JOptionPane.showMessageDialog(null, "등록에 성공 했습니다.", "d-day 추가 결과", JOptionPane.WARNING_MESSAGE);
							}
							else
							{
								JOptionPane.showMessageDialog(null, "등록에 실패 했습니다.", "d-day 추가 결과", JOptionPane.WARNING_MESSAGE);							
							}
						}
					}
					//System.out.println("쓰레드 그냥 종료");
					//closeAll();
				}catch(Exception e){
					//System.out.println(e);
					//closeAll();
					System.out.println("쓰레[드 캐치 종료");
					JOptionPane.showMessageDialog(null, "서버와 접속이 끊어 졌습니다..", "ㅊㅋ", JOptionPane.WARNING_MESSAGE);
					jButton6MouseClicked(null);
				}
			}
		};
		listenThread.setDaemon(true);
		listenThread.start();
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				NewSwingApp inst = new NewSwingApp();
				ul = new ArrayList<String>();

				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public NewSwingApp() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				this.setTitle("try \uba54\uc2e0\uc800");
				this.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent evt) {
						thisWindowClosing(evt);
					}
				});
				{
					jPanel1 = new JPanel();
					getContentPane().add(jPanel1);
					jPanel1.setBounds(0, 0, 258, 286);
					jPanel1.setLayout(null);
					{
						jTextField1 = new JTextField();
						jPanel1.add(jTextField1);
						jTextField1.setText("\uc11c\ubc84IP");
						jTextField1.setBounds(26, 12, 92, 22);
						jTextField1.setNextFocusableComponent(jTextField2);

					}
					{
						jTextField2 = new JTextField();
						jPanel1.add(jTextField2);
						jTextField2.setText("\uc544\uc774\ub514");
						jTextField2.setBounds(26, 46, 92, 22);
						jTextField2.setNextFocusableComponent(jPasswordField1);
					}
					{
						jButton1 = new JButton();
						jPanel1.add(jButton1);
						jButton1.setText("\uc811\uc18d");
						jButton1.setBounds(130, 12, 92, 22);
					}
					{
						jButton2 = new JButton();
						jPanel1.add(jButton2);
						jButton2.setText("\ub85c\uadf8\uc778");
						jButton2.setBounds(130, 48, 92, 22);
						jButton2.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								jButton2MouseClicked(evt);
							}
						});
					}
					{
						jButton3 = new JButton();
						jPanel1.add(jButton3);
						jButton3.setText("\ud68c\uc6d0\uac00\uc785");
						jButton3.setBounds(130, 80, 92, 22);
					}
					{
						jPasswordField1 = new JPasswordField();
						jPasswordField1.setText("1111");
						jPanel1.add(jPasswordField1);
						jPasswordField1.setBounds(26, 80, 92, 22);
						jPasswordField1.addKeyListener(new KeyAdapter() {
							public void keyPressed(KeyEvent evt) {
								jPasswordField1KeyPressed(evt);
							}
						});

					}
					jButton1.setEnabled(true);
					jButton1.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							jButton1MouseClicked(evt);
						}
					});
					jButton2.setEnabled(false);
					jButton3.setEnabled(false);
					jPasswordField1.setEnabled(false);
					jButton3.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							jButton3MouseClicked(evt);
						}
					});
					jTextField1.setEditable(true);
					jTextField1.addKeyListener(new KeyAdapter() {
						public void keyPressed(KeyEvent evt) {
							jTextField1KeyPressed(evt);
						}
					});
					jTextField2.setEditable(false);
					jPasswordField1.setEditable(false);

				}
				{
					jPanel2 = new JPanel();
					getContentPane().add(jPanel2);
					jPanel2.setBounds(0, 0, 258, 313);
					jPanel2.setLayout(null);
					{
						jScrollPane1 = new JScrollPane();
						jPanel2.add(jScrollPane1);
						jScrollPane1.setBounds(26, 20, 225, 170);
						{
							listModel = new DefaultListModel();
							jList1 = new JList(listModel);
							jScrollPane1.setViewportView(jList1);
							jList1.setBounds(26, 12, 82, 58);
							jList1.setPreferredSize(new java.awt.Dimension(206, 167));
							jList1.addMouseListener(new MouseAdapter() {
								public void mouseClicked(MouseEvent evt) {
									jList1MouseClicked(evt);
								}
							});
							jList1.addListSelectionListener(new ListSelectionListener() {
								public void valueChanged(ListSelectionEvent evt) {
									jList1ValueChanged(evt);
								}
							});
						}
					}
					{
						jButton4 = new JButton();
						jPanel2.add(jButton4);
						jButton4.setText("\ucabd\uc9c0 \ubcf4\ub0b4\uae30");
						jButton4.setBounds(26, 196, 113, 22);
						jButton4.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								jButton4MouseClicked(evt);
							}
						});
					}
					{
						jButton5 = new JButton();
						jPanel2.add(jButton5);
						jButton5.setText("\ub300\ud654 \ud558\uae30");
						jButton5.setBounds(150, 196, 108, 22);
						jButton5.setEnabled(false);
						jButton5.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								jButton5MouseClicked(evt);
							}
						});
					}
					{
						jButton6 = new JButton();
						jPanel2.add(jButton6);
						jButton6.setText("\ub85c\uadf8\uc544\uc6c3");
						jButton6.setBounds(26, 223, 113, 22);
						jButton6.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								jButton6MouseClicked(evt);
							}
						});
					}
					{
						jButton8 = new JButton();
						jPanel2.add(jButton8);
						jButton8.setText("\uce5c\uad6c\ucd94\uac00");
						jButton8.setBounds(150, 223, 108, 22);
						jButton8.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								jButton8MouseClicked(evt);
							}
						});
					}
					jPanel2.setVisible(false);
				}
			}
			this.setSize(285, 345);
			{
				jMenuBar1 = new JMenuBar();
				setJMenuBar(jMenuBar1);
				{
					jMenu3 = new JMenu();
					jMenuBar1.add(jMenu3);
					jMenu3.setText("File");
					jMenu3.setPreferredSize(new java.awt.Dimension(43, 21));
					{
						newFileMenuItem = new JMenuItem();
						jMenu3.add(newFileMenuItem);
						newFileMenuItem.setText("New");
					}
					{
						openFileMenuItem = new JMenuItem();
						jMenu3.add(openFileMenuItem);
						openFileMenuItem.setText("Open");
					}
					{
						saveMenuItem = new JMenuItem();
						jMenu3.add(saveMenuItem);
						saveMenuItem.setText("Save");
					}
					{
						saveAsMenuItem = new JMenuItem();
						jMenu3.add(saveAsMenuItem);
						saveAsMenuItem.setText("Save As ...");
					}
					{
						closeFileMenuItem = new JMenuItem();
						jMenu3.add(closeFileMenuItem);
						closeFileMenuItem.setText("Close");
					}
					{
						jSeparator2 = new JSeparator();
						jMenu3.add(jSeparator2);
					}
					{
						exitMenuItem = new JMenuItem();
						jMenu3.add(exitMenuItem);
						exitMenuItem.setText("Exit");
					}
				}
				{
					jMenu5 = new JMenu();
					jMenuBar1.add(getJMenu1());
					jMenuBar1.add(jMenu5);
					jMenu5.setText("Help");
					jMenu5.setPreferredSize(new java.awt.Dimension(50, 21));
					{
						helpMenuItem = new JMenuItem();
						jMenu5.add(helpMenuItem);
						helpMenuItem.setText("Help");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jButton1MouseClicked(MouseEvent evt) {
		System.out.println("jButton1.mouseClicked, event="+evt);
		//TODO add your code for jButton1.mouseClicked
		
		
		try {
			socket=new Socket(jTextField1.getText(),5555);
			reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "서버에 접속 할 수 없습니다.", "접속 실패", JOptionPane.WARNING_MESSAGE);
			return;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "서버에 접속 할 수 없습니다.", "접속 실패", JOptionPane.WARNING_MESSAGE);
			return;
		}


		jTextField2.setEnabled(true);
		jPasswordField1.setEnabled(true);
		jTextField1.setEnabled(false);
		jTextField2.setEditable(true);
		jPasswordField1.setEditable(true);
		jButton1.setEnabled(false);
		jButton2.setEnabled(true);
		jButton3.setEnabled(true);
		listenMessage();
		try {
			myserver=new p2p_server(this);
			myserver.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void Send_to_server(String x)
	{
		try {
			writer.write(x+"\n");
			writer.flush();
			System.out.println(x);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "서버 전송에 실패 했습니다.", "났다", JOptionPane.WARNING_MESSAGE);
		}

	}

	private void jButton2MouseClicked(MouseEvent evt) {
		System.out.println("jButton2.mouseClicked, event="+evt);
		//TODO add your code for jButton2.mouseClicked
		if(jButton2.isEnabled())
		{
			Send_to_server("A "+jTextField2.getText()+" "+jPasswordField1.getText());

		}
		//jPanel1.setVisible(false);
		//jPanel2.setVisible(true);
	}

	private void jButton3MouseClicked(MouseEvent evt) {
		System.out.println("jButton3.mouseClicked, event="+evt);
		//TODO add your code for jButton3.mouseClicked
		if(jButton3.isEnabled())
		{
			join=getJoin();
			join.setVisible(true);
			join.setTitle("Newbe");
			join.setAlwaysOnTop(true);

			join.setPreferredSize(new java.awt.Dimension(157, 220));
			join.setBounds(357, 321, 157, 220);

			join.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent evt) {
					joinKeyPressed(evt);
				}
			});
		}
	}

	private JDialog getJoin() {
		if(join == null) {
			join = new JDialog(this);
			join.getContentPane().setLayout(null);
			{
				jTextField4 = new JTextField();
				join.getContentPane().add(jTextField4);
				jTextField4.setText("\uc774\ub984");
				jTextField4.setBounds(22, 15, 102, 22);
			}
			{
				jTextField5 = new JTextField();
				join.getContentPane().add(jTextField5);
				jTextField5.setText("\uc544\uc774\ub514");
				jTextField5.setBounds(22, 49, 102, 22);
			}
			{
				jButton7 = new JButton();
				join.getContentPane().add(jButton7);
				join.getContentPane().add(getJPasswordField2());
				join.getContentPane().add(getJPasswordField3());
				jButton7.setText("\ud68c\uc6d0\uac00\uc785");
				jButton7.setBounds(22, 158, 102, 22);
				jButton7.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						jButton7MouseClicked(evt);
					}
				});

			}
			join.setSize(157, 220);
			join.setBounds(0, 0, 157, 220);
		}
		return join;
	}

	private void jButton7MouseClicked(MouseEvent evt) {
		System.out.println("jButton7.mouseClicked, event="+evt);
		//TODO add your code for jButton7.mouseClicked
		String x,y,z,k;

		x=jPasswordField2.getText();
		y=jPasswordField3.getText();
		z=jTextField5.getText();
		k=jTextField4.getText();

		if(x.length()!=y.length())
		{
			JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않아", "틀려", JOptionPane.WARNING_MESSAGE);
			return;
		}
		for(int i=0;i<x.length();i++)
		{
			if(x.charAt(i)!=y.charAt(i))
			{
				JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않아", "틀려", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}

		CharSequence char0 = " ", char1 = ",",char2 = "(",char3 = ")";
		if(x.contains(char0)||x.contains(char1)||x.contains(char2)||x.contains(char3))
		{
			JOptionPane.showMessageDialog(null, "비밀번호에 공백/콤마/괄호를 넣지마라", "틀려", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(z.contains(char0)||z.contains(char1)||z.contains(char2)||z.contains(char3))
		{
			JOptionPane.showMessageDialog(null, "ID에 공백/콤마/괄호를 넣지마라", "틀려", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(k.contains(char0)||k.contains(char1)||k.contains(char2)||k.contains(char3))
		{
			JOptionPane.showMessageDialog(null, "이름에 공백/ 콤마/괄호를 넣지마라", "틀려", JOptionPane.WARNING_MESSAGE);
			return;
		}
		//JOptionPane.showMessageDialog(null, "성공이야", "맞다", JOptionPane.WARNING_MESSAGE);
		join.setVisible(false);
		//jTextField3.setText("개새기야");
		Send_to_server("B "+k+" "+z+" "+x);


	}

	

	private void thisWindowClosing(WindowEvent evt) {
		System.out.println("this.windowClosing, event="+evt);
		//TODO add your code for this.windowClosing
		System.exit(0);

	}

	private void jTextField1KeyPressed(KeyEvent evt) {
		//System.out.println("jTextField1.keyPressed, event="+evt);
		//TODO add your code for jTextField1.keyPressed
		if(evt.getKeyCode()==10)
		{
			jButton1MouseClicked(null);
		}

	}

	private void jButton8MouseClicked(MouseEvent evt) {
		System.out.println("jButton8.mouseClicked, event="+evt);
		//TODO add your code for jButton8.mouseClicked
		//listModel.addElement("개새기야");
		add=getAdd();
		add.setVisible(true);
		add.setPreferredSize(new java.awt.Dimension(276, 191));
	}

	private void joinKeyPressed(KeyEvent evt) {
		System.out.println("join.keyPressed, event="+evt);
		//TODO add your code for join.keyPressed
	}

	private void jTextField3KeyPressed(KeyEvent evt) {
		System.out.println("jTextField3.keyPressed, event="+evt);
		//TODO add your code for jTextField3.keyPressed
		if(evt.getKeyCode()==10)
		{
			jButton16MouseClicked(null);
		}
	}

	private void jButton6MouseClicked(MouseEvent evt) {
		System.out.println("jButton6.mouseClicked, event="+evt);
		//TODO add your code for jButton6.mouseClicked
		if(jButton6.isEnabled())
		{
			try {
				socket.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(this.isVisible())
			{
				this.setVisible(false);
				main(null);
			}
		}

	}

	private JDialog getAdd() {
		if(add == null) {
			add = new JDialog(this);
			add.getContentPane().setLayout(null);
			add.setTitle("\uce5c\uad6c \ucd94\uac00");
			{
				jTextField8 = new JTextField();
				add.getContentPane().add(jTextField8);
				jTextField8.setText("\uac80\uc0c9\ud560 \uce5c\uad6c \uc774\ub984");
				jTextField8.setBounds(7, 20, 154, 22);
				jTextField8.addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent evt) {
						jTextField8KeyPressed(evt);
					}
				});
			}
			{
				jButton9 = new JButton();
				add.getContentPane().add(jButton9);
				jButton9.setText("\uac80\uc0c9");
				jButton9.setBounds(173, 20, 78, 22);
				jButton9.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						jButton9MouseClicked(evt);
					}
				});
			}
			{
				jScrollPane2 = new JScrollPane();
				add.getContentPane().add(jScrollPane2);
				jScrollPane2.setBounds(7, 54, 227, 56);
				{
					listModel2 = new DefaultListModel();
					jList2 = new JList(listModel2);
					jScrollPane2.setViewportView(jList2);
					jList2.setBounds(7, 54, 227, 76);
					jList2.setPreferredSize(new java.awt.Dimension(224, 44));
					jList2.addListSelectionListener(new ListSelectionListener() {
						public void valueChanged(ListSelectionEvent evt) {
							jList2ValueChanged(evt);
						}
					});

				}
			}
			{
				jButton10 = new JButton();
				add.getContentPane().add(jButton10);
				jButton10.setText("\ucd94\uac00 \ud558\uae30");
				jButton10.setBounds(79, 123, 102, 22);
				jButton10.setEnabled(false);
				jButton10.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						jButton10MouseClicked(evt);
					}
				});
			}
			add.setSize(276, 191);
			add.setBounds(200, 300, 276, 191);
		}
		return add;
	}

	private void jButton9MouseClicked(MouseEvent evt) {
		System.out.println("jButton9.mouseClicked, event="+evt);
		//TODO add your code for jButton9.mouseClicked
		jButton10.setEnabled(false);
		listModel2.removeAllElements();
		Send_to_server("C "+jTextField8.getText());

	}

	private void jList2ValueChanged(ListSelectionEvent evt) {
		System.out.println("jList2.valueChanged, event="+evt);
		//TODO add your code for jList2.valueChanged
		//JOptionPane.showMessageDialog(null, jList2.getSelectedValue().toString(), "리스트", JOptionPane.WARNING_MESSAGE);
		jButton10.setEnabled(true);
	}

	private void jButton10MouseClicked(MouseEvent evt) {
		System.out.println("jButton10.mouseClicked, event="+evt);
		//TODO add your code for jButton10.mouseClicked
		if(jButton10.isEnabled()){

			//JOptionPane.showMessageDialog(null, jList2.getSelectedValue().toString(), "리스트", JOptionPane.WARNING_MESSAGE);
			st = new StringTokenizer(jList2.getSelectedValue().toString(),"(");
			String kk;
			kk=st.nextToken();
			Send_to_server("D "+kk);
			add.setVisible(false);
		}

	}

	private void jTextField8KeyPressed(KeyEvent evt) {
		System.out.println("jTextField8.keyPressed, event="+evt);
		//TODO add your code for jTextField8.keyPressed
		if(evt.getKeyCode()==10)
		{
			jButton9MouseClicked(null);
		}
	}

	private void jList1ValueChanged(ListSelectionEvent evt) {
		System.out.println("jList1.valueChanged, event="+evt);
		//TODO add your code for jList1.valueChanged
		jButton4.setEnabled(true);
		jButton5.setEnabled(true);
		jButton5.setEnabled(true);
		jMenuItem2.setEnabled(true);
	}
	void Send_to_p2p(String ip,String Msg)
	{
		try {
			p2p_socket=new Socket(ip,9999);
			p2p_writer=new BufferedWriter(new OutputStreamWriter(p2p_socket.getOutputStream()));
			p2p_writer.write(Msg+"\n");
			p2p_writer.flush();
			p2p_socket.close();
			System.out.println(Msg);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	public void Set_receiver(String xx)
	{
		sms=getSms();
		sms.setPreferredSize(new java.awt.Dimension(320, 227));
		jLabel1.setText("받는이 : "+xx);
		sms.setVisible(true);
	}
	public void Set_dest_ip(String xx)
	{
		dest_ip=xx;
	}
	private void jButton4MouseClicked(MouseEvent evt) {
		//System.out.println("jButton4.mouseClicked, event="+evt);
		//TODO add your code for jButton4.mouseClicked
		if(jButton4.isEnabled())
		{
			st2 = new StringTokenizer(jList1.getSelectedValue().toString(),"(");
			String x2,kk,x3;
			x2=st2.nextToken();


			your_name=x2;
			int j;
			for(j=0;j<ul.size();j++)
			{
				st2 = new StringTokenizer(ul.get(j)," ");
				kk=st2.nextToken();
				if(x2.length()==kk.length())
				{
					int k;
					for(k=0;k<x2.length();k++)
					{
						if(x2.charAt(k)!=kk.charAt(k))break;													
					}
					if(k==x2.length())
					{
						kk=st2.nextToken();
						if(kk.length()>2)
						{
							Set_dest_ip(kk);
							Set_receiver(your_name);
						}
						else
							JOptionPane.showMessageDialog(null, "offline", "ㅅㅂ", JOptionPane.WARNING_MESSAGE);

						break;
					}
				}

			}
		}
		else
		{
			//JOptionPane.showMessageDialog(null, "낫인", "ㅅㅂ", JOptionPane.WARNING_MESSAGE);
		}
	}

	private void jList1MouseClicked(MouseEvent evt) {
		if(evt.getClickCount()==2)
		{
			jButton4MouseClicked(null);
			//JOptionPane.showMessageDialog(null, jList1.getSelectedValue().toString(), "ㅅㅂ", JOptionPane.WARNING_MESSAGE);
		}
		jMenuItem2.setEnabled(true);
	}

	private JDialog getSms() {
		if(sms == null) {
			sms = new JDialog(this);
			sms.getContentPane().setLayout(null);
			sms.setTitle("\ucabd\uc9c0 \ubcf4\ub0b4\uae30");
			sms.getContentPane().add(getJLabel1());
			sms.getContentPane().add(getJButton11());
			sms.getContentPane().add(getJScrollPane3());
			sms.getContentPane().add(getJButton14());
			sms.setSize(320, 227);
			sms.setBounds(320, 320, 320, 227);
		}
		return sms;
	}

	private JLabel getJLabel1() {
		if(jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("jLabel1");
			jLabel1.setBounds(17, 12, 225, 16);
		}
		return jLabel1;
	}

	private JButton getJButton11() {
		if(jButton11 == null) {
			jButton11 = new JButton();
			jButton11.setText("\ucabd\uc9c0 \ubcf4\ub0b4\uae30");
			jButton11.setBounds(12, 167, 114, 22);
			jButton11.setEnabled(false);
			jButton11.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					jButton11MouseClicked(evt);
				}
			});
		}
		return jButton11;
	}

	private void jButton11MouseClicked(MouseEvent evt) {
		//System.out.println("jButton11.mouseClicked, event="+evt);
		//TODO add your code for jButton11.mouseClicked
		if(jButton11.isEnabled())
		{
			sms.setVisible(false);
			String xx,yy;
			xx=jTextArea2.getText();
			yy=xx.replace(" ", "^s^");
			xx=yy.replace("\n", "^nl^");
			Send_to_p2p(dest_ip,"E "+my_name+" "+xx);
			jTextArea2.setText(null);
		}
	}



	private JTextArea getJTextArea2() {
		if(jTextArea2 == null) {
			jTextArea2 = new JTextArea();
			jTextArea2.setText("jTextArea2");
			jTextArea2.setBounds(17, 40, 225, 81);
			jTextArea2.setPreferredSize(new java.awt.Dimension(260, 111));
			jTextArea2.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent evt) {
					jTextArea2KeyPressed(evt);
				}
			});
		}
		return jTextArea2;
	}

	private JScrollPane getJScrollPane3() {
		if(jScrollPane3 == null) {
			jScrollPane3 = new JScrollPane();
			jScrollPane3.setBounds(17, 40, 278, 121);
			jScrollPane3.setViewportView(getJTextArea2());
		}
		return jScrollPane3;
	}

	private void jTextArea2KeyPressed(KeyEvent evt) {
		System.out.println("jTextArea2.keyPressed, event="+evt);
		//TODO add your code for jTextArea2.keyPressed
		jButton11.setEnabled(true);
		jButton14.setEnabled(true);
	}

	private JDialog getMatch() {
		if(match == null) {
			match = new JDialog(this);
			match.getContentPane().setLayout(null);
			match.setPreferredSize(new java.awt.Dimension(321, 248));
			match.setTitle("\uc120\ud0dd \ucabd\uc9c0 \ubcf4\ub0b4\uae30");
			match.getContentPane().add(getJComboBox1());
			match.getContentPane().add(getJComboBox2());
			match.getContentPane().add(getJComboBox3());
			match.getContentPane().add(getJComboBox4());
			match.getContentPane().add(getJComboBox5());
			match.getContentPane().add(getJComboBox6());
			match.getContentPane().add(getJComboBox7());
			match.getContentPane().add(getJComboBox8());
			match.getContentPane().add(getJScrollPane4());
			match.getContentPane().add(getJButton12());
			match.setSize(321, 248);
			match.setBounds(200, 300,321 , 248);
		}
		return match;
	}

	private JComboBox getJComboBox1() {
		if(jComboBox1 == null) {
			ComboBoxModel jComboBox1Model = 
				new DefaultComboBoxModel(
						new String[] { "성별", "남","여","무관" });
			jComboBox1 = new JComboBox();
			jComboBox1.setModel(jComboBox1Model);
			jComboBox1.setBounds(5, 12, 66, 23);
			jComboBox1.setAutoscrolls(true);
		}
		return jComboBox1;
	}

	private JComboBox getJComboBox2() {
		if(jComboBox2 == null) {
			ComboBoxModel jComboBox2Model = 
				new DefaultComboBoxModel(
						new String[] { "나이", "1-15","15-20","20-22","22-25","25-27","27-30","30-35","35-45","45-50","50-60","60-100","100-256","무관" });
			jComboBox2 = new JComboBox();
			jComboBox2.setModel(jComboBox2Model);
			jComboBox2.setBounds(77, 12, 67, 23);
		}
		return jComboBox2;
	}

	private JComboBox getJComboBox3() {
		if(jComboBox3 == null) {
			ComboBoxModel jComboBox3Model = 
				new DefaultComboBoxModel(
						new String[] { "별자리", "물병","물고기","양","황소","쌍둥이","게","사자","처녀","천칭","전갈","사수","염소","무관" });
			jComboBox3 = new JComboBox();
			jComboBox3.setModel(jComboBox3Model);
			jComboBox3.setBounds(150, 12, 68, 22);
		}
		return jComboBox3;
	}

	private JComboBox getJComboBox4() {
		if(jComboBox4 == null) {
			ComboBoxModel jComboBox4Model = 
				new DefaultComboBoxModel(
						new String[] { "키", "1-140","140-150","150-155","155-160","160-165","165-168","168-172","172-174","174-176","176-178","178-180","180-182","182-186","186-190","190-200","200-210","210-230","230-256","무관" });
			jComboBox4 = new JComboBox();
			jComboBox4.setModel(jComboBox4Model);
			jComboBox4.setBounds(230, 12, 71, 22);
		}
		return jComboBox4;
	}

	private JComboBox getJComboBox5() {
		if(jComboBox5 == null) {
			ComboBoxModel jComboBox5Model = 
				new DefaultComboBoxModel(
						new String[] { "얼굴", "얼짱","평범","폭탄","괴물","유니크","인형","조각","귀여움","이국적","무관" });
			jComboBox5 = new JComboBox();
			jComboBox5.setModel(jComboBox5Model);
			jComboBox5.setBounds(5, 53, 66, 22);
		}
		return jComboBox5;
	}

	private JComboBox getJComboBox6() {
		if(jComboBox6 == null) {
			ComboBoxModel jComboBox6Model = 
				new DefaultComboBoxModel(
						new String[] { "성격", "엉뚱","호탕","험악","수줍음","터프","바보","천재","또라이","가식","무관" });
			jComboBox6 = new JComboBox();
			jComboBox6.setModel(jComboBox6Model);
			jComboBox6.setBounds(77, 53, 66, 22);
		}
		return jComboBox6;
	}

	private JComboBox getJComboBox7() {
		if(jComboBox7 == null) {
			ComboBoxModel jComboBox7Model = 
				new DefaultComboBoxModel(
						new String[] { "취미", "도박","공예","그리기","여행","스포츠","댄스","게임","연주","노래","수집","무관" });
			jComboBox7 = new JComboBox();
			jComboBox7.setModel(jComboBox7Model);
			jComboBox7.setBounds(149, 53, 69, 22);
		}
		return jComboBox7;
	}

	private JComboBox getJComboBox8() {
		if(jComboBox8 == null) {
			ComboBoxModel jComboBox8Model = 
				new DefaultComboBoxModel(
						new String[] { "지역", "부산","경남","울산","서울","경기","인천","강원","충남","충북","대전","경북","대구","전남","전북","광주","제주","외국","무관" });
			jComboBox8 = new JComboBox();
			jComboBox8.setModel(jComboBox8Model);
			jComboBox8.setBounds(230, 53, 71, 22);
		}
		return jComboBox8;
	}

	private JTextArea getJTextArea1() {
		if(jTextArea1 == null) {
			jTextArea1 = new JTextArea();
			jTextArea1.setBounds(5, 81, 296, 101);
		}
		return jTextArea1;
	}

	private JButton getJButton12() {
		if(jButton12 == null) {
			jButton12 = new JButton();
			jButton12.setText("\ubcf4\ub0b4\uae30");
			jButton12.setBounds(103, 187, 112, 22);
			jButton12.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					jButton12MouseClicked(evt);
				}
			});
		}
		return jButton12;
	}

	private JMenu getJMenu1() {
		if(jMenu1 == null) {
			jMenu1 = new JMenu();
			jMenu1.setText("\uae30\ub2a5");
			jMenu1.setPreferredSize(new java.awt.Dimension(56, 23));
			jMenu1.add(getJMenuItem1());
			jMenu1.add(getJMenuItem2());
			jMenu1.add(getJMenuItem3());
		}
		return jMenu1;
	}

	private JMenuItem getJMenuItem1() {
		if(jMenuItem1 == null) {
			jMenuItem1 = new JMenuItem();
			jMenuItem1.setText("\ub9e4\uce6d \ucabd\uc9c0");
			jMenuItem1.setEnabled(false);
			jMenuItem1.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent evt) {
					jMenuItem1MousePressed(evt);
				}
				
			});
		}
		return jMenuItem1;
	}

	void process_find_match()
	{
		match=getMatch();
		match.setVisible(true);
	}
	private void jMenuItem1MousePressed(MouseEvent evt) {
		System.out.println("jMenuItem1.mousePressed, event="+evt);
		//TODO add your code for jMenuItem1.mousePressed
		if(jMenuItem1.isEnabled())
		{
			Send_to_server("F1 "+my_name);

		}
	}

	private JScrollPane getJScrollPane4() {
		if(jScrollPane4 == null) {
			jScrollPane4 = new JScrollPane();
			jScrollPane4.setBounds(5, 81, 296, 101);
			jScrollPane4.setViewportView(getJTextArea1());
		}
		return jScrollPane4;
	}

	private JDialog getAdd_info() {
		if(add_info == null) {
			add_info = new JDialog(this);
			add_info.getContentPane().setLayout(null);
			add_info.setTitle("\ucd94\uac00 \uc815\ubcf4 \uc785\ub825");
			add_info.setPreferredSize(new java.awt.Dimension(358, 157));
			add_info.getContentPane().add(getJComboBox9());
			add_info.getContentPane().add(getJTextField9());
			add_info.getContentPane().add(getJComboBox10());
			add_info.getContentPane().add(getJTextField10());
			add_info.getContentPane().add(getJComboBox11());
			add_info.getContentPane().add(getJComboBox12());
			add_info.getContentPane().add(getJComboBox13());
			add_info.getContentPane().add(getJComboBox14());
			add_info.getContentPane().add(getJButton13());
			add_info.setSize(358, 157);
			add_info.setBounds(200, 300, 358, 157);
		}
		return add_info;
	}

	private JComboBox getJComboBox9() {
		if(jComboBox9 == null) {
			ComboBoxModel jComboBox9Model = 
				new DefaultComboBoxModel(
						new String[] { "성별", "남자","여자" });
			jComboBox9 = new JComboBox();
			jComboBox9.setModel(jComboBox9Model);
			jComboBox9.setBounds(12, 12, 69, 22);
		}
		return jComboBox9;
	}

	private JTextField getJTextField9() {
		if(jTextField9 == null) {
			jTextField9 = new JTextField();
			jTextField9.setText("\ucd9c\uc0dd\ub144\ub3c4");
			jTextField9.setBounds(87, 13, 67, 22);
		}
		return jTextField9;
	}

	private JComboBox getJComboBox10() {
		if(jComboBox10 == null) {
			ComboBoxModel jComboBox10Model = 
				new DefaultComboBoxModel(
						new String[] { "별자리", "물병","물고기","양","황소","쌍둥이","게","사자","처녀","천칭","전갈","사수","염소" });
			jComboBox10 = new JComboBox();
			jComboBox10.setModel(jComboBox10Model);
			jComboBox10.setBounds(166, 12, 75, 22);
		}
		return jComboBox10;
	}

	private JTextField getJTextField10() {
		if(jTextField10 == null) {
			jTextField10 = new JTextField();
			jTextField10.setText("\ud0a4");
			jTextField10.setBounds(253, 13, 63, 22);
		}
		return jTextField10;
	}

	private JComboBox getJComboBox11() {
		if(jComboBox11 == null) {
			ComboBoxModel jComboBox11Model = 
				new DefaultComboBoxModel(
						new String[] { "얼굴", "얼짱","평범","폭탄","괴물","유니크","인형","조각","귀여움","이국적" });
			jComboBox11 = new JComboBox();
			jComboBox11.setModel(jComboBox11Model);
			jComboBox11.setBounds(12, 47, 69, 22);
		}
		return jComboBox11;
	}

	private JComboBox getJComboBox12() {
		if(jComboBox12 == null) {
			ComboBoxModel jComboBox12Model = 
				new DefaultComboBoxModel(
						new String[] { "성격", "엉뚱","호탕","험악","수줍음","터프","바보","천재","또라이","가식" });
			jComboBox12 = new JComboBox();
			jComboBox12.setModel(jComboBox12Model);
			jComboBox12.setBounds(87, 47, 67, 22);
		}
		return jComboBox12;
	}

	private JComboBox getJComboBox13() {
		if(jComboBox13 == null) {
			ComboBoxModel jComboBox13Model = 
				new DefaultComboBoxModel(
						new String[] { "취미", "도박","공예","그리기","여행","스포츠","댄스","게임","연주","노래","수집" });
			jComboBox13 = new JComboBox();
			jComboBox13.setModel(jComboBox13Model);
			jComboBox13.setBounds(166, 47, 75, 22);
		}
		return jComboBox13;
	}

	private JComboBox getJComboBox14() {
		if(jComboBox14 == null) {
			ComboBoxModel jComboBox14Model = 
				new DefaultComboBoxModel(
						new String[] { "지역", "부산","경남","울산","서울","경기","인천","강원","충남","충북","대전","경북","대구","전남","전북","광주","제주","외국" });
			jComboBox14 = new JComboBox();
			jComboBox14.setModel(jComboBox14Model);
			jComboBox14.setBounds(253, 47, 63, 22);
		}
		return jComboBox14;
	}

	private JButton getJButton13() {
		if(jButton13 == null) {
			jButton13 = new JButton();
			jButton13.setText("\ucd94\uac00 \uc815\ubcf4 \uc785\ub825");
			jButton13.setBounds(109, 89, 144, 22);
			jButton13.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					jButton13MouseClicked(evt);
				}
			});
		}
		return jButton13;
	}
	void process_add_info()
	{
		add_info=getAdd_info();
		add_info.setVisible(true);
	}
	void summit_match_info()
	{
		int x1,x2,x3,x4,x5,x6,x7,x8;
		int x11,x22,x33,x44,x55,x66,x77,x88;
		x1=jComboBox1.getSelectedIndex();

		x2=jComboBox2.getSelectedIndex();
		x3=jComboBox3.getSelectedIndex();
		x4=jComboBox4.getSelectedIndex();
		x5=jComboBox5.getSelectedIndex();
		x6=jComboBox6.getSelectedIndex();
		x7=jComboBox7.getSelectedIndex();
		x8=jComboBox8.getSelectedIndex();
		if(x1*x2*x3*x4*x5*x6*x7*x8==0)
		{
			JOptionPane.showMessageDialog(null, "모든 항목을 선택해야 합니다", "dg", JOptionPane.WARNING_MESSAGE);
			return;
		}
		x11=jComboBox1.getItemCount();
		x22=jComboBox2.getItemCount();
		x33=jComboBox3.getItemCount();
		x44=jComboBox4.getItemCount();
		x55=jComboBox5.getItemCount();
		x66=jComboBox6.getItemCount();
		x77=jComboBox7.getItemCount();
		x88=jComboBox8.getItemCount();
		String xx;
		if(x1+1==x11)
			x1=0;
		if(x2+1==x22)
			x2=-1;
		if(x3+1==x33)
			x3=-1;
		if(x4+1==x44)
			x4=-1;
		if(x5+1==x55)
			x5=-1;
		if(x6+1==x66)
			x6=-1;
		if(x7+1==x77)
			x7=-1;
		if(x8+1==x88)
			x8=-1;

		xx="F3 "+(x1-1)+" "+x2+" "+x3+" "+x4+" "+x5+" "+x6+" "+x7+" "+x8;
		Send_to_server(xx);
		add_info.setVisible(false);
	}
	void summit_add_info()
	{
		int x1,x2,x3,x4,x5,x6,x7,x8;

		x1=jComboBox9.getSelectedIndex();
		x2=jComboBox10.getSelectedIndex();
		x3=jComboBox11.getSelectedIndex();
		x4=jComboBox12.getSelectedIndex();
		x5=jComboBox13.getSelectedIndex();
		x6=jComboBox14.getSelectedIndex();
		if(x1*x2*x3*x4*x5*x6==0)
		{
			JOptionPane.showMessageDialog(null, "성별, 별자리, 얼굴, 성격, 취미, 지역을 선택하세요", "dg", JOptionPane.WARNING_MESSAGE);
			return;
		}
		String xx=jTextField9.getText();

		try
		{
			x7=Integer.parseInt(xx);
			Calendar oCalendar = Calendar.getInstance( );
			int y=oCalendar.get(Calendar.YEAR);
			if(y-x7>256||y-x7<0)
			{
				JOptionPane.showMessageDialog(null,"출생년도를 다시 입력하세요" , "dg", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
		catch(NumberFormatException e)
		{
			JOptionPane.showMessageDialog(null, "출생년도는 숫자만 입력하세요", "dg", JOptionPane.WARNING_MESSAGE);
			return;
		}
		xx=jTextField10.getText();
		try
		{
			x8=Integer.parseInt(xx);
			if(x8>256||x8<1)
			{
				JOptionPane.showMessageDialog(null,"키를  정확히 입력하세요(1~255)" , "dg", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
		catch(NumberFormatException e)
		{
			JOptionPane.showMessageDialog(null, "키는 숫자만 입력하세요", "dg", JOptionPane.WARNING_MESSAGE);
			return;
		}
		xx="F2 "+my_name+" "+(x1-1)+" "+x7+" "+x2+" "+x8+" "+x3+" "+x4+" "+x5+" "+x6;
		Send_to_server(xx);
		add_info.setVisible(false);
	}
	private void jButton13MouseClicked(MouseEvent evt) {
		System.out.println("jButton13.mouseClicked, event="+evt);
		summit_add_info();		
	}



	private void jButton12MouseClicked(MouseEvent evt) {
		System.out.println("jButton12.mouseClicked, event="+evt);
		//TODO add your code for jButton12.mouseClicked
		summit_match_info();
	}



	private JPasswordField getJPasswordField2() {
		if(jPasswordField2 == null) {
			jPasswordField2 = new JPasswordField();
			jPasswordField2.setText("1234");
			jPasswordField2.setBounds(22, 111, 102, 22);
			jPasswordField2.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent evt) {
					jPasswordField2KeyPressed(evt);
				}
			});
		}
		return jPasswordField2;
	}

	private JPasswordField getJPasswordField3() {
		if(jPasswordField3 == null) {
			jPasswordField3 = new JPasswordField();
			jPasswordField3.setText("1234");
			jPasswordField3.setBounds(22, 83, 102, 22);
		}
		return jPasswordField3;
	}

	private void jPasswordField1KeyPressed(KeyEvent evt) {
		//System.out.println("jPasswordField1.keyPressed, event="+evt);
		//TODO add your code for jPasswordField1.keyPressed
		if(evt.getKeyCode()==10)
		{
			jButton2MouseClicked(null);
		}
	}
	
	private JButton getJButton14() {
		if(jButton14 == null) {
			jButton14 = new JButton();
			jButton14.setText("[\ubcf4\uc548] \ucabd\uc9c0 \ubcf4\ub0b4\uae30");
			jButton14.setBounds(137, 167, 158, 22);
			jButton14.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					jButton14MouseClicked(evt);
				}
			});
			jButton14.setEnabled(false);
		}
		return jButton14;
	}
	
	private void jButton14MouseClicked(MouseEvent evt) {
		//System.out.println("jButton14.mouseClicked, event="+evt);
		//TODO add your code for jButton14.mouseClicked
		if(jButton14.isEnabled())
		{
			sms.setVisible(false);
			String xx,yy;
			xx=jTextArea2.getText();
			char []gg;
			char []gg2;
			
			gg=new char [xx.length()];
			gg2=new char [xx.length()];
			String aa;
			aa="";
			int i;
			for( i=0;i<xx.length();i++)
			{
				gg[i]=xx.charAt(i);
				gg[i]=(char) (gg[i]+((50+i)%127));
				gg2[xx.length()-1-i]=gg[i];
				
			}
			for(i=0;i<xx.length();i++)
			{
				aa=aa+gg2[i];
			};
			yy=aa.replace(" ", "^s^");
			xx=yy.replace("\n", "^nl^");
			
			Send_to_p2p(dest_ip,"S "+my_name+" "+xx);
			jTextArea2.setText(null);
		}
	}
	
	private JDialog getOne_to_one() {
		if(one_to_one == null) {
			one_to_one = new JDialog(this);
			one_to_one.setLayout(null);
			one_to_one.setTitle("1:1 \ub300\ud654");
			one_to_one.getContentPane().add(getJScrollPane5());
			one_to_one.getContentPane().add(getJTextField3());
			one_to_one.getContentPane().add(getJButton15());
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
	
	private JTextArea getJTextArea3() {
		if(jTextArea3 == null) {
			jTextArea3 = new JTextArea();
			jTextArea3.setBounds(12, 12, 248, 239);
			jTextArea3.setEditable(false);
		}
		return jTextArea3;
	}
	
	private JScrollPane getJScrollPane5() {
		if(jScrollPane5 == null) {
			jScrollPane5 = new JScrollPane();
			jScrollPane5.setBounds(12, 12, 248, 239);
			jScrollPane5.setViewportView(getJTextArea3());
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
	
	private JButton getJButton15() {
		if(jButton15 == null) {
			jButton15 = new JButton();
			jButton15.setText("\ub300\ud654 \ub05d\ub0b4\uae30");
			jButton15.setBounds(59, 298, 132, 22);
			jButton15.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					jButton15MouseClicked(evt);
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
		jTextArea3.setText(jTextArea3.getText()+"\n"+"["+my_name+"] "+jTextField3.getText());
		try {
			p2p_writer.write("G ["+my_name+"] "+kk+"\n");
			p2p_writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jTextField3.setText("");

	}
	
	private void jButton15MouseClicked(MouseEvent evt) {
		System.out.println("jButton15.mouseClicked, event="+evt);
		//TODO add your code for jButton15.mouseClicked
		try {
			p2p_socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		one_to_one.setVisible(false);
	}
	
	private void jButton5MouseClicked(MouseEvent evt) {
		System.out.println("jButton5.mouseClicked, event="+evt);
		//TODO add your code for jButton5.mouseClicked
		//one_to_one=getOne_to_one();
		//one_to_one.setBounds(200, 300, 280, 351);
		//one_to_one.setVisible(true);
		if(jButton5.isEnabled())
		{
			st2 = new StringTokenizer(jList1.getSelectedValue().toString(),"(");
			String x2,kk,x3;
			x2=st2.nextToken();


			your_name=x2;
			int j;
			for(j=0;j<ul.size();j++)
			{
				st2 = new StringTokenizer(ul.get(j)," ");
				kk=st2.nextToken();
				if(x2.length()==kk.length())
				{
					int k;
					for(k=0;k<x2.length();k++)
					{
						if(x2.charAt(k)!=kk.charAt(k))break;													
					}
					if(k==x2.length())
					{
						kk=st2.nextToken();
						if(kk.length()>2)
						{
							//JOptionPane.showMessageDialog(null, kk, "ㅅㅂ", JOptionPane.WARNING_MESSAGE);
							try {
								p2p_socket=new Socket(kk,9999);
								p2p_reader=new BufferedReader(new InputStreamReader(p2p_socket.getInputStream()));
								p2p_writer=new BufferedWriter(new OutputStreamWriter(p2p_socket.getOutputStream()));
								one_to_one=getOne_to_one();
								one_to_one.setVisible(true);
								jTextArea3.setText("");
								listenMessage2();
							} catch (UnknownHostException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
						else
							JOptionPane.showMessageDialog(null, "offline", "ㅅㅂ", JOptionPane.WARNING_MESSAGE);

						break;
					}
				}

			}
		}
		else
		{
			//JOptionPane.showMessageDialog(null, "낫인", "ㅅㅂ", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void listenMessage2(){
		
		listenThread2=new Thread(){
			public void run(){
				int xk = 0;
				try{
					while( (my=p2p_reader.readLine()) != null ){
						System.out.println(my);
						xk=0;
						if(my.startsWith("G")){
							st = new StringTokenizer(my);
							xk=1;
							st.nextToken();
							String kk,cc,zz;
							kk=st.nextToken();
							cc=st.nextToken();
							zz=cc.replace("^s^"," ");
							cc=zz.replace("^ln^","\n");
							jTextArea3.setText(jTextArea3.getText()+"\n"+kk+" "+cc);
						}else if(my.startsWith("h1")){
							
							st = new StringTokenizer(my);
							st.nextToken();
							String kk;
							kk=st.nextToken();
							if(kk.startsWith("0"))
							{
								//파일 전송 시작
								DataOutputStream dos = null;
								fis = new FileInputStream(file);
								dis = new DataInputStream(fis);
								dos = new DataOutputStream(p2p_socket.getOutputStream());
								byte[] buffer = new byte[128];
								
								
								long x2=file.length();
								int len;
								jProgressBar1.setMaximum((int) (x2/128+0.5));
								int k2=0;
								while((len=dis.read(buffer))!=-1)
								{
									dos.write(buffer,0,len);
									dos.flush();
									jLabel3.setText(((k2*128)+len)+" / "+x2+"Bytes");
									jProgressBar1.setValue(k2++);
								}
								p2p_socket.close();
								JOptionPane.showMessageDialog(null, "Transmission complete", "ㄱㄳ", JOptionPane.WARNING_MESSAGE);
								jDialog1.setVisible(false);
							}
							else
							{
								//파일 수신 거부
								JOptionPane.showMessageDialog(null, "상대방이 파일 수신을 거부함", "ㄱㄳ", JOptionPane.WARNING_MESSAGE);
								p2p_socket.close();
								jDialog1.setVisible(false);
							}
						}
						
					}
					if(xk==1)
						jTextArea3.setText(jTextArea3.getText()+"\n**상대방이 나갔습니다.");
				}catch(Exception e){
					e.printStackTrace();
					if(xk==1)
						jTextArea3.setText(jTextArea3.getText()+"\n**상대방이 나갔습니다.");
				}
			}
		};
		listenThread2.setDaemon(true);
		listenThread2.start();
	}
	
	private JMenuItem getJMenuItem2() {
		if(jMenuItem2 == null) {
			jMenuItem2 = new JMenuItem();
			jMenuItem2.setText("\ud30c\uc77c \uc804\uc1a1");
			jMenuItem2.setEnabled(false);
			jMenuItem2.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent evt) {
					jMenuItem2MousePressed(evt);
				}
				
			});
		}
		return jMenuItem2;
	}
	
	private void jMenuItem2MousePressed(MouseEvent evt) {
		System.out.println("jMenuItem2.mousePressed, event="+evt);
		//TODO add your code for jMenuItem2.mousePressed
		jList1.updateUI();
		if(jMenuItem2.isEnabled())
		{
			//
			st2 = new StringTokenizer(jList1.getSelectedValue().toString(),"(");
			String x2,kk,x3;
			x2=st2.nextToken();
			//JOptionPane.showMessageDialog(null, x2, "ㅅㅂ", JOptionPane.WARNING_MESSAGE);


			your_name=x2;
			int j;
			for(j=0;j<ul.size();j++)
			{
				st2 = new StringTokenizer(ul.get(j)," ");
				kk=st2.nextToken();
				if(x2.length()==kk.length())
				{
					//JOptionPane.showMessageDialog(null, "개새끼야", "ㅅㅂ", JOptionPane.WARNING_MESSAGE);
					int k;
					for(k=0;k<x2.length();k++)
					{
						if(x2.charAt(k)!=kk.charAt(k))break;													
					}
					if(k==x2.length())
					{
						kk=st2.nextToken();
						if(kk.length()>2)
						{
							dest_ip=kk;
							jDialog1=getJDialog1();
							jDialog1.setVisible(true);
							jLabel2.setText("");
							jLabel3.setText("");
							jProgressBar1.setValue(0);
							
							
							
						}
						else
							JOptionPane.showMessageDialog(null, "offline", "ㅅㅂ", JOptionPane.WARNING_MESSAGE);

						break;
					}
				}

			}
			
		}
	}
	

	
	private JDialog getJDialog1() {
		if(jDialog1 == null) {
			jDialog1 = new JDialog(this);
			jDialog1.setLayout(null);
			jDialog1.setTitle("\ud30c\uc77c\uc804\uc1a1");
			jDialog1.getContentPane().add(getJLabel2());
			jDialog1.getContentPane().add(getJLabel3());
			jDialog1.getContentPane().add(getJButton17());
			jDialog1.getContentPane().add(getJButton18());
			jDialog1.getContentPane().add(getJProgressBar1());
			jDialog1.setSize(333, 201);
			jDialog1.setBounds(333, 201,333, 201);
		}
		return jDialog1;
	}
	
	private JLabel getJLabel2() {
		if(jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setBounds(23, 19, 290, 19);
		}
		return jLabel2;
	}
	
	private JLabel getJLabel3() {
		if(jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("jLabel3");
			jLabel3.setBounds(23, 63, 255, 15);
		}
		return jLabel3;
	}
	
	private JButton getJButton17() {
		if(jButton17 == null) {
			jButton17 = new JButton();
			jButton17.setText("\ubcf4\ub0b4\uae30");
			jButton17.setBounds(198, 127, 98, 22);
			jButton17.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					jButton17MouseClicked(evt);
				}
			});
		}
		return jButton17;
	}
	
	private JButton getJButton18() {
		if(jButton18 == null) {
			jButton18 = new JButton();
			jButton18.setText("\ud30c\uc77c \ucc3e\uae30");
			jButton18.setBounds(23, 127, 105, 22);
			jButton18.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					jButton18MouseClicked(evt);
				}
			});
		}
		return jButton18;
	}
	
	private void jButton18MouseClicked(MouseEvent evt) {
		System.out.println("jButton18.mouseClicked, event="+evt);
		//TODO add your code for jButton18.mouseClicked
		jFileChooser1=getJFileChooser1();
		jFileChooser1.setName("\ubcd1\uc2e0.txt");
		jFileChooser1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jFileChooser1ActionPerformed(evt);
			}
		});
		jFileChooser1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				jFileChooser1MouseClicked(evt);
			}
		});
		int y=jFileChooser1.showOpenDialog(null);
		 
	}
	
	private JFileChooser getJFileChooser1() {
		if(jFileChooser1 == null) {
			jFileChooser1 = new JFileChooser();
		}
		return jFileChooser1;
	}
	
	private void jButton17MouseClicked(MouseEvent evt) {
		System.out.println("jButton17.mouseClicked, event="+evt);
		//TODO add your code for jButton17.mouseClicked
		if(file.isFile()&&jButton17.isEnabled())
		{
			try {
				p2p_socket=new Socket(dest_ip,9999);
				p2p_reader=new BufferedReader(new InputStreamReader(p2p_socket.getInputStream()));
				p2p_writer=new BufferedWriter(new OutputStreamWriter(p2p_socket.getOutputStream()));
				p2p_writer.write("H "+my_name+" "+file.getName()+" "+file.length()+"\n");
				p2p_writer.flush();
				jButton17.setEnabled(false);
				jLabel3.setText("상대방의 수신 승인을 기다리고 있습니다..");
				listenMessage2();
				
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	private JProgressBar getJProgressBar1() {
		if(jProgressBar1 == null) {
			jProgressBar1 = new JProgressBar();
			jProgressBar1.setBounds(23, 96, 273, 19);
		}
		return jProgressBar1;
	}
	
	private JDialog getD_day_input() {
		if(d_day_input == null) {
			d_day_input = new JDialog(this);
			d_day_input.setLayout(null);
			d_day_input.setTitle("D-day \ub4f1\ub85d");
			d_day_input.getContentPane().add(getJTextField6());
			d_day_input.getContentPane().add(getJTextField7());
			d_day_input.getContentPane().add(getJTextField11());
			d_day_input.getContentPane().add(getJTextField12());
			d_day_input.getContentPane().add(getJTextField13());
			d_day_input.getContentPane().add(getJButton19());
			d_day_input.setSize(313, 175);
			d_day_input.setBounds(313, 175,313, 175);
		}
		return d_day_input;
	}
	
	private JTextField getJTextField6() {
		if(jTextField6 == null) {
			jTextField6 = new JTextField();
			jTextField6.setText("\ub144\ub3c4");
			jTextField6.setBounds(22, 33, 74, 22);
		}
		return jTextField6;
	}
	
	private JTextField getJTextField7() {
		if(jTextField7 == null) {
			jTextField7 = new JTextField();
			jTextField7.setText("\uc6d4");
			jTextField7.setBounds(108, 33, 46, 22);
		}
		return jTextField7;
	}
	
	private JTextField getJTextField11() {
		if(jTextField11 == null) {
			jTextField11 = new JTextField();
			jTextField11.setText("\uc77c");
			jTextField11.setBounds(166, 33, 41, 22);
		}
		return jTextField11;
	}
	
	private JTextField getJTextField12() {
		if(jTextField12 == null) {
			jTextField12 = new JTextField();
			jTextField12.setText("\uc2dc\uac01");
			jTextField12.setBounds(219, 33, 47, 22);
		}
		return jTextField12;
	}
	
	private JTextField getJTextField13() {
		if(jTextField13 == null) {
			jTextField13 = new JTextField();
			jTextField13.setText("\uacfc\uc81c \uc774\ub984");
			jTextField13.setBounds(22, 67, 244, 22);
		}
		return jTextField13;
	}
	
	private JButton getJButton19() {
		if(jButton19 == null) {
			jButton19 = new JButton();
			jButton19.setText("\ub4f1\ub85d\ud558\uae30");
			jButton19.setBounds(96, 109, 107, 22);
			jButton19.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					jButton19MouseClicked(evt);
				}
			});
		}
		return jButton19;
	}
	
	private JMenuItem getJMenuItem3() {
		if(jMenuItem3 == null) {
			jMenuItem3 = new JMenuItem();
			jMenuItem3.setText("D-day \ub4f1\ub85d");
			jMenuItem3.setEnabled(false);
			jMenuItem3.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent evt) {
					jMenuItem3MousePressed(evt);
				}
			});
		}
		return jMenuItem3;
	}
	
	private void jMenuItem3MousePressed(MouseEvent evt) {
		System.out.println("jMenuItem3.mousePressed, event="+evt);
		//TODO add your code for jMenuItem3.mousePressed
		if(jMenuItem3.isEnabled())
		{
			d_day_input=getD_day_input();
			d_day_input.setVisible(true);
		}
	}
	
	private void jButton19MouseClicked(MouseEvent evt) {
		System.out.println("jButton19.mouseClicked, event="+evt);
		//TODO add your code for jButton19.mouseClicked
		int a = 0,b = 0,c=0,d;
		
		Date date1 = new Date(); 

		
		String x1,x2,x3,x4,x5;
		x1=jTextField6.getText();
		if(x1.length()>4)
		{
			JOptionPane.showMessageDialog(null, "9999이하의 값만 입력하세요", "ㅅㅂ", JOptionPane.WARNING_MESSAGE);
			return;
		}
		try
		{
			a=Integer.parseInt(x1);
			if(a-1900<date1.getYear())
			{
				JOptionPane.showMessageDialog(null, "올해 이후의 년도만 입력하세요", "ㅅㅂ", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"년도는 숫자만 입력하세요", "ㅅㅂ", JOptionPane.WARNING_MESSAGE);
			return;
		}
		//JOptionPane.showMessageDialog(null,a+" "+date1.getYear()+" "+(a<date1.getYear()), "ㅅㅂ", JOptionPane.WARNING_MESSAGE);
		
		x2=jTextField7.getText();
		if(x2.length()>2)
		{
			JOptionPane.showMessageDialog(null, "12이하의 값만 입력하세요", "ㅅㅂ", JOptionPane.WARNING_MESSAGE);
			return;
		}
		try
		{
			b=Integer.parseInt(x2);
			if(a-1900==date1.getYear()&&b<date1.getMonth()+1)
			{
				JOptionPane.showMessageDialog(null, "이번 달 이후의 값만 입력하세요", "ㅅㅂ", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null,"월은 숫자만 입력하세요", "ㅅㅂ", JOptionPane.WARNING_MESSAGE);
			return;
		}
		//JOptionPane.showMessageDialog(null,date1.getMonth()+" "+b, "ㅅㅂ", JOptionPane.WARNING_MESSAGE);
		
		x3=jTextField11.getText();
		if(x3.length()>2)
		{
			JOptionPane.showMessageDialog(null, "31이하의 값만 입력하세요", "ㅅㅂ", JOptionPane.WARNING_MESSAGE);
			return;
		}
		try
		{
			c=Integer.parseInt(x3);
			//JOptionPane.showMessageDialog(null, c+" "+date1.getDate(), "ㅅㅂ", JOptionPane.WARNING_MESSAGE);
			if(b==date1.getMonth()+1&&c<date1.getDate())
			{
				JOptionPane.showMessageDialog(null, "오늘 이후의 값만 입력하세요", "ㅅㅂ", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null,"일은 숫자만 입력하세요", "ㅅㅂ", JOptionPane.WARNING_MESSAGE);
			return;
		}
		x4=jTextField12.getText();
		if(x4.length()>5)
		{
			JOptionPane.showMessageDialog(null, "5자리 이하로 입력하세요", "ㅅㅂ", JOptionPane.WARNING_MESSAGE);
			return;
		}
		x5=jTextField13.getText();
		if(x5.length()>100)
		{
			JOptionPane.showMessageDialog(null, "100자리 이하로 입력하세요", "ㅅㅂ", JOptionPane.WARNING_MESSAGE);
			return;
		}
		String x6;
		x6=x5.replace(" ", "^s^");
		x5=x6.replace("\n", "^nl");
		Send_to_server("I "+x1+" "+x2+" "+x3+" "+x4+" "+x5);
		d_day_input.setVisible(false);
		d_day_input=null;
	}
	
	private JDialog getNotice() {
		if(notice == null) {
			notice = new JDialog(this);
			notice.setLayout(null);
			notice.setTitle("d-day \uc54c\ub9bc");
			notice.getContentPane().add(getJScrollPane6());
			notice.setSize(354, 236);
			notice.setBounds(354, 236,354, 236);
		}
		return notice;
	}
	
	private JList getJList3() {
		if(jList3 == null) {
			listModel3 = new DefaultListModel();
			jList3 = new JList(listModel3);
			jList3.setBounds(12, 12, 322, 185);
		}
		return jList3;
	}
	
	private JScrollPane getJScrollPane6() {
		if(jScrollPane6 == null) {
			jScrollPane6 = new JScrollPane();
			jScrollPane6.setBounds(12, 12, 322, 185);
			jScrollPane6.setViewportView(getJList3());
		}
		return jScrollPane6;
	}
	
	private void jFileChooser1MouseClicked(MouseEvent evt) {
		//System.out.println("jFileChooser1.mouseClicked, event="+evt);
		//TODO add your code for jFileChooser1.mouseClicked
		
	}
	
	private void jFileChooser1ActionPerformed(ActionEvent evt) {
		//System.out.println("jFileChooser1.actionPerformed, event="+evt);
		//TODO add your code for jFileChooser1.actionPerformed
		//System.out.println("액션 이벤트 : "+evt.getActionCommand());
		
		if(evt.getActionCommand().startsWith("Approve"))
		{
			//System.out.println("열기");
			file = jFileChooser1.getSelectedFile();
			jLabel2.setText(" 보내려는 파일 : " +file.getName());
			jButton17.setEnabled(true);
		}
		
	
	}
	
	private void one_to_oneWindowClosing(WindowEvent evt) {
		System.out.println("one_to_one.windowClosing, event="+evt);
		//TODO add your code for one_to_one.windowClosing
		try {
			p2p_socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void jPasswordField2KeyPressed(KeyEvent evt) {
		System.out.println("jPasswordField2.keyPressed, event="+evt);
		//TODO add your code for jPasswordField2.keyPressed
		if(evt.getKeyCode()==10)
		{
			jButton7MouseClicked(null);
		}
	}

}

