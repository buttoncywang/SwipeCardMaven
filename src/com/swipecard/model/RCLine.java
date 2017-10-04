package com.swipecard.model;

import java.sql.Timestamp;

public class RCLine {
	private String RC_Record_ID;
	private String RC_NO;
	private String PRIMARY_ITEM_NO;
	private String STD_MAN_POWER;
	private String PROD_LINE_CODE;
	private Timestamp CUR_DATE;
	
	public String getRC_Record_ID() {
		return RC_Record_ID;
	}
	public void setRC_Record_ID(String rC_Record_ID) {
		RC_Record_ID = rC_Record_ID;
	}
	public String getRC_NO() {
		return RC_NO;
	}
	public void setRC_NO(String rC_NO) {
		RC_NO = rC_NO;
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
	public Timestamp getCUR_DATE() {
		return CUR_DATE;
	}
	public void setCUR_DATE(Timestamp cUR_DATE) {
		CUR_DATE = cUR_DATE;
	}
	

}
