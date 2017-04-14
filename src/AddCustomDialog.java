import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class AddCustomDialog extends JDialog implements ActionListener{

	JPanel mainPanel;
	JPanel selectionPanels[];
	JTextField textFields[][];
	JPanel currentSelectionPanel;

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

		preparePanels();
		changePanel(0);

		getContentPane().add(mainPanel);
		pack();
		setVisible(true);
	}



	public void preparePanels(){
		selectionPanels = new JPanel[4];
		textFields = new JTextField[4][9];
		
		{
			//Customer addPanel
			//Customer(c_id, first_name, last_name, street, city, state, zip, date_birth, m_code, m_start_date)
			selectionPanels[0] = new JPanel();
			selectionPanels[0].setLayout(new BoxLayout(selectionPanels[0], BoxLayout.PAGE_AXIS));
			selectionPanels[0].add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("c_id: "));
					this.add(textFields[0][0] = new JTextField(10));
					return this;
				}
			}.make());
			selectionPanels[0].add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("first name: "));
					this.add(textFields[0][1] = new JTextField(10));
					return this;
				}
			}.make());
			selectionPanels[0].add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("last name: "));
					this.add(textFields[0][2] = new JTextField(10));
					return this;
				}
			}.make());
			selectionPanels[0].add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("street: "));
					this.add(textFields[0][3] = new JTextField(10));
					return this;
				}
			}.make());
			selectionPanels[0].add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("city: "));
					this.add(textFields[0][4] = new JTextField(10));
					return this;
				}
			}.make());
			selectionPanels[0].add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("state: "));
					this.add(textFields[0][5] = new JTextField(10));
					return this;
				}
			}.make());
			selectionPanels[0].add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("zip: "));
					this.add(textFields[0][6] = new JTextField(10));
					return this;
				}
			}.make());
			selectionPanels[0].add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("date_birth: "));
					this.add(textFields[0][7] = new JTextField(10));
					return this;
				}
			}.make());
			selectionPanels[0].add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("m_code: "));
					this.add(textFields[0][8] = new JTextField(10));
					return this;
				}
			}.make());
			JButton buttonAddCustomer = new JButton("Add");
			buttonAddCustomer.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					addCustomerClicked();
				}
			});
			selectionPanels[0].add(buttonAddCustomer);
		}

		{
			//Staff addPanel
			//Staff(s_id, first_name, last_name, street, city, state, zip, designation, date_birth)
			selectionPanels[1] = new JPanel();
			selectionPanels[1].setLayout(new BoxLayout(selectionPanels[1], BoxLayout.PAGE_AXIS));
			selectionPanels[1].add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("s_id: "));
					this.add(textFields[1][0] = new JTextField(10));
					return this;
				}
			}.make());
			selectionPanels[1].add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("first name: "));
					this.add(textFields[1][1] = new JTextField(10));
					return this;
				}
			}.make());
			selectionPanels[1].add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("last name: "));
					this.add(textFields[1][2] = new JTextField(10));
					return this;
				}
			}.make());
			selectionPanels[1].add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("street: "));
					this.add(textFields[1][3] = new JTextField(10));
					return this;
				}
			}.make());
			selectionPanels[1].add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("city: "));
					this.add(textFields[1][4] = new JTextField(10));
					return this;
				}
			}.make());
			selectionPanels[1].add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("state: "));
					this.add(textFields[1][5] = new JTextField(10));
					return this;
				}
			}.make());
			selectionPanels[1].add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("zip: "));
					this.add(textFields[1][6] = new JTextField(10));
					return this;
				}
			}.make());
			selectionPanels[1].add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("designt: "));
					this.add(textFields[1][7] = new JTextField(10));
					return this;
				}
			}.make());
			selectionPanels[1].add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("date_birth: "));
					this.add(textFields[1][8] = new JTextField(10));
					return this;
				}
			}.make());
			JButton buttonAddStaff = new JButton("Add");
			buttonAddStaff.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					addStaffClicked();
				}
			});
			selectionPanels[1].add(buttonAddStaff);
		}

		{
			//Equipment addPanel
			//Equipment(e_id, name, type, is_working)
			selectionPanels[2] = new JPanel();
			selectionPanels[2].setLayout(new BoxLayout(selectionPanels[2], BoxLayout.PAGE_AXIS));
			selectionPanels[2].add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("e_id: "));
					this.add(textFields[2][0] = new JTextField(10));
					return this;
				}
			}.make());
			selectionPanels[2].add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("name: "));
					this.add(textFields[2][1] = new JTextField(10));
					return this;
				}
			}.make());
			selectionPanels[2].add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("type: "));
					this.add(textFields[2][2] = new JTextField(10));
					return this;
				}
			}.make());
			selectionPanels[2].add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("is_working: "));
					this.add(textFields[2][3] = new JTextField(10));
					return this;
				}
			}.make());
			JButton buttonAddEquipment = new JButton("Add");
			buttonAddEquipment.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					addEquipmentClicked();
				}
			});
			selectionPanels[2].add(buttonAddEquipment);
		}

		//Membership addPanel
		selectionPanels[3] = new JPanel();
		selectionPanels[3].add(new JLabel("new membership"));
	}

	public void addCustomerClicked(){
		
	}

	public void addStaffClicked(){

	}

	public void addEquipmentClicked(){
		
	}

	public void changePanel(int panelIndex){
		if(currentSelectionPanel!=null)
			mainPanel.remove(currentSelectionPanel);
		currentSelectionPanel = selectionPanels[panelIndex];
		mainPanel.add(currentSelectionPanel, BorderLayout.CENTER);
		mainPanel.revalidate();
		mainPanel.repaint();
	}

	public void actionPerformed(ActionEvent e){
		//dispose();
	}

}