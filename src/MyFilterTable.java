import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class MyFilterTable extends JPanel{

	final int numEntriesPerPage = 10;
	int lastValidPageNum;

	int pageNum = 0;
	JTable table;
	JButton buttonLeft, buttonRight;
	TableRowSorter<TableModel> sorter;

	public MyFilterTable(Object[][] rowData, Object[] colNames){
		this.setLayout(new BorderLayout());

		table = new JTable(rowData, colNames);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		this.add(scrollPane, BorderLayout.CENTER);

		buttonLeft = new JButton("<-");
		buttonLeft.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				leftClicked();
			}
		});
		buttonRight = new JButton("->");
		buttonRight.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				rightClicked();
			}
		});

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.add(buttonLeft);
		buttonPanel.add(buttonRight);
		buttonPanel.setBorder(new EmptyBorder(0, 0, 10, 0));

		this.add(buttonPanel, BorderLayout.SOUTH);
	}

	public MyFilterTable(){
		this.setLayout(new BorderLayout());

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		this.add(scrollPane, BorderLayout.CENTER);

		buttonLeft = new JButton("<-");
		buttonLeft.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				leftClicked();
			}
		});
		buttonRight = new JButton("->");
		buttonRight.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				rightClicked();
			}
		});

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.add(buttonLeft);
		buttonPanel.add(buttonRight);
		buttonPanel.setBorder(new EmptyBorder(0, 0, 10, 0));

		this.add(buttonPanel, BorderLayout.SOUTH);
	}

	private void switchPage(int newPageNum){
		pageNum = newPageNum;

		RowFilter<TableModel,Integer> showRangeFilter = new RowFilter<TableModel,Integer>() {
		   public boolean include(Entry entry) {
		     int index = (Integer)entry.getIdentifier();
		     if(index>=pageNum*numEntriesPerPage && index<(pageNum+1)*numEntriesPerPage)
		     	return true;
		     else
		     	return false;
		   }
		};

		sorter.setRowFilter(showRangeFilter);
	}

	public void leftClicked(){
		if(pageNum>0)
			switchPage(pageNum-1);
	}

	public void rightClicked(){
		if(pageNum<lastValidPageNum)
			switchPage(pageNum+1);
	}

	public void setModel(TableModel model){
		table.setModel(model);
		sorter = new TableRowSorter<TableModel>(model);
		table.setRowSorter(sorter);
		switchPage(0);
		lastValidPageNum = (model.getRowCount()%numEntriesPerPage==0)?(model.getRowCount()/numEntriesPerPage-1):(model.getRowCount()/numEntriesPerPage);
	}

	public TableModel getModel(){
		return table.getModel();
	}

	public JTable getTable(){
		return table;
	}
}