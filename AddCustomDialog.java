import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class AddCustomDialog extends JDialog implements ActionListener{

	public AddCustomDialog(JFrame parentFrame){
		super(parentFrame, "Add new entry", true);
		setLocation(parentFrame.getLocation().x+80, parentFrame.getLocation().y+80);
		//setSize(parentFrame.getSize().width/2, parentFrame.getSize().height/2);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		//drop-down list
		String[] selectionStrings = { "Customer", "Staff", "Equipment", "MembershipPlan"};
		JComboBox selectionList = new JComboBox(selectionStrings);
		selectionList.setSelectedIndex(0);
		selectionList.addActionListener(this);
		panel.add(selectionList, BorderLayout.NORTH);

		

		getContentPane().add(panel);
		pack();
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e){
		dispose();
	}

}