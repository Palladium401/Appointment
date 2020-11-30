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

public class Pappointment {

	private JFrame frame;
	private JTable AvliableAppointment;
	private JTable FutureAppointment;
	private JSeparator separator;
	private JLabel lblPersonalinfo;
	private JLabel lblFutureAppointment;
	private JLabel lblAvaliable;
	private JLabel lblName;
	private JLabel lblGender;
	private JLabel lblPhoneNumber;
	private JButton button;
	private JButton btnNewButton;
	private JButton btnUpdate;
	private JLabel lblAge;
	private JButton btnInfoUpdate;
	private JLabel lblSin;
	private JSeparator separator_1;
	private JScrollPane FutureAp;
	private JScrollPane AvliableAp;

	private Connection con = null;
	private Statement stat = null;
	private ResultSet rs = null;
	private PreparedStatement pst = null;
	private JButton btnAddAppointment;
	private JScrollPane YourAp;
	private JTextField textname;
	private JTextField textgender;
	private JTextField textphonenumber;
	private JTextField textage;
	private JTextField textsin;
	private static  String id;
	private JTextField aid;
	private JButton btnLogout;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pappointment window = new Pappointment(id);
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
	public Pappointment(String id) {
		this.id= id;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1126, 871);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lbltitle = new JLabel("Patient Appointment Page");
		lbltitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbltitle.setFont(new Font("BIZ UDPMincho Medium", Font.BOLD, 13));

		separator = new JSeparator();

		lblPersonalinfo = new JLabel("Parsonal Information:");
		lblPersonalinfo.setFont(new Font("BIZ UDPMincho Medium", Font.BOLD, 12));

		lblFutureAppointment = new JLabel("Future Appointment:");
		lblFutureAppointment.setFont(new Font("BIZ UDPMincho Medium", Font.BOLD, 12));

		lblAvaliable = new JLabel("Available Appointment:");
		lblAvaliable.setFont(new Font("BIZ UDPMincho Medium", Font.BOLD, 12));

		lblName = new JLabel("Name:");
		lblName.setFont(new Font("BIZ UDPMincho Medium", Font.BOLD, 12));

		lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("BIZ UDPMincho Medium", Font.BOLD, 12));

		lblPhoneNumber = new JLabel("PhoneNumber");
		lblPhoneNumber.setFont(new Font("BIZ UDPMincho Medium", Font.BOLD, 12));

		button = new JButton("Make an appointment");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Pmake pmake =new Pmake();
				boolean result =pmake.Make_Appointment(aid.getText(), textsin.getText());
				if(result) {
					JOptionPane.showMessageDialog(null, "                   Successful Booked", null,
							JOptionPane.PLAIN_MESSAGE);
				}else {
					
				}
				
				/*if (aid.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please input a Appointment id to make an appointment", "Try it again",
							JOptionPane.ERROR_MESSAGE);
				}
				else {
					try {
						Class.forName("com.mysql.jdbc.Driver");
						con = DBUtil.getConn();
						stat = con.createStatement();
						
						pst = con.prepareStatement(
								"Select * from appointment where Aid = '"+aid.getText() +"'");
						String sql ="Select * from appointment where Aid = '"+aid.getText() +"' and Psin = '-1'";
						rs = stat.executeQuery(sql);
						if(rs.next())  {
						pst = con.prepareStatement(
								"UPDATE appointment SET Psin='" + textsin.getText() + "' where Aid = '"+aid.getText() +"'");
						int x = pst.executeUpdate();
						if (x > 0) {
							JOptionPane.showMessageDialog(null, "                   Successful Booked", null,
									JOptionPane.PLAIN_MESSAGE);
							textname.setText(textname.getText());
							textgender.setText(textgender.getText());
							textphonenumber.setText(textphonenumber.getText());
							textage.setText(textage.getText());
							textsin.setText(textsin.getText());			
						} else {
							JOptionPane.showMessageDialog(null, "Booking failed Try it again", "Try it again",
									JOptionPane.ERROR_MESSAGE);
						}
						}else {
							JOptionPane.showMessageDialog(null, "Appointment Unavailable", "Try it again",
									JOptionPane.ERROR_MESSAGE);
						}
						

					} catch (Exception e1) {
						System.out.println(e1);
					}
					
				}*/
			}
		});

		btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					Class.forName("com.mysql.jdbc.Driver");
					con = DBUtil.getConn();
					stat = con.createStatement();
					String query = "Select * from appointment where Psin = '-1'";
					pst = con.prepareStatement(query);
					rs = pst.executeQuery();
					
					AvliableAppointment.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception e1) {
					System.out.println(e1);
				}
			}
		});

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					Class.forName("com.mysql.jdbc.Driver");
					con = DBUtil.getConn();
					stat = con.createStatement();
					String query = "Select * from appointment where Psin ='" + id + "'";
					pst = con.prepareStatement(query);
					rs = pst.executeQuery();
					FutureAppointment.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception e1) {
					System.out.println(e1);
				}
			}
		});

		lblAge = new JLabel("Age:");
		lblAge.setFont(new Font("BIZ UDPMincho Medium", Font.BOLD, 12));

		btnInfoUpdate = new JButton("UpdateInfo");
		btnInfoUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Pupdateinfo pupdateinfo = new Pupdateinfo();
				boolean result = pupdateinfo.updateinfo(textname.getText(), textgender.getText(), textphonenumber.getText(), textage.getText(), textsin.getText());
				if(result) {
					textname.setText(textname.getText());
					textgender.setText(textgender.getText());
					textphonenumber.setText(textphonenumber.getText());
					textage.setText(textage.getText());
					textsin.setText(textsin.getText());
				}else {
					
				}
				
				/*if (textname.getText().equals("") ||  textgender.getText().equals("") || textphonenumber.getText().equals("")
						|| textage.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please input all the information", "Try it again",
							JOptionPane.ERROR_MESSAGE);
				} else {
					
						try {
							Class.forName("com.mysql.jdbc.Driver");
							con = DBUtil.getConn();
							stat = con.createStatement();
							pst = con.prepareStatement(
									"UPDATE patient SET name='" + textname.getText() + "', Gender='" + textgender.getText() + "', Phonenumber='" + textphonenumber.getText() + "', Age='" + textage.getText() + "' where SIM = '"+textsin.getText() +"'");

							int x = pst.executeUpdate();
							if (x > 0) {
								JOptionPane.showMessageDialog(null, "                   Successful Sign-up", null,
										JOptionPane.PLAIN_MESSAGE);
								textname.setText(textname.getText());
								textgender.setText(textgender.getText());
								textphonenumber.setText(textphonenumber.getText());
								textage.setText(textage.getText());
								textsin.setText(textsin.getText());			
							} else {
								JOptionPane.showMessageDialog(null, "Sign-up failed Try it again", "Try it again",
										JOptionPane.ERROR_MESSAGE);
							}

						} catch (Exception e1) {
							System.out.println(e1);
						}
			
		}*/}});

		lblSin = new JLabel("SIN:");
		lblSin.setFont(new Font("BIZ UDPMincho Medium", Font.BOLD, 12));

		try {

			Class.forName("com.mysql.jdbc.Driver");
			con = DBUtil.getConn();
			stat = con.createStatement();
		
		String query = "Select * from patient where SIM = '" + id + "'";
		pst = con.prepareStatement(query);
		rs = pst.executeQuery();
		
		rs.next(); 
		//FutureAppointment.setModel(DbUtils.resultSetToTableModel(rs));
		separator_1 = new JSeparator();		
		FutureAp = new JScrollPane();		
		AvliableAp = new JScrollPane();
		textname = new JTextField(rs.getString("name"));
		textname.setColumns(10);		
		textgender = new JTextField(rs.getString("Gender"));
		textgender.setColumns(10);		
		textphonenumber = new JTextField(rs.getString("Phonenumber"));
		textphonenumber.setColumns(10);		
		textage = new JTextField(rs.getString("Age"));
		textage.setColumns(10);		
		textsin = new JTextField(rs.getString("SIM"));
		textsin.setColumns(10);

		} catch (Exception e1) {
			System.out.println(e1);
		}
		
		aid = new JTextField();
		aid.setColumns(10);
		
		JLabel lblInputAid = new JLabel("Input appointment id to book and delete:");
		lblInputAid.setHorizontalAlignment(SwingConstants.CENTER);
		lblInputAid.setFont(new Font("BIZ UDPMincho Medium", Font.BOLD, 12));
		
		JButton btnCancelap = new JButton("CancelAppointment");
		btnCancelap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Pcancel pcancel = new Pcancel();
				boolean result = pcancel.cancel(aid.getText(), textsin.getText());
				
				/*if (aid.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please input a Appointment id to make an appointment", "Try it again",
							JOptionPane.ERROR_MESSAGE);
				}
				else {
					try {
						Class.forName("com.mysql.jdbc.Driver");
						con = DBUtil.getConn();
						stat = con.createStatement();
						
						pst = con.prepareStatement(
								"Select * from appointment where Aid = '"+aid.getText() +"' and Psin ='"+textsin.getText()+"'");
						String sql ="Select * from appointment where Aid = '"+aid.getText() +"' and Psin ='"+textsin.getText()+"'";
						rs = stat.executeQuery(sql);
						if(rs.next()) {
						pst = con.prepareStatement(
								"UPDATE appointment SET Psin='-1' where Aid = '"+aid.getText() +"'");
						int x = pst.executeUpdate();
						if (x > 0) {
							JOptionPane.showMessageDialog(null, "                   Successful cancel", null,
									JOptionPane.PLAIN_MESSAGE);
							textname.setText(textname.getText());
							textgender.setText(textgender.getText());
							textphonenumber.setText(textphonenumber.getText());
							textage.setText(textage.getText());
							textsin.setText(textsin.getText());			
						} else {
							JOptionPane.showMessageDialog(null, "cancel failed Try it again", "Try it again",
									JOptionPane.ERROR_MESSAGE);
						}
						}else {
							JOptionPane.showMessageDialog(null, "Appointment Unavailable", "Try it again",
									JOptionPane.ERROR_MESSAGE);
						}
						

					} catch (Exception e1) {
						System.out.println(e1);
					}
					
				}*/
			}
		});
		
		btnLogout = new JButton("Log Out");
		btnLogout.addActionListener(new ActionListener() {
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
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(lblAvaliable, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(34)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(separator, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 853, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addGroup(groupLayout.createSequentialGroup()
													.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
														.addGroup(groupLayout.createSequentialGroup()
															.addComponent(lblPersonalinfo, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
															.addGap(222))
														.addComponent(lblGender, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblPhoneNumber, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
														.addComponent(lblAge, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
														.addGroup(groupLayout.createSequentialGroup()
															.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
																.addComponent(button)
																.addGroup(groupLayout.createSequentialGroup()
																	.addComponent(lblSin, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
																	.addGap(11)))
															.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
																.addGroup(groupLayout.createSequentialGroup()
																	.addPreferredGap(ComponentPlacement.RELATED)
																	.addComponent(btnCancelap, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
																	.addGap(18)
																	.addComponent(btnLogout)
																	.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE))
																.addGroup(groupLayout.createSequentialGroup()
																	.addPreferredGap(ComponentPlacement.RELATED)
																	.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
																		.addComponent(textage, Alignment.TRAILING)
																		.addComponent(textsin, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
																		.addComponent(textphonenumber, Alignment.TRAILING)
																		.addComponent(textgender, Alignment.TRAILING)
																		.addComponent(textname, Alignment.TRAILING))))))
													.addPreferredGap(ComponentPlacement.RELATED))
												.addGroup(groupLayout.createSequentialGroup()
													.addGap(153)
													.addComponent(lblInputAid, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED))
												.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE))
											.addGap(18))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(btnInfoUpdate)
											.addGap(163)))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblFutureAppointment, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(btnUpdate))
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(aid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGap(104)
												.addComponent(btnNewButton)
												.addGap(13)))
										.addComponent(FutureAp, GroupLayout.PREFERRED_SIZE, 563, GroupLayout.PREFERRED_SIZE))))
							.addGap(128)))
					.addGap(93))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(331)
					.addComponent(lbltitle, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(719, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(AvliableAp, GroupLayout.PREFERRED_SIZE, 1084, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(202, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lbltitle, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(lblPersonalinfo, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblFutureAppointment, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnUpdate)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(40)
							.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 9, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(textname, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblGender, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(textgender, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPhoneNumber, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(textphonenumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAge, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(textage, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSin, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(textsin, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnInfoUpdate, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addGap(43)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(button)
								.addComponent(btnCancelap)
								.addComponent(btnLogout))
							.addGap(24))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(22)
							.addComponent(FutureAp, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAvaliable, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton)
						.addComponent(lblInputAid, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(aid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(AvliableAp, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
				AvliableAppointment = new JTable();
				AvliableAp.setViewportView(AvliableAppointment);
		
				FutureAppointment = new JTable();
				FutureAp.setViewportView(FutureAppointment);
		frame.getContentPane().setLayout(groupLayout);
	}
}
