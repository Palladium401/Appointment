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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Dappointment {

	private JFrame frame;
	private JSeparator separator_1;
	private JLabel AddAppointment;
	private JLabel lblDate;
	private JLabel lblDepartment;
	private JLabel TableInfo;
	private JTable FutureAppointment;
	private JLabel lblFutureappointment;
	private JTextField Datetext;

	private Connection con = null;
	private Statement stat = null;
	private ResultSet rs = null;
	private PreparedStatement pst = null;
	private JButton btnAddAppointment;
	private JScrollPane YourAp;
	private JTable table;
	private JScrollPane FutureAp;
	private static  String id;
	private JButton btnupdateap;
	private JLabel inputappointmentid;
	private JTextField aid;
	private JButton btndelete;
	private JButton btnLogOUT;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dappointment window = new Dappointment(id);
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
	public Dappointment(String id) {
		this.id = id;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1314, 846);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblTitle = new JLabel("Doctor Appointment system");
		lblTitle.setFont(new Font("BIZ UDPMincho Medium", Font.BOLD, 17));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

		separator_1 = new JSeparator();

		AddAppointment = new JLabel("Add Appointment:");
		AddAppointment.setFont(new Font("BIZ UDPMincho Medium", Font.BOLD, 13));

		lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("BIZ UDPMincho Medium", Font.BOLD, 12));

		lblDepartment = new JLabel("Department:");
		lblDepartment.setFont(new Font("BIZ UDPMincho Medium", Font.BOLD, 12));

		TableInfo = new JLabel("Your appointment:");
		TableInfo.setFont(new Font("BIZ UDPMincho Medium", Font.BOLD, 13));

		lblFutureappointment = new JLabel("FutureAppointment:");
		lblFutureappointment.setFont(new Font("BIZ UDPMincho Medium", Font.BOLD, 13));
		
		JComboBox Department = new JComboBox();
		Department.setBounds(149, 323, 128, 21);
		frame.getContentPane().add(Department);
		Department.addItem("Internel");
		Department.addItem("Surgery");
		Department.addItem("Child");

		JButton btnYourAp = new JButton("Refresh");
		btnYourAp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					Class.forName("com.mysql.jdbc.Driver");
					con = DBUtil.getConn();
					stat = con.createStatement();
					String query = "Select * from appointment where DoctorID ='"+id+"'";
					pst = con.prepareStatement(query);
					rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception e1) {
					System.out.println(e1);
				}
			}
		});

		JButton btnUpdate = new JButton("Refresh");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					Class.forName("com.mysql.jdbc.Driver");
					con = DBUtil.getConn();
					stat = con.createStatement();
					String query = "Select * from appointment where Psin != '-1'and DoctorID ='"+id+"'";
					pst = con.prepareStatement(query);
					rs = pst.executeQuery();
					FutureAppointment.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception e1) {
					System.out.println(e1);
				}
			}
		});

		Datetext = new JTextField();
		Datetext.setColumns(10);

		btnAddAppointment = new JButton("Upload");
		btnAddAppointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DaUpload Dupload = new DaUpload();
				boolean result =Dupload.upload(Datetext.getText(),Department.getSelectedItem().toString().trim(), id);
				if(result) {
					JOptionPane.showMessageDialog(null, "                   Successful upload", null,
							JOptionPane.PLAIN_MESSAGE);
				}else {
					Datetext.setText(null);
					//Departmenttext.setText(null);
				}
				
				/*try {
					
					if (Datetext.getText().equals("") ||  Departmenttext.getText().equals("") ) {
						JOptionPane.showMessageDialog(null, "Please input all the information", "Try it again",
								JOptionPane.ERROR_MESSAGE);
					} else {
						Class.forName("com.mysql.jdbc.Driver");
						con = DBUtil.getConn();
						stat = con.createStatement();
					
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
					  Date bt =sdf.parse(Datetext.getText()); 
					  Date et =sdf.parse(sdf.format(System.currentTimeMillis()));
					  
					  if (bt.before(et)){ 
						  JOptionPane.showMessageDialog(null, "Time unavailable", "Try it again",
									JOptionPane.ERROR_MESSAGE);
							Datetext.setText(null);
							Departmenttext.setText(null);
						  }else{ 
							  pst = con.prepareStatement(
										"insert into appointment(DoctorID,Department,ADate) values(?,?,?)");
								pst.setString(1,id);
								pst.setString(3,Datetext.getText());
								pst.setString(2, Departmenttext.getText());
								//pst.setString(4,df.format(System.currentTimeMillis()));
								//pst.setString(3, txtSim.getText());
								
								int x = pst.executeUpdate();
								if (x > 0) {
									JOptionPane.showMessageDialog(null, "                   Successful upload", null,
											JOptionPane.PLAIN_MESSAGE);
									con.close();
								} else {
									JOptionPane.showMessageDialog(null, "Up-Load failed", "Try it again",
											JOptionPane.ERROR_MESSAGE);
									Datetext.setText(null);
									Departmenttext.setText(null);						
								} 	
						  } 
					} 
					

				} catch (Exception e1) {
					System.out.println(e1);
			
			}*/}} );

		YourAp = new JScrollPane();
		
		FutureAp = new JScrollPane();
		
		btnupdateap = new JButton("Update");
		btnupdateap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DaUpdate Dupload = new DaUpdate();
				boolean result = Dupload.Update(Datetext.getText(), Department.getSelectedItem().toString().trim(), aid.getText());
				if(result) {
					
				}else {
					Datetext.setText(null);
					//Departmenttext.setText(null);
				}
				/*try {
					
					if (Datetext.getText().equals("") ||  Departmenttext.getText().equals("")||aid.getText().equals("") ) {
						JOptionPane.showMessageDialog(null, "Please input all the information", "Try it again",
								JOptionPane.ERROR_MESSAGE);
					} else {
						Class.forName("com.mysql.jdbc.Driver");
						con = DBUtil.getConn();
						stat = con.createStatement();
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
					  Date bt =sdf.parse(Datetext.getText()); 
					  Date et =sdf.parse(sdf.format(System.currentTimeMillis()));
					  
					  if (bt.before(et)){ 
						  JOptionPane.showMessageDialog(null, "Date unavailable", "Try it again",
									JOptionPane.ERROR_MESSAGE);
							Datetext.setText(null);
							Departmenttext.setText(null);
						  }else{ 
							  String sql ="Select * from appointment where Aid = '"+aid.getText() +"' and Psin ='-1'";
							  rs = stat.executeQuery(sql);
							  if(rs.next()) {
							  pst = con.prepareStatement(
									  "UPDATE appointment SET ADate='"+Datetext.getText() +"' , Department = '"+Departmenttext.getText() +"' where Aid = '"+aid.getText() +"'");	
							  
								int x = pst.executeUpdate();
								if (x > 0) {
									JOptionPane.showMessageDialog(null, "                   Successful upload", null,
											JOptionPane.PLAIN_MESSAGE);
									con.close();
								} else {
									JOptionPane.showMessageDialog(null, "Up-Load failed", "Try it again",
											JOptionPane.ERROR_MESSAGE);
									Datetext.setText(null);
									Departmenttext.setText(null);						
								}
							  }else {
								  JOptionPane.showMessageDialog(null, "Appointment has been made can not be changed.", "Try it again",
											JOptionPane.ERROR_MESSAGE);
									Datetext.setText(null);
									Departmenttext.setText(null);	
							  }
								
						  } 
					} 
					

				} catch (Exception e1) {
					System.out.println(e1);
			
			}*/
				
			}
		});
		
		inputappointmentid = new JLabel("Input id to update or delete appointment:");
		inputappointmentid.setFont(new Font("BIZ UDPMincho Medium", Font.BOLD, 13));
		
		aid = new JTextField();
		aid.setColumns(10);
		
		btndelete = new JButton("Delete");
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DaDelete Ddelete = new DaDelete();
				boolean result = Ddelete.Delete(aid.getText());
				if(result) {
					
				}else {
					Datetext.setText(null);
					//Departmenttext.setText(null);	
				}
				/*try {
					
					if (aid.getText().equals("") ) {
						JOptionPane.showMessageDialog(null, "Please input Appointment id", "Try it again",
								JOptionPane.ERROR_MESSAGE);
					} else {
						Class.forName("com.mysql.jdbc.Driver");
						con = DBUtil.getConn();
						stat = con.createStatement();
					  
					  
					String sql ="Select * from appointment where Aid = '"+aid.getText() +"' and Psin ='-1'";
					rs = stat.executeQuery(sql);
					if(rs.next()) {
					pst = con.prepareStatement(
							"DELETE from appointment where Aid = '"+aid.getText() +"'");		  
					int x = pst.executeUpdate();
					if (x > 0) {
							JOptionPane.showMessageDialog(null, "                   Successful delete", null,
							JOptionPane.PLAIN_MESSAGE);
							con.close();
							} else {
									JOptionPane.showMessageDialog(null, "Delete failed", "Try it again",
											JOptionPane.ERROR_MESSAGE);
									Datetext.setText(null);
									Departmenttext.setText(null);						
								}
							  }else {
								  JOptionPane.showMessageDialog(null, "Appointment has been made can not be deleted.", "Try it again",
											JOptionPane.ERROR_MESSAGE);
									Datetext.setText(null);
									Departmenttext.setText(null);	
							  }
								
						  } 
					
					

				} catch (Exception e1) {
					System.out.println(e1);
			
			}*/
			}
		});
		
		btnLogOUT = new JButton("Log Out");
		btnLogOUT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame frmLoginSystem = new JFrame("Exit");
				if (JOptionPane.showConfirmDialog(frmLoginSystem, "Confirm if you want to exit", "Login Systems",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
						LoginPage login = new LoginPage();
						LoginPage.main(null);
						frame.setVisible(false);
				}
			}
		});
		
		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(TableInfo, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(inputappointmentid)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(aid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 569, Short.MAX_VALUE)
							.addComponent(btnYourAp, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(AddAppointment, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblDepartment, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(Department, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE))
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblDate, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(Datetext, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)))
										.addGap(11)))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnAddAppointment, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnupdateap, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btndelete, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)))
							.addGap(16)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblFutureappointment, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 279, Short.MAX_VALUE)
									.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 344, Short.MAX_VALUE))
								.addComponent(FutureAp, GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE))))
					.addGap(37))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(YourAp, GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnLogOUT, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(1168, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(201)
					.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 546, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(553, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnUpdate)
						.addComponent(AddAppointment, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFutureappointment, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(38)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
								.addComponent(Datetext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDepartment, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
								.addComponent(Department, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAddAppointment)
								.addComponent(btndelete)
								.addComponent(btnupdateap))
							.addGap(18)
							.addComponent(btnLogOUT)
							.addGap(13))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(FutureAp, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(TableInfo, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnYourAp))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(inputappointmentid, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addComponent(aid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(39)
					.addComponent(YourAp, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.addGap(20))
		);
		
				FutureAppointment = new JTable();
				FutureAp.setViewportView(FutureAppointment);

		table = new JTable();
		YourAp.setViewportView(table);
		frame.getContentPane().setLayout(groupLayout);
	}
}
