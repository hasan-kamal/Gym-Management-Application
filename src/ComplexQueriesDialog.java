import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.sql.*;

public class ComplexQueriesDialog extends JDialog{

	ComplexQueryPanel currentPanel;
	JPanel panel;

	public ComplexQueriesDialog(JFrame parentFrame){
		super(parentFrame, "Complex queries", true);
		setLocation(parentFrame.getLocation().x+80, parentFrame.getLocation().y+80);
		//setSize(parentFrame.getSize().width/2, parentFrame.getSize().height/2);

		panel = new JPanel();
		panel.setLayout(new BorderLayout());

		//drop-down list
		String[] selectionStrings = { "Query 1", "Query 2", "Query 3", "Query 4", "Query 5"};
		JComboBox selectionList = new JComboBox(selectionStrings);
		selectionList.setSelectedIndex(0);
		changePanel(0);
		selectionList.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				changePanel(selectionList.getSelectedIndex());
			}
		});
		panel.add(selectionList, BorderLayout.NORTH);

		//your code goes here!

		getContentPane().add(panel);
		pack();
		setVisible(true);
	}

	public void changePanel(int index){
		if(currentPanel!=null)
			panel.remove(currentPanel);
		switch(index){
			case 0:
				currentPanel = new Query1();
				break;
			case 1:
				currentPanel = new Query2();
				break;
			case 2:
				currentPanel = new Query3();
				break;
			case 3:
				currentPanel = new Query4();
				break;
			case 4:
				currentPanel = new Query5();
				break;
		}
		panel.add(currentPanel, BorderLayout.CENTER);
		panel.revalidate();
		panel.repaint();
	}

}