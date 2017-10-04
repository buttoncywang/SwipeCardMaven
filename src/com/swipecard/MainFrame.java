package com.swipecard;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends JFrame {
    
    
    private JMenuBar jmb ;
    private JLabel nameLbl;
    private JLabel sexLbl;
    private JLabel ageLbl;
    private JLabel classLbl;
    private JLabel photoLbl;
    
    private JButton selectJbt;
    private JButton deleteJbt;
    private JButton udpateJbt;
    private JButton insertJbt;
    private JRadioButton maleJrb;
    private JRadioButton femaleJrb;
    private ButtonGroup bg;
    
    private JTextField nameJtf;
    private JTextField ageJtf;
    private JTextField classJtf;
    private JTextField searchJtf;
    
    
    private JTable jtbl;
    private DefaultTableModel dtm; //表格用的数据模型
    private JScrollPane jsp;//存放表格的，表格必须放在里面
    
    
    public MainFrame(){
        
        init();
        
        this.setBounds(100, 100,600,480);
        this.setTitle("学生信息管理");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
    }
    public void init(){
        this.setLayout(null);
        this.add(this.getNameLbl());
        this.add(this.getNameJtf());
        this.add(this.getSexLbl());
        this.getBg();
        this.add(this.getMaleJrb());
        this.add(this.getFemaleJrb());
        this.add(this.getPhotoLbl());
        this.add(this.getAgeLbl());
        this.add(this.getAgeJtf());
        this.add(this.getClassLbl());
        this.add(this.getClassJtf());
        this.add(this.getUdpateJbt());
        this.add(this.getJsp());
    }
    
    
    
    public JMenuBar getJmb() {
        return jmb;
    }
    public void setJmb(JMenuBar jmb) {
        this.jmb = jmb;
    }
    //-------------------------------------name------------------------
    public JLabel getNameLbl() {
        if(nameLbl==null){
            nameLbl = new JLabel("姓名:");
            nameLbl.setBounds(30, 30, 50, 30);
        }
        return nameLbl;
    }
    public void setNameLbl(JLabel nameLbl) {
        this.nameLbl = nameLbl;
    }
    public JLabel getSexLbl() {
        if(sexLbl == null){
            sexLbl = new JLabel("性别:");
            sexLbl.setBounds(250, 30, 50, 30);
        }
        return sexLbl;
    }
    public void setSexLbl(JLabel sexLbl) {
        this.sexLbl = sexLbl;
    }
    //-----------------------------------------age--------------------------
    public JLabel getAgeLbl() {
        if(ageLbl==null){
            ageLbl = new JLabel("年龄:");
            ageLbl.setBounds(30, 100, 50, 30);
        }
        return ageLbl;
    }
    public void setAgeLbl(JLabel ageLbl) {
        this.ageLbl = ageLbl;
    }
    //----------------------------------------class---------------------------------
    public JLabel getClassLbl() {
        if(classLbl == null){
            classLbl = new JLabel("班级:");
            classLbl.setBounds(250, 100, 50, 30);
        }
        return classLbl;
    }
    public void setClassLbl(JLabel classLbl) {
        this.classLbl = classLbl;
    }
    //-----------------------------------------photo------------------------------
    public JLabel getPhotoLbl() {
        if(photoLbl == null){
            photoLbl = new JLabel();
            photoLbl.setBounds(460, 30, 80, 80);
            setDefaultPhoto();//设置默认图片
        }
        return photoLbl;
    }

    public void setPhotoLbl(JLabel photoLbl) {
        this.photoLbl = photoLbl;
    }
    public JButton getSelectJbt() {
        return selectJbt;
    }
    public void setSelectJbt(JButton selectJbt) {
        this.selectJbt = selectJbt;
    }
    public JButton getDeleteJbt() {
        return deleteJbt;
    }
    public void setDeleteJbt(JButton deleteJbt) {
        this.deleteJbt = deleteJbt;
    }
    public JButton getUdpateJbt() {
        if(udpateJbt==null){
            udpateJbt = new JButton("修改");
            udpateJbt.setBounds(350, 150, 60, 30);
        }
        return udpateJbt;
    }
    public void setUdpateJbt(JButton udpateJbt) {
        this.udpateJbt = udpateJbt;
    }
    public JButton getInsertJbt() {
        return insertJbt;
    }
    public void setInsertJbt(JButton insertJbt) {
        this.insertJbt = insertJbt;
    }
    //------------------------------------------------namejtf----------------------
    public JTextField getNameJtf() {
        if(nameJtf==null){
            nameJtf = new JTextField();
            nameJtf.setBounds(80, 30, 120, 30);
        }
        return nameJtf;
    }
    public void setNameJtf(JTextField nameJtf) {
        this.nameJtf = nameJtf;
    }
    //---------------------------------------------agejtf------------------------------
    public JTextField getAgeJtf() {
        if(ageJtf==null){
            ageJtf = new JTextField();
            ageJtf.setBounds(80, 100, 120, 30);
        }
        return ageJtf;
    }
    public void setAgeJtf(JTextField ageJtf) {
        this.ageJtf = ageJtf;
    }
    public JTextField getClassJtf() {
        if(classJtf == null){
            classJtf = new JTextField();
            classJtf.setBounds(300, 100, 120, 30);
        }
        return classJtf;
    }
    public void setClassJtf(JTextField classJtf) {
        this.classJtf = classJtf;
    }
    public JTextField getSearchJtf() {
        return searchJtf;
    }
    public void setSearchJtf(JTextField searchJtf) {
        this.searchJtf = searchJtf;
    }
    //---------------------------------------------jtbl--------------------
    public JTable getJtbl() {
        if(jtbl == null){
            jtbl = new JTable(this.getDtm());
        }
        return jtbl;
    }
    public void setJtbl(JTable jtbl) {
        this.jtbl = jtbl;
    }
    public DefaultTableModel getDtm() {
        
        Vector<String> cols = new Vector<String>();
        cols.add("姓名");cols.add("性别");cols.add("年龄");cols.add("班级");cols.add("照片");
        //private Vector<Vector<Object>> rowData = new Vector<Vector<Object>>();
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
//        Vector<Object>
        StudentDao sd = new StudentDao();
        ArrayList<?> alist = sd.getAllStudent();
        for(Object obj : alist){
            Vector<Object> v = new Vector<Object>();
           // Student s = (Student)obj;
            v.add("test");
            v.add("test");
            v.add("test");
            v.add("test");
            v.add("test");
//            v.add(s.getSex());
//            v.add(s.getAge());
//            v.add(s.getClazz());
//            v.add(s.getPhoto());
            
            data.add(v);
        }
        dtm = new DefaultTableModel(data,cols);
        return dtm;
    }
    public void setDtm(DefaultTableModel dtm) {
        this.dtm = dtm;
    }
    //------------------------------------jsp------------------------
    public JScrollPane getJsp() {
        if(jsp == null){
            jsp = new JScrollPane(this.getJtbl());
            jsp.setBounds(0, 240, 600, 240);
        }
        return jsp;
    }
    public void setJsp(JScrollPane jsp) {
        this.jsp = jsp;
    }
    public static void main(String[] args) {
        
        new MainFrame();
        
    }
    public JRadioButton getMaleJrb() {
        if(maleJrb == null){
            maleJrb = new JRadioButton("男");
            maleJrb.setBounds(300, 30, 60, 30);
        }
        return maleJrb;
    }
    public void setMaleJrb(JRadioButton maleJrb) {
        this.maleJrb = maleJrb;
    }
    public JRadioButton getFemaleJrb() {
        if(femaleJrb == null){
            femaleJrb = new JRadioButton("女");
            femaleJrb.setBounds(360, 30, 60, 30);
        }
        return femaleJrb;
    }
    public void setFemaleJrb(JRadioButton femaleJrb) {
        this.femaleJrb = femaleJrb;
    }
    public ButtonGroup getBg() {
        if(bg == null){
            bg = new ButtonGroup();
            bg.add(this.getMaleJrb());bg.add(this.getFemaleJrb());
        }
        return bg;
    }
    public void setBg(ButtonGroup bg) {
        this.bg = bg;
    }
    
    private void setDefaultPhoto() {
        // TODO Auto-generated method stub
        getPhotoLbl().setIcon(new ImageIcon("src/qq2012.png"));
    }
}