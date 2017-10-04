package com.swipecard.model;

import java.sql.Timestamp;

/* *
 * selectUserByLineNoAndWorkshopNo  selectUserByLineNoAndWorkshopNo_DShift 
 * selectUserByLineNoAndWorkshopNo_NShift selectUserByLastDay
 * */
public class EmpSwipeInfos {
	private String CardID;
	private String Id;
	private String Name;
	private String RC_NO;
	private Timestamp SwipeCardTime;
	private Timestamp SwipeCardTime2;
	public String getCardID() {
		return CardID;
	}
	public void setCardID(String cardID) {
		CardID = cardID;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
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
	public String getRC_NO() {
		return RC_NO;
	}
	public void setRC_NO(String rC_NO) {
		RC_NO = rC_NO;
	}
	public Timestamp getSwipeCardTime2() {
		return SwipeCardTime2;
	}
	public void setSwipeCardTime2(Timestamp swipeCardTime2) {
		SwipeCardTime2 = swipeCardTime2;
	}
}
