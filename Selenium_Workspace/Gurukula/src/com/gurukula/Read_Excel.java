package com.gurukula;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import actions.Perform_UIActions;
import loadobjects.Readobjects;

public class Read_Excel 
{


WebDriver driver;

@Test	

	
  public void run_testsuite()
  {
	Readobjects obj= new Readobjects();
	
	Properties p=obj.readobjproperties();
	String Testcase_name; 		
	String screen=null;	
	String pageobj=null;
	String input=null;
	    
		
	try
	{
		
	
	//create a directory to store screenshots
		
		System.out.println("Starting script"+getTimestamp());
		String Screenshot_dir=System.getProperty("user.dir")+"\\testoutputs\\screenshots\\"+getTimestamp();
		File Screenshot_file= new File(Screenshot_dir);
	    	if(!Screenshot_file.exists())
		    {
				Screenshot_file.mkdir();
			}
	    System.out.println("Screenshots are stored at:"+Screenshot_dir);	
	    
	  //get config file 
			
		String Configfile_dir=System.getProperty("user.dir")+"\\testinputs\\datasheet\\Configuration_File.xlsx";
		FileInputStream Configfile_file= new FileInputStream(Configfile_dir);
		XSSFWorkbook Configfile_workbook=new XSSFWorkbook(Configfile_file);
		XSSFSheet Configfile_sheet=Configfile_workbook.getSheetAt(0);
		

		
		int Configfile_rowcount= Configfile_sheet.getLastRowNum()-Configfile_sheet.getFirstRowNum();
		
		
		  for(int j=1;j<Configfile_rowcount+1;j++)
		  {
		
			Row Configfilecurrent_row=Configfile_sheet.getRow(j);
			String run_value=Configfilecurrent_row.getCell(1).getStringCellValue();
			
			
			  	
			  if(run_value.contentEquals("Y"))
			  {
				  String browser_type=Configfilecurrent_row.getCell(2).getStringCellValue();
				  switch(browser_type.toUpperCase())
				  {
				    case "FIREFOX": driver=new FirefoxDriver();
				                    driver.manage().deleteAllCookies();
				                  System.out.println("FIREFOX Browser started");
				                  driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
				                  break;
				                  
				    case "IE":System.setProperty("webdriver.ie.driver", "./Library/IEDriverServer.exe");
				    	      driver=new InternetExplorerDriver();
				    	       driver.manage().deleteAllCookies();
				                System.out.println("IE Browser started");
				                driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	                              break;
	                        
				    case "CHROME": System.setProperty("webdriver.chrome.driver", "./Library/chromedriver.exe");
				    	           driver=new ChromeDriver();
				    	           driver.manage().deleteAllCookies();
		                         System.out.println("Chrome Browser started");
		                         driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
                             break;
	                        
				  }
				  
				  
				  Perform_UIActions ui = new Perform_UIActions(driver);
		   
			  	  Testcase_name=Configfilecurrent_row.getCell(0).getStringCellValue();	
			  	  String[] Testcase_sheetid=Testcase_name.split("_");
			  	  
			  	  
				
				//copy of file to result folder
				String Src_dir=System.getProperty("user.dir")+"\\testinputs\\datasheet\\"+Testcase_name+".xlsx";
				String Dest_dir=System.getProperty("user.dir")+"\\testoutputs\\reports\\"+Testcase_name+"_"+getTimestamp()+".xlsx";
				
				FileInputStream Src_file= new FileInputStream(Src_dir);
				XSSFWorkbook Src_workbook=new XSSFWorkbook(Src_file);
				FileOutputStream Dest_file=new FileOutputStream(Dest_dir);
				
				Src_workbook.write(Dest_file);
				Dest_file.flush();
				Dest_file.close();
				System.out.println("Results are stored at:"+Dest_dir);
				
				
				//open the test case sheet
				FileInputStream TestCase_file= new FileInputStream(Dest_dir);
				XSSFWorkbook Testcase_workbook = new XSSFWorkbook(TestCase_file);
				XSSFSheet Testcase_sheet=Testcase_workbook.getSheet(Testcase_sheetid[0]);
				
				
				XSSFSheet Datasheet=Testcase_workbook.getSheet("#data");
				
				
				System.out.println("Executing:"+Testcase_name);					
				int Testcase_rowcount= Testcase_sheet.getLastRowNum()-Testcase_sheet.getFirstRowNum();
				//System.out.println(Testcase_rowcount);
			       for(int i=5;i<Testcase_rowcount+1;i++)
			       {
				             
			    	   Row Testcasecurrent_row=Testcase_sheet.getRow(i);
			    	   int Stepno=(int) Testcasecurrent_row.getCell(0).getNumericCellValue();
			    	   String description=Testcasecurrent_row.getCell(1).getStringCellValue();
			    	   String action=Testcasecurrent_row.getCell(2).getStringCellValue();
			    	   //String page_item=Testcasecurrent_row.getCell(6).getStringCellValue();
			    
			    		   Cell testcase_cell3=Testcasecurrent_row.getCell(3);
			    		   
			    		   switch(testcase_cell3.getCellType())
			    		   {
			    		   
			    		   case Cell.CELL_TYPE_STRING:
			    			   pageobj=testcase_cell3.getStringCellValue();
			    			   	       break;
			    			    
			    		   case Cell.CELL_TYPE_BLANK:
			    			     pageobj="NA";
			    			     break;
			    			   			   
			    			}
			    		   
			    		   Cell testcase_cell4=Testcasecurrent_row.getCell(4);
			    		   
			    		   switch(testcase_cell4.getCellType())
			    		   {
			    		   
			    		   case Cell.CELL_TYPE_STRING:
			    			    input=testcase_cell4.getStringCellValue();
			    			 	    break;
			    			    
			    		   case Cell.CELL_TYPE_BLANK:
			    			   input="NA";
			    			     break;
			    			   			   
			    			}
			    		   
			    		   Cell testcase_cell5=Testcasecurrent_row.getCell(5);
			    		   
			    		   switch(testcase_cell5.getCellType())
			    		   {
			    		   
			    		   case Cell.CELL_TYPE_STRING:
			    			   screen=testcase_cell5.getStringCellValue();
			    			 		    break;
			    			    
			    		   case Cell.CELL_TYPE_BLANK:
			    			   screen="NA";
			    			     break;
			    			   			   
			    			}
			    	   if(input.startsWith("#data"))
			    	   {
			    		   
			    		   String[] var_value=input.split("_");
			    		   String var=var_value[1];
			    		   
			    		   
			    		   
			    	
			    		   
			    		   
			    		   int datacount=Datasheet.getLastRowNum()-Datasheet.getFirstRowNum();
			    		   for(int d=1;d<datacount+1;d++)
			    		   {
			    			   
			    			   Row datasheet_currentrow=Datasheet.getRow(d);
			    			   
			    			   Cell data_cell=datasheet_currentrow.getCell(1);
			    			  String sheet_var=datasheet_currentrow.getCell(0).getStringCellValue();
			    			   if(sheet_var.equals(var_value[1]))
			    				   
			    			   {
			    				   switch(data_cell.getCellType()) 
			    				   {
			    				   case Cell.CELL_TYPE_STRING:
			    					   input=datasheet_currentrow.getCell(1).getStringCellValue();
				    				   break;
			    					   
			    				   case Cell.CELL_TYPE_NUMERIC:
			    					   input=String.valueOf(datasheet_currentrow.getCell(1).getNumericCellValue());
			    					   
				    				   break;
			    					   
			    					   
			    				   }
			    				   
			    				   
			    			   }
			    			   
			    		   
			    		   }
			    	   }
			    	 
			    				
					System.out.println("**********************************");
					System.out.print(Stepno+":");
					System.out.println(description+".");
					System.out.print("Result of step:");
					
					
					
			        
			        //Calling the action class
			        
			         String result=ui.ui_action(action, p, pageobj,input);
			         
			         if(result.contentEquals("Fail"))
			         {
					 System.out.println("Error page_item not found/reached");
					 
			         }
					
				   //screenshot 
			         if(screen.contentEquals("Y"))
					{
						String path=Screenshot_dir+"\\"+Testcase_sheetid[0]+"_"+Stepno+"_"+getTimestamp()+".png";
						capture(path);
	
						
					}
					
					
					CellStyle cell_style=Testcase_workbook.createCellStyle();
					if(result.contentEquals("Pass"))
					{
						cell_style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
						cell_style.setFillPattern(CellStyle.SOLID_FOREGROUND);
						Cell resultcell=Testcasecurrent_row.createCell(6);
						
						resultcell.setCellValue(result);
						resultcell.setCellStyle(cell_style);	
					}
					
					else
					{
						cell_style.setFillForegroundColor(IndexedColors.RED.getIndex());
						cell_style.setFillPattern(CellStyle.SOLID_FOREGROUND);
						Cell resultcell=Testcasecurrent_row.createCell(6);
						
						
						resultcell.setCellValue(result);
						resultcell.setCellStyle(cell_style);
						break;
						
					}
					
				}
			
			       FileOutputStream Dest_file1=new FileOutputStream(Dest_dir);
					 
			       Testcase_workbook.write(Dest_file1);
			       Dest_file1.close();  
			       
					
			       System.out.println("End of the Test:"+getTimestamp());
			       driver.close();
			       System.out.println("Exported Result to Excel");
			       
	
			}	
			
			
		}
		
		
		  Configfile_file.close();
		
	}
	
	
    catch(FileNotFoundException f)
    {
	  System.out.println("Exception:Unable to Open file:"+f);
		
    }
		
    catch(IOException io)
    {
	  System.out.println("IO exception:"+io);	
    }
 }
	
public String getTimestamp()
{
		DateFormat dateFormat= new SimpleDateFormat("yyyymmddHHmmss");
		Date date= new Date();
		String timestamp = dateFormat.format(date);
		//System.out.println(timestamp);
		return timestamp;
}
	


public void capture(String dir)
{

	
		try
		{
		File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot,new File(dir));
		}
		
		catch(IOException io)
		{
			System.out.println("Exception"+io);
		}
	}
	
}