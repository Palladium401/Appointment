package Hospital;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sign_up {

	private JFrame frame;
	private JTextField txtUsername;
	private JTextField txtBirthday;
	private JTextField txtSim;
	private JTextField txtGender;
	private JTextField txtPhonenumber;
	private JTextField txtAge;
	private JTextField txtPassword;
	private JTextField txtPasswordConfirm;

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
					Sign_up window = new Sign_up();
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
	public Sign_up() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 811, 1002);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblName = new JLabel("Name");
		lblName.setForeground(new Color(70, 130, 180));
		lblName.setFont(new Font("宋体", Font.PLAIN, 25));
		lblName.setBounds(44, 173, 137, 48);
		frame.getContentPane().add(lblName);

		JLabel lblBirthday = new JLabel("Birthday");
		lblBirthday.setForeground(new Color(70, 130, 180));
		lblBirthday.setFont(new Font("宋体", Font.PLAIN, 25));
		lblBirthday.setBounds(44, 242, 137, 48);
		frame.getContentPane().add(lblBirthday);

		JLabel lblSim = new JLabel("SIM");
		lblSim.setForeground(new Color(70, 130, 180));
		lblSim.setFont(new Font("宋体", Font.PLAIN, 25));
		lblSim.setBounds(44, 300, 137, 48);
		frame.getContentPane().add(lblSim);

		JLabel lblGender = new JLabel("Gender");
		lblGender.setForeground(new Color(70, 130, 180));
		lblGender.setFont(new Font("宋体", Font.PLAIN, 25));
		lblGender.setBounds(44, 372, 137, 48);
		frame.getContentPane().add(lblGender);

		JLabel lblPhonenumber = new JLabel("Phonenumber");
		lblPhonenumber.setForeground(new Color(70, 130, 180));
		lblPhonenumber.setFont(new Font("宋体", Font.PLAIN, 25));
		lblPhonenumber.setBounds(44, 457, 195, 48);
		frame.getContentPane().add(lblPhonenumber);

		JLabel lblAge = new JLabel("Age");
		lblAge.setForeground(new Color(70, 130, 180));
		lblAge.setFont(new Font("宋体", Font.PLAIN, 25));
		lblAge.setBounds(44, 532, 137, 48);
		frame.getContentPane().add(lblAge);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(70, 130, 180));
		lblPassword.setFont(new Font("宋体", Font.PLAIN, 25));
		lblPassword.setBounds(44, 603, 137, 48);
		frame.getContentPane().add(lblPassword);

		JLabel lblNewLabel = new JLabel("Sign-up");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel.setBounds(278, 33, 185, 60);
		frame.getContentPane().add(lblNewLabel);

		txtUsername = new JTextField();
		txtUsername.setBounds(278, 191, 298, 21);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);

		txtBirthday = new JTextField();
		txtBirthday.setColumns(10);
		txtBirthday.setBounds(278, 260, 298, 21);
		frame.getContentPane().add(txtBirthday);

		txtSim = new JTextField();
		txtSim.setColumns(10);
		txtSim.setBounds(278, 318, 298, 21);
		frame.getContentPane().add(txtSim);

		txtGender = new JTextField();
		txtGender.setColumns(10);
		txtGender.setBounds(278, 390, 298, 21);
		frame.getContentPane().add(txtGender);

		txtPhonenumber = new JTextField();
		txtPhonenumber.setColumns(10);
		txtPhonenumber.setBounds(278, 475, 298, 21);
		frame.getContentPane().add(txtPhonenumber);

		txtAge = new JTextField();
		txtAge.setColumns(10);
		txtAge.setBounds(278, 550, 298, 21);
		frame.getContentPane().add(txtAge);

		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(278, 621, 298, 21);
		frame.getContentPane().add(txtPassword);

		JSeparator separator = new JSeparator();
		separator.setBounds(20, 124, 701, 2);
		frame.getContentPane().add(separator);

		JLabel lblPasswordConfrim = new JLabel("PasswordConfrim");
		lblPasswordConfrim.setForeground(new Color(70, 130, 180));
		lblPasswordConfrim.setFont(new Font("宋体", Font.PLAIN, 25));
		lblPasswordConfrim.setBounds(44, 667, 195, 48);
		frame.getContentPane().add(lblPasswordConfrim);

		txtPasswordConfirm = new JTextField();
		txtPasswordConfirm.setBounds(278, 685, 298, 21);
		frame.getContentPane().add(txtPasswordConfirm);
		txtPasswordConfirm.setColumns(10);

		JButton btnSignUp = new JButton("SignUp");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String password = txtPassword.getText();
				String passwordconfirm = txtPasswordConfirm.getText();

				if (txtUsername.getText().equals("") || txtBirthday.getText().equals("") || txtSim.getText().equals("")
						|| txtGender.getText().equals("") || txtPhonenumber.getText().equals("")
						|| txtAge.getText().equals("") || txtPassword.getText().equals("")
						|| txtPasswordConfirm.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please input all the information", "Try it again",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (password.equals(passwordconfirm)) {
						try {
							Class.forName("com.mysql.jdbc.Driver");
							con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "root");
							pst = con.prepareStatement(
									"insert into patient(name,birthday,SIM,Gender,Phonenumber,Age,Password) values(?,?,?,?,?,?,?)");

							pst.setString(1, txtUsername.getText());
							pst.setString(2, txtBirthday.getText());
							pst.setString(3, txtSim.getText());
							pst.setString(4, txtGender.getText());
							pst.setString(5, txtPhonenumber.getText());
							pst.setString(6, txtAge.getText());
							pst.setString(7, txtPassword.getText());
							int x = pst.executeUpdate();
							if (x > 0) {
								JOptionPane.showMessageDialog(null, "                   Successful Sign-up", null,
										JOptionPane.PLAIN_MESSAGE);
								con.close();
							} else {
								JOptionPane.showMessageDialog(null, "Sign-up failed", "Try it again",
										JOptionPane.ERROR_MESSAGE);
								txtUsername.setText(null);
								txtBirthday.setText(null);
								txtSim.setText(null);
								txtGender.setText(null);
								txtPhonenumber.setText(null);
								txtAge.setText(null);
								txtPassword.setText(null);
								txtPasswordConfirm.setText(null);
							}

						} catch (Exception e1) {
							System.out.println(e1);
						}

						LoginPage login = new LoginPage();
						LoginPage.main(null);
						frame.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, "Password is not Same", "Try it again",
								JOptionPane.ERROR_MESSAGE);
						txtPassword.setText(null);
						txtPasswordConfirm.setText(null);

					}

				}
			}
		});
		btnSignUp.setBounds(142, 835, 124, 38);
		frame.getContentPane().add(btnSignUp);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginPage login = new LoginPage();
				LoginPage.main(null);
				frame.setVisible(false);
			}
		});
		btnCancel.setBounds(452, 835, 124, 38);
		frame.getContentPane().add(btnCancel);
	}
}
