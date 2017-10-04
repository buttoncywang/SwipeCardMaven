package com.swipecard;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.Reader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ComboBoxEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.swipecard.model.User;

public class JLabelA extends JFrame {

	private static final long serialVersionUID = -4892684184268025880L;
	private static final Timer time = new Timer("test");
	private Vector<Vector<Object>> rowData = new Vector<Vector<Object>>();
	private JTable table;
	private int count = 0;

	static JTabbedPane tabbedPane;
	static JLabel label1, label3;
	static JLabel labelS1, labelS2, labelS3;
	static JLabel Shift;
	static JPanel panel1, panel2, panel3;
	static ImageIcon image;
	static JLabel labelT2_1, labelT2_2, labelT1_2, labelT2_3, labelT1_3, labelT1_4, labelT1_5, labelT1_6, labelT1_1;
	static JComboBox comboBox;
	static MyJButton butT1_1, butT1_2, butT1_3, butT1_4, butT1_5, butT1_6, butT2_1, butT2_2, butT2_3, butT1_7,
			butT2_rcno;
	static JTextArea jtextT1_1, jtextT1_2;
	static TextField textT2_1, textT1_2, textT2_2, textT1_3, textT1_4, textT1_5, textT1_1, textT1_7;
	static JTextField jtf;
	static JScrollPane jspT1_1, jspT2_2, JspTable, myScrollPane;
	// static Object[] str1 = getItems();
	static Object[] str1 = null;
	private MyNewTableModel myModel;
	private JTable mytable;
	Textc textc = null;
	String LineNo = "";

	static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;
	static {
		try {
			reader = Resources.getResourceAsReader("Configuration.xml");
			/*
			 * String filePath = System.getProperty("user.dir") +
			 * "/Configuration.xml"; FileReader reader = new
			 * FileReader(filePath);
			 */
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSession() {
		return sqlSessionFactory;
	}

	// public JLabelA(String WorkshopNo,String LineNo) {

	public JLabelA(String WorkshopNo) {

		super("產線端白班刷卡程式-V20170706");
		setBounds(12, 84, 1000, 630);
		setResizable(false);

		Container c = getContentPane();
		tabbedPane = new JTabbedPane(JTabbedPane.LEFT); // 创建选项卡面板对象
		// 创建标签
		labelS1 = new JLabel("指示單號");
		labelS2 = new JLabel("料號");
		labelS3 = new JLabel("標準人數");

		Shift = new JLabel("白班刷卡", JLabel.CENTER);
		Shift.setFont(new Font("微软雅黑", Font.BOLD, 90));
		// label3 = new JLabel("第三个标签的面板", SwingConstants.CENTER);
		// 创建面板
		panel1 = new JPanel();
		panel1.setLayout(null);
		panel2 = new JPanel();
		panel2.setLayout(null);
		panel3 = new JPanel();

		labelT2_1 = new JLabel("指示單號：");// 指示單號

		str1 = getItems();
		if (str1 != null) {
			comboBox = new JComboBox(str1);
		} else {
			comboBox = new JComboBox();
		}

		comboBox.setEditable(true);

		comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		jtf = (JTextField) comboBox.getEditor().getEditorComponent();
		// jtf.setEnabled(false);

		textT1_1 = new TextField(15);// 車間
		textT1_1.setFont(new Font("微软雅黑", Font.PLAIN, 25));

		textT1_2 = new TextField(15);// 線別
		textT1_2.setFont(new Font("微软雅黑", Font.PLAIN, 25));

		textT1_3 = new TextField(15);// 上班
		textT1_3.setFont(new Font("微软雅黑", Font.PLAIN, 25));

		textT1_4 = new TextField(15);// 下班
		textT1_4.setFont(new Font("微软雅黑", Font.PLAIN, 25));

		textT1_5 = new TextField(15);// 實際人數
		textT1_7 = new TextField(15);// 換線上班

		jtextT1_1 = new JTextArea();// 刷卡人員信息,JTextArea(int rows, int columns)
		jtextT1_1.setBackground(Color.WHITE);
		jtextT1_2 = new JTextArea();// 備註
		textT2_1 = new TextField(15);// "料號"
		textT2_2 = new TextField(15);// "標準人數"

		// text3 = new JTextArea(2, 20);

		labelT1_1 = new JLabel("車間:");
		labelT1_1.setFont(new Font("微软雅黑", Font.BOLD, 25));

		labelT1_2 = new JLabel("線別:");
		labelT1_2.setFont(new Font("微软雅黑", Font.BOLD, 25));

		labelT1_3 = new JLabel("上班:");
		labelT1_3.setFont(new Font("微软雅黑", Font.BOLD, 25));

		labelT1_4 = new JLabel("下班:");
		labelT1_4.setFont(new Font("微软雅黑", Font.BOLD, 25));

		labelT1_5 = new JLabel("實際人數:");
		labelT1_6 = new JLabel("備註:");
		labelT2_2 = new JLabel("料號:");
		labelT2_3 = new JLabel("標準人數:");

		// 未補充指示單號人員信息
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("姓名");
		columnNames.add("刷卡時間1");
		columnNames.add("刷卡時間2");
		// columnNames.add("確認");
		table = new JTable(new DefaultTableModel(rowData, columnNames));
		JspTable = new JScrollPane(table);
		JspTable.setBounds(310, 40, 520, 400);

		myModel = new MyNewTableModel(WorkshopNo, "D");
		mytable = new JTable(myModel);
		setTable();
		myScrollPane = new JScrollPane(mytable);
		myScrollPane.setBounds(310, 40, 520, 400);

		int x1 = 15, x2 = 100, x3 = 200, x4 = 400, x5 = 130, x6 = 460, x7 = 90;
		int y1 = 40, y4 = 180;
		/*
		 * 有料號和標準人數時的位置 labelT2_1.setBounds(x1, 3 * y1 + 20, x7, y1);
		 * comboBox.setBounds(x1 + x7, 3 * y1 + 20, x3, y1);// 指示單號
		 */
		labelT2_1.setBounds(x1, y1, x7, y1);
		comboBox.setBounds(x1 + x7, y1, x3, y1);// 指示單號

		// text1.setBounds(x, y, width, height) x,y,寬 高
		labelT2_2.setBounds(x1, y1, x7, y1);
		labelT2_3.setBounds(x1, 2 * y1 + 10, x7, y1);
		/*
		 * labelT1_2.setBounds(x1, 2 * y1, x7, y1); labelT1_3.setBounds(x1, 3 *
		 * y1, x7, y1); labelT1_4.setBounds(x1, 4*y1, x7, y1);
		 */
		labelT1_1.setBounds(x1 + 20, y1, x7, y1);
		labelT1_3.setBounds(x1 + 20, 2 * y1 + 20, x7, y1);
		labelT1_4.setBounds(x1 + 20, 3 * y1 + 40, x7, y1);
		Shift.setBounds(x1, 330, 400, 100);

		labelT1_5.setBounds(x1, 7 * y1, x7, y1);
		labelT1_6.setBounds(x1, 8 * y1, x7, y1);

		/*
		 * textT1_2.setBounds(x1 + x7, 2 * y1, y4, y1); textT1_3.setBounds(x1 +
		 * x7, 3 * y1, y4, y1); textT1_4.setBounds(x1 + x7, 4 * y1, y4, y1);
		 */
		textT1_1.setBounds(x1 + x7, 1 * y1, y4 + 100, y1);
		textT1_3.setBounds(x1 + x7, 2 * y1 + 20, y4 + 100, y1);
		textT1_4.setBounds(x1 + x7, 3 * y1 + 40, y4 + 100, y1);

		textT1_5.setBounds(x1 + x7, 7 * y1, y4, y1);
		// textT1_7.setBounds(x1 + x7, 3 * y1, y4, y1);
		jtextT1_2.setBounds(x1 + x7, 9 * y1, x4, y1);

		textT2_1.setBounds(x1 + x7, 1 * y1, y4, y1);
		textT2_2.setBounds(x1 + x7, 2 * y1 + 10, y4, y1);

		jspT1_1 = new JScrollPane(jtextT1_1);
		jspT1_1.setBounds(400, y1, x4, 250);

		jspT2_2 = new JScrollPane(jtextT1_2);
		jspT2_2.setBounds(x1, 9 * y1, x3 + x7, 150);
		int cc = 240;
		Color d = new Color(cc, cc, cc);// 这里可以设置颜色的rgb

		textT1_1.setEditable(false);
		textT1_2.setEditable(false);
		textT1_3.setEditable(false);
		textT1_4.setEditable(false);
		textT1_5.setEditable(false);
		textT1_7.setEditable(false);

		jtextT1_1.setEditable(false);
		jtextT1_2.setEditable(false);

		textT2_1.setEditable(false);
		textT2_2.setEditable(false);

		jtextT1_1.setLineWrap(true);
		jtextT1_2.setLineWrap(true);

		textT1_3.setBackground(Color.GRAY);
		textT1_4.setBackground(Color.GRAY);
		jtextT1_2.setBackground(d);

		butT1_1 = new MyJButton("上班刷卡模式 ", 2);
		butT1_2 = new MyJButton("下班刷卡模式 ", 2);

		butT1_3 = new MyJButton("加班刷卡模式 ", 2);
		butT1_4 = new MyJButton("提交加班單", 2);

		butT1_5 = new MyJButton("登出(切換帳號)", 2);
		butT1_6 = new MyJButton("退出程式", 2);
		butT1_7 = new MyJButton("換線上班刷卡", 2);

		butT2_1 = new MyJButton("換料 ", 2);
		butT2_2 = new MyJButton("確認提交", 2);
		butT2_3 = new MyJButton("人員刷新", 2);
		butT2_rcno = new MyJButton("刷新指示單", 2);

		butT1_1.setBounds(x6, 350, x5, y1);
		butT1_2.setBounds(x6 + 160, 350, x5, y1);
		// butT1_7.setBounds(x6 + 220, 260, x5, y1);

		butT1_3.setBounds(x1, 13 * y1, x5, y1);
		butT1_4.setBounds(x1 + x5 + 10, 13 * y1, x2, y1);

		butT1_5.setBounds(x6, 350 + y1 + 20, x5, y1);
		butT1_6.setBounds(x6 + 160, 350 + y1 + 20, x5, y1);
		butT2_1.setBounds(x4, 400, x5, y1);
		butT2_3.setBounds(x6 + 60, 12 * y1, x5, y1);
		/*
		 * butT2_2.setBounds(x2 + 110, 5 * y1, 90, y1); butT2_rcno.setBounds(x2,
		 * 5 * y1, 100, y1);
		 */
		butT2_rcno.setBounds(x2, 2 * y1 + 20, 100, y1);
		butT2_2.setBounds(x2 + 110, 2 * y1 + 20, 90, y1);
		// butT1_1.addActionListener(textc);
		// butT1_2.addActionListener(textc);
		// butT1_1.setActionCommand("上班刷卡模式");
		// butT1_2.setActionCommand("下班刷卡模式");

		panel1.add(textT1_1);
		// panel1.add(textT1_2);
		panel1.add(textT1_3);
		panel1.add(textT1_4);
		// panel1.add(textT1_5);
		// panel1.add(textT1_7);

		panel1.add(labelT1_1);
		// panel1.add(labelT1_2);
		panel1.add(labelT1_3);
		panel1.add(labelT1_4);
		panel1.add(Shift);
		// panel1.add(labelT1_5);
		// panel1.add(labelT1_6);

		panel1.add(jspT1_1);
		// panel1.add(jspT2_2);

		panel1.add(butT1_1);
		panel1.add(butT1_2);
		// panel1.add(butT1_3);
		// panel1.add(butT1_4);
		panel1.add(butT1_5);
		panel1.add(butT1_6);
		// panel1.add(butT1_7);

		// panel2.add(butT2_1);
		panel2.add(butT2_2);

		panel2.add(butT2_3);
		panel2.add(butT2_rcno);

		panel2.add(labelT2_1);
		// panel2.add(labelT2_2);
		// panel2.add(labelT2_3);
		panel2.add(comboBox);
		// panel2.add(textT2_1);
		// panel2.add(textT2_2);
		// panel2.add(JspTable);
		panel2.add(myScrollPane);

		// panel2.add(butT1_3);
		// panel2.add(butT1_4);
		// panel2.add(textT1_5);
		// panel2.add(labelT1_5);
		// panel2.add(labelT1_6);
		// panel2.add(jspT2_2);
		// time.schedule(new TimerTask() {;
		// @Override
		// public void run() {
		// update();
		// }
		// }, 0, 1000*10*60);

		panel1.setBackground(Color.WHITE);
		panel2.setBackground(Color.WHITE);
		panel3.setBackground(Color.WHITE);
		// 将标签面板加入到选项卡面板对象上
		tabbedPane.addTab("上下班刷卡界面", null, panel1, "First panel");
		tabbedPane.addTab("補充指示單號", null, panel2, "Second panel");
		// tabbedPane.addTab("工單號", null, panel3, "Third panel");

		// update();
		// ItemListene取得用户选取的项目,ActionListener在JComboBox上自行输入完毕后按下[Enter]键,运作相对应的工作
		comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO

				if (e.getStateChange() == ItemEvent.SELECTED) {

					// System.out.println("-----------e.getItem():"+e.getStateChange()+"-------------");
					String RC_NO = jtf.getText();
					if (RC_NO.length() == 0) {
						textT2_1.setText("");
						textT2_2.setText("");
					} else {
						SqlSession session = sqlSessionFactory.openSession();
						try {
							User eif = (User) session.selectOne("selectUserByRCNo", RC_NO);
							if (eif != null) {
								textT2_1.setText(eif.getPRIMARY_ITEM_NO());
								textT2_2.setText(eif.getSTD_MAN_POWER());
							}

						} finally {
							if (session != null) {
								session.close();
							}
						}
					}

				}
			}
		});

		// TODO addKeyListener用于接收键盘事件（击键）的侦听器接口
		jtf.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
				String key = jtf.getText();
				comboBox.removeAllItems();
				// for (Object item : getItems()) {
				if (str1 != null) {
					for (Object item : str1) {
						// 这里是以key開頭的项目都筛选出来0313240578

						// 可以把contains改成startsWith就是筛选以key开头的项目
						// contains(key)/startsWith(key)
						if (((String) item).startsWith(key)) {
							comboBox.addItem(item);
						}
					}
				}
				jtf.setText(key);
			}

			public void keyPressed(KeyEvent e) {
			}
		});

		butT1_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int cc = 240;
				Color d = new Color(cc, cc, cc);// 这里可以设置颜色的rgb

				// 1、進入批量刷卡上班模式2、做一個判斷
				textT1_3.setEditable(true);
				textT1_4.setEditable(false);
				textT1_5.setEditable(false);
				jtextT1_2.setEditable(false);
				textT1_3.setBackground(Color.WHITE);
				textT1_4.setBackground(Color.GRAY);
				textT1_5.setBackground(d);
				jtextT1_2.setBackground(d);
				textT1_3.requestFocus();
			}
		});

		butT1_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int cc = 240;
				Color d = new Color(cc, cc, cc);// 这里可以设置颜色的rgb
				// 進入批量刷卡下班模式
				textT1_3.setEditable(false);
				textT1_4.setEditable(true);
				textT1_4.requestFocus();
				textT1_5.setEditable(false);
				jtextT1_2.setEditable(false);
				textT1_3.setBackground(Color.GRAY);
				textT1_4.setBackground(Color.WHITE);
				textT1_5.setBackground(d);
				jtextT1_2.setBackground(d);
			}
		});

		butT1_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {// TODO 彈出新窗口
				textT1_3.setEditable(false);
				textT1_4.setEditable(false);
				textT1_5.setEditable(true);
				jtextT1_2.setEditable(true);
				textT1_5.setBackground(Color.WHITE);
				jtextT1_2.setBackground(Color.WHITE);
				textT1_3.setBackground(Color.GRAY);
				textT1_4.setBackground(Color.GRAY);

			}
		});

		butT1_4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 中途刷卡原因
				int cc = 240;
				Color d = new Color(cc, cc, cc);// 这里可以设置颜色的rgb
				jtf.setEditable(false);
				textT1_3.setEditable(false);
				textT1_4.setEditable(false);
				textT1_5.setEditable(false);
				jtextT1_2.setEditable(false);
				textT1_3.setBackground(Color.GRAY);
				textT1_4.setBackground(Color.GRAY);
				textT1_5.setBackground(d);
				jtextT1_2.setBackground(d);
				// TODO Auto-generated method stub
				addUser();
			}
		});

		butT1_5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				InitGlobalFont(new Font("微软雅黑", Font.BOLD, 18));
				dispose();
				Login d = new Login();
			}
		});

		butT1_6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO
				System.exit(0);
			}
		});

		butT1_7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int cc = 240;
				Color d = new Color(cc, cc, cc);// 这里可以设置颜色的rgb

				// panel1.remove(textT1_3);
				// panel1.add(text);

				// panel1.repaint();
				// 1、進入批量刷卡上班模式2、做一個判斷
				// textT1_3.setVisible(false);
				textT1_4.setEditable(false);
				textT1_5.setEditable(false);
				textT1_7.setEditable(true);
				jtextT1_2.setEditable(false);
				textT1_3.setBackground(Color.WHITE);
				textT1_4.setBackground(Color.GRAY);
				textT1_7.setBackground(Color.WHITE);
				panel1.remove(textT1_3);
				panel1.updateUI();
				panel1.repaint();

			}
		});

		butT2_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 中途刷卡原因
				jtf.setEditable(true);
			}
		});

		butT2_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SqlSession session = sqlSessionFactory.openSession();
				// TODO Auto-generated method stub
				int countRow = mytable.getRowCount();

				Boolean State = null;
				User user1 = new User();
				// String LineNo = textT1_2.getText();
				String WorkshopNo = textT1_1.getText();
				String RC_NO = jtf.getText();// 指示單號改為工單號
				String PRIMARY_ITEM_NO = textT2_1.getText();// 使用工單號時此值為null
				String Name = "";
				String sTime1 = "";
				try {
					StringBuilder strBuilder = new StringBuilder();// 创建字符串构建器
					for (int i = 0; i < RC_NO.length(); i++) {// 遍历字符串
						char charAt = RC_NO.charAt(i);// 获取每个字符
						if (charAt == ' ')// 过滤空格字符
							continue;
						strBuilder.append(charAt);// 追加非空格字符到字符构建器
					}
					RC_NO = strBuilder.toString();
					System.out.println("RC_NO: " + RC_NO);
					System.out.println("RC_NO.length: " + RC_NO.length());

					if (!RC_NO.equals("") && RC_NO != "" && RC_NO != null) {
						// user1.setPROD_LINE_CODE(LineNo);
						user1.setWorkshopNo(WorkshopNo);
						user1.setRC_NO(RC_NO); // 把工單號當做指示單號來用
						user1.setPRIMARY_ITEM_NO(PRIMARY_ITEM_NO);// 工單號時，此值為null
						boolean isaddItem = false;
						str1 = getItems();
						if (str1 != null) {
							for (Object item : str1) {
								if (((String) item).equals(RC_NO)) {
									isaddItem = false;
									// JOptionPane.showMessageDialog(null,"工單號已存在!",
									// "提示",JOptionPane.WARNING_MESSAGE);
									break;
								} else {
									isaddItem = true;
								}
							}
						}
						if (isaddItem) {
							session.insert("insertRCInfo", user1);
							session.commit();
						}
						for (int i = 0; i < countRow; i++) {
							State = (Boolean) mytable.getValueAt(i, 0);
							if (State == true) {
								Name = (String) mytable.getValueAt(i, 2);
								user1.setName(Name);

								session.update("Update_rcno_ByLineNOandCardID", user1);
								session.commit();
								System.out.println("Name: " + Name);
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "指示單號不得為空!", "提示", JOptionPane.WARNING_MESSAGE);
					}

					panel2.remove(myScrollPane);
					// myModel = new MyNewTableModel(LineNo, "D");
					myModel = new MyNewTableModel(WorkshopNo, "D");
					mytable = new JTable(myModel);
					setTable();
					myScrollPane = new JScrollPane(mytable);
					myScrollPane.setBounds(310, 40, 520, 400);
					panel2.add(myScrollPane);
					panel2.updateUI(); // 重绘
					panel2.repaint(); // 重绘此组件。
					// System.out.println("State!"+ mytable.getColumnClass(0));
				} finally {
					if (session != null) {
						session.close();
					}
				}
			}
		});

		butT2_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				update();
			}
		});

		butT2_rcno.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				str1 = getItems();
			}
		});

		// TODO 上班刷卡模式
		textT1_3.addTextListener(new TextListener() {

			@Override
			public void textValueChanged(TextEvent e) {
				SqlSession session = sqlSessionFactory.openSession();

				String CardID = textT1_3.getText();

				// text1.setText("");
				String pattern = "^[0-9]\\d{9}$";
				Pattern r = Pattern.compile(pattern, Pattern.DOTALL);
				Matcher m = r.matcher(CardID);

				String time = DateGet.getTime();
				String WorkshopNo = textT1_1.getText();
				// String PROD_LINE_CODE = textT1_2.getText();
				// 驗證是否為10位整數，是則繼續執行，否則提示
				// System.out.println(m.matches());
				if (m.matches() == true) {
					// String[] a = CardID.split("\\s+");

					// Arrays.sort(a);
					// for (int i = 0; i < a.length; i++) {
					// text2.append("CardID2: " + a[i] + "\n");
					// }

					try {
						Map<String, Object> param = new HashMap<String, Object>();
						param.put("CardID", CardID);
						// param.put("PROD_LINE_CODE", PROD_LINE_CODE);
						param.put("WorkshopNo", WorkshopNo);
						String ShiftD = "D";
						param.put("Shift", ShiftD);
						User rows = (User) session.selectOne("selectCountAByCardID", param);
						System.out.println("param: " + param);
						// 通過卡號查詢員工個人信息
						// 1、判斷是否今天第一次刷卡
						// System.out.println("getRowsa: " + rows.getRowsa());
						User havUser = (User) session.selectOne("selectUserByCardID", CardID);

						if (havUser == null) {
							String swipeDate = DateGet.getDate();
							User selEmp = new User();
							selEmp.setCardID(CardID);
							selEmp.setSwipeDate(swipeDate);
							User rows1 = (User) session.selectOne("selectLoseEmployee", selEmp);
							int loseCount = rows1.getLostCon();
							if (loseCount > 0) {
								// JOptionPane.showMessageDialog(null,
								// "已記錄當前異常刷卡人員，今天不用再次刷卡！");
								jtextT1_1.setText("已記錄當前異常刷卡人員，今天不用再次刷卡！\n");
								jtextT1_1.setBackground(Color.RED);
								textT1_3.setText("");
								return;
							}
							/*
							 * JOptionPane.showMessageDialog(null,
							 * "當前刷卡人員不存在；可能是新進人員，或是舊卡丟失補辦，人員資料暫時未更新，請線長記錄，協助助理走原有簽核流程！"
							 * );
							 */
							jtextT1_1.setText("當前刷卡人員不存在；可能是新進人員，或是舊卡丟失補辦，人員資料暫時未更新，請線長記錄，協助助理走原有簽核流程！\n");
							jtextT1_1.setBackground(Color.RED);
							User user1 = new User();
							user1.setCardID(CardID);
							user1.setWorkshopNo(WorkshopNo);
							user1.setSwipeDate(swipeDate);
							session.insert("insertUserByNoCard", user1);
							session.commit();
							/*
							 * String[] inputIDName = null; String inputID =
							 * null; String inputName = null; inputIDName =
							 * showIDDialog(); if (inputIDName != null) {
							 * inputID = inputIDName[0]; inputName =
							 * inputIDName[1]; System.out.println("ID1：" +
							 * inputID); System.out.println("Name1：" +
							 * inputName); User user1 = new User();
							 * user1.setCardID(CardID);
							 * user1.setName(inputName); user1.setId(inputID);
							 * user1.setSwipeDate(swipeDate); //
							 * user1.setPROD_LINE_CODE(PROD_LINE_CODE);
							 * user1.setWorkshopNo(WorkshopNo);
							 * session.insert("insertUserByNoCard", user1);
							 * session.commit(); }
							 */

							// 改寫sql語句，一天只會執行一次，是不是應該先檢測一下
						} else if (rows.getRowsa() == 0) {
							// /**
							// text2.append();
							User eif = (User) session.selectOne("selectUserByCardID", CardID);
							// String cardid = eif.getCardID();

							String name = eif.getName();
							String RC_NO = jtf.getText();

							String PRIMARY_ITEM_NO = textT2_1.getText();
							jtextT1_1.setBackground(Color.WHITE);
							jtextT1_1.setText("第一次刷卡\n" + "ID: " + eif.getId() + "\nName: " + eif.getName() + "\n刷卡時間： "
									+ time + "\n" + "員工上班刷卡成功！\n------------\n");
							User user1 = new User();
							String shift = "D";
							user1.setCardID(CardID);
							user1.setName(name);
							String swipeCardTime = time;
							user1.setSwipeCardTime(swipeCardTime);
							user1.setRC_NO(RC_NO);
							user1.setPRIMARY_ITEM_NO(PRIMARY_ITEM_NO);
							// user1.setPROD_LINE_CODE(PROD_LINE_CODE);
							user1.setWorkshopNo(WorkshopNo);
							user1.setShift(shift);
							session.insert("insertUserByOnDNShift", user1);
							session.commit();
							// System.out.println("HH!");
							// text2.append("員工上班刷卡成功！\n------------\n");
							// */
						} else if (rows.getRowsa() > 0) {
							User eif = (User) session.selectOne("selectUserByCardID", CardID);
							System.out.println("row.getRowsA: " + rows.getRowsa());
							jtextT1_1.setBackground(Color.WHITE);
							jtextT1_1.append("Name: " + eif.getName() + "\n" + "上班重複刷卡！\n\n");
						}
					} finally {
						if (session != null) {
							session.close();
						}
					}
					textT1_3.setText("");
				} else {
					System.out.println("無輸入內容或輸入錯誤!");
				}
				// text2.setText("");
				// if(rows.getRowsa)
				// System.out.println("This is methodA!");

			}
		});

		// TODO 下班刷卡模式
		textT1_4.addTextListener(new TextListener() {

			@Override
			public void textValueChanged(TextEvent e) {
				SqlSession session = sqlSessionFactory.openSession();
				String CardID = textT1_4.getText();

				// 驗證是否為10位整數，是則繼續執行，否則提示
				String pattern = "^[0-9]\\d{9}$";
				Pattern r = Pattern.compile(pattern, Pattern.DOTALL);
				Matcher m = r.matcher(CardID);
				String time = DateGet.getTime();
				String WorkshopNo = textT1_1.getText();
				// String PROD_LINE_CODE = textT1_2.getText();
				if (m.matches() == true) {
					System.out.println("CardID: " + CardID);
					try {
						Map<String, Object> param = new HashMap<String, Object>();
						param.put("CardID", CardID);
						// param.put("PROD_LINE_CODE", PROD_LINE_CODE);
						param.put("WorkshopNo", WorkshopNo);
						User rows = (User) session.selectOne("selectCountBByCardID", param);
						// 通過卡號查詢員工個人信息
						// 1、判斷是否今天第一次刷卡
						if (rows.getRowsb() == 0) {
							// /**
							// text2.append();
							System.out.println("row.getRowsB: " + rows.getRowsb());
							User havUser = (User) session.selectOne("selectUserByCardID", CardID);
							if (havUser == null) {
								String swipeDate = DateGet.getDate();
								User selEmp = new User();
								selEmp.setCardID(CardID);
								selEmp.setSwipeDate(swipeDate);
								User rows1 = (User) session.selectOne("selectLoseEmployee", selEmp);
								int loseCount = rows1.getLostCon();
								if (loseCount > 0) {
									// JOptionPane.showMessageDialog(null,
									// "已記錄當前異常刷卡人員，今天不用再次刷卡！");
									jtextT1_1.setBackground(Color.RED);
									jtextT1_1.setText("已記錄當前異常刷卡人員，今天不用再次刷卡！\n");
									textT1_4.setText("");
									return;
								}
								/*
								 * JOptionPane.showMessageDialog(null,
								 * "當前刷卡人員不存在；可能是新進人員，或是舊卡丟失補辦，人員資料暫時未更新，請線長記錄，協助助理走原有簽核流程！"
								 * );
								 */
								jtextT1_1.setBackground(Color.RED);
								jtextT1_1.setText("當前刷卡人員不存在；可能是新進人員，或是舊卡丟失補辦，人員資料暫時未更新，請線長記錄，協助助理走原有簽核流程！\n");
								User user1 = new User();
								user1.setCardID(CardID);
								user1.setWorkshopNo(WorkshopNo);
								user1.setSwipeDate(swipeDate);
								session.insert("insertUserByNoCard", user1);
								session.commit();

								/*
								 * String[] inputIDName = null; String inputID =
								 * null; String inputName = null; inputIDName =
								 * showIDDialog(); if (inputIDName != null) {
								 * inputID = inputIDName[0]; inputName =
								 * inputIDName[1]; System.out.println("ID1：" +
								 * inputID); System.out.println("Name1：" +
								 * inputName); User user1 = new User();
								 * user1.setCardID(CardID);
								 * user1.setName(inputName);
								 * user1.setId(inputID);
								 * 
								 * // user1.setSwipeCardTime(swipeCardTime);
								 * user1.setSwipeDate(swipeDate); //
								 * user1.setPROD_LINE_CODE(PROD_LINE_CODE);
								 * user1.setWorkshopNo(WorkshopNo);
								 * session.insert("insertUserByNoCard", user1);
								 * session.commit(); }
								 */
								// JOptionPane.showMessageDialog(null,
								// "當前刷卡人員不存在；可能是新進人員，或是舊卡丟失補辦，人員資料暫時未更新，請線長記錄，走原有簽核流程！");

							} else {
								String name = havUser.getName();

								if (jtf != null) {
									String RC_NO = jtf.getText();// TODO
								}
								String RC_NO = jtf.getText();
								String PRIMARY_ITEM_NO = textT2_1.getText();
								jtextT1_1.setBackground(Color.WHITE);
								jtextT1_1.setText("第一次刷卡\n" + "ID: " + havUser.getId() + "\nName: " + havUser.getName()
										+ "\n刷卡時間： " + time + "\n" + "員工下班刷卡成功！\n------------\n");
								User user1 = new User();
								String shift = "D";
								String SwipeCardTime2 = time;
								// String SwipeCardTime2 = "2017-06-09
								// 07:30:00";

								user1.setSwipeCardTime2(SwipeCardTime2);
								user1.setCardID(CardID);
								user1.setShift(shift);
								// user1.setReason("下班");
								System.out.println("user1: " + SwipeCardTime2);
								session.update("updateUserByOffDDuty", user1);
								session.commit();

							}
						} else if (rows.getRowsb() > 0) {
							User eif = (User) session.selectOne("selectUserByCardID", CardID);
							System.out.println("row.getRowsB: " + rows.getRowsb());
							jtextT1_1.setBackground(Color.WHITE);
							jtextT1_1.append("Name: " + eif.getName() + "\n" + "下班重複刷卡！\n\n");
						}
					} finally {
						if (session != null) {
							session.close();
						}
					}
					textT1_4.setText("");
				} else {
					System.out.println("無輸入內容或輸入錯誤!");
				}
				// System.out.println("This is methodA!");
			}
		});

		textT1_7.addTextListener(new TextListener() {

			@Override
			public void textValueChanged(TextEvent e) {
				SqlSession session = sqlSessionFactory.openSession();
				String CardID = textT1_7.getText();
				// text1.setText("");
				String pattern = "^[0-9]\\d{9}$";
				Pattern r = Pattern.compile(pattern, Pattern.DOTALL);
				Matcher m = r.matcher(CardID);

				String time = DateGet.getTime();
				String WorkshopNo = textT1_1.getText();
				// String PROD_LINE_CODE = textT1_2.getText();
				// 驗證是否為10位整數，是則繼續執行，否則提示
				// System.out.println(m.matches());
				if (m.matches() == true) {
					// String[] a = CardID.split("\\s+");

					// Arrays.sort(a);
					// for (int i = 0; i < a.length; i++) {
					// text2.append("CardID2: " + a[i] + "\n");
					// }

					try {
						Map<String, Object> param = new HashMap<String, Object>();
						param.put("CardID", CardID);
						// param.put("PROD_LINE_CODE", PROD_LINE_CODE);
						param.put("WorkshopNo", WorkshopNo);
						String ShiftD = "D";
						param.put("Shift", ShiftD);
						User rows = (User) session.selectOne("selectCountAByCardID", param);
						System.out.println("param: " + param);
						// 通過卡號查詢員工個人信息
						// 1、判斷是否今天第一次刷卡
						// System.out.println("getRowsa: " + rows.getRowsa());
						if (rows.getRowsa() == 0) {
							User line = (User) session.selectOne("selectChangeLineByCardID", param);
							System.out.println("param: " + param);
							String workshopNo = line.getWorkshopNo();
							// 通過卡號查詢員工個人信息
							if (workshopNo != null) {
								// /**
								// text2.append();
								User eif = (User) session.selectOne("selectUserByCardID", CardID);
								// String cardid = eif.getCardID();
								jtextT1_1.setBackground(Color.WHITE);
								jtextT1_1.setText("第一次刷卡\n" + "ID: " + eif.getId() + "\nName: " + eif.getName()
										+ "\n刷卡時間： " + time + "\n" + "員工換線上班刷卡成功！\n------------\n");
								User user1 = new User();
								String name = eif.getName();
								user1.setCardID(CardID);
								String swipeCardTime2 = time;
								user1.setSwipeCardTime2(swipeCardTime2);
								// user1.setPROD_LINE_CODE(preline);
								user1.setWorkshopNo(workshopNo);
								session.update("updateChangeLineUserByOnDuty", user1);
								session.commit();
								User user2 = new User();

								String RC_NO = jtf.getText();
								String PRIMARY_ITEM_NO = textT2_1.getText();
								String swipeCardTime = time;

								user2.setCardID(CardID);
								user2.setName(name);
								user2.setSwipeCardTime(swipeCardTime);
								user2.setRC_NO(RC_NO);
								user2.setPRIMARY_ITEM_NO(PRIMARY_ITEM_NO);
								// user2.setPROD_LINE_CODE(PROD_LINE_CODE);
								user2.setWorkshopNo(WorkshopNo);
								session.insert("insertUserByOnDuty", user2);
								session.commit();
							}
						} else if (rows.getRowsa() > 0) {
							User eif = (User) session.selectOne("selectUserByCardID", CardID);
							System.out.println("row.getRowsA: " + rows.getRowsa());
							jtextT1_1.setBackground(Color.WHITE);
							jtextT1_1.append("Name: " + eif.getName() + "\n" + "換線上班重複刷卡！\n\n");
						}
					} finally {
						if (session != null) {
							session.close();
						}
					}
					textT1_7.setText("");
				} else {
					System.out.println("無輸入內容或輸入錯誤!");
				}

			}
		});

		c.add(tabbedPane);
		c.setBackground(Color.lightGray);

		textT1_1.setText(WorkshopNo);// 綁定車間
		textT1_2.setText(LineNo);// 綁定線別

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * TODO
	 * 
	 * @return 指示單號
	 */
	public Object[] getItems() {
		List<User> user;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			user = session.selectList("selectRCNo");
			int con = user.size();
			System.out.println(user.size());
			Object[] a = null;
			if (con > 0) {
				a = new Object[con + 1];
				a[0] = "";
				for (int i = 1; i < con + 1; i++) {
					// System.out.println(user.get(i).getRC_NO());
					a[i] = user.get(i - 1).getRC_NO();
					// a.add(user.get(i).getRC_NO());
				}
			}
			final Object[] s = a;
			return a;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/**
	 * 員工刷入卡號判斷是否無記錄，並作出相應對策
	 */
	private String[] showIDDialog() {
		String[] aArray = new String[2];
		String inputID = JOptionPane.showInputDialog("Please input a id");
		String inputName = null;
		aArray[0] = inputID;

		if (inputID == null) {

			return null;
		} else if (inputID.isEmpty()) {
			showIDDialog();
		} else {
			inputName = showNameDialog();
			aArray[1] = inputName;
		}
		// return aArray;
		return aArray;

	}

	private void breakShow() {
		return;
	}

	private String showNameDialog() {
		String inputName = JOptionPane.showInputDialog("Please input a Name");
		if (inputName == null) {
			return null;
		}
		if (inputName.isEmpty()) {
			showNameDialog();
		}
		return inputName;
	}

	public void updatexxx() {
		new SwingWorker<Object, Object>() { // TODO 待添加查詢條件-
			String WorkshopNo = textT1_1.getText();
			String PROD_LINE_CODE = textT1_2.getText();

			SqlSession session = sqlSessionFactory.openSession();

			List<User> eif = session.selectList("selectUserByLineNoAndWorkshopNo", WorkshopNo);

			protected Object doInBackground() throws Exception {
				rowData.clear();
				Vector<Object> info = new Vector<Object>();
				info.add(new Boolean(false));
				rowData.add(info);
				// count++;
				return null;
			}

			protected void done() {
				((DefaultTableModel) table.getModel()).fireTableDataChanged();
			}
		}.execute();

	}

	public String checkFill() {
		SqlSession session = sqlSessionFactory.openSession();
		String WorkshopNo = textT1_1.getText();
		String PROD_LINE_CODE = textT1_2.getText();
		User user1 = new User();
		user1.setWorkshopNo(WorkshopNo);
		// user1.setPROD_LINE_CODE(PROD_LINE_CODE);
		User rows = (User) session.selectOne("checkFill", user1);
		session.commit();
		String message;
		if (rows.getFillRows() == 0) {
			// System.out.println("未綁定指示單號");
			JOptionPane.showMessageDialog(null, "請補充指示單號");
			message = "未綁定指示單號";
		} else {
			// System.out.println("已綁定指示單號");
			message = "綁定指示單號";
		}

		return message;

	}

	public void addUser() {// TODO
		SqlSession session = sqlSessionFactory.openSession();
		String WorkshopNo = textT1_1.getText();
		// String PROD_LINE_CODE = textT1_2.getText();
		String ACTUAL_POWER = textT1_5.getText();
		String REMARK = jtextT1_2.getText();
		String RC_NO = jtf.getText();
		String PRIMARY_ITEM_NO = textT2_1.getText();
		String STD_MAN_POWER = textT2_2.getText();
		try {
			User user1 = new User();

			user1.setRC_NO(RC_NO);
			user1.setPRIMARY_ITEM_NO(PRIMARY_ITEM_NO);
			// user1.setPROD_LINE_CODE(PROD_LINE_CODE);
			user1.setWorkshopNo(WorkshopNo);
			user1.setSTD_MAN_POWER(STD_MAN_POWER);
			user1.setACTUAL_POWER(ACTUAL_POWER);
			user1.setREMARK(REMARK);
			session.insert("com.yihaomen.mybatis.models.UserMapper.insertInfor", user1);
			session.commit();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	protected void methodC() {
		textT2_1.setEditable(false);
		jtextT1_1.setBackground(Color.WHITE);
		jtextT1_1.setEditable(false);
		textT2_1.setText("");
		// textT1_2.setText("");
		jtextT1_1.setBackground(Color.WHITE);
		jtextT1_1.setText("");
	}

	private static void InitGlobalFont(Font font) {
		FontUIResource fontRes = new FontUIResource(font);
		for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource) {
				UIManager.put(key, fontRes);
			}
		}
	}

	public static void main(String args[]) {
		InitGlobalFont(new Font("微软雅黑", Font.BOLD, 18));
		String WorkShopNo = "第一車間";
		String LineNo = "1L-01";
		// JLabelA d = new JLabelA(WorkShopNo, LineNo);
		JLabelA d = new JLabelA(WorkShopNo);
	}

	public void setNameValue(String valueString) {
		// TODO Auto-generated method stub
		textT1_2.setText(valueString);
	}

	public void update() {
		// String LineNo = textT1_2.getText();
		String WorkshopNo = textT1_1.getText();
		panel2.remove(myScrollPane);
		myModel = new MyNewTableModel(WorkshopNo, "D");
		mytable = new JTable(myModel);

		myScrollPane = new JScrollPane(mytable);
		myScrollPane.setBounds(310, 40, 520, 400);
		setTable();
		panel2.add(myScrollPane);
		panel2.updateUI();
		panel2.repaint();
	}

	public void setTable() {
		mytable.getColumnModel().getColumn(0).setMaxWidth(40);
		mytable.getColumnModel().getColumn(1).setMaxWidth(40);
		mytable.getColumnModel().getColumn(2).setMaxWidth(60);
		mytable.setRowHeight(25);
		mytable.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		JTableHeader header = mytable.getTableHeader();
		header.setFont(new Font("微软雅黑", Font.BOLD, 16));
		header.setPreferredSize(new Dimension(header.getWidth(), 30));
	}

}

class Textc implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("上班刷卡模式")) {// 多态的思想
			System.out.println("A");
		}

		if (e.getActionCommand().equals("下班刷卡模式")) {// 多态的思想
			System.out.println("B");
		}
	}
}
