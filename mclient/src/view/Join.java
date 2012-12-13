package view;

import helper.ConnectHelper;
import helper.ConnectHelper.BooleanListener;
import helper.ConnectHelper.GetFriendsListener;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;

import model.Member;
import model.Member.NodeType;
import javax.swing.JTree;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Join extends JFrame {
	private JTextField txtId;
	private JTextField txtName;
	
	private ConnectHelper connectHelper;
	private JPasswordField txtPw;
	private JPasswordField txtPw2;
	JTree tree;

	/**
	 * Create the frame.
	 */
	public Join() {
		final JFrame self = this;
		setTitle("\uACC4\uC815 \uB4F1\uB85D");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				new Login().setVisible(true);
			}
		});
		connectHelper = ConnectHelper.getInstance();
		setBounds(100, 100, 240, 449);
		getContentPane().setLayout(null);
		
		txtId = new JTextField();
		txtId.setBounds(89, 10, 116, 21);
		getContentPane().add(txtId);
		txtId.setColumns(10);
		
		txtName = new JTextField();
		txtName.setBounds(89, 102, 116, 21);
		getContentPane().add(txtName);
		txtName.setColumns(10);
		
		connectHelper.getFriends(new GetFriendsListener() {
			@Override
			public void receiveFriends(DefaultMutableTreeNode departments) {
				Enumeration<?> en = departments.depthFirstEnumeration();
				while(en.hasMoreElements()){
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) en.nextElement();
					Member m = (Member) node.getUserObject();
					if(!m.getType().equals(NodeType.DEPARTMENT)){
						node.removeFromParent();
					}
				}
				tree = new JTree(departments);
				tree.setBounds(20, 161, 185, 201);
				tree.expandRow(2);
				getContentPane().add(tree);
				repaint();
			}
		});
		
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(20, 13, 57, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(20, 44, 57, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uC7AC\uC785\uB825");
		lblNewLabel_2.setBounds(20, 72, 57, 15);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\uC131\uBA85");
		lblNewLabel_3.setBounds(20, 105, 57, 15);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\uC18C\uC18D");
		lblNewLabel_4.setBounds(20, 136, 57, 15);
		getContentPane().add(lblNewLabel_4);
		
		JButton btnOK = new JButton("\uAC00\uC785\uC644\uB8CC");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TreePath treepath = tree.getSelectionPath();
				if(txtId.getText().equals("")){
					JOptionPane.showMessageDialog(self, "아이디가 입력되지 않았습니다.");
				}else if(txtPw.getPassword().toString().equals("")){
					JOptionPane.showMessageDialog(self, "패스워드가 입력되지 않았습니다.");
				}else if(!txtPw.getPassword().toString().equals(txtPw2.getPassword().toString())){
					JOptionPane.showMessageDialog(self, "패스워드가 일치하지 않습니다.");
				}else if(txtName.getText().equals("")){
					JOptionPane.showMessageDialog(self, "이름이 입력되지 않았습니다.");
				}else if(treepath == null){
					JOptionPane.showMessageDialog(self, "부서가 선택되지 않았습니다.");
				}else{
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)treepath.getLastPathComponent();
					Member dept = (Member)selectedNode.getUserObject();
					connectHelper.join(txtId.getText(), txtName.getText(), txtPw.getPassword().toString(), dept.getId(), new BooleanListener() {
						@Override
						public void receiveResult(Boolean success, String msg) {
							if(success){
								JOptionPane.showMessageDialog(self, "회원가입이 완료되었습니다. 로그인해 주세요.");
								new Login().setVisible(true);
								dispose();
							}else{
								JOptionPane.showMessageDialog(self, "회원가입에 실패하였습니다 : " + msg);
							}
						}
					});
				}
			}
		});
		btnOK.setBounds(63, 378, 97, 23);
		getContentPane().add(btnOK);
		
		txtPw = new JPasswordField();
		txtPw.setBounds(89, 41, 116, 21);
		getContentPane().add(txtPw);
		
		txtPw2 = new JPasswordField();
		txtPw2.setBounds(89, 69, 116, 21);
		getContentPane().add(txtPw2);
	}
}
