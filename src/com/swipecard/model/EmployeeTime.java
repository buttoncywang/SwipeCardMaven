package com.swipecard.model;

import java.sql.Timestamp;

public class EmployeeTime {
	/*
	 *
  `CardID` char(10) NOT NULL,
  `Name` varchar(10) NOT NULL,
  `SwipeCardTime` datetime DEFAULT NULL,
  `SwipeCardTime2` datetime DEFAULT NULL,
  `CheckState` varchar(10) NOT NULL DEFAULT '0',
  `PROD_LINE_CODE` varchar(20) NOT NULL DEFAULT 'null',
  `WorkshopNo` char(30) NOT NULL,
  `PRIMARY_ITEM_NO` varchar(80) NOT NULL DEFAULT 'null',
  `RC_NO` varchar(80) NOT NULL DEFAULT 'null',
  `Shift` varchar(2) DEFAULT NULL,
  `OvertimeState` int(2) NOT NULL DEFAULT '0',
  `overtimeType` int(2) NOT NULL DEFAULT '0',
  `overtimeCal` int(2) NOT NULL DEFAULT '0',
	 * */
	
	private String CardID;
	private String Name;
	private Timestamp SwipeCardTime;
	private Timestamp SwipeCardTime2;
	private String CheckState;
	private String ProdLineCode;
	private String WorkshopNo;
	private String PrimaryItemNo;
	private String RCNO;
	private String Shift;
	private int OverTimeState;
	private int OverTimeType;
	private int OverTimeCal;
	
	public String getCardID() {
		return CardID;
	}
	public void setCardID(String cardID) {
		CardID = cardID;
	}
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	public Timestamp getSwipeCardTime() {
		return SwipeCardTime;
	}
	public void setSwipeCardTime(Timestamp swipeCardTime) {
		SwipeCardTime = swipeCardTime;
	}
	
	public Timestamp getSwipeCardTime2() {
		return SwipeCardTime2;
	}
	public void setSwipeCardTime2(Timestamp swipeCardTime2) {
		SwipeCardTime2 = swipeCardTime2;
	}
	
	public String getCheckState() {
		return CheckState;
	}
	public void setCheckState(String checkState) {
		CheckState = checkState;
	}
	public String getProdLineCode() {
		return ProdLineCode;
	}
	public void setProdLineCode(String prodLineCode) {
		ProdLineCode = prodLineCode;
	}
	public String getWorkshopNo() {
		return WorkshopNo;
	}
	public void setWorkshopNo(String workshopNo) {
		WorkshopNo = workshopNo;
	}
	public String getPrimaryItemNo() {
		return PrimaryItemNo;
	}
	public void setPrimaryItemNo(String primaryItemNo) {
		PrimaryItemNo = primaryItemNo;
	}
	public String getRCNO() {
		return RCNO;
	}
	public void setRCNO(String rCNO) {
		RCNO = rCNO;
	}
	public String getShift() {
		return Shift;
	}
	public void setShift(String shift) {
		Shift = shift;
	}
	public int getOverTimeState() {
		return OverTimeState;
	}
	public void setOverTimeState(int overTimeState) {
		OverTimeState = overTimeState;
	}
	public int getOverTimeType() {
		return OverTimeType;
	}
	public void setOverTimeType(int overTimeType) {
		OverTimeType = overTimeType;
	}
	public int getOverTimeCal() {
		return OverTimeCal;
	}
	public void setOverTimeCal(int overTimeCal) {
		OverTimeCal = overTimeCal;
	}
	

}
