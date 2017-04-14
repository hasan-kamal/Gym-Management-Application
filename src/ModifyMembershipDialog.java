import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class ModifyMembershipDialog extends JDialog{

	JPanel mainPanel;
	GymManager gymManager;
	JTextField textFields[];

	String oldData[];
	String m_code;

	Connection conn;
	Statement stmt;
	
	public ModifyMembershipDialog(JFrame parentFrame, GymManager g, String id){
		super(parentFrame, "Modify Membership", true);
		gymManager = g;
		m_code = id;
		setLocation(parentFrame.getLocation().x+80, parentFrame.getLocation().y+80);
		//setSize(parentFrame.getSize().width/2, parentFrame.getSize().height/2);

		loadOldData();

		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

		{
			textFields = new JTextField[4];
			mainPanel.add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("m_code: "));
					this.add(textFields[0] = new JTextField(oldData[0], 10));
					textFields[0].setEditable(false);
					return this;
				}
			}.make());
			mainPanel.add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("name: "));
					this.add(textFields[1] = new JTextField(oldData[1], 10));
					return this;
				}
			}.make());
			mainPanel.add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("price: "));
					this.add(textFields[2] = new JTextField(oldData[2], 10));
					return this;
				}
			}.make());
			mainPanel.add(new JPanel(){
				public JPanel make(){
					this.setLayout(new FlowLayout(FlowLayout.TRAILING));
					this.add(new JLabel("duration: "));
					this.add(textFields[3] = new JTextField(oldData[3], 10));
					return this;
				}
			}.make());
			JButton buttonCommit = new JButton("Commit");
			buttonCommit.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					commitClicked();
				}
			});
			mainPanel.add(buttonCommit);

			JButton buttonCancel = new JButton("Cancel");
			buttonCancel.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					cancelClicked();
				}
			});
			mainPanel.add(buttonCancel);
		}
		
		getContentPane().add(mainPanel);
		pack();
		setVisible(true);
	}

	public void loadOldData(){
		oldData = new String[9];
		try{
			
			//Membership addPanel
			//MembershipPlan(m_code, name, price, duration)	
			Class.forName("com.mysql.jdbc.Driver");
			//Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@db.yale.edu:1521:univdb",userid, passwd);
			String url = "jdbc:" + "mysql" + "://" + "localhost" + ":" + "3306" + "/" + Constants.dbName + "?autoReconnect=true&useSSL=false";
			String userid = Constants.userid;
			String passwd = Constants.password;

			conn = DriverManager.getConnection(url, userid, passwd);
			stmt = conn.createStatement();

			ResultSet rset = stmt.executeQuery( "select * from MembershipPlan where m_code="+m_code); 
			
			oldData = new String[4];
			int i=0;
			while (rset.next()) {
				for(int j=0; j<4; j++)
					oldData[j] = rset.getString(j+1);
				i++;
			}
			stmt.close();
			conn.close();

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void commitClicked(){
		try{

			Class.forName("com.mysql.jdbc.Driver");
			//Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@db.yale.edu:1521:univdb",userid, passwd);
			String url = "jdbc:" + "mysql" + "://" + "localhost" + ":" + "3306" + "/" + Constants.dbName + "?autoReconnect=true&useSSL=false";
			String userid = Constants.userid;
			String passwd = Constants.password;

			conn = DriverManager.getConnection(url, userid, passwd);
			stmt = conn.createStatement();

			//Membership addPanel
			//MembershipPlan(m_code, name, price, duration)
			//String str = (""+"\'"+textFields[0].getText()+"\'" + ", ");
			String str = ("name="+"\'"+textFields[1].getText()+"\'" + ", ");
			str += ("price="+"\'"+textFields[2].getText()+"\'" + ", ");
			str += ("duration="+"\'"+textFields[3].getText()+"\'");
			
			try{
				stmt.executeUpdate("update MembershipPlan set "+ str + " where m_code="+m_code); 
				stmt.close();
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}

		}catch(Exception ex){
			ex.printStackTrace();
		}
		this.dispose();
	}

	public void cancelClicked(){
		this.dispose();
	}

	public void dispose(){
		gymManager.applyMembershipModel();
		super.dispose();
	}
}