import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.sql.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class GymManager{

	JFrame mainFrame;
	MyFilterTable table;
	JButton buttonModify, buttonDelete;
	int displayMode; //0-Customer, 1-Staff, 2-Equipment, 3-Membership
	JTextField checkInTextField, checkOutTextField;
	Calendar c;

	private final SimpleDateFormat sd  = new SimpleDateFormat("hh:mm:ss");
	private final SimpleDateFormat sdDate = new SimpleDateFormat("yyyy-MM-dd");

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
			topPart.add(appTitle, BorderLayout.WEST);

			JLabel timerLabel = new JLabel("");
			timerLabel.setFont(timerLabel.getFont().deriveFont(20.0f));
			timerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			timerLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
			topPart.add(timerLabel, BorderLayout.CENTER);
			
			c = Calendar.getInstance();
			ActionListener updateClock = new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
			  		String s = "";
			        s+=(new java.util.Date(System.currentTimeMillis()).toString());
			        s+=" "; 
			    	//s+=String.format("%s", sd.format(c.getTime())); 
					timerLabel.setText(s);
			    }
			};
			javax.swing.Timer t = new javax.swing.Timer(1000, updateClock);
			t.start();

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

		JPanel outPanel = new JPanel();
		outPanel.setLayout(new BoxLayout(outPanel, BoxLayout.PAGE_AXIS));

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
				// table = new JTable();
				// table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				// applyCustomerModel();
				// JScrollPane scrollPane = new JScrollPane(table);
				// table.setFillsViewportHeight(true);
				// centerPanel.add(scrollPane, BorderLayout.CENTER);
				table = new MyFilterTable();
				centerPanel.add(table, BorderLayout.CENTER);
				applyCustomerModel();
			}

			//Second row of buttons
			{
				GridLayout buttonsInRowLayout = new GridLayout(1, 10);
				JPanel buttonList = new JPanel();
				buttonList.setLayout(buttonsInRowLayout);

				buttonModify = new JButton("Modify");
				buttonModify.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						int[] sel = table.getTable().getSelectedRows();
						if(sel.length>0){
							modifyEntry(sel[0]);
						}
					}
				});
				buttonList.add(buttonModify);

				buttonDelete = new JButton("Delete");
				buttonDelete.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						int[] sel = table.getTable().getSelectedRows();
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
				centerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));				
			}

			outPanel.add(centerPanel);

			//check-in panel
			JPanel splitPanel = new JPanel();
			GridLayout splitLayout = new GridLayout(1, 2);
			splitPanel.setLayout(splitLayout);

			{
				JPanel checkInPanel = new JPanel();
				checkInPanel.setLayout(new BoxLayout(checkInPanel, BoxLayout.PAGE_AXIS));
				checkInPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
				
				JLabel label = new JLabel("Check-in");
				label.setFont(label.getFont().deriveFont(15.0f));
				//label.setBorder(new EmptyBorder(20, 20, 20, 20));
				//label.setAlignmentX(Component.CENTER_ALIGNMENT);
				checkInPanel.add(label);

				checkInTextField = new JTextField(10);
				checkInPanel.add(checkInTextField);

				JButton bCustomer = new JButton("Check-in customer");
				bCustomer.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						checkinCustomer();
					}
				});
				checkInPanel.add(bCustomer);

				JButton bStaff = new JButton("Check-in staff");
				bStaff.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						checkinStaff();
					}
				});
				checkInPanel.add(bStaff);

				splitPanel.add(checkInPanel);
			}

			//check-out panel
			{
				JPanel checkOutPanel = new JPanel();

				checkOutPanel.setLayout(new BoxLayout(checkOutPanel, BoxLayout.PAGE_AXIS));
				checkOutPanel.setBorder(new CompoundBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.black), new EmptyBorder(20, 20, 20, 20)));
				
				JLabel label = new JLabel("Check-out");
				label.setFont(label.getFont().deriveFont(15.0f));
				//label.setBorder(new EmptyBorder(20, 20, 20, 20));
				//label.setAlignmentX(Component.CENTER_ALIGNMENT);
				checkOutPanel.add(label);

				checkOutTextField = new JTextField(10);
				checkOutPanel.add(checkOutTextField);

				JButton bCustomer = new JButton("Check-out customer");
				bCustomer.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						checkoutCustomer();
					}
				});
				checkOutPanel.add(bCustomer);

				JButton bStaff = new JButton("Check-out staff");
				bStaff.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						checkoutStaff();
					}
				});
				checkOutPanel.add(bStaff);

				splitPanel.add(checkOutPanel);
			}

			outPanel.add(splitPanel);
			parentPanel.add(outPanel, BorderLayout.CENTER);
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

	public void deleteEntry(int ind1){
		int ind = table.getTable().convertRowIndexToModel(ind1);
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
				stmt.executeUpdate("delete from MembershipPlan where m_code="+id);
				applyMembershipModel();
			}
			System.out.println(id);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void modifyEntry(int ind1){
		int ind = table.getTable().convertRowIndexToModel(ind1);
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

	public void checkinCustomer(){
		try
		{
			Class.forName ("com.mysql.jdbc.Driver");
			//Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@db.yale.edu:1521:univdb",userid, passwd);
			String url = "jdbc:" + "mysql" + "://" + "localhost" + ":" + "3306" + "/" + Constants.dbName + "?autoReconnect=true&useSSL=false";
			String userid = Constants.userid;
			String passwd = Constants.password;

			Connection conn = DriverManager.getConnection(url, userid, passwd);
			
			Statement stmt = conn.createStatement();

			String day="", date="", time="", in_out="", inp="";
			java.util.Date d = new java.util.Date(System.currentTimeMillis());
			if(d.getDay()==0){
				day = "Sun";
			}else if(d.getDay()==1){
				day = "Mon";
			}else if(d.getDay()==2){
				day = "Tue";
			}else if(d.getDay()==3){
				day = "Wed";
			}else if(d.getDay()==4){
				day = "Thu";
			}else if(d.getDay()==5){
				day = "Fri";
			}else if(d.getDay()==6){
				day = "Sat";
			}

			date=sdDate.format(d);
			time=String.format("%s", sd.format(c.getTime()));
			in_out="TRUE";
			inp=checkInTextField.getText();

			String query = "insert into CustomerLog values(\'" + day +"\',\'" + date + "\', \'" + time + "\', " + in_out + ", " + inp + ")";
			System.out.println();
			System.out.println(query);
			System.out.println();
			stmt.executeUpdate(query);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void checkinStaff(){
		try
		{
			Class.forName ("com.mysql.jdbc.Driver");
			//Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@db.yale.edu:1521:univdb",userid, passwd);
			String url = "jdbc:" + "mysql" + "://" + "localhost" + ":" + "3306" + "/" + Constants.dbName + "?autoReconnect=true&useSSL=false";
			String userid = Constants.userid;
			String passwd = Constants.password;

			Connection conn = DriverManager.getConnection(url, userid, passwd);
			
			Statement stmt = conn.createStatement();

			String day="", date="", time="", in_out="", inp="";
			java.util.Date d = new java.util.Date(System.currentTimeMillis());
			if(d.getDay()==0){
				day = "Sun";
			}else if(d.getDay()==1){
				day = "Mon";
			}else if(d.getDay()==2){
				day = "Tue";
			}else if(d.getDay()==3){
				day = "Wed";
			}else if(d.getDay()==4){
				day = "Thu";
			}else if(d.getDay()==5){
				day = "Fri";
			}else if(d.getDay()==6){
				day = "Sat";
			}

			date=sdDate.format(d);
			time=String.format("%s", sd.format(c.getTime()));
			in_out="TRUE";
			inp=checkInTextField.getText();

			String query = "insert into StaffLog values(\'" + day +"\',\'" + date + "\', \'" + time + "\', " + in_out + ", " + inp + ")";
			System.out.println();
			System.out.println(query);
			System.out.println();
			stmt.executeUpdate(query);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void checkoutCustomer(){
		try
		{
			Class.forName ("com.mysql.jdbc.Driver");
			//Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@db.yale.edu:1521:univdb",userid, passwd);
			String url = "jdbc:" + "mysql" + "://" + "localhost" + ":" + "3306" + "/" + Constants.dbName + "?autoReconnect=true&useSSL=false";
			String userid = Constants.userid;
			String passwd = Constants.password;

			Connection conn = DriverManager.getConnection(url, userid, passwd);
			
			Statement stmt = conn.createStatement();

			String day="", date="", time="", in_out="", inp="";
			java.util.Date d = new java.util.Date(System.currentTimeMillis());
			if(d.getDay()==0){
				day = "Sun";
			}else if(d.getDay()==1){
				day = "Mon";
			}else if(d.getDay()==2){
				day = "Tue";
			}else if(d.getDay()==3){
				day = "Wed";
			}else if(d.getDay()==4){
				day = "Thu";
			}else if(d.getDay()==5){
				day = "Fri";
			}else if(d.getDay()==6){
				day = "Sat";
			}

			date=sdDate.format(d);
			time=String.format("%s", sd.format(c.getTime()));
			in_out="FALSE";
			inp=checkOutTextField.getText();

			String query = "insert into CustomerLog values(\'" + day +"\',\'" + date + "\', \'" + time + "\', " + in_out + ", " + inp + ")";
			System.out.println();
			System.out.println(query);
			System.out.println();
			stmt.executeUpdate(query);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void checkoutStaff(){
		try
		{
			Class.forName ("com.mysql.jdbc.Driver");
			//Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@db.yale.edu:1521:univdb",userid, passwd);
			String url = "jdbc:" + "mysql" + "://" + "localhost" + ":" + "3306" + "/" + Constants.dbName + "?autoReconnect=true&useSSL=false";
			String userid = Constants.userid;
			String passwd = Constants.password;

			Connection conn = DriverManager.getConnection(url, userid, passwd);
			
			Statement stmt = conn.createStatement();

			String day="", date="", time="", in_out="", inp="";
			java.util.Date d = new java.util.Date(System.currentTimeMillis());
			if(d.getDay()==0){
				day = "Sun";
			}else if(d.getDay()==1){
				day = "Mon";
			}else if(d.getDay()==2){
				day = "Tue";
			}else if(d.getDay()==3){
				day = "Wed";
			}else if(d.getDay()==4){
				day = "Thu";
			}else if(d.getDay()==5){
				day = "Fri";
			}else if(d.getDay()==6){
				day = "Sat";
			}

			date=sdDate.format(d);
			time=String.format("%s", sd.format(c.getTime()));
			in_out="FALSE";
			inp=checkOutTextField.getText();

			String query = "insert into StaffLog values(\'" + day +"\',\'" + date + "\', \'" + time + "\', " + in_out + ", " + inp + ")";
			System.out.println();
			System.out.println(query);
			System.out.println();
			stmt.executeUpdate(query);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}