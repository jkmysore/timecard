package org.kns.timecard.frontend.common.utility;

import java.util.Calendar;


import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
 * Created by Bhagya on September 01st, 2014
 * Class to Upload Projects through excel
 *
 */
public class ProjectExportTemplate extends AbstractExcelView{
	
	private static Logger log=Logger.getLogger(ProjectExportTemplate.class);
	
	
	
	
	@Override
	protected void buildExcelDocument(Map<String, Object> map,HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			log.info("inside buildExcelDocument()");
			HSSFSheet sheet = workbook.createSheet("projects");	
		
		 	CellStyle style = workbook.createCellStyle();
	        Font font = workbook.createFont();
	        font.setColor(HSSFColor.BLACK.index);
	        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style.setFont(font);
		        
	        CellStyle currency=workbook.createCellStyle();
			currency.setDataFormat((short)7);
			CellStyle num=workbook.createCellStyle();
			num.setDataFormat((short) 3);
	
			HSSFRow header = sheet.createRow(0);		
			
			header.createCell(0).setCellValue("S.No");
			header.createCell(1).setCellValue("Organization Name");
			header.createCell(2).setCellValue("Project Number");
			header.createCell(3).setCellValue("Project Name");
			header.createCell(4).setCellValue("Project Status");
			header.createCell(5).setCellValue("StartDate");
			header.createCell(6).setCellValue("Completion Date");
			header.createCell(7).setCellValue("Created By");
			header.createCell(8).setCellValue("Modified By");
			header.createCell(8).setCellValue("Created Date");
			header.createCell(9).setCellValue("Modified Date");
			
			
			
			
			header.setRowStyle(style);	
			
			/*int rowNum = 2;*/
			HSSFCellStyle dateStyle=workbook.createCellStyle();
			CreationHelper createHelper=workbook.getCreationHelper();
			dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("MM/dd/yyyy"));
			
			Calendar cal=Calendar.getInstance();
			cal.setTime(new Date());
			 response.setHeader("Content-Disposition", "inline; filename=projects_"+(cal.get(Calendar.MONTH)+1)+"_"+cal.get(Calendar.DATE)+"_"+cal.get(Calendar.YEAR)+".xls");
			  // Make sure to set the correct content type
			  response.setContentType("application/vnd.ms-excel");
			//workbook.write(response.getOutputStream());
			sheet.getWorkbook().write(response.getOutputStream());
			response.getOutputStream().flush();			
	}

}

