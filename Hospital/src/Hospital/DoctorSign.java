package Hospital;

import javax.swing.JOptionPane;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
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
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JComboBox;

public class DoctorSign {
	
	String textDoctorID;
	String textDepartment;
	String textEmail;
	String textDoctorName;
	String textPassword;
	String passwordconfirm;
	
	private Connection con = null;
	private Statement stat = null;
	private ResultSet rs = null;
	private PreparedStatement pst = null;
	
	public boolean signUp(String textDoctorID ,String textDepartment ,String textEmail ,String textDoctorName,String textPassword,String passwordconfirm) {
	if (textDoctorID.equals("") || textDepartment.equals("")
			|| textEmail.equals("") || textDoctorName.equals("")
			|| textPassword.equals("")) {
		JOptionPane.showMessageDialog(null, "Please input all the information", "Try it again",
				JOptionPane.ERROR_MESSAGE);
		return false;
	} else {
		if (textPassword.equals(passwordconfirm)) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DBUtil.getConn();
				stat = con.createStatement();

				String DoctorID = textDoctorID;
				String sql = "Select * from doctor where DoctorID ='" + DoctorID + "'";
				rs = stat.executeQuery(sql);
				if (rs.next()) {
					JOptionPane.showMessageDialog(null, "The ID has already exist", null,
							JOptionPane.ERROR_MESSAGE);
					return false;
				} else {
					pst = con.prepareStatement(
							"insert into doctor(DoctorID,Department,Email,DName,DPassword) values(?,?,?,?,?)");

					pst.setString(1, textDoctorID);
					pst.setString(2, textDepartment);
					pst.setString(3, textEmail);
					pst.setString(4, textDoctorName);
					pst.setString(5, textPassword);

					int x = pst.executeUpdate();
					if (x > 0) {
						JOptionPane.showMessageDialog(null, "                   Successful Sign-up", null,
								JOptionPane.PLAIN_MESSAGE);
						//LoginPage login = new LoginPage();
						//LoginPage.main(null);
						//frame.setVisible(false);
						con.close();
						return true;
					} else {
						JOptionPane.showMessageDialog(null, "Sign-up failed", "Try it again",
								JOptionPane.ERROR_MESSAGE);
						return false;
						//textDoctorName.setText(null);
						//textDepartment.setText(null);
						//textEmail.setText(null);
						//textPassword.setText(null);
						//textPasswordCo.setText(null);
					}

				}
			} catch (Exception e1) {
				System.out.println(e1);
			}

		} else {
			JOptionPane.showMessageDialog(null, "Password is not Same", "Try it again",
					JOptionPane.ERROR_MESSAGE);
			return false;
			//textPassword.setText(null);
			//textPasswordCo.setText(null);

		}
	}
	return true;
}
}
