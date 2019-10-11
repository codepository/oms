package com.lt.cloud.utils;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;


public class ExcelUtils {
	public static HSSFWorkbook getHSSFWorkbook(List list,String colnames) {
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet("sheet");
		HSSFRow row=sheet.createRow(0);
		//set the header
		row=setHead(row, colnames);
		//set the content
		row=setContent(row, sheet, list);
		return wb;
	}
	public static HSSFWorkbook getHSSFWorkbook(List list,String colnames,boolean sumOrNot) {
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet("sheet");
		HSSFRow row=sheet.createRow(0);
		//set the header
		row=setHead(row, colnames);
		//set the content
		row=setContent(row, sheet, list);
		return wb;
	}
	//export
	public static byte[] exportDataToExcelAsByteArray(List list,String colnames){
		HSSFWorkbook wb=getHSSFWorkbook(list, colnames);
		//convert to byte[]
		return convertToByteArray(wb);
	}
	//export
	public static ByteArrayInputStream exportDataToExcel(List list,String colnames){
		
		HSSFWorkbook wb=getHSSFWorkbook(list, colnames);
		//转化输出流
		return convertToByteArrayInputStream(wb);
		
	}
	//convert result to byte[]
	public static byte[] convertToByteArray(HSSFWorkbook wb){
		ByteArrayOutputStream os=new ByteArrayOutputStream();
		try {
			wb.write(os);
			return os.toByteArray();
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally{
			try {
				os.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		return null;
	}
	//convert  result to  ByteArrayInputStream
	public static ByteArrayInputStream convertToByteArrayInputStream(HSSFWorkbook wb){
		
		
		return new ByteArrayInputStream(convertToByteArray(wb));

	}
	//set the content
	public static HSSFRow setContent(HSSFRow row,HSSFSheet sheet,List list){
		if(list.size()==0) return row;
		for(int i=0;i<list.size();i++){
			row = sheet.createRow(i + 1);//new row

			Object obj=list.get(i);
			if (obj instanceof List) {
				java.lang.reflect.Field[] fields = obj.getClass().getDeclaredFields();
				if(fields.length==0) continue;
				for(int j=0;j<fields.length;j++){
					try {
						row.createCell(j).setCellValue(getValueFromField(fields[j], obj));
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					}
				}
			}
			if (obj instanceof HashMap) {
				int j=0;
				Map map=(HashMap)obj;
				for (Object key : map.keySet()) {
					setCellValue(row.createCell(j), map.get(key));
					j++;
				}
				
			}
			
		}
		//遍历所有单元格的值，如何是double就求和
		return row;
		
	}
//	private static void setSum(HSSFSheet sheet, List list) {
//		sheet.setRowSumsBelow(true);
//		HSSFRow row = sheet.createRow(list.size()+2);
//		
//	}
	public static void setCellValue(HSSFCell cell,Object value) {
//		System.out.println("---------cell.value=,=null:"+value+","+(value==null));
		
		if (value instanceof Double) {
			cell.setCellValue((double) value);
		} else if (value instanceof Date){
			cell.setCellValue(DateUtil.formatDefaultDate((Date) value));
		} else if (value instanceof Integer || value instanceof Long) {
			cell.setCellValue(value.toString());
		}else if(value==null){
			cell.setCellValue("");
		}else {
			cell.setCellValue((String) value);
		}
		
	}
	public static String getValueFromField(java.lang.reflect.Field field,Object source){
		if(!field.isAccessible()) field.setAccessible(true);
		if(field==null)return "";
		Object val;
		try {
			val = field.get(source);
			if (val instanceof String||val instanceof Integer) {
				return val.toString();
			}
			if(val instanceof Date){
				return DateUtil.formatDefaultDate((Date)val);
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
		
		}
		
		return "";
	}
	//set the firs row
	public static HSSFRow setHead(HSSFRow row,String colnames){
		if(row==null||colnames==null) return null;
		String[] names=colnames.split(",");
		int z=0;
		for(String x:names){
			HSSFCell cell=row.createCell(z);
			cell.setCellValue(x);
			z++;
		}
		return row;
		
	}
	
	public static List<List<String>> readExcel(File file) throws FileNotFoundException, IOException{
		if(!file.exists()) return null;
		String extension=getFileExtensionName(file);
        
        if("xls".equals(extension)){
        	return readXls(file);
        }
		if("xlsx".equals(extension)){
			return readXlsx(file);
		}
		return null;
		
	}
	private static String getFileExtensionName(File file) {
		return file.getName().lastIndexOf(".")==-1?"":file.getName()
				.substring(file.getName().lastIndexOf(".")+1);
	}
	public static List<List<String>> readXls(File file) throws FileNotFoundException, IOException{
		
		List<List<String>> result=new ArrayList<List<String>>();
		HSSFWorkbook hwb=new HSSFWorkbook(new FileInputStream(file));
		HSSFSheet sheet=hwb.getSheetAt(0);
		HSSFRow row=null;
		HSSFCell cell=null;
		for(int i=sheet.getFirstRowNum();i<sheet.getPhysicalNumberOfRows();i++){
			row=sheet.getRow(i);
			if(row==null)continue;
			List<String> rowResult=new ArrayList<>();
			for(int j=row.getFirstCellNum();j<=row.getLastCellNum();j++){
				cell=row.getCell(j);
//				if(cell==null)continue;
				String cellValue=getCellValue(cell);
				rowResult.add(cellValue);
			}
			result.add(rowResult);
		}
		return result;
		
	}
	public static List<List<String>> readXlsx(File file){
		List<List<String>> result=new ArrayList<List<String>>();
		
		return null;
		
	}
	public static String getCellValue(Cell cell){
		String result="";
		if(cell==null) return result;
		CellType cellType=cell.getCellTypeEnum();
		switch (cellType) {
		case STRING:
			result=cell.getStringCellValue();
			break;
		case NUMERIC:
			NumberFormat nf = NumberFormat.getInstance();
			result=String.valueOf(nf.format(cell.getNumericCellValue()));
			result=result.replaceAll(",", "");
		   
			break;
		case FORMULA:
			try {
				result = String.valueOf(cell.getNumericCellValue());
			} catch (IllegalStateException e) {
				result = String.valueOf(cell.getRichStringCellValue());
			}
			break;
		case BLANK:
			result="";
		default:
			break;
		}
		return result;
	}
}
