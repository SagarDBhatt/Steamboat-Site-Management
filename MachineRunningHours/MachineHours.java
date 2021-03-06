package SteamboatSprings.SiteManagementAPI.MachineRunningHours;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPanel;
import java.awt.Font;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.TitledBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class MachineHours {

	private JFrame machineHourFrame;
	private JTextField txtFieldClockHours;
	private JTextField txtFieldComments;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MachineHours window = new MachineHours();
					window.machineHourFrame.setVisible(true); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MachineHours() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		machineHourFrame = new JFrame();
		machineHourFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("F:\\Source Code\\SBHATT_Eclipse_Workspace\\DatabaseIntegration\\src\\windowBuilder\\Resources\\RS_logo.jpg"));
		machineHourFrame.setTitle("Revolution Systems Machine Hour Tracker");
		machineHourFrame.setBounds(100, 100, 373, 308);
		machineHourFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelInputFunctions = new JPanel();
		panelInputFunctions.setBorder(new TitledBorder(null, "Hours Tracker", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panelButtons = new JPanel();
		GroupLayout groupLayout = new GroupLayout(machineHourFrame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panelButtons, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panelInputFunctions, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(69, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelInputFunctions, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelButtons, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelButtons.setLayout(new MigLayout("", "[83px][69px][59px]", "[29px]"));

//ActionListener Method for "Clear" button.		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtFieldComments.setText("");
				txtFieldClockHours.setText("");
			}
		});
//End of ActionListener. 		

		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelButtons.add(btnClear, "cell 1 0,alignx right,aligny top");

//ActionListener Method for "Exit" button.		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int reply = JOptionPane.showConfirmDialog(null, "Do you want to Exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				
				if (reply == JOptionPane.YES_OPTION)
				{
					System.exit(0);
				}
				else
					txtFieldClockHours.requestFocus();
			}
			
		});
//End of ActionListener. 
		
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelButtons.add(btnExit, "cell 2 0,alignx left,aligny top");
		panelInputFunctions.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("94px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("170px"),},
			new RowSpec[] {
				FormSpecs.PARAGRAPH_GAP_ROWSPEC,
				RowSpec.decode("25px"),
				FormSpecs.LINE_GAP_ROWSPEC,
				RowSpec.decode("27px"),
				FormSpecs.LINE_GAP_ROWSPEC,
				RowSpec.decode("69px"),}));
		
		JLabel lblClockHours = new JLabel("Clock Hours");
		lblClockHours.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelInputFunctions.add(lblClockHours, "2, 2, left, top");
		
		txtFieldClockHours = new JTextField();
		txtFieldClockHours.setColumns(10);
		panelInputFunctions.add(txtFieldClockHours, "4, 2, fill, fill");
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelInputFunctions.add(lblDate, "2, 4, center, top");
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("MM-dd-yyyy");
		panelInputFunctions.add(dateChooser, "4, 4, fill, fill");
		dateChooser.setDateFormatString("MM-dd-yyyy");
		
		JLabel lblComments = new JLabel("Comments");
		lblComments.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelInputFunctions.add(lblComments, "2, 6, left, center");
		
		txtFieldComments = new JTextField();
		txtFieldComments.setColumns(10);
		panelInputFunctions.add(txtFieldComments, "4, 6, fill, fill");
		machineHourFrame.getContentPane().setLayout(groupLayout);
		
		
//ActionListener Method for Submit button		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Tracker.connectToSQL();
				Tracker.reviewData(dateChooser, txtFieldClockHours.getText(), txtFieldComments.getText().toString());
				txtFieldClockHours.setText("");
				txtFieldComments.setText("");
				
			}
		});
//End of ActionListener method
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelButtons.add(btnNewButton, "cell 0 0,alignx left,aligny top");
		
		
	}
}
