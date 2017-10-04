package com.swipecard.model;

import java.sql.Date;
import java.sql.Timestamp;

/*
 * For getYesdayShiftByEmpId  getCurShiftByEmpId
 * */
public class EmpShiftInfos {
	private String Id;
	private Date EmpDate;
	private String ClassNo;
	private String ClassDesc;
	private String Shift;
	private Timestamp ClassStart;
	private Timestamp ClassEnd;
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	
	public Date getEmpDate() {
		return EmpDate;
	}
	public void setEmpDate(Date empDate) {
		EmpDate = empDate;
	}
	public String getClassNo() {
		return ClassNo;
	}
	public void setClassNo(String classNo) {
		ClassNo = classNo;
	}
	public String getClassDesc() {
		return ClassDesc;
	}
	public void setClassDesc(String classDesc) {
		ClassDesc = classDesc;
	}
	public String getShift() {
		return Shift;
	}
	public void setShift(String shift) {
		Shift = shift;
	}
	public Timestamp getClassStart() {
		return ClassStart;
	}
	public void setClassStart(Timestamp classStart) {
		ClassStart = classStart;
	}
	public Timestamp getClassEnd() {
		return ClassEnd;
	}
	public void setClassEnd(Timestamp classEnd) {
		ClassEnd = classEnd;
	}
}
