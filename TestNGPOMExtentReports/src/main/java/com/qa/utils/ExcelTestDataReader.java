/**
 * 
 */
package com.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * @author madhavi.dokiparthi

 */

public class ExcelTestDataReader {
    /**
     * Method is used to read the test data from excel
     *
     * @param testName - represents script name
     * @return LinkedHashMap contains script name and test data
     */
    public LinkedHashMap<String, List<ExcelTestDataVO>> readTestData() {
    	
                  PropertyFileReader propertyFileReader = new PropertyFileReader();
                  String filePath = propertyFileReader.getValue("TEST_DATA_FILE");
                  String excelExtn = propertyFileReader.getValue("EXCEL_EXTENSION");
                  
                  if(null != excelExtn && !"".equals(excelExtn) && "xlsx".equalsIgnoreCase(excelExtn)){
                                  filePath = filePath+"x";
                  }
                  
                  LinkedHashMap<String, List<ExcelTestDataVO>> iterationDataMap = new LinkedHashMap<String, List<ExcelTestDataVO>>();
                  Workbook workbookForScript = null;
                  Sheet sheetDetailsForScript = null;
                  
                  File fileForScript = new File(filePath);
                  try(FileInputStream fileInputStreamForScript = new FileInputStream(fileForScript);){

                                  if(filePath.contains("xlsx")) {
                                                  workbookForScript = new XSSFWorkbook(fileInputStreamForScript);
                                  }else{
                                                  workbookForScript = new HSSFWorkbook(fileInputStreamForScript);
                                  }
                                  sheetDetailsForScript = workbookForScript.getSheetAt(0);
                                  int rows = sheetDetailsForScript.getPhysicalNumberOfRows();
          
                                  int readColumnFrom = 3;
                                  String testDataColNo = propertyFileReader.getValue("TEST_DATA_FILE_COLUMN_NO");
                                  if(null != testDataColNo && !"".equals(testDataColNo)){
                                                  readColumnFrom = Integer.parseInt(testDataColNo);
                                  }
                                    
                      for (int i = 1; i < rows; i++) {
                                    
                                  GetExcelCellVaueUtil cellVaueUtil = new GetExcelCellVaueUtil();
                                  String scriptName = cellVaueUtil.getCellValue(sheetDetailsForScript.getRow(i).getCell(1));
                                  String value = cellVaueUtil.getCellValue(sheetDetailsForScript.getRow(i).getCell(2));
                                  if ("yes".equalsIgnoreCase(value)) {
                                                                  iterationDataMap = getDataVOs(iterationDataMap, sheetDetailsForScript, readColumnFrom, i,
                                                                                                  cellVaueUtil,scriptName);
                                  }
                                  
                    }
                                  
                  }catch (Exception exception) {
                    FreeCRMLogger.writeExceptionToLog(exception);
                    FreeCRMLogger.writeToLog(exception.toString());
                  }

                  return iterationDataMap;
    }

  private  LinkedHashMap<String, List<ExcelTestDataVO>> getDataVOs(LinkedHashMap<String, List<ExcelTestDataVO>> iterationDataMap,
                                  Sheet sheetDetailsForScript, int readColumnFrom, int i, GetExcelCellVaueUtil cellVaueUtil, String scriptName) {
                   cellVaueUtil.getCellValueForFirstColumn(sheetDetailsForScript.getRow(i).getCell(0));
                  
                   
                    int  lastColumn = sheetDetailsForScript.getRow(i).getLastCellNum();
                    List<ExcelTestDataVO> listExcelTestDataVOs = new ArrayList<ExcelTestDataVO>();

                    
                    
                    for(int j = readColumnFrom; j < lastColumn; j++){
                                    ExcelTestDataVO excelTestDataVO = new ExcelTestDataVO();
                                    String rowData = sheetDetailsForScript.getRow(i).getCell(j).getStringCellValue();
                                    String[] nameValue1 = rowData.split("=");
                                    if(rowData.contains("==") || nameValue1[0].equalsIgnoreCase("password")){ 
                                                    String[] nameValue = rowData.split("=");
                                                    if(null != nameValue[0]){
                                                                    excelTestDataVO.setName(nameValue[0].trim());  
                                                    }if(null != nameValue[1]){
                                                                    excelTestDataVO.setValue(rowData.trim().replaceAll(nameValue[0].trim()+"=", "")); 
                                                                    excelTestDataVO.getValue();
                                                    }
                                                    listExcelTestDataVOs.add(excelTestDataVO);
                                    }else if (rowData.contains("=")){
                                                    String[] nameValue = rowData.split("=");
                                                    if(nameValue.length>2){
                                                                    excelTestDataVO.setName(nameValue[0].trim());  
                                                                    excelTestDataVO.setValue(rowData.trim().replaceAll(nameValue[0].trim()+"=", "")); 
                                                    }else{
                                                    if(null != nameValue[0]){
                                                                    excelTestDataVO.setName(nameValue[0].trim());  
                                                    }
                                                    if(null != nameValue[1]){
                                                                    excelTestDataVO.setValue(nameValue[1].trim()); 
                                                    }
                                    }
                                                  
                                                    listExcelTestDataVOs.add(excelTestDataVO);
                                    }
                    }
                    iterationDataMap.put(scriptName, listExcelTestDataVOs);
                  return iterationDataMap;
  }
    
  public static void main(String[] args) throws Exception {
                  ExcelTestDataReader dataExcelxReader = new ExcelTestDataReader();
                  LinkedHashMap<String, List<ExcelTestDataVO>> map = dataExcelxReader.readTestData();
                 Set<String> s = map.keySet();
                  Iterator<String> it = s.iterator();
                 int count=0;
                  while(it.hasNext())
                  {
                	 
                	  String key = it.next();
                	  System.out.println("key"+key);
//                	 Class<?> cls =  Class.forName("com.accenture.pcs.selenium.testcases."+key);
//		        	    Method meth = cls.getMethod("main", String[].class);
//		         String[] params = null; // init params accordingly
//		        	 	
//		        	  meth.invoke(null, (Object) params); 
		        	  count++;
		        	  if(count==5)
		        	  {
		        		  Thread.sleep(10000);
		        		  count=0;
		        	  }
		        	
                  }
                	  
                  
  }


}
