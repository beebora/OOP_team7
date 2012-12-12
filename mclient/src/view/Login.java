package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JPasswordField txtPw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("\uC0AC\uB0B4\uC6A9 \uBA54\uC2E0\uC800");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 235, 179);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLogin = new JButton("\uB85C\uADF8\uC778");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("로그인 Click");
			}
		});
		btnLogin.setBounds(110, 95, 97, 23);
		contentPane.add(btnLogin);
		
		txtId = new JTextField();
		txtId.setBounds(91, 32, 116, 21);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		txtPw = new JPasswordField();
		txtPw.setBounds(91, 63, 116, 21);
		contentPane.add(txtPw);
		
		JButton btnJoin = new JButton("\uACC4\uC815\uB4F1\uB85D");
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("계정등록 Click");
			}
		});
		btnJoin.setBounds(12, 95, 97, 23);
		contentPane.add(btnJoin);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(22, 35, 57, 15);
		contentPane.add(lblId);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(22, 66, 57, 15);
		contentPane.add(lblPassword);
	}
}
