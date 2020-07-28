package SteamboatSprings.SiteManagementAPI.EmployeeTimesheet;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.SpringLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

public class EmpWorkHours {

	private JFrame frmRevolutionSystemsEmployee;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmpWorkHours window = new EmpWorkHours();
					window.frmRevolutionSystemsEmployee.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EmpWorkHours() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRevolutionSystemsEmployee = new JFrame();
		frmRevolutionSystemsEmployee.setIconImage(Toolkit.getDefaultToolkit().getImage(EmpWorkHours.class.getResource("/windowBuilder/Resources/6cfcb4e9556799a6fcbf983aab9fab19-32bits-16.png")));
		frmRevolutionSystemsEmployee.setTitle("Revolution Systems Employee Timesheet");
		frmRevolutionSystemsEmployee.setBounds(100, 100, 415, 240);
		frmRevolutionSystemsEmployee.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRevolutionSystemsEmployee.getContentPane().setLayout(null);
		
		JPanel panelEmplTS = new JPanel();
		panelEmplTS.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Employee Timesheet", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelEmplTS.setBounds(12, 13, 388, 130);
		frmRevolutionSystemsEmployee.getContentPane().add(panelEmplTS);
		panelEmplTS.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		JLabel lblEmployeeName = new JLabel("Employee Name");
		lblEmployeeName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelEmplTS.add(lblEmployeeName, "2, 2, center, fill");
		
		JComboBox comboBoxEmplName = new JComboBox();
		comboBoxEmplName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBoxEmplName.setModel(new DefaultComboBoxModel(new String[] {"Danir Hernandez", "Heidy Alcantar", "Jaime Sevilla Hernandez", "Maynor Galeano Martinez", "Ruth Alcantar", "Wilmer Sevilla"}));
		panelEmplTS.add(comboBoxEmplName, "6, 2, fill, fill");
		
		JLabel lblWorkHours = new JLabel("Work Hours");
		lblWorkHours.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelEmplTS.add(lblWorkHours, "2, 4, center, default");
		
		JComboBox comboBoxWorkHours = new JComboBox();
		comboBoxWorkHours.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBoxWorkHours.setModel(new DefaultComboBoxModel(new String[] {"", "4", "4.25", "4.5", "4.75", "5", "5.25", "6.5", "6.75", "7", "7.25", "7.5", "7.75", "8", "8.25", "8.5", "8.75", "9", "9.25", "9.5", "9.75", "10", "10.25", "10.5", "10.75", "11"}));
		panelEmplTS.add(comboBoxWorkHours, "6, 4, fill, fill");
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelEmplTS.add(lblDate, "2, 6, center, top");
		
		JDateChooser dateChooser = new JDateChooser();
		panelEmplTS.add(dateChooser, "6, 6, fill, fill");
		
		JPanel panelButtons = new JPanel();
		panelButtons.setBounds(12, 156, 375, 33);
		frmRevolutionSystemsEmployee.getContentPane().add(panelButtons);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(0, 3, 120, 29);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(124, 3, 120, 29);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExit.setBounds(248, 3, 115, 29);
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelButtons.setLayout(null);
		panelButtons.add(btnSubmit);
		panelButtons.add(btnClear);
		panelButtons.add(btnExit);
	}
}
