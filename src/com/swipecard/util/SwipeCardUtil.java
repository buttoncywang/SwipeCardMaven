package com.swipecard.util;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.swipecard.DateGet;
import com.swipecard.model.User;

public class SwipeCardUtil {

	
	public String SwipeCardRecord(){
		
		return null;
	}
	
	public String OnDutyOrOffDutySwipeRecord(SqlSession session, User eif, String CardID, String curShift,
			String curClassDesc,String WorkshopNo){
		String id = eif.getId();
		String swipeCardTime = DateGet.getTime();
		//String WorkshopNo = textT1_1.getText();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("CardID", CardID);
		param.put("WorkshopNo", WorkshopNo);
		param.put("Shift", curShift);
		param.put("SwipeCardTime", swipeCardTime);

		User curDayGoWorkCardCount = (User) session.selectOne("selectCountAByCardID", param);
		// 無刷卡記錄
		if (curDayGoWorkCardCount.getRowsa() == 0) {
			OnDutySwipeCard(session, eif, CardID, curShift, curClassDesc);

		} else if (curDayGoWorkCardCount.getRowsa() > 0) {

			User isGoWorkSwipeDuplicate = (User) session.selectOne("isGoWorkSwipeDuplicate", param);
//			if (isGoWorkSwipeDuplicate.getGoWorkCount() > 0) {
//				OnDutySwipeCardDuplicate(session, eif, CardID, curShift);
//			} else {
//				// 下班刷卡
//				OffDutySwipeCard(session, eif, CardID, curShift, curClassDesc);
//			}
		}
		return null;
	}
	
	public String OnDutySwipeCard(SqlSession session, User eif, String CardID, String curShift, String curClassDesc){
	
		return null;
	}
	
	public void OffDutySwipeCard(SqlSession session, User eif, String CardID, String Shift, 
			String ClassDesc,String WorkshopNo,String RC_NO,String PRIMARY_ITEM_NO){
//		String swipeCardTime = DateGet.getTime();
//		//String WorkshopNo = textT1_1.getText();
//		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("CardID", CardID);
//		param.put("WorkshopNo", WorkshopNo);
//		param.put("Shift", Shift);
//		param.put("SwipeCardTime2", swipeCardTime);
//
//		User curDayOutWorkCardCount = (User) session.selectOne("selectCountBByCardID", param);
//
//		if (curDayOutWorkCardCount.getRowsb() > 0) {
//			User isOutWoakSwipeDuplicate = (User) session.selectOne("isOutWorkSwipeDuplicate", param);
//			if (isOutWoakSwipeDuplicate.getOutWorkCount() > 0) {
//
//				OffDutySwipeCardDuplicate(session, eif, CardID, Shift,WorkshopNo,RC_NO,PRIMARY_ITEM_NO);
//
//			} else {
//				jtextT1_1.setBackground(Color.WHITE);
//				jtextT1_1.append("ID: " + eif.getId() + " Name: " + eif.getName() + "\n" + "今日上下班卡已刷，此次刷卡無效！\n\n");
//			}
//		} else if (curDayOutWorkCardCount.getRowsb() == 0) {
//			jtextT1_1.setBackground(Color.WHITE);
//			jtextT1_1.setText("下班刷卡\n" + "ID: " + eif.getId() + "\nName: " + eif.getName() + "\n刷卡時間： " + swipeCardTime
//					+ "\n" + "\n班別： " + ClassDesc + "員工下班刷卡成功！\n------------\n");
//			User user1 = new User();
//			user1.setSwipeCardTime2(swipeCardTime);
//			user1.setCardID(CardID);
//			user1.setShift(Shift);
//			user1.setWorkshopNo(WorkshopNo);
//			session.update("updateOutWorkDSwipeTime", user1);
//			session.commit();
//		}
	}
	
	public void OnDutySwipeCardDuplicate(SqlSession session, User eif, 
			String CardID, String curShift,String WorkshopNo,String RC_NO,String PRIMARY_ITEM_NO){
		String swipeCardTime = DateGet.getTime();
		//String WorkshopNo = textT1_1.getText();
		String name = eif.getName();
		String Id = eif.getId();

		User userSwipeDup = new User();
		// String shift = "D";
		userSwipeDup.setCardID(CardID);
		userSwipeDup.setName(name);
		userSwipeDup.setId(Id);
		userSwipeDup.setSwipeCardTime(swipeCardTime);
		userSwipeDup.setRC_NO(RC_NO);
		userSwipeDup.setPRIMARY_ITEM_NO(PRIMARY_ITEM_NO);
		userSwipeDup.setWorkshopNo(WorkshopNo);
		userSwipeDup.setShift(curShift);
		try{
			session.insert("goWorkSwipeDuplicate", userSwipeDup);
			session.commit();
		}
		catch(Exception ex){
			System.out.println(ex.toString());
		}
	}
	
	public void OffDutySwipeCardDuplicate(SqlSession session, User eif, 
			String CardID, String curShift,String WorkshopNo,String RC_NO,String PRIMARY_ITEM_NO){
		String swipeCardTime2 = DateGet.getTime();
		String name = eif.getName();
		String Id = eif.getId();

		User userSwipeDup = new User();
		// String shift = "D";
		userSwipeDup.setCardID(CardID);
		userSwipeDup.setName(name);
		userSwipeDup.setId(Id);
		userSwipeDup.setSwipeCardTime2(swipeCardTime2);
		userSwipeDup.setRC_NO(RC_NO);
		userSwipeDup.setPRIMARY_ITEM_NO(PRIMARY_ITEM_NO);
		userSwipeDup.setWorkshopNo(WorkshopNo);
		userSwipeDup.setShift(curShift);
		try{
			session.insert("outWorkSwipeDuplicate", userSwipeDup);
			session.commit();
		}
		catch(Exception ex){
			System.out.println(ex.toString());
		}
	}
}
