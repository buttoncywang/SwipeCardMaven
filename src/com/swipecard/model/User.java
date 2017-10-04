package com.swipecard.model;

import java.sql.Timestamp;

public class User {
	// Table:testemployee
	private String Id;
	private String Name;
	private String depid;
	private String depname;
	private String CardID;
	// table:
	private String RC_NO;
	private String PRIMARY_ITEM_NO;
	private String STD_MAN_POWER;
	private String PROD_LINE_CODE;
	private String ACTUAL_POWER;
	private String REMARK;
	
	// Table:lineno
	private String LineNo;
	private String WorkshopNo;

	// Table:testEmployeeTime
	private String SwipeCardTime;
	private String SwipeCardTime2;
	private String Shift;
	private String curDateTime;
	
	//Table:lost_employee
	private String SwipeDate;
	private int lostCon;
	
	//Table：emp_class和 classno
	private String class_desc;
	private Timestamp class_start;
	private Timestamp class_end;

	private int rowsa;
	private int rowsb;
	private int rowsc;
	private int rowsd;
	private int rowse;
	private int rowsf;
	private int rowsg;
	private int goWorkCount;
	private int outWorkCount;
	private int curShiftCount;
	private int yesShiftCount;	
	
	private int fillRows;

	
	public String getId() {
		return Id;
	}

	public void setId(String Id) {
		this.Id = Id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDepid() {
		return depid;
	}

	public void setDepid(String depid) {
		this.depid = depid;
	}

	public String getDepname() {
		return depname;
	}

	public void setDepname(String depname) {
		this.depname = depname;
	}

	public String getCardID() {
		return CardID;
	}

	public void setCardID(String cardID) {
		CardID = cardID;
	}

	public void setSwipeCardTime(String swipeCardTime) {
		this.SwipeCardTime = swipeCardTime;
	}

	public String getSwipeCardTime() {
		return SwipeCardTime;
	}

	public int getRowsa() {
		return rowsa;
	}

	public void setRowsa(int rowsa) {
		this.rowsa = rowsa;
	}

	public int getRowsb() {
		return rowsb;
	}

	public void setRowsb(int rowsb) {
		this.rowsb = rowsb;
	}

	public void setRC_NO(String rC_NO) {
		RC_NO = rC_NO;
	}

	public String getRC_NO() {
		return RC_NO;
	}

	public String getPRIMARY_ITEM_NO() {
		return PRIMARY_ITEM_NO;
	}

	public void setPRIMARY_ITEM_NO(String pRIMARY_ITEM_NO) {
		PRIMARY_ITEM_NO = pRIMARY_ITEM_NO;
	}

	public String getSTD_MAN_POWER() {
		return STD_MAN_POWER;
	}

	public void setSTD_MAN_POWER(String sTD_MAN_POWER) {
		STD_MAN_POWER = sTD_MAN_POWER;
	}

	public String getPROD_LINE_CODE() {
		return PROD_LINE_CODE;
	}

	public void setPROD_LINE_CODE(String pROD_LINE_CODE) {
		PROD_LINE_CODE = pROD_LINE_CODE;
	}

	public void setRowsc(int rowsc) {
		this.rowsc = rowsc;
	}

	public int getRowsc() {
		return rowsc;
	}

	public String getACTUAL_POWER() {
		return ACTUAL_POWER;
	}

	public void setACTUAL_POWER(String aCTUAL_POWER) {
		ACTUAL_POWER = aCTUAL_POWER;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}

	public String getLineNo() {
		return LineNo;
	}

	public void setLineNo(String lineNo) {
		LineNo = lineNo;
	}

	public String getWorkshopNo() {
		return WorkshopNo;
	}

	public void setWorkshopNo(String workshopNo) {
		WorkshopNo = workshopNo;
	}

	public void setSwipeCardTime2(String swipeCardTime2) {
		SwipeCardTime2 = swipeCardTime2;
	}

	public String getSwipeCardTime2() {
		return SwipeCardTime2;
	}

	public void setFillRows(int fillRows) {
		this.fillRows = fillRows;
	}

	public int getFillRows() {
		return fillRows;
	}

	public void setSwipeDate(String swipeDate) {
		SwipeDate = swipeDate;
	}

	public String getSwipeDate() {
		return SwipeDate;
	}

	public int getLostCon() {
		return lostCon;
	}

	public void setLostCon(int lostCon) {
		this.lostCon = lostCon;
	}

	public String getShift() {
		return Shift;
	}

	public void setShift(String shift) {
		Shift = shift;
	}

	public String getCurDateTime() {
		return curDateTime;
	}

	public void setCurDateTime(String curDateTime) {
		this.curDateTime = curDateTime;
	}

	public String getClass_desc() {
		return class_desc;
	}

	public void setClass_desc(String class_desc) {
		this.class_desc = class_desc;
	}

	public int getGoWorkCount() {
		return goWorkCount;
	}

	public void setGoWorkCount(int goWorkCount) {
		this.goWorkCount = goWorkCount;
	}

	public int getOutWorkCount() {
		return outWorkCount;
	}

	public void setOutWorkCount(int outWorkCount) {
		this.outWorkCount = outWorkCount;
	}


	public int getRowsd() {
		return rowsd;
	}

	public void setRowsd(int rowsd) {
		this.rowsd = rowsd;
	}

	public int getCurShiftCount() {
		return curShiftCount;
	}

	public void setCurShiftCount(int curShiftCount) {
		this.curShiftCount = curShiftCount;
	}

	public int getYesShiftCount() {
		return yesShiftCount;
	}

	public void setYesShiftCount(int yesShiftCount) {
		this.yesShiftCount = yesShiftCount;
	}

	public Timestamp getClass_start() {
		return class_start;
	}

	public void setClass_start(Timestamp class_start) {
		this.class_start = class_start;
	}

	public Timestamp getClass_end() {
		return class_end;
	}

	public void setClass_end(Timestamp class_end) {
		this.class_end = class_end;
	}

	public int getRowse() {
		return rowse;
	}

	public void setRowse(int rowse) {
		this.rowse = rowse;
	}

	public int getRowsf() {
		return rowsf;
	}

	public void setRowsf(int rowsf) {
		this.rowsf = rowsf;
	}

	public int getRowsg() {
		return rowsg;
	}

	public void setRowsg(int rowsg) {
		this.rowsg = rowsg;
	}

}