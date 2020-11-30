package Hospital;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class Pupdateinfo {
	private Connection con = null;
	private Statement stat = null;
	private ResultSet rs = null;
	private PreparedStatement pst = null;
	
	public boolean updateinfo(String name,String gender,String Phonenumber,String age,String sin) {
		if (name.equals("") ||  gender.equals("") || Phonenumber.equals("")
				|| age.equals("")) {
			JOptionPane.showMessageDialog(null, "Please input all the information", "Try it again",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DBUtil.getConn();
					stat = con.createStatement();
					pst = con.prepareStatement(
							"UPDATE patient SET name='" + name + "', Gender='" + gender + "', Phonenumber='" + Phonenumber + "', Age='" + age + "' where SIM = '"+sin +"'");

					int x = pst.executeUpdate();
					if (x > 0) {
						JOptionPane.showMessageDialog(null, "                   Successful Sign-up", null,
								JOptionPane.PLAIN_MESSAGE);
						return true;
						//textname.setText(textname.getText());
						//textgender.setText(textgender.getText());
						//textphonenumber.setText(textphonenumber.getText());
						//textage.setText(textage.getText());
						//textsin.setText(textsin.getText());			
					} else {
						JOptionPane.showMessageDialog(null, "Sign-up failed Try it again", "Try it again",
								JOptionPane.ERROR_MESSAGE);
						return false;
					}

				} catch (Exception e1) {
					System.out.println(e1);
				}
				return true;
	
}
	}
}
