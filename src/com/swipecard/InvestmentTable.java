package com.swipecard;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;


public class InvestmentTable {
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new InvestmentTableFrame();
				frame.setTitle("InvestmentTable");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

class InvestmentTableFrame extends JFrame{
	public InvestmentTableFrame(){
		TableModel model = new InvestmentTableModel(10,5,10);
		JTable table = new JTable(model);
		System.out.println(table.getColumnName(0));
		add(table);
		pack();
	}
}

class InvestmentTableModel extends AbstractTableModel{
	private static double INITTIAL_BALANCE = 100000.0;

	private int years;
	private int minRate;
	private int maxRate;
	
	public InvestmentTableModel(int y, int r1, int r2){
		years = y;
		minRate = r1;
		maxRate = r2;
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return years;
		
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return maxRate - minRate +1;
	}

	@Override
	public Object getValueAt(int r, int c) {
		// TODO Auto-generated method stub
		double rate =(c+minRate)/100.0;
		int nperiods = r;
		double futureBalance = INITTIAL_BALANCE * Math.pow(1+rate,nperiods);
		return String.format("%.2f", futureBalance);
	}
	
	
	public String getColumnName(int c){
		return (c+minRate) + "%";
	}
}