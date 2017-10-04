package com.swipecard;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.FontUIResource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.swipecard.model.User;

public class Login extends JFrame {
	private JPanel panel1;
	private JLabel label1, label2;
	static JPasswordField text1;
	//static JTextField jtf1, jtf2,jtf3;
	static JTextField jtf1,jtf3;
	private MyJButton but1;
	//static JComboBox comboBox1, comboBox2,comboBox3;
	static JComboBox comboBox1,comboBox3;

	final Object[] WorkshopNo = getWorkshopNo();
	final Object[] LineLeader = getLineLeader();

	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;
	static {
		try {
		    //读取内部配置文件
			reader = Resources.getResourceAsReader("Configuration.xml");
			
			/*// 读取系统外配置文件 (即Jar包外文件) --- 外部工程引用该Jar包时需要在工程下创建config目录存放配置文件
		    String filePath = System.getProperty("user.dir") + "/Configuration.xml";
		    FileReader reader=new FileReader(filePath);*/
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSession() {
		return sqlSessionFactory;
	}

	public Login() {
		super("管理人員登陸");
		setBounds(212, 159, 600, 450);
		setResizable(false);
		Container c = getContentPane();
		panel1 = new JPanel();
		panel1.setLayout(null);
		label1 = new JLabel("實時工時系統", JLabel.CENTER);
		label2 = new JLabel("管理人員：");
		text1 = new JPasswordField("");
//		text1 = new JPasswordField();


		but1 = new MyJButton("確認 ", 2);

		comboBox1 = new JComboBox(getWorkshopNo());// getWorkNo 0313199858
		comboBox1.setEditable(true);
		comboBox1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		jtf1 = (JTextField) comboBox1.getEditor().getEditorComponent();

	/*	comboBox2 = new JComboBox(getLineNoByWorkshopNo());// getLineNoByWorkNo
		comboBox2.setEditable(false);// 可編輯
		comboBox2.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		jtf2 = (JTextField) comboBox2.getEditor().getEditorComponent();*/
		
		comboBox3 = new JComboBox();// getLineNoByWorkNo
		comboBox3.addItem("");
		comboBox3.addItem("日班");
		comboBox3.addItem("夜班");
		comboBox3.setEditable(false);// 可編輯
		comboBox3.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		jtf3 = (JTextField) comboBox3.getEditor().getEditorComponent();

		label1.setBounds(150, 50, 300, 40);
		label1.setFont(new Font("微软雅黑", Font.BOLD, 40));
		but1.setFont(new Font("微软雅黑", Font.BOLD, 18));
		label2.setBounds(170, 200, 100, 30);
		text1.setBounds(270, 200, 160, 40);
		but1.setBounds(250, 300, 120, 40);
		/*comboBox1.setBounds(160, 120, 100, 30);
		comboBox2.setBounds(260, 120, 100, 30);
		comboBox3.setBounds(360, 120, 100, 30);*/
		
		comboBox1.setBounds(180, 120, 120, 40);
		comboBox3.setBounds(300, 120, 120, 40);

		panel1.add(label1);
		panel1.add(label2);
		panel1.add(text1);
		panel1.add(but1);
		panel1.add(comboBox1);
	//	panel1.add(comboBox2);
		panel1.add(comboBox3);
		c.add(panel1);
		but1.addActionListener(new TextFrame_jButton1_actionAdapter1(this));
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/**
		 * @author 
		 */
		comboBox1.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub

				if (e.getStateChange() == ItemEvent.SELECTED) {
					// 要执行的代码
					String key = jtf1.getText();
					System.out.println("key: " + key);
					/*comboBox2.removeAllItems();
					for (Object item : getLineNoByWorkshopNo()) {
						comboBox2.addItem(item);
					}*/
				}
			}
		});

		/*comboBox2.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					jtf2.setText((String) e.getItem());
				}
			}
		});*/

	}

	public Object[] getWorkshopNo() {// TODO
		List<User> user;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			user = session.selectList("selectWorkshopNo");
			int con = user.size();
			Object[] a = new Object[con];
			for (int i = 0; i < con; i++) {
				a[i] = user.get(i).getWorkshopNo();
			}
			return a;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public Object[] getLineNoByWorkshopNo() {//TODO 線號
		List<User> user;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			String wno = jtf1.getText();
			user = session.selectList("selectLineNoByWorkshopNo", wno);
			System.out.println(user.size());
			int con = user.size() + 1;
			Object[] a = new Object[con];
			a[0] = "";
			for (int i = 0; i < con - 1; i++) {
				a[i + 1] = user.get(i).getLineNo();
			}
			return a;
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@SuppressWarnings("rawtypes")
	public Object[] getLineLeader() {
		// TODO
		List<User> user;

		SqlSession session = sqlSessionFactory.openSession();
		user = session.selectList("selectUserByPermission");
		System.out.println("Admin.size(): " + user.size());
		Object[] a = new Object[user.size()];
		for (int i = 0; i < user.size(); i++) {
			a[i] = user.get(i).getCardID();
			// System.out.println("eif: "+ user.get(i).getCardID());
	//		System.out.println("Admin: " + a[i]);
		}
		return a;
	}

	public static void main(String args[]) {
		InitGlobalFont(new Font("微软雅黑", Font.BOLD, 18));
		Login d = new Login();
        //  SwipeCardLogin.create();
		//	SwipeCardLogin.setOSTime();
	}
	

	public static void create() {
		Process proc = null;
		 String[] cmd = {"cmd", "/k","net runas /user:administrator start w32time & w32tm /config /update /manualpeerlist:192.168.78.8 & w32tm /resync"};
		try {
		proc = Runtime.getRuntime().exec(cmd);
		InputStream inputStream = proc.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 读取进程的输出信息并打印到控制台就不会发生阻塞，程序能正常的结束。
	private static void printMessage(final InputStream input) {
		new Thread(new Runnable() {
			public void run() {
				Reader reader;
				try {
					reader = new InputStreamReader(input, "gbk");
					BufferedReader bf = new BufferedReader(reader);
					String line = null;
					while ((line = bf.readLine()) != null) {
						System.out.println(line);
					}
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}

	public static void setOSTime() {

		System.out.println("开始访问百度服务器：http://www.baidu.com");
		URLConnection conn;
		try {
			conn = new URL("http://www.baidu.com").openConnection();
			String dateStr = conn.getHeaderField("Date");
			System.out.println("获取到的服务器时间：" + dateStr);

			// 解析为北京时间：GMT+8
			DateFormat httpDateFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z", Locale.US);
			httpDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			Date date = httpDateFormat.parse(dateStr);
			System.out.println("解析成北京时间格式：" + date);

			// 解析成简洁的日期格式
			DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
			dateTimeFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			System.out.println("解析成标准时间格式：" + dateTimeFormat.format(date));

			// 取日期
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
			dateTimeFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			String currDate = dateFormat.format(date);

			// 设置Windows系统日期
			Process exec = Runtime.getRuntime().exec("cmd /k date " + currDate);
			printMessage(exec.getInputStream());
			printMessage(exec.getErrorStream());
			if (exec.waitFor() == 0) {
				System.out.println("设置系统日期成功：" + currDate);
			} else {
				System.out.println("设置系统日期失败：" + currDate);
			}

			// 取时间
			DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.US);
			timeFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			String currTime = timeFormat.format(date);

			// 设置Windows系统时间
			exec = Runtime.getRuntime().exec("cmd /k time " + currTime);
			printMessage(exec.getInputStream());
			printMessage(exec.getErrorStream());
			if (exec.waitFor() == 0) {
				System.out.println("设置系统时间成功：" + currTime);
			} else {
				System.out.println("设置系统时间失败：" + currTime);
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void InitGlobalFont(Font font) {
		FontUIResource fontRes = new FontUIResource(font);
		for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys
				.hasMoreElements();) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource) {
				UIManager.put(key, fontRes);
			}
		}
	}
}

class TextFrame_jButton1_actionAdapter1 implements ActionListener {
	private Login adaptee;

	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;
	static {
		try {
			reader = Resources.getResourceAsReader("Configuration.xml");
			/* String filePath = System.getProperty("user.dir") + "/Configuration.xml";
			 FileReader reader=new FileReader(filePath);*/
			 sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSession() {
		return sqlSessionFactory;
	}

	public TextFrame_jButton1_actionAdapter1(Login adaptee) {
		this.adaptee = adaptee;
	}

	public static boolean isHave(Object[] a, String s) {
		/*
		 * 此方法有两个参数，第一个是要查找的字符串数组，第二个是要查找的字符或字符串
		 */
		for (int i = 0; i < a.length; i++) {
			// if (obj[i].indexOf(s) != -1) {// 循环查找字符串数组中的每个字符串中是否包含所有查找的内容
			if (a[i].equals(s)) {
				return true;// 查找到了就返回真，不在继续查询
			}
		}
		return false;// 没找到返回false
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		char[] pass = adaptee.text1.getPassword();
		String CardID = new String(pass);
		System.out.println("CardID: " + CardID);
		String pattern = "^[0-9]\\d{9}$";
		Pattern r = Pattern.compile(pattern, Pattern.DOTALL);
		Matcher m = r.matcher(CardID);
	//	String LineNo = Login.jtf2.getText();
		String Shift = "";

		Object ShiftName = Login.comboBox3.getSelectedItem();
		System.out.println("test"+ShiftName);
		
		/*if (Login.jtf2.getText().equals("")) {
			JOptionPane.showMessageDialog(adaptee, "未選擇線別，請選擇");
			return;
		}*/
		
		if (ShiftName.equals("")) { 
			JOptionPane.showMessageDialog(adaptee, "未選擇班別，請選擇");
			return;
		}
		if (m.matches() == true) {
			Object[] a = adaptee.LineLeader;
			if(ShiftName.equals("日班")){
				Shift="D";
			}else if(ShiftName.equals("夜班")){
				Shift="N";
			}
			if (isHave(a, CardID)) {// 调用自己定义的函数isHave，如果包含则返回true,否则返回false
				System.out.println("成功登陸！");
				adaptee.dispose();
				String WorkshopNo = Login.jtf1.getText();
				if(Shift.equals("D")){
					//JLabelA d = new JLabelA(WorkshopNo, LineNo);
					JLabelA d = new JLabelA(WorkshopNo);
				}else if(Shift.equals("N")){
					//JLabelN d = new JLabelN(WorkshopNo, LineNo);
					JLabelN d = new JLabelN(WorkshopNo);
				}
				
			} else {
				JOptionPane.showMessageDialog(adaptee, "輸入有誤，請重新刷卡");
				System.out.println("不包含");// 打印结果
			}
		} else {
			JOptionPane.showMessageDialog(adaptee, "輸入有誤，請重新刷卡");
			System.out.println("不合法卡號，含有非數字字符或卡號長度不正確");
		}
	}
}
