import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;

public class GymManager{

	JFrame mainFrame;
	JTable table;
	JButton buttonLeft, buttonRight, buttonModify, buttonDelete;
	int displayMode; //0-Customer, 1-Staff, 2-Equipment, 3-Membership

	public GymManager(){

		//outer enclosing JPanel
		JPanel parentPanel = new JPanel();
		parentPanel.setLayout(new BorderLayout());

		//NORTH
		{
			JPanel topPart = new JPanel();
			topPart.setLayout(new BorderLayout());
			topPart.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
			
			JLabel appTitle = new JLabel("Gym Management");
			appTitle.setFont(appTitle.getFont().deriveFont(20.0f));
			appTitle.setBorder(new EmptyBorder(20, 20, 20, 20));
			//appTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
			topPart.add(appTitle, BorderLayout.CENTER);

			parentPanel.add(topPart, BorderLayout.NORTH);
		}

		//CENTER
		{
			JPanel centerPanel = new JPanel();
			centerPanel.setLayout(new BorderLayout());
			centerPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

			//First row of buttons
			{
				GridLayout buttonsInRowLayout = new GridLayout(1, 4);
				JPanel buttonList = new JPanel();
				buttonList.setLayout(buttonsInRowLayout);

				JButton buttonCustomer = new JButton("Customer");
				buttonCustomer.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						applyCustomerModel();
					}
				});
				buttonList.add(buttonCustomer);

				JButton buttonStaff = new JButton("Staff");
				buttonStaff.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						applyStaffModel();
					}
				});
				buttonList.add(buttonStaff);

				JButton buttonEquipment = new JButton("Equipment");
				buttonEquipment.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						applyEquipmentModel();
					}
				});
				buttonList.add(buttonEquipment);

				JButton buttonMembership = new JButton("Membership");
				buttonMembership.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						applyMembershipModel();
					}
				});
				buttonList.add(buttonMembership);
				centerPanel.add(buttonList, BorderLayout.NORTH);
			}

			//table
			{
				table = new JTable();
				applyCustomerModel();
				JScrollPane scrollPane = new JScrollPane(table);
				table.setFillsViewportHeight(true);
				centerPanel.add(scrollPane, BorderLayout.CENTER);
			}

			//Second row of buttons
			{
				GridLayout buttonsInRowLayout = new GridLayout(1, 10);
				JPanel buttonList = new JPanel();
				buttonList.setLayout(buttonsInRowLayout);

				buttonLeft = new JButton("<-");
				buttonLeft.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){

					}
				});
				buttonList.add(buttonLeft);

				buttonRight = new JButton("->");
				buttonRight.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){

					}
				});
				buttonList.add(buttonRight);

				buttonModify = new JButton("Modify");
				buttonModify.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){

					}
				});
				buttonList.add(buttonModify);

				buttonDelete = new JButton("Delete");
				buttonDelete.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){

					}
				});
				buttonList.add(buttonDelete);

				for(int i=0; i<4; i++){
					JButton dummyButton = new JButton();
					dummyButton.setVisible(false);
					buttonList.add(dummyButton);
				}

				centerPanel.add(buttonList, BorderLayout.SOUTH);
			}

			parentPanel.add(centerPanel, BorderLayout.CENTER);
		}

		//main JFrame
		mainFrame = new JFrame();
		mainFrame.getContentPane().add(parentPanel);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(900, 700);
		mainFrame.setVisible(true);
	}

	public void applyCustomerModel(){
		table.setModel(new CustomerModel());
		displayMode = 0;
	}

	public void applyStaffModel(){
		table.setModel(new StaffModel());
		displayMode = 1;
	}

	public void applyEquipmentModel(){
		table.setModel(new EquipmentModel());
		displayMode = 2;
	}

	public void applyMembershipModel(){
		table.setModel(new MembershipModel());
		displayMode = 3;
	}

	public static void main(String[] args){
		GymManager manager = new GymManager();
	}

}