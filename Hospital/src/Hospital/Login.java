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


public class Login {

	private Connection con = null;
	private Statement stat = null;
	private ResultSet rs = null;
	private PreparedStatement pst = null;
	private String id;
	
	public  int LoginAct(String type,String username,String password) {
	
		try {

		Class.forName("com.mysql.jdbc.Driver");
		con = DBUtil.getConn();
		stat = con.createStatement();

		if (type == "select") {
			JOptionPane.showMessageDialog(null, "Please choose your type");
		} else if (type == "Patient") {

			String sql = "Select SIM from patient where name='" + username + "' and password='" + password
					+ "'";
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				id = rs.getString("SIM");
				JOptionPane.showMessageDialog(null, "Welcome to Hospital " + username + " Your ID is : "+id);
				//Pappointment Pa = new Pappointment(id);
				//Pa.main(null);
				//frame.setVisible(false);
				return 1;
			} else {
				JOptionPane.showMessageDialog(null, "Username or Password is incorrect");
				return -1;
				//txtPassword.setText(null);
				//txtUsername.setText(null);
			}
		} else {
			String sql = "Select DoctorID from doctor where DName='" + username + "' and DPassword='" + password
					+ "'";
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				id = rs.getString("DoctorID");
				JOptionPane.showMessageDialog(null, "Welcome to Hospital " + username+" Your ID is : "+id);

				//sql ="Select DoctorID from doctor where DName='"+username+"' and DPassword='"+password+"'";
				//rs = stat.executeQuery(sql);
				
				 
				//Dappointment Da = new Dappointment(id);
				//Da.main(null);
				//frame.setVisible(false);
				return 2;
			} else {
				JOptionPane.showMessageDialog(null, "Username or Password is incorrect");
				return -1;
			}
		}
	} catch (Exception e1) {
		System.out.println(e1);
	}
		return 0;
	}

	public String getid() {
		return id;
	}
}

