package com.swipecard.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.swipecard.DateGet;

public class JsonFileUtil {

	public static boolean createJsonFile(String jsonString, String fileName) {
		boolean flag = true;
		// File.separator如果文件路径考虑跨平台,将分隔符用File.separator 代替
		// String filePath = System.getProperty("user.dir");
		String filePath = "D:/swipeCard/logs/";
		String WorkshopNoSavePath = filePath + fileName;
		try {
			File file = new File(WorkshopNoSavePath);
			if (!file.getParentFile().exists()) { // 如果父目录不存在，创建父目录
				file.getParentFile().mkdirs();
			}
			if (file.exists()) { // 如果已存在,删除旧文件
				file.delete();
			}
			file.createNewFile();

			jsonString = JsonFormatTool.formatJson(jsonString);

			Writer write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			write.write(jsonString);
			write.flush();
			write.close();
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	public Object[] getWorkshopNoByJson() {
		String filePath = "D:/swipeCard/logs/";
		String fileName = "WorkshopNo.json";
		String workshopNoSavePath = filePath + fileName;
		File file = new File(workshopNoSavePath);

		Object[] a = null;
		BufferedReader brRread = null;
		String workshopNoStr = "";
		JSONArray workshopNoJsonArray;
		try {
			if (!file.exists()) {
				a = new Object[1];
				a[0] = "";
			} else {
				InputStreamReader streamReader = new InputStreamReader(new FileInputStream(file), "UTF-8");
				 brRread = new BufferedReader(streamReader);
			//	brRread = new BufferedReader(new FileReader(workshopNoSavePath));
				String tempString = null;
				while ((tempString = brRread.readLine()) != null) {
					workshopNoStr += tempString;
				}
				workshopNoJsonArray = new JSONArray(workshopNoStr);
				if (workshopNoJsonArray.length() > 0) {
					a = new Object[workshopNoJsonArray.length()];
					for (int i = 0; i < workshopNoJsonArray.length(); i++) {
						JSONObject workshopNoJson = workshopNoJsonArray.getJSONObject(i);
					//	System.out.println(workshopNoJson);
						a[i] = workshopNoJson.get("workshopNo");
					}
				}
				brRread.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (brRread != null) {
				try {
					brRread.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return a;
	}
	
	public JSONObject getSwipeCardRecordByJson() {
		String filePath = "D:/swipeCard/logs/";
		String fileName = "swipeCardRecord" + DateGet.getDate() + ".json";
		String swipeCardRecordPath = filePath + fileName;
		File file = new File(swipeCardRecordPath);

		BufferedReader brRread = null;
		String swipeCardRecordStr = "";
		JSONObject swipeCardRecordJson = null;	
		try {
			if (!file.exists()) {
				System.out.println("本地log無刷卡記錄!");
			} else {
				InputStreamReader streamReader = new InputStreamReader(new FileInputStream(file), "UTF-8");
				 brRread = new BufferedReader(streamReader);
				String tempString = null;
				while ((tempString = brRread.readLine()) != null) {
					swipeCardRecordStr += tempString;
				}
				swipeCardRecordJson = new JSONObject(swipeCardRecordStr);
							
				brRread.close();
				
			//	file.renameTo(new File(filePath+"swipeCardRecord" + DateGet.getDate()+"_"+new Date().getHours() + ".json"));  
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (brRread != null) {
				try {
					brRread.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return swipeCardRecordJson;
	}

}
