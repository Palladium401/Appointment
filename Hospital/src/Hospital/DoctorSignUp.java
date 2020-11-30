package Hospital;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DoctorSignUp {

	private JFrame frame;
	private JTextField textDoctorName;
	private JTextField textPassword;
	private JTextField textPasswordCo;
	private JTextField textDepartment;
	private JTextField textEmail;
	private JTextField textDoctorID;

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
					DoctorSignUp window = new DoctorSignUp();
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
	public DoctorSignUp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 729, 891);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblSignUp = new JLabel("Sign-Up");
		lblSignUp.setBounds(241, 35, 187, 57);
		lblSignUp.setFont(new Font("Arial", Font.PLAIN, 25));
		lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);

		JSeparator separator = new JSeparator();
		separator.setBounds(28, 102, 618, 2);

		JLabel lblDoctorName = new JLabel("DoctorName");
		lblDoctorName.setBounds(28, 137, 169, 48);
		lblDoctorName.setFont(new Font("Arial", Font.PLAIN, 25));

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(28, 222, 137, 48);
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 25));

		JLabel lblNewLabel_2 = new JLabel("Department");
		lblNewLabel_2.setBounds(28, 390, 137, 48);
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 25));

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(28, 471, 137, 48);
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 25));

		JLabel lblPasswordConfirm = new JLabel("PasswordConfirm");
		lblPasswordConfirm.setBounds(28, 313, 230, 48);
		lblPasswordConfirm.setFont(new Font("Arial", Font.PLAIN, 25));

		textDoctorName = new JTextField();
		textDoctorName.setBounds(241, 154, 253, 24);
		textDoctorName.setColumns(10);

		textPassword = new JTextField();
		textPassword.setBounds(241, 239, 253, 24);
		textPassword.setColumns(10);

		textPasswordCo = new JTextField();
		textPasswordCo.setBounds(241, 330, 253, 24);
		textPasswordCo.setColumns(10);

		textDepartment = new JTextField();
		textDepartment.setBounds(241, 407, 253, 24);
		textDepartment.setColumns(10);

		textEmail = new JTextField();
		textEmail.setBounds(241, 488, 253, 24);
		textEmail.setColumns(10);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(lblSignUp);
		frame.getContentPane().add(separator);
		frame.getContentPane().add(textPassword);
		frame.getContentPane().add(textPasswordCo);
		frame.getContentPane().add(lblEmail);
		frame.getContentPane().add(lblPasswordConfirm);
		frame.getContentPane().add(lblNewLabel_2);
		frame.getContentPane().add(lblDoctorName);
		frame.getContentPane().add(lblPassword);
		frame.getContentPane().add(textDoctorName);
		frame.getContentPane().add(textDepartment);
		frame.getContentPane().add(textEmail);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(28, 675, 615, 2);
		frame.getContentPane().add(separator_1);

		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String password = textPassword.getText();
				String passwordconfirm = textPasswordCo.getText();
				
				DoctorSign doctor = new DoctorSign();
				boolean result = doctor.signUp(textDoctorID.getText(), textDepartment.getText(), textEmail.getText(), textDoctorName.getText(), password, passwordconfirm);
				if(result) {
					LoginPage login = new LoginPage();
					LoginPage.main(null);
					frame.setVisible(false);
				}else {
					textDoctorName.setText(null);
					textDepartment.setText(null);
					textEmail.setText(null);
					textPassword.setText(null);
					textPasswordCo.setText(null);
					textDoctorID.setText(null);
				}
				/*if (textDoctorID.getText().equals("") || textDepartment.getText().equals("")
						|| textEmail.getText().equals("") || textDoctorName.getText().equals("")
						|| textPassword.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please input all the information", "Try it again",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (password.equals(passwordconfirm)) {
						try {
							Class.forName("com.mysql.jdbc.Driver");
							con = DBUtil.getConn();
							stat = con.createStatement();

							String DoctorID = textDoctorID.getText();
							String sql = "Select * from doctor where DoctorID ='" + DoctorID + "'";
							rs = stat.executeQuery(sql);
							if (rs.next()) {
								JOptionPane.showMessageDialog(null, "The ID has already exist", null,
										JOptionPane.ERROR_MESSAGE);
							} else {
								pst = con.prepareStatement(
										"insert into doctor(DoctorID,Department,Email,DName,DPassword) values(?,?,?,?,?)");

								pst.setString(1, textDoctorID.getText());
								pst.setString(2, textDepartment.getText());
								pst.setString(3, textEmail.getText());
								pst.setString(4, textDoctorName.getText());
								pst.setString(5, textPassword.getText());

								int x = pst.executeUpdate();
								if (x > 0) {
									JOptionPane.showMessageDialog(null, "                   Successful Sign-up", null,
											JOptionPane.PLAIN_MESSAGE);
									LoginPage login = new LoginPage();
									LoginPage.main(null);
									frame.setVisible(false);
									con.close();
								} else {
									JOptionPane.showMessageDialog(null, "Sign-up failed", "Try it again",
											JOptionPane.ERROR_MESSAGE);
									textDoctorName.setText(null);
									textDepartment.setText(null);
									textEmail.setText(null);
									textPassword.setText(null);
									textPasswordCo.setText(null);
								}

							}
						} catch (Exception e1) {
							System.out.println(e1);
						}

					} else {
						JOptionPane.showMessageDialog(null, "Password is not Same", "Try it again",
								JOptionPane.ERROR_MESSAGE);
						textPassword.setText(null);
						textPasswordCo.setText(null);

					}
				}*/
			}
		});
		btnSignUp.setBounds(104, 747, 93, 23);
		frame.getContentPane().add(btnSignUp);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame frmLoginSystem = new JFrame("Cancel");
				if (JOptionPane.showConfirmDialog(frmLoginSystem, "Confirm if you want to cancel", "Login Systems",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					LoginPage login = new LoginPage();
					LoginPage.main(null);
					frame.setVisible(false);
			}
		}});
		btnCancel.setBounds(465, 747, 93, 23);
		frame.getContentPane().add(btnCancel);

		JLabel lblDoctorID = new JLabel("DoctorID");
		lblDoctorID.setFont(new Font("Arial", Font.PLAIN, 25));
		lblDoctorID.setBounds(28, 549, 137, 48);
		frame.getContentPane().add(lblDoctorID);

		textDoctorID = new JTextField();
		textDoctorID.setColumns(10);
		textDoctorID.setBounds(241, 567, 253, 24);
		frame.getContentPane().add(textDoctorID);
	}
}
