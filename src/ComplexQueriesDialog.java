import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ComplexQueriesDialog extends JDialog implements ActionListener{

	public ComplexQueriesDialog(JFrame parentFrame){
		super(parentFrame, "Complex queries", true);
		setLocation(parentFrame.getLocation().x+80, parentFrame.getLocation().y+80);
		//setSize(parentFrame.getSize().width/2, parentFrame.getSize().height/2);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		//drop-down list
		String[] selectionStrings = { "Query 1", "Query 2", "Query 3", "Query 4"};
		JComboBox selectionList = new JComboBox(selectionStrings);
		selectionList.setSelectedIndex(0);
		selectionList.addActionListener(this);
		panel.add(selectionList, BorderLayout.NORTH);

		//your code goes here!

		getContentPane().add(panel);
		pack();
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e){
		dispose();
	}

}