package com.swipecard;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.Enumeration;
import java.util.List;
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
import javax.swing.plaf.FontUIResource;

import org.apache.ibatis.exceptions.ExceptionFactory;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import com.swipecard.util.FrameShowUtil;
import com.swipecard.util.JsonFileUtil;
import com.swipecard.model.User;

public class SwipeCardLogin extends JFrame {
	private final static String CurrentVersion="V20170913";
	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;
	static {
		try {
			// 读取内部配置文件
			reader = Resources.getResourceAsReader("Configuration.xml");

			/*
			 * // 读取系统外配置文件 (即Jar包外文件) --- 外部工程引用该Jar包时需要在工程下创建config目录存放配置文件
			 * String filePath = System.getProperty("user.dir") + "/Configuration.xml"; 
			 * FileReader reader=new FileReader(filePath);
			 */
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			//System.out.println("sqlSessionFactory:"+sqlSessionFactory);
		} catch (Exception e) {
			//e.printStackTrace();
			SwipeCardNoDB d = new SwipeCardNoDB(null);
			throw ExceptionFactory.wrapException("Error building SqlSession.", e);
		}
	}

	public static SqlSessionFactory getSession() {
		return sqlSessionFactory;
	}

	final Object[] WorkshopNo = getWorkshopNo();
	final Object[] LineLeader = getLineLeader();
	
	private JPanel panel1;
	private JLabel label1, label2, label3;
	static JPasswordField text1;
	static JTextField jtf1, jtf3;
	private MyJButton but1;
	static JComboBox comboBox1;	
	
	public SwipeCardLogin() {
		super("管理人員登陸-"+CurrentVersion);
		setBounds(212, 159, 600, 450);
		setResizable(true);
				
		Container c = getContentPane();		
		panel1 = new JPanel();
		panel1.setLayout(null);
		label1 = new JLabel("實時工時系統", JLabel.CENTER);
		label2 = new JLabel("管理人員：");
		label3 = new JLabel("车间：");

		text1 = new JPasswordField("");
		// text1 = new JPasswordField();

		but1 = new MyJButton("確認 ", 2);

		comboBox1 = new JComboBox(WorkshopNo);
		comboBox1.setEditable(false);//設置comboBox1為不可編輯，此時jtf1不生效
		comboBox1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
				
		jtf1 = (JTextField) comboBox1.getEditor().getEditorComponent();

		label1.setBounds(150, 50, 300, 40);
		label1.setFont(new Font("微软雅黑", Font.BOLD, 40));
		but1.setFont(new Font("微软雅黑", Font.BOLD, 18));
		label2.setBounds(120, 200, 100, 30);
		text1.setBounds(220, 200, 160, 40);
		but1.setBounds(240, 300, 120, 40);

		label3.setBounds(120, 120, 100, 30);
		comboBox1.setBounds(220, 120, 160, 40);

		panel1.add(label1);
		panel1.add(label2);
		panel1.add(label3);
		panel1.add(text1);
		panel1.add(but1);
		panel1.add(comboBox1);
		c.add(panel1);
		but1.addActionListener(new TextFrame_jButton1_actionAdapter(this));
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
/*
		int fraWidth = this.getWidth();//frame的宽  
        int fraHeight = this.getHeight();//frame的高  
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  
        int screenWidth = screenSize.width;  
        int screenHeight = screenSize.height;  
        this.setSize(screenWidth, screenHeight);  
        this.setLocation(0, 0);  
        float proportionW = screenWidth/fraWidth;  
        float proportionH = screenHeight/fraHeight;  
        FrameShowUtil frameShow=new FrameShowUtil();
        frameShow.modifyComponentSize(this, proportionW,proportionH);  
        this.toFront();  
 */       
		/**
		 * @author
		 */
		comboBox1.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub

				if (e.getStateChange() == ItemEvent.SELECTED) {
					String key = jtf1.getText();					
				}
			}
		});
	}
	JsonFileUtil jsonFileUtil=new JsonFileUtil();
	@SuppressWarnings("unchecked")
	public Object[] getWorkshopNo(){// TODO
		List<User> user;
		Object[] a = null;
		try {
			SqlSession session = sqlSessionFactory.openSession();		
			JSONArray workshopNoArray=new JSONArray();
			user = session.selectList("selectWorkshopNo");
			int con = user.size();		
			if(con>0){	
				a = new Object[con];
				for (int i = 0; i < con; i++) {					
					a[i] = user.get(i).getWorkshopNo();
				    JSONObject workshopNoJson = new JSONObject();
				    workshopNoJson.put("workshopNo", a[i].toString());
				    workshopNoArray.put(workshopNoJson);
					}
				String fileName="WorkshopNo.json";
				jsonFileUtil.createJsonFile(workshopNoArray.toString(),fileName);
				}
			else{
				a[0]="";
			}
		}
		catch (Exception e) {		
			System.out.println("Error opening session");
			dispose();
			SwipeCardNoDB d = new SwipeCardNoDB(null);
		    throw ExceptionFactory.wrapException("Error opening session.  Cause: " + e, e);
		  } finally {			  
		    ErrorContext.instance().reset();
		  }	
		return a;
	}


	@SuppressWarnings("rawtypes")
	public Object[] getLineLeader() {
		// TODO
		List<User> user;
		Object[] a=null;
		try{
			SqlSession session = sqlSessionFactory.openSession();
			user = session.selectList("selectUserByPermission");
			if(user.size()>0){
				a = new Object[user.size()];
				for (int i = 0; i < user.size(); i++) {
				a[i] = user.get(i).getCardID();
				// System.out.println("Admin: " + a[i]);
				}
				}
		else{
			a[0]="";
		}
		}
		catch (Exception e) {		
				System.out.println("Error opening session");
				dispose();
				SwipeCardNoDB d = new SwipeCardNoDB(null);
				throw ExceptionFactory.wrapException("Error opening session.  Cause: " + e, e);
				}
		finally {			  
			ErrorContext.instance().reset();
			}
		return a;
	}
	
	public static void main(String args[]) {
		InitGlobalFont(new Font("微软雅黑", Font.BOLD, 18));
		SwipeCardLogin d = new SwipeCardLogin();
		
		CheckCurrentVersion chkVersion=new CheckCurrentVersion(CurrentVersion);
 		Thread executeCheckVersion=new Thread(chkVersion);
 		executeCheckVersion.start();
		
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
}

class TextFrame_jButton1_actionAdapter implements ActionListener {
	private SwipeCardLogin adaptee;

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
			SwipeCardNoDB d = new SwipeCardNoDB(null);
			e.printStackTrace();			
		}
	}

	public static SqlSessionFactory getSession() {
		return sqlSessionFactory;
	}

	public TextFrame_jButton1_actionAdapter(SwipeCardLogin adaptee) {
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

		if (m.matches() == true) {
			Object[] a = adaptee.LineLeader;
			/*
			 * if(ShiftName.equals("日班")){ Shift="D"; }else
			 * if(ShiftName.equals("夜班")){ Shift="N"; }
			 */
			if (isHave(a, CardID)) {// 调用自己定义的函数isHave，如果包含则返回true,否则返回false
				System.out.println("成功登陸！");
				adaptee.dispose();
				//String WorkshopNo = SwipeCardLogin.jtf1.getText();
				String selectWorkShopNo = adaptee.comboBox1.getSelectedItem().toString();
				SwipeCard swipe = new SwipeCard(selectWorkShopNo);
			//	System.out.println("WorkShopNo: " + selectWorkShopNo);
			} else {
				JOptionPane.showMessageDialog(adaptee, "此卡無管理員權限");
				System.out.println("此管理员不存在");// 打印结果
			}
		} else {
			JOptionPane.showMessageDialog(adaptee, "不合法卡號");
			System.out.println("不合法卡號，含有非數字字符或卡號長度不正確");
		}
	}
}
