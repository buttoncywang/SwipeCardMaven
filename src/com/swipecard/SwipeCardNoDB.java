package com.swipecard;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.swipecard.util.JsonFileUtil;
import com.swipecard.util.PingMySqlUtil;
import com.swipecard.swipeRecordLog.SwipeRecordLogToDB;

public class SwipeCardNoDB extends JFrame {
	private final static String CurrentVersion="V20170907";
	private static final Timer nowTime = new Timer();
	private Vector<Vector<Object>> rowData = new Vector<Vector<Object>>();
	private int count = 0;
	private String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private String time;
	private int ONE_SECOND = 1000;

	static JTabbedPane tabbedPane;
	static JLabel label1, label3, swipeTimeLable, curTimeLable;
	static JPanel panel1;
	static ImageIcon image;
	static JLabel labelT1_1, labelT1_3;
	static MyJButton butT1_5, butT1_6;
	static JTextArea jtextT1_1, jtextT1_2;
	static TextField textT1_3, textT1_1;
	static JScrollPane jspT1_1, JspTable, myScrollPane;
	Textc textc = null;
	static JComboBox comboBox1;
	static JTextField jtf1;

	/**
	 * Timer task to update the time display area
	 *
	 */

	protected class JLabelTimerTask extends TimerTask {
		SimpleDateFormat dateFormatter = new SimpleDateFormat(DEFAULT_TIME_FORMAT);

		@Override
		public void run() {
			time = dateFormatter.format(Calendar.getInstance().getTime());
			curTimeLable.setText(time);
		}
	}

	protected class CheckDBLinkTimerTask extends TimerTask {

		@Override
		public void run() {
			PingMySqlUtil PingUtil = new PingMySqlUtil();
		    String ipAddress = "10.72.0.179";
	        try {
				 // System.out.println(PingUtil.ping(ipAddress));
				 // PingUtil.ping02(ipAddress);
			       System.out.println(PingUtil.ping(ipAddress, 5, 5000));
			        
			       // String selectWorkShopNo = comboBox1.getSelectedItem().toString();
			        String selectWorkShopNo = jtf1.getText();
					if(PingUtil.ping(ipAddress, 5, 5000))
					{
						SwipeRecordLogToDB logToDB=new SwipeRecordLogToDB();
						logToDB.SwipeRecordLogToDB();
						dispose();
						SwipeCard swipe = new SwipeCard(selectWorkShopNo);
					    this.cancel();
					}
					
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      
		}

	}

	
	
	public SwipeCardNoDB(String workshopNoWithDB) {

		super("產線端刷卡無DB模式"+CurrentVersion);
		setBounds(12, 84, 1000, 630);
		setResizable(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		Container c = getContentPane();
		tabbedPane = new JTabbedPane(JTabbedPane.LEFT); // 创建选项卡面板对象

		panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBackground(Color.WHITE);

		textT1_1 = new TextField(15);// 車間
		textT1_1.setFont(new Font("微软雅黑", Font.PLAIN, 25));

		textT1_3 = new TextField(15);// 上班
		textT1_3.setFont(new Font("微软雅黑", Font.PLAIN, 25));

		jtextT1_1 = new JTextArea();// 刷卡人員信息,JTextArea(int rows, int columns)
		jtextT1_1.setBackground(Color.WHITE);
		jtextT1_2 = new JTextArea();// 備註

		// text3 = new JTextArea(2, 20);

		labelT1_1 = new JLabel("車間:");
		labelT1_1.setFont(new Font("微软雅黑", Font.BOLD, 25));

		labelT1_3 = new JLabel("刷卡:");
		labelT1_3.setFont(new Font("微软雅黑", Font.BOLD, 25));

		JsonFileUtil jsonFileUtil = new JsonFileUtil();
		final Object[] WorkshopNo = jsonFileUtil.getWorkshopNoByJson();
		comboBox1 = new JComboBox(WorkshopNo);
		comboBox1.setEditable(true);
		comboBox1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		if(workshopNoWithDB!=null){
			comboBox1.setSelectedItem(workshopNoWithDB);
		}

		jtf1 = (JTextField) comboBox1.getEditor().getEditorComponent();

		Timer tmr = new Timer();
		tmr.scheduleAtFixedRate(new JLabelTimerTask(), new Date(), ONE_SECOND);

		final Timer checkDBLinktmr = new Timer();
		checkDBLinktmr.scheduleAtFixedRate(new CheckDBLinkTimerTask(),15 * 60 * 1000, 15 * 60 * 1000);
		
		curTimeLable = new JLabel();
		curTimeLable.setFont(new Font("微软雅黑", Font.BOLD, 35));

		swipeTimeLable = new JLabel();
		swipeTimeLable.setFont(new Font("微软雅黑", Font.BOLD, 35));

		int x1 = 15, x2 = 100, x3 = 200, x4 = 400, x5 = 130, x6 = 460, x7 = 90;
		int y1 = 40, y4 = 180;

		labelT1_1.setBounds(x1 + 20, y1, x7, y1);
		labelT1_3.setBounds(x1 + 20, 2 * y1 + 20, x7, y1);

		// textT1_1.setBounds(x1 + x7, 1 * y1, y4 + 100, y1);
		comboBox1.setBounds(x1 + x7, 1 * y1, y4 + 100, y1);
		textT1_3.setBounds(x1 + x7, 2 * y1 + 20, y4 + 100, y1);

		jtextT1_2.setBounds(x1 + x7, 9 * y1, x4, y1);

		swipeTimeLable.setBounds(400, y1, x4, 50);
		curTimeLable.setBounds(x1 + 10, 3 * y1 + 40, 400, 50);

		jspT1_1 = new JScrollPane(jtextT1_1);
		jspT1_1.setBounds(400, 2 * y1 + 20, x4, 250);

		int cc = 240;
		Color d = new Color(cc, cc, cc);// 这里可以设置颜色的rgb

		// 将标签面板加入到选项卡面板对象上
		tabbedPane.addTab("無DB刷卡界面", null, panel1, "First panel");
		tabbedPane.setSelectedIndex(0); // 设置默认选中的
		this.setVisible(true);

		textT1_1.setEditable(false);
		textT1_3.setEditable(true);
		// 使用swing的线程做獲取焦點的界面绘制，避免获取不到的情况。
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				textT1_3.requestFocusInWindow();
			}
		});

		jtextT1_1.setEditable(false);
		jtextT1_2.setEditable(false);

		jtextT1_1.setLineWrap(true);
		jtextT1_2.setLineWrap(true);

		// textT1_3.setBackground(Color.GRAY);
		jtextT1_2.setBackground(d);

		butT1_5 = new MyJButton("登出(切換帳號)", 2);
		butT1_6 = new MyJButton("退出程式", 2);

		butT1_5.setBounds(x6, 350 + y1 + 20, x5, y1);
		butT1_6.setBounds(x6 + 160, 350 + y1 + 20, x5, y1);
		panel1.add(comboBox1);
		// panel1.add(textT1_1);
		panel1.add(textT1_3);

		panel1.add(labelT1_1);
		panel1.add(labelT1_3);
		panel1.add(swipeTimeLable);
		panel1.add(curTimeLable);

		panel1.add(jspT1_1);

		panel1.add(butT1_5);
		panel1.add(butT1_6);

		butT1_5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				InitGlobalFont(new Font("微软雅黑", Font.BOLD, 18));
				checkDBLinktmr.cancel();
				dispose();
				SwipeCardLogin d = new SwipeCardLogin();
			}
		});

		butT1_6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO
				System.exit(0);
			}
		});

		// TODO 刷卡模式
		textT1_3.addTextListener(new TextListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void textValueChanged(TextEvent e) {

				String CardID = textT1_3.getText();

				// text1.setText("");

				String swipeCardTime = DateGet.getTime();
				//String selectWorkShopNo = comboBox1.getSelectedItem().toString();
				String selectWorkShopNo = jtf1.getText();
				JSONObject swipeCardRecord = new JSONObject();

				// 驗證是否為10位整數，是則繼續執行，否則提示
				if (CardID.length() > 10) {
					jtextT1_1.setBackground(Color.WHITE);
					jtextT1_1.setText("卡號輸入有誤，請再次刷卡\n");
					textT1_3.setText("");
				} else {
					String pattern = "^[0-9]\\d{9}$";
					Pattern r = Pattern.compile(pattern, Pattern.DOTALL);
					Matcher m = r.matcher(CardID);
					if (m.matches() == true) {
						try {
							swipeTimeLable.setText(swipeCardTime);
							swipeCardRecord.put("WorkshopNo", selectWorkShopNo);
							// String filePath = System.getProperty("user.dir");
							String filePath = "D:/swipecard/logs/";
							String fileName = "swipeCardRecord" + DateGet.getDate() + ".json";
							String swipeCardRecordSavePath = filePath + fileName;
							File file = new File(swipeCardRecordSavePath);
							JSONArray swipeCardData = new JSONArray();
							JSONObject swipeData = new JSONObject();

							swipeData.put("CardID", CardID);
							swipeData.put("swipeCardTime", swipeCardTime);
							swipeCardData.put(swipeData);
							swipeCardRecord.put("SwipeData", swipeCardData);

							if (!file.getParentFile().exists()) {
								file.getParentFile().mkdirs();
							}
							if (!file.exists()) {
								file.createNewFile();
								// String jsonString =
								// JsonFormatTool.formatJson(swipeCardRecord.toString());
								BufferedWriter write = new BufferedWriter(new FileWriter(file));
								write.write(swipeCardRecord.toString());
								write.flush();
								write.close();

							} else {
								BufferedReader br = new BufferedReader(new FileReader(swipeCardRecordSavePath));// 读取原始json文件
								String s = null, ws = null;

								while ((s = br.readLine()) != null) {
									//System.out.println(s);
									JSONObject dataJson = new JSONObject(s);// 创建一个包含原始json串的json对象
									JSONArray SwipeDataArray = dataJson.getJSONArray("SwipeData");// 找到SwipeData的json数组
									SwipeDataArray.put(swipeData);
									swipeCardRecord.put("SwipeData", SwipeDataArray);
								}

								BufferedWriter bw = new BufferedWriter(new FileWriter(swipeCardRecordSavePath));// 输出新的json文件
								bw.write(swipeCardRecord.toString());
								bw.flush();
								br.close();
								// System.out.println("swipeCardRecord:" +
								// swipeCardRecord);
							}
							jtextT1_1.setBackground(Color.WHITE);
							jtextT1_1.setText("卡號為:" + CardID + "的員工\n" + swipeCardTime + "刷卡成功！\n");

						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (JSONException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} finally {
							textT1_3.setText("");
						}
						textT1_3.setText("");
					} else {
						System.out.println("無輸入內容或輸入錯誤!");
					}
				}
			}
		});

		c.add(tabbedPane);
		c.setBackground(Color.lightGray);

		// textT1_1.setText(WorkshopNo);// 綁定車間
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		String WorkShopNo = "FD1Q3F1";
		// JLabelA d = new JLabelA(WorkShopNo, LineNo);
		SwipeCardNoDB d = new SwipeCardNoDB(null);
	}

}
