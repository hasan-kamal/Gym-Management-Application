import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class AddCustomDialog extends JDialog implements ActionListener{

	JPanel mainPanel;
	AddState currentState;

	public AddCustomDialog(JFrame parentFrame){
		super(parentFrame, "Add new entry", true);
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
				currentState = new CustomerAddState();
				break;
			case 1:
				currentState = new StaffAddState();
				break;
			case 2:
				currentState = new EquipmentAddState();
				break;
			case 3:
				currentState = new MembershipAddState();
				break;
		};
		mainPanel.add(currentState, BorderLayout.CENTER);
		mainPanel.revalidate();
		mainPanel.repaint();
	}

	public void actionPerformed(ActionEvent e){
		//dispose();
	}

}