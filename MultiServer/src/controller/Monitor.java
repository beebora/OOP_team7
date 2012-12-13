package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Monitor extends JFrame {
	private static ServerMain serverMain;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Monitor().setVisible(true);
					serverMain = new ServerMain();
					serverMain.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Monitor() {
		setTitle("\uC11C\uBC84 ON");
		System.out.println("monitor init");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				serverMain.close();
				dispose();
			}
		});
		setBounds(100, 100, 254, 152);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
}
