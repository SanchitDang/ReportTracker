package org.sanapplications.GUI;

import javax.swing.*;
import java.awt.Font;
import java.util.Timer;

public class LoginPage extends JFrame {
	private JTextField textField;
	private JPasswordField passwordField;

	public LoginPage() {
		initialize();
	}

	private void initialize() {
		setTitle("Report Tracker");
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel label = new JLabel("Report Tracker");
		label.setFont(new Font("Tahoma", Font.BOLD, 17));
		label.setBounds(147, 11, 151, 41);
		getContentPane().add(label);

		JLabel lblLoginScreen = new JLabel("Login Screen");
		lblLoginScreen.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLoginScreen.setBounds(170, 63, 101, 23);
		getContentPane().add(lblLoginScreen);

		JLabel lblUsername = new JLabel("ID:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUsername.setBounds(55, 119, 64, 23);
		getContentPane().add(lblUsername);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPassword.setBounds(55, 159, 64, 23);
		getContentPane().add(lblPassword);

		textField = new JTextField();
		textField.setBounds(130, 121, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(130, 161, 86, 20);
		getContentPane().add(passwordField);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(e -> {
			String user = textField.getText();
			String pass = new String(passwordField.getPassword());
			if (user.equals("empl") && pass.equals("1234567890")) {
				JOptionPane.showMessageDialog(LoginPage.this, "Login Successfully");
				setVisible(false);
				HomePage homePage = new HomePage();
				homePage.setVisible(true);

				// Schedule the task to run every 2 hours
				Timer timer = new Timer();
				timer.scheduleAtFixedRate(new java.util.TimerTask() {
					@Override
					public void run() {
						homePage.setVisible(true);
						homePage.toFront();
					}
				}, 0, 2 * 60 * 60 * 1000); // Start immediately and repeat every 2 hours
			} else {
				JOptionPane.showMessageDialog(LoginPage.this, "Login Failed");
			}
		});

		btnLogin.setBounds(260, 138, 89, 23);
		getContentPane().add(btnLogin);
	}

	public static void main(String[] args) {
		LoginPage loginPage = new LoginPage();
		loginPage.setVisible(true);
	}
}
