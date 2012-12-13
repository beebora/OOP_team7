package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Message extends JFrame {

	private JPanel contentPane;
	private JTextField txtMsg;
	JLabel lblTitle;
	JButton btnOK;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Message frame = new Message();
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
	public Message() {
		setTitle("\uCABD\uC9C0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 217, 248);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtMsg = new JTextField();
		txtMsg.setBounds(12, 34, 177, 129);
		contentPane.add(txtMsg);
		txtMsg.setColumns(10);
		
		lblTitle = new JLabel("New label");
		lblTitle.setBounds(12, 9, 57, 15);
		contentPane.add(lblTitle);
		
		btnOK = new JButton("\uB2F5\uC7A5\uD558\uAE30");
		btnOK.setBounds(91, 173, 97, 23);
		contentPane.add(btnOK);
	}
	
	public void setData(String title, String msg, ActionListener al){
		lblTitle.setText(title);
		txtMsg.setText(msg);
		btnOK.addActionListener(al);
	}
}
