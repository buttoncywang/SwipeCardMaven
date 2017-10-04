package com.swipecard;

import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.swipecard.model.User;

public class MyNewTableModel extends AbstractTableModel {

	/** * @author paul */
	private Vector<Object> TableData;// 用来存放表格数据的线性表
	// private Vector TableTitle;// 表格的 列标题
	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;
	static {
		try {
			reader = Resources.getResourceAsReader("Configuration.xml");
			/*
			 * String filePath = System.getProperty("user.dir") +
			 * "/Configuration.xml"; FileReader reader=new FileReader(filePath);
			 */
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSession() {
		return sqlSessionFactory;
	}

	// 注意构造函数是第一个执行的，用于初始化 TableData，TableTitle
	private String[] columnNames = { "Check", "序號","工號", "姓名", "上刷時間", "下刷時間", "指示單號" };

	public MyNewTableModel() {
		// 先new 一下
		TableData = new Vector<Object>();
		
		// 第0行,都显示表格的坐标
		Object[] Line1 = { "(0,0)", "(0,1)", new Boolean(false) };
		// 第1行
		Object[] Line2 = { "(1,0)", "(1,1)", new Boolean(false) };
		// 第2行
		Object[] Line3 = { "(2,0)", "(2,1)", new Boolean(false) };
		// 将数据挂到线性表形成二维的数据表，形成映射
		String LineNo = "3L-37";
		SqlSession session = sqlSessionFactory.openSession();

		List<User> eif = session.selectList("selectUserByLineNoAndWorkshopNo", LineNo);

		int i = 0;
		System.out.println(eif.size());
		Boolean State = false;
		String sTime1 = "";
		String sTime2 = "";
		String rcno = "";
		String Name = "";
		String empId = "";
	
		int j = 1;
		for (i = 0; i < eif.size(); i++) {
			empId=eif.get(i).getId();
			Name = eif.get(i).getName();
			rcno = eif.get(i).getRC_NO();
			sTime1 = eif.get(i).getSwipeCardTime();
			sTime2 = eif.get(i).getSwipeCardTime2();

			Object[] temp1 = { State, j,empId, Name, sTime1, sTime2, rcno };
			j++;
			TableData.add(temp1);
		}
	
	}

	// public MyNewTableModel(String lineno,String Shift){
	public MyNewTableModel(String WorkshopNo,String Shift){		
		// 先new 一下
		TableData = new Vector<Object>();
		SqlSession session = sqlSessionFactory.openSession();
		String time = DateGet.getTime();//確保是準確的時間
//		String time = "2017-06-14 07:00:00";
		User user1 = new User();
		user1.setCurDateTime(time);
	//	user1.setPROD_LINE_CODE(lineno);
		user1.setWorkshopNo(WorkshopNo);
		List<User> eif = null;
		try{
		if(Shift=="D"){
			 eif = session.selectList(
					"selectUserByLineNoAndWorkshopNo_DShift", WorkshopNo);
		}else if(Shift=="N"){
			 eif = session.selectList(
					"selectUserByLineNoAndWorkshopNo_NShift", user1);
		}

		int i=0;
		
		Boolean State = false;
		String sTime1 ="";
		String sTime2 ="";
		String rcno = "";
		String Name = "";
		String empId = "";
		int j = 1;
		for(i=0;i<eif.size();i++){
//			System.out.println("lineno: "+eif.get(i).getName());
			empId=eif.get(i).getId();
			Name = eif.get(i).getName();
			rcno = eif.get(i).getRC_NO();
			sTime1 = eif.get(i).getSwipeCardTime();
			sTime2 = eif.get(i).getSwipeCardTime2();
			if(sTime2==null){
				sTime2="";
			}
			Object[] temp1 = {State,j,empId,Name,sTime1,sTime2,rcno};
			j++;
			TableData.add(temp1);
		}
	}
		finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public int getRowCount() {
		// 这里是告知表格应该有多少行，我们返回TableData上挂的String数组个数
		return TableData.size();
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public int getColumnCount() {
		// 告知列数，用标题数组的大小,这样表格就是TableData.size()行，TableTitle.size()列了
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// 获取了表格的大小，当然还要获取数据，根据坐标直接返回对应的数据
		// 小心 都是从 0开始的，小心下标越界 的问题
		// 我们之前是将 String[]挂到了线性表上，所以要先获取到String[]
		//
		// 获取每一行对应的String[]数组
		// return TableData[rowIndex][columnIndex];
		Object LineTemp[] = (Object[]) this.TableData.get(rowIndex);
		// 提取出对 应的数据
		return LineTemp[columnIndex];

		// 如果我们是将 线性表Vector挂到了Vector上要注意每次我们获取的是 一行线性表
		// 例如
		// return ((Vector)TableData.get(rowIndex)).get(columnIndex);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// 这个函数式设置每个单元格的编辑属性的
		// 这个函数AbstractTableModel已经实现，默认的是 不允许编辑状态
		return true;// super.isCellEditable(rowIndex, columnIndex);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// 当单元格的数据发生改变的时候掉用该函数重设单元格的数据
		// 我们想一下，数据是放在TableData 中的，说白了修改数据就是修改的
		// TableData中的数据，所以我们仅仅在此处将TableData的对应数据修改即可

		((Object[]) this.TableData.get(rowIndex))[columnIndex] = (Object) aValue;
		super.setValueAt(aValue, rowIndex, columnIndex);
		//
		// 其实这里super的方法是调用了fireTableCellUpdated()只对应更新了
		// 对应单元格的数据
		// fireTableCellUpdated(rowIndex, columnIndex);
	}

	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	public static void main(String[] args) {
		JFrame frm = new JFrame();
		MyNewTableModel myModel = new MyNewTableModel("3L-37", "D");

		JTable mytable = new JTable(myModel);
		mytable.setRowHeight(50);
		JScrollPane myScrollPane = new JScrollPane(mytable);
		myScrollPane.setBounds(40, 40, 500, 400);
		frm.add(myScrollPane);

		frm.setLayout(null);
		frm.setBounds(200, 100, 800, 600);

		frm.setDefaultCloseOperation(3);
		frm.setVisible(true);
	}

}
