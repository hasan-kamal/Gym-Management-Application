import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.sql.*;

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

			JPanel tempPanel = new JPanel();
			tempPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
			
			GridLayout twoButtonsLayout = new GridLayout(2, 1);
			tempPanel.setLayout(twoButtonsLayout);

			JButton addButton = new JButton("+");
			addButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					addButtonClicked();
				}
			});
			tempPanel.add(addButton);

			JButton complexButton = new JButton("*");
			complexButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					complexButtonClicked();
				}
			});
			tempPanel.add(complexButton);

			topPart.add(tempPanel, BorderLayout.EAST);

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
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
						int[] sel = table.getSelectedRows();
						if(sel.length>0){
							modifyEntry(sel[0]);
						}
					}
				});
				buttonList.add(buttonModify);

				buttonDelete = new JButton("Delete");
				buttonDelete.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						int[] sel = table.getSelectedRows();
						if(sel.length>0){
							deleteEntry(sel[0]);
						}
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

	public void addButtonClicked(){
		//System.out.println("add clicked");
		AddCustomDialog addDialog = new AddCustomDialog(mainFrame, this);
	}

	public void complexButtonClicked(){
		//System.out.println("complex clicked");
		ComplexQueriesDialog queriesDialog = new ComplexQueriesDialog(mainFrame);
	}

	public void deleteEntry(int ind){
		try
		{
			Class.forName ("com.mysql.jdbc.Driver");
			//Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@db.yale.edu:1521:univdb",userid, passwd);
			String url = "jdbc:" + "mysql" + "://" + "localhost" + ":" + "3306" + "/" + Constants.dbName + "?autoReconnect=true&useSSL=false";
			String userid = Constants.userid;
			String passwd = Constants.password;

			Connection conn = DriverManager.getConnection(url, userid, passwd);
				
			Statement stmt = conn.createStatement();

			TableModel x = table.getModel();
			String id = "-";
			if(displayMode==0){
				id = ((CustomerModel)x).data[ind][0];
				stmt.executeUpdate("delete from Customer where c_id="+id);
				applyCustomerModel();
			}else if(displayMode==1){
				id = ((StaffModel)x).data[ind][0];
				stmt.executeUpdate("delete from Staff where s_id="+id);
				applyStaffModel();
			}else if(displayMode==2){
				id = ((EquipmentModel)x).data[ind][0];
				stmt.executeUpdate("delete from Equipment where e_id="+id);
				applyEquipmentModel();
			}else if(displayMode==3){
				id = ((MembershipModel)x).data[ind][0];
				stmt.executeUpdate("delete from MembershipPlan where m_id="+id);
				applyMembershipModel();
			}
			System.out.println(id);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void modifyEntry(int ind){
		try
		{
			Class.forName ("com.mysql.jdbc.Driver");
			//Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@db.yale.edu:1521:univdb",userid, passwd);
			String url = "jdbc:" + "mysql" + "://" + "localhost" + ":" + "3306" + "/" + Constants.dbName + "?autoReconnect=true&useSSL=false";
			String userid = Constants.userid;
			String passwd = Constants.password;

			Connection conn = DriverManager.getConnection(url, userid, passwd);
				
			Statement stmt = conn.createStatement();

			TableModel x = table.getModel();
			String id = "-";
			if(displayMode==0){
				id = ((CustomerModel)x).data[ind][0];
				new ModifyCustomerDialog(mainFrame, this, ""+id);
			}else if(displayMode==1){
				id = ((StaffModel)x).data[ind][0];
				new ModifyStaffDialog(mainFrame, this, ""+id);				
			}else if(displayMode==2){
				id = ((EquipmentModel)x).data[ind][0];
				new ModifyEquipmentDialog(mainFrame, this, ""+id);
			}else if(displayMode==3){
				id = ((MembershipModel)x).data[ind][0];
				new ModifyMembershipDialog(mainFrame, this, ""+id);
			}
			System.out.println(id);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public static void main(String[] args){
		GymManager manager = new GymManager();
	}

}