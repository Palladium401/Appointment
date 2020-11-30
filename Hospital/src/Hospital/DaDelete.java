package Hospital;

import java.awt.EventQueue;

import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import net.proteanit.sql.DbUtils;

import java.awt.Font;

import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.*;
import java.text.SimpleDateFormat;

public class DaDelete {

	private Connection con = null;
	private Statement stat = null;
	private ResultSet rs = null;
	private PreparedStatement pst = null;
	
	public boolean Delete(String aid) {
		try {
			
			if (aid.equals("") ) {
				JOptionPane.showMessageDialog(null, "Please input Appointment id", "Try it again",
						JOptionPane.ERROR_MESSAGE);
				return false;
			} else {
				Class.forName("com.mysql.jdbc.Driver");
				con = DBUtil.getConn();
				stat = con.createStatement();
			  
			  
			String sql ="Select * from appointment where Aid = '"+aid +"' and Psin ='-1'";
			rs = stat.executeQuery(sql);
			if(rs.next()) {
			pst = con.prepareStatement(
					"DELETE from appointment where Aid = '"+aid +"'");		  
			int x = pst.executeUpdate();
			if (x > 0) {
					JOptionPane.showMessageDialog(null, "                   Successful delete", null,
					JOptionPane.PLAIN_MESSAGE);
					con.close();
					return true;
					} else {
							JOptionPane.showMessageDialog(null, "Delete failed", "Try it again",
									JOptionPane.ERROR_MESSAGE);
							return false;
							//Datetext.setText(null);
							//Departmenttext.setText(null);						
						}
					  }else {
						  JOptionPane.showMessageDialog(null, "Appointment has been made can not be deleted.", "Try it again",
									JOptionPane.ERROR_MESSAGE);
						  return false;
							//Datetext.setText(null);
							//Departmenttext.setText(null);	
					  }
						
				  } 
		} catch (Exception e1) {
			System.out.println(e1);
	
	}
		return true;
	}
	
}
