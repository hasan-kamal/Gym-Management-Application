import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class AddCustomDialog extends JDialog{

	JPanel mainPanel;
	AddState currentState;
	GymManager gymManager;
	int cState;

	public AddCustomDialog(JFrame parentFrame, GymManager g){
		super(parentFrame, "Add new entry", true);
		gymManager = g;
		setLocation(parentFrame.getLocation().x+80, parentFrame.getLocation().y+80);
		//setSize(parentFrame.getSize().width/2, parentFrame.getSize().height/2);

		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		//drop-down list
		String[] selectionStrings = { "Customer", "Staff", "Equipment", "MembershipPlan"};
		JComboBox selectionList = new JComboBox(selectionStrings);
		selectionList.setSelectedIndex(0);
		selectionList.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				changePanel(selectionList.getSelectedIndex());
			}
		});
		mainPanel.add(selectionList, BorderLayout.NORTH);

		changePanel(0);

		getContentPane().add(mainPanel);
		pack();
		setVisible(true);
	}

	public void changePanel(int panelIndex){
		if(currentState!=null)
			mainPanel.remove(currentState);
		switch(panelIndex){
			case 0:
				currentState = new CustomerAddState(this);
				cState = 0;
				break;
			case 1:
				currentState = new StaffAddState(this);
				cState = 1;
				break;
			case 2:
				currentState = new EquipmentAddState(this);
				cState = 2;
				break;
			case 3:
				currentState = new MembershipAddState(this);
				cState = 3;
				break;
		};
		mainPanel.add(currentState, BorderLayout.CENTER);
		mainPanel.revalidate();
		mainPanel.repaint();
	}

	public void dispose(){
		if(cState==0)
			gymManager.applyCustomerModel();
		else if(cState==1)
			gymManager.applyStaffModel();
		else if(cState==2)
			gymManager.applyEquipmentModel();
		else if(cState==3)
			gymManager.applyMembershipModel();
		super.dispose();
	}
}