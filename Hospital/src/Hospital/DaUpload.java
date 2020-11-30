package Hospital;

import java.text.SimpleDateFormat;
import java.util.Date;

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

import javax.swing.JOptionPane;

public class DaUpload {

	private Connection con = null;
	private Statement stat = null;
	private ResultSet rs = null;
	private PreparedStatement pst = null;
	
	public boolean upload(String Datetext,String Department,String id) {
		try {
			
			if (Datetext.equals("") ||  Department.equals("") ) {
				JOptionPane.showMessageDialog(null, "Please input all the information", "Try it again",
						JOptionPane.ERROR_MESSAGE);
				return false;
			} else {
				Class.forName("com.mysql.jdbc.Driver");
				con = DBUtil.getConn();
				stat = con.createStatement();
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
			  Date bt =sdf.parse(Datetext); 
			  Date et =sdf.parse(sdf.format(System.currentTimeMillis()));
			  
			  if (bt.before(et)){ 
				  JOptionPane.showMessageDialog(null, "Time unavailable", "Try it again",
							JOptionPane.ERROR_MESSAGE);
				  	return false;
					//Datetext.setText(null);
					//Departmenttext.setText(null);
				  }else{ 
					  pst = con.prepareStatement(
								"insert into appointment(DoctorID,Department,ADate) values(?,?,?)");
						pst.setString(1,id);
						pst.setString(3,Datetext);
						pst.setString(2, Department);
						//pst.setString(4,df.format(System.currentTimeMillis()));
						//pst.setString(3, txtSim.getText());
						
						int x = pst.executeUpdate();
						if (x > 0) {
							con.close();
							return true;
						} else {
							JOptionPane.showMessageDialog(null, "Up-Load failed", "Try it again",
									JOptionPane.ERROR_MESSAGE);
							return false;
							//Datetext.setText(null);
							//Departmenttext.setText(null);						
						} 	
				  } 
			} 
			

		} catch (Exception e1) {
			System.out.println(e1);
	
	}
		return true;
	}
}
