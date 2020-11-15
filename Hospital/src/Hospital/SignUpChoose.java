package Hospital;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignUpChoose {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpChoose window = new SignUpChoose();
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
	public SignUpChoose() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblTitle = new JLabel("You are a doctor or patient?");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.PLAIN, 24));

		JSeparator separator = new JSeparator();

		JButton btnNewDoctor = new JButton("Doctor");
		btnNewDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DoctorSignUp DSP = new DoctorSignUp();
				DSP.main(null);
				frame.setVisible(false);
			}
		});

		JButton btnNewPatient = new JButton("Patient");
		btnNewPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Sign_up SP = new Sign_up();
				SP.main(null);
				frame.setVisible(false);
			}
		});

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(separator,
										GroupLayout.PREFERRED_SIZE, 402, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup().addGap(35).addComponent(lblTitle,
										GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(24, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup().addGap(54).addComponent(btnNewDoctor)
						.addPreferredGap(ComponentPlacement.RELATED, 124, Short.MAX_VALUE).addComponent(btnNewPatient)
						.addGap(96)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(19)
						.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(71).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewDoctor).addComponent(btnNewPatient))
						.addContainerGap(83, Short.MAX_VALUE)));
		frame.getContentPane().setLayout(groupLayout);
	}
}
