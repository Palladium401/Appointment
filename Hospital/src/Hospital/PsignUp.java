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

public class PsignUp {
	
	private Connection con = null;
	private Statement stat = null;
	private ResultSet rs = null;
	private PreparedStatement pst = null;

	public int signup(String Username,String Birthday,String Sim,String Gender,String Phonenumber,String Age,String Password,String PasswordConfirm ) {
		
		if (Username.equals("") || Birthday.equals("") || Sim.equals("")
				|| Gender.equals("") || Phonenumber.equals("")
				|| Age.equals("") || Password.equals("")
				|| PasswordConfirm.equals("")) {
			JOptionPane.showMessageDialog(null, "Please input all the information", "Try it again",
					JOptionPane.ERROR_MESSAGE);
			return -1;
		} else {
			if (Password.equals(PasswordConfirm)) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DBUtil.getConn();
					stat = con.createStatement();

					String sql = "Select * from patient where SIM ='" + Sim + "'";
					rs = stat.executeQuery(sql);
					if (rs.next()) {
						JOptionPane.showMessageDialog(null, "The ID has already exist", null,
								JOptionPane.ERROR_MESSAGE);
						return -2;
					}
					else {
					Class.forName("com.mysql.jdbc.Driver");
					con = DBUtil.getConn();
					pst = con.prepareStatement(
							"insert into patient(name,birthday,SIM,Gender,Phonenumber,Age,Password) values(?,?,?,?,?,?,?)");

					pst.setString(1, Username);
					pst.setString(2, Birthday);
					pst.setString(3, Sim);
					pst.setString(4, Gender);
					pst.setString(5, Phonenumber);
					pst.setString(6, Age);
					pst.setString(7, Password);
					int x = pst.executeUpdate();
					if (x > 0) {
						JOptionPane.showMessageDialog(null, "                   Successful Sign-up", null,
								JOptionPane.PLAIN_MESSAGE);
						con.close();
						//LoginPage login = new LoginPage();
						//LoginPage.main(null);
						//frame.setVisible(false);
						return 1;
					} else {
						JOptionPane.showMessageDialog(null, "Sign-up failed", "Try it again",
								JOptionPane.ERROR_MESSAGE);
						//txtUsername.setText(null);
						//txtBirthday.setText(null);
						//txtSim.setText(null);
						//txtGender.setText(null);
						//txtPhonenumber.setText(null);
						//txtAge.setText(null);
						//txtPassword.setText(null);
						//txtPasswordConfirm.setText(null);
						return -2;
					}}

				} catch (Exception e1) {
					System.out.println(e1);
				}
				return -1;
				
			} else {
				JOptionPane.showMessageDialog(null, "Password is not Same", "Try it again",
						JOptionPane.ERROR_MESSAGE);
				//txtPassword.setText(null);
				//txtPasswordConfirm.setText(null);
				return -3;

			}

		}
		
	}
	
}
