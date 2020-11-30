package Hospital;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JComboBox;

public class LoginPage {

	private JFrame frame;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	private Connection con = null;
	private Statement stat = null;
	private ResultSet rs = null;
	private PreparedStatement pst = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage window = new LoginPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(200, 100, 693, 556);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblLogin = new JLabel("Clinic Appointment System--LoginPage");
		lblLogin.setBounds(33, 27, 609, 35);
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setBackground(SystemColor.inactiveCaption);
		lblLogin.setFont(lblLogin.getFont().deriveFont(lblLogin.getFont().getSize() + 18f));
		frame.getContentPane().add(lblLogin);

		JLabel lblUserName = new JLabel("UserName");
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserName.setBounds(48, 185, 89, 72);
		frame.getContentPane().add(lblUserName);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(48, 252, 89, 58);
		frame.getContentPane().add(lblPassword);

		txtUsername = new JTextField();
		txtUsername.setBounds(149, 211, 370, 21);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(149, 271, 370, 21);
		frame.getContentPane().add(txtPassword);

		JComboBox UserType = new JComboBox();
		UserType.setBounds(149, 323, 128, 21);
		frame.getContentPane().add(UserType);
		UserType.addItem("select");
		UserType.addItem("Doctor");
		UserType.addItem("Patient");

		/*
		String test_sql = "SELECT VERSION()";
		
		try(Connection c = DBUtil.getConn(); PreparedStatement ps = c.prepareStatement(test_sql)) {
			ResultSet rs = ps.executeQuery();
			int result = rs.getRow();
			System.out.println("Line num: " + result);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		*/
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String password = txtPassword.getText();
				String username = txtUsername.getText();
				String type = UserType.getSelectedItem().toString().trim();

				
				Login login = new Login();
				int result = login.LoginAct(type, username, password);
				if(result == -1) {
					txtPassword.setText(null);
					txtUsername.setText(null);
				}
				else if(result == 1) {
					Pappointment Pa = new Pappointment(login.getid());
					Pa.main(null);
					frame.setVisible(false);
				}else if(result == 2){
					Dappointment Da = new Dappointment(login.getid());
					Da.main(null);
					frame.setVisible(false);
				}
				

			}
		});
		btnLogin.setBounds(116, 442, 93, 23);
		frame.getContentPane().add(btnLogin);

		JButton btnSignup = new JButton("Sign up");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SignUpChoose SPC = new SignUpChoose();
				SPC.main(null);
				frame.setVisible(false);
			}
		});
		btnSignup.setBounds(293, 442, 93, 23);
		frame.getContentPane().add(btnSignup);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame frmLoginSystem = new JFrame("Exit");
				if (JOptionPane.showConfirmDialog(frmLoginSystem, "Confirm if you want to exit", "Login Systems",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
											
					System.exit(0);
				}
			}
		});
		btnCancel.setBounds(471, 442, 93, 23);
		frame.getContentPane().add(btnCancel);

		JSeparator separator = new JSeparator();
		separator.setBounds(48, 385, 609, 2);
		frame.getContentPane().add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(45, 124, 614, 2);
		frame.getContentPane().add(separator_1);

		JLabel lblUserType = new JLabel("User Type");
		lblUserType.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserType.setBounds(58, 320, 79, 29);
		frame.getContentPane().add(lblUserType);

	}
}
