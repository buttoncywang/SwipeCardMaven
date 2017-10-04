package com.swipecard;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class CheckBoxDemo extends JPanel implements ItemListener {
	JCheckBox chin;
	JCheckBox glass;
	JCheckBox hire;
	JCheckBox teeth;
	StringBuffer choice;
	JLabel labelPicture;

	public CheckBoxDemo() {
		super(new BorderLayout());
		// 创建复选框按键，并设置快捷键，和选定
		chin = new JCheckBox("chin");
		chin.setMnemonic(KeyEvent.VK_C);
		chin.setSelected(true);
		glass = new JCheckBox("glass");
		glass.setMnemonic(KeyEvent.VK_G);
		glass.setSelected(true);
		hire = new JCheckBox("hire");
		hire.setMnemonic(KeyEvent.VK_H);
		hire.setSelected(true);
		teeth = new JCheckBox("teeth");
		teeth.setMnemonic(KeyEvent.VK_T);
		teeth.setSelected(true);
		// 设置一个panel，将复选框放入同一个panel
		JPanel checkPanel = new JPanel(new GridLayout(0, 1));
		checkPanel.add(chin);
		checkPanel.add(glass);
		checkPanel.add(hire);
		checkPanel.add(teeth);
		// 添加复选框的监听事件 chin.addItemListener(this);
		glass.addItemListener(this);
		hire.addItemListener(this);
		teeth.addItemListener(this);
		// 将复选框的panel添加到面板的左边
		add(checkPanel, BorderLayout.WEST);
		// 创建图片显示区
		labelPicture = new JLabel();
		// 将图片显示区放到面板中间
		add(labelPicture, BorderLayout.CENTER);
		// 设置面板的边界，使得控件能够与边界有一定距离
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		// 设置初始图片
		choice = new StringBuffer("cght");
		// 显示更新图片
		upDatePicture();
	}

	private void upDatePicture() {
		ImageIcon ii = createImageIcon(File.separator + "images"
				+ File.separator + "geek" + File.separator + "geek-"
				+ choice.toString() + ".gif");
		labelPicture.setIcon(ii);
	}

	private ImageIcon createImageIcon(String string) {
		URL url = CheckBoxDemo.class.getResource(string);
		if (url != null)
			return new ImageIcon(url);
		else
			System.out.println("image " + string + " not exist!");
		return null;
	}

	public static void createAndShowGUI() {
		JFrame frame = new JFrame("复选框测试");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JComponent panel = new CheckBoxDemo();
		frame.add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createAndShowGUI();
			}
		});
	} // 接受处理复选框点击事件

	@Override
	public void itemStateChanged(ItemEvent e) { // 获取改变的复选按键
		Object source = e.getItemSelectable();
		int index = -1;
		char c = '-';
		if (source == chin) {
			index = 0;
			c = 'c';
		} else if (source == glass) {
			index = 1;
			c = 'g';
		} else if (source == hire) {
			index = 2;
			c = 'h';
		} else if (source == teeth) {
			index = 3;
			c = 't';
		} // 判断改变的按键的改变后的状态
		if (e.getStateChange() == ItemEvent.DESELECTED)
			c = '-';
		choice.setCharAt(index, c);
		upDatePicture();
	}
}