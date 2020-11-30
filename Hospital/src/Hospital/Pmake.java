package Hospital;

import javax.swing.JOptionPane;
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

public class Pmake {
	
	private Connection con = null;
	private Statement stat = null;
	private ResultSet rs = null;
	private PreparedStatement pst = null;
	
	public boolean Make_Appointment(String aid,String sin) {
		if (aid.equals("")||sin.equals("")) {
			JOptionPane.showMessageDialog(null, "Please input a Appointment id to make an appointment", "Try it again",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DBUtil.getConn();
				stat = con.createStatement();
				
				pst = con.prepareStatement(
						"Select * from appointment where Aid = '"+aid +"'");
				String sql ="Select * from appointment where Aid = '"+aid +"' and Psin = '-1'";
				rs = stat.executeQuery(sql);
				if(rs.next())  {
				pst = con.prepareStatement(
						"UPDATE appointment SET Psin='" + sin + "' where Aid = '"+aid +"'");
				int x = pst.executeUpdate();
				if (x > 0) {
					//JOptionPane.showMessageDialog(null, "                   Successful Booked", null,
					//		JOptionPane.PLAIN_MESSAGE);
					//textname.setText(textname.getText());
					//textgender.setText(textgender.getText());
					//textphonenumber.setText(textphonenumber.getText());
					//textage.setText(textage.getText());
					//textsin.setText(textsin.getText());
					return true;
				} else {
					JOptionPane.showMessageDialog(null, "Booking failed Try it again", "Try it again",
							JOptionPane.ERROR_MESSAGE);
					return false;
				}
				}else {
					JOptionPane.showMessageDialog(null, "Appointment Unavailable", "Try it again",
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
