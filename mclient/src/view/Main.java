package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTree;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JComboBox;

public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setTitle("\uC0AC\uB0B4 \uBA54\uC2E0\uC800");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTree tree = new JTree();
		tree.setBounds(12, 10, 154, 242);
		contentPane.add(tree);
		
		JButton btnSendmessage = new JButton("SendMessage");
		btnSendmessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSendmessage.setBounds(177, 7, 97, 23);
		contentPane.add(btnSendmessage);
		
		JButton btnOpenchat = new JButton("OpenChat");
		btnOpenchat.setBounds(178, 40, 97, 23);
		contentPane.add(btnOpenchat);
		
		JLabel lblSharedfolder = new JLabel("SharedFolder");
		lblSharedfolder.setBounds(178, 73, 86, 15);
		contentPane.add(lblSharedfolder);
		
		JButton btnFileupload = new JButton("FileUpload");
		btnFileupload.setBounds(177, 229, 97, 23);
		contentPane.add(btnFileupload);
		
		JList list = new JList();
		list.setBounds(176, 98, 246, 126);
		contentPane.add(list);
		
		JButton btnDownload = new JButton("Download");
		btnDownload.setBounds(325, 229, 97, 23);
		contentPane.add(btnDownload);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(336, 70, 86, 21);
		contentPane.add(comboBox);
	}
}
