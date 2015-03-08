package com.iwinner.rms.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.iwinner.rms.constants.RMSConstants;
import com.iwinner.rms.factory.ServiceFactory;
import com.iwinner.rms.model.ItemInfo;
import com.iwinner.rms.service.ItemServiceIF;
import com.iwinner.rms.service.UserItemServiceIF;

public class ReportUtils {
	public static Logger logger = Logger
			.getLogger(ReportUtils.class);
   private static ItemServiceIF itemServiceIF=null;
   private static UserItemServiceIF userItemServiceIF=null;
	public static HSSFWorkbook buildExcel(List<String> rowOfLogsInfo,	List<ItemInfo> listOfItemInfo) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		logger.info("Enter Into the buildExcel " + new Date());
		try {
			HSSFSheet sheet = wb.createSheet("ROOM Expen Report");
			HSSFCellStyle headstyle = wb.createCellStyle();
			HSSFCellStyle rowsstyle = wb.createCellStyle();
			HSSFFont fhead = wb.createFont();
			HSSFFont frows = wb.createFont();
			fhead.setFontHeightInPoints((short) 10);
			fhead.setColor((short) HSSFColor.BROWN.index);
			fhead.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			fhead.setFontName("Verdana");
			headstyle.setFont(fhead);
			headstyle.setFillBackgroundColor(HSSFColor.GREY_50_PERCENT.index);
			frows.setFontHeightInPoints((short) 8);
			frows.setFontName("Verdana");
			rowsstyle.setFont(frows);
			HSSFRow row = sheet.createRow(0);
			if (rowOfLogsInfo != null) {
				for (int i = 0; i < rowOfLogsInfo.size(); i++) {
					HSSFCell cell = row.createCell((short) i);
					cell.setCellStyle(headstyle);
					HSSFRichTextString str = new HSSFRichTextString(
							(String) rowOfLogsInfo.get(i));
					cell.setCellValue(str);
				}
			}

			// select id,HOSTNAME,VISTORRANID,ipAddress,viewDateAndTime,browser
			// from vistior order by viewDateAndTime desc LIMIT 120
			if (listOfItemInfo != null) {
				ItemInfo itemInfo=null;
				for (int i = 0; i < listOfItemInfo.size(); i++) {
					row = sheet.createRow(i + 1);
					itemInfo = (ItemInfo) listOfItemInfo.get(i);

					HSSFCell cell0 = row.createCell((short) 0);
					cell0.setCellStyle(rowsstyle);
					HSSFRichTextString str0 = new HSSFRichTextString((String) itemInfo.getItemId().toString());
					cell0.setCellValue(str0);

					HSSFCell cell1 = row.createCell((short) 1);
					cell1.setCellStyle(rowsstyle);
					HSSFRichTextString str1 = new HSSFRichTextString((String) itemInfo.getItemName());
					cell1.setCellValue(str1);

					HSSFCell cell2 = row.createCell((short) 2);
					cell2.setCellStyle(rowsstyle);
					HSSFRichTextString str2 = new HSSFRichTextString((String) itemInfo.getPrice().toString());
					cell2.setCellValue(str2);

					HSSFCell cell3 = row.createCell((short) 3);
					cell3.setCellStyle(rowsstyle);
					HSSFRichTextString str3 = new HSSFRichTextString((String) itemInfo.getTakingPlace());
					cell3.setCellValue(str3);

					HSSFCell cell4 = row.createCell((short) 4);
					cell4.setCellStyle(rowsstyle);
					HSSFRichTextString str4 = new HSSFRichTextString((String) itemInfo.getDate().toString());
					cell4.setCellValue(str4);


					HSSFCell cell5 = row.createCell((short) 5);
					cell5.setCellStyle(rowsstyle);
					HSSFRichTextString str5 = new HSSFRichTextString((String) itemInfo.getTakenTime().toString());
					cell5.setCellValue(str5);

					HSSFCell cell6 = row.createCell((short) 6);
					cell6.setCellStyle(rowsstyle);
					HSSFRichTextString str6 = new HSSFRichTextString((String) itemInfo.getItemTakenPerson());
					cell6.setCellValue(str6);

					HSSFCell cell7 = row.createCell((short) 7);
					cell7.setCellStyle(rowsstyle);
					HSSFRichTextString str7 = new HSSFRichTextString((String) itemInfo.getPersonsWith());
					cell7.setCellValue(str7);

					HSSFCell cell8 = row.createCell((short) 8);
					cell8.setCellStyle(rowsstyle);
					HSSFRichTextString str8 = new HSSFRichTextString((String) itemInfo.getComments());
					cell8.setCellValue(str8);
					
				}
			/*	Date date = new Date();

				// String currentTime=new
				// SimpleDateFormat("ddmmyyyy_hh_mm_ss").format(date);
				File file = new File(fileName + ".xls");
				file.createNewFile();
				FileOutputStream fos = new FileOutputStream(file);
				wb.write(fos);
				fos.close();*/
			}
			return wb;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error into the buildExcel () " + e.getMessage());
		}
		logger.info("Exit Into the buildExcel " + new Date());
		return wb;
		
	}
	
	public static void main(String[] args)throws Exception {
		ReportUtils rU=new ReportUtils();
		itemServiceIF=ServiceFactory.getItemServiceFactory();
		List<ItemInfo> listOfInfo=itemServiceIF.viewAllItems();
		String fileName="E:\\Tech_Learning\\saiWork_space\\Ndmns_OutPut\\NDMNSSUCCESS";
		//rU.buildExcel(RMSConstants.headersInformation, listOfInfo, fileName);

		userItemServiceIF=ServiceFactory.getUserItemInfo();
		List<ItemInfo> listOfUserItemInfo=userItemServiceIF.viewAllUserItems("jjj");
	//	rU.buildExcel(RMSConstants.headersInformation, listOfUserItemInfo, fileName);
		
	
	
	}

}
