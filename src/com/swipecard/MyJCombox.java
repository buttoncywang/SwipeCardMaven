package com.swipecard;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Reader;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.swipecard.model.User;

public class MyJCombox extends JComboBox {
	final Object[] str1 = getItems();
	private static JComboBox comboBox;
	static JTextField jtf;
	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;
	static {
		try {
			reader = Resources.getResourceAsReader("Configuration.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSession() {
		return sqlSessionFactory;
	}

	public MyJCombox() {

		comboBox = new JComboBox(str1);
		comboBox.setEditable(true);

		comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		jtf = (JTextField) comboBox.getEditor().getEditorComponent();

		
		
		
		jtf.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
				System.out.println("jtfListen->");
				String key = jtf.getText();
				comboBox.removeAllItems();
				// for (Object item : getItems()) {
				for (Object item : str1) {
					// 这里是以key開頭的项目都筛选出来0313240578

					// 可以把contains改成startsWith就是筛选以key开头的项目
					// contains(key)/startsWith(key)
					if (((String) item).startsWith(key)) {
						comboBox.addItem(item);
					}
				}
				jtf.setText(key);
			}

			public void keyPressed(KeyEvent e) {
			}
		});

	}

	private static Object[] getItems() {
		List<User> user;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			user = session.selectList("selectRCNo");
			// System.out.println(user.size());
			int con = user.size();
			Object[] a = new Object[con];
			a[0] = "";
			for (int i = 0; i < con - 1; i++) {
				// System.out.println(user.get(i).getRC_NO());
				a[i + 1] = user.get(i).getRC_NO();
				// a.add(user.get(i).getRC_NO());
			}
			final Object[] s = a;
			return a;
		} finally {
			if (session != null) {
				session.close();
			}
			// System.out.println("123"); 小米手環2
		}
	}

	public static void main(String[] args) {
		JFrame frm = new JFrame();
		MyJCombox comboBox1 = new MyJCombox();
		frm.setLayout(null);
		frm.setBounds(200, 100, 800, 600);
		comboBox1.setBounds(30, 30, 200, 30);
		frm.add(comboBox1);
		frm.setDefaultCloseOperation(3);
		frm.setVisible(true);
	}

}
