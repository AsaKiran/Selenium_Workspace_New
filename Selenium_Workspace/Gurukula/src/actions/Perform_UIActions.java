package actions;
//import com.gurukula.Login;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hpsf.ClassID;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gurukula.Reusable_Functions;

import junit.framework.Assert;

public class Perform_UIActions {
	
	WebDriver driver;
	WebDriverWait w;
	String result;
	
	public Perform_UIActions(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public String ui_action(String actions,Properties p,String Obj,String Input) 
	{
	
		
		switch(actions.toLowerCase() )
		{
		
/***************************Action to open URL********************************/
		
		case "openurl":
			
			driver.get(Input);
			
			   String expectedtitle="gurukula";
			   
			   String gettitle=driver.getTitle();
		       if(expectedtitle.contentEquals(gettitle))
			   {
				   System.out.println("Website URL:"+"'"+Input+"'"+"Navigation Successfull");
				   result="Pass";
				   
			   }
			   
			   else
			   {
				   System.out.println("Website URL:"+"'"+Input+"'"+"Navigation Failed");
				   driver.close();
				   result="Fail";
			   }
			break;
			
						
/*************************Verify Page Title**********************************/		
			
		case	"verifytitle":
			
			String gettitle2=driver.getTitle();
			String actual=Input;
			if(gettitle2.contentEquals(actual))
			{
				System.out.println("Title: "+"'"+Input+"'"+" displayed correctly");
				result="Pass";
			}
			else
			{
				System.out.println("Title: "+"'"+Input+"'"+" not displayed correctly");
				result="Fail";
			}
           break;
			
/****************************Action to Verify Text*******************************/
			
		case "verifytxt":
			
			
			if(driver.findElement(By.tagName("body")).getText().contains(Input))
			{
				System.out.println("Text:"+"'"+Input+"'"+" is displayed correctly");
				result="Pass";
			}
			else
			{
				System.out.println("Text:"+"'"+Input+"'"+" not displayed correctly");
				result="Fail";
			
			}		
			break;
/******************************Verify Element Displayed***********************************/
			
		case "iselementdisplayed":
			
		if(driver.findElement(By.xpath(p.getProperty(Obj))).isDisplayed())
         {
			System.out.println("Element: "+"'"+Obj+"'"+" is Displayed");
			result="Pass";
         }
		else
		{
			System.out.println("Element: "+"'"+Obj+"'"+" is not Displayed");
			result="Fail";
		
		}		
		break;
/******************************Verify Element Selected**********************************/	
		case "iselementselected":
			
		if(driver.findElement(By.xpath(p.getProperty(Obj))).isSelected())
         {
			System.out.println("Element: "+"'"+Obj+"'"+" is Selected");
			result="Pass";
         }
		else
		{
			System.out.println("Element: "+"'"+Obj+"'"+" is not Selected");
			result="Fail";
		
		}		
		break;
		
		
		

/******************************Verify Object enabled***************************************/
		
		case "iselementenabled":
			
			if(driver.findElement(By.xpath(p.getProperty(Obj))).isEnabled())
           {
				System.out.println("Element: "+"'"+Obj+"'"+" is Enabled");
				result="Pass";
           }
  		else
  		{
  			System.out.println("Element: "+"'"+Obj+"'"+" not Enabled");
  			result="Fail";
  		
  		}		
  		break;
	


			
/*************************Action click on link/button***********************************/		
			
		case "click":			
			
			
			if(driver.findElement(By.xpath(p.getProperty(Obj))).isEnabled())
				
			{
				driver.findElement(By.xpath(p.getProperty(Obj))).click();
				System.out.println("Clicked on element:"+"'"+Obj+"'");
				result="Pass";
			}
			
			else
			{
				System.out.println("Unable to Click on:"+"'"+Obj+"'");
				result="Fail";
			
			
		}
			break; 

/****************************Verify Element Present********* *****************************/			
			
		case "verifyelementpresent":
			
			String text=driver.findElement(By.xpath(p.getProperty(Obj))).getText();
			if(text.contentEquals(Input))
			{
				System.out.println("Element : "+"'"+Input+" ' "+ "is present"); 
				result="Pass";
			}
			else
			{
				System.out.println("Element : "+"'"+Input+" ' "+ "is not present");
				result="Fail";
			}
			
			break;
/**************************Verify Placeholder Text*******************************/
			
		case "verifyplaceholder":
			
			String placeholdertext=driver.findElement(By.xpath(p.getProperty(Obj))).getAttribute("placeholder");
			if(placeholdertext.contentEquals(Input))
			{
				System.out.println("PlaceHolder: "+"'"+Input+"'"+ "is displayed correctly"); 
				result="Pass";
			}
			else
			{
				System.out.println("PlaceHolder: "+"'"+Input+"'"+ "not displayed correctly");
				result="Fail";
			}
			
			break;

		
/************************** Input text box********************************/
			
		case "inputtext":
			
			driver.findElement(By.xpath(p.getProperty(Obj))).clear();
			driver.findElement(By.xpath(p.getProperty(Obj))).sendKeys(Input);
			if(driver.findElement(By.xpath(p.getProperty(Obj))).getAttribute("value").contentEquals(Input))
			{
				System.out.println("Text:"+"'"+Input+"' "+ "is entered in the input textbox "); 
				result="Pass";
			}
			
			else
			{
				System.out.println("Text:"+"'"+Input+"' "+ "is not entered in the input textbox ");
				result="Fail";
			}
			break;

/************************** input text box clear********************************/			
		case "clearvalue":
			 driver.findElement(By.xpath(p.getProperty(Obj))).clear();
			 System.out.println("Value cleared"); 
			 result="Pass";
		     break;
			
			
/************************** assertText(locator,Value)********************************/		
		case "asserttext":
			
			String message=driver.findElement(By.xpath(p.getProperty(Obj))).getText();
		    
			
			if(message.contentEquals(Input))
		         {
		        	 
		        	System.out.println("Text:"+"'"+Input+"' "+ "is displayed"); 
					result="Pass";
				}
				
				else
				{
					System.out.println("Text:"+"'"+Input+"' "+ "is not displayed ");
					result="Fail";
					System.exit(0);
				}
				break;
				
/************************** wait for elementpresent(l)********************************/		
		case "waitforelementpresent":
			
		 w=new WebDriverWait(driver,70);
		if( w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(p.getProperty(Obj)))) != null)
			
		{
		        	System.out.println("ElementLocated:"+"'"+Obj+"'"); 
					result="Pass";
				}
				
				else
				{
					System.out.println("Uable to Located the Element:"+"'"+Obj+"'");
					result="Fail";
				}			
				
		break;
		
		

/****************************Switch to Active Window**************************************/	 
		case "switchtoactivewindow":
			
			if(driver.switchTo().activeElement()!=null)
			{
			  System.out.println("Switched to Active Element");
				result="Pass";
			}
			else
			{
				 System.out.println("Cannot Switch to Active Element");
					result="Fail";
			}
			break;

/******************** find the code in the first row*************************/
	
case "tablesearchcode":
	
	
	String Codevalue=driver.findElement(By.xpath(p.getProperty(Obj))).getText();
	System.out.println(Codevalue);
	System.out.println(Input);
	if(Codevalue.contentEquals(Input))
	{
		System.out.println("Element found in table"+"'"+Input+"'");
		result="Pass";
	}
	else
	{
		 System.out.println("Element not found"+"'"+Input+"'");
			result="Fail";
	}
	break;
	

	
/*********************deleted element******************************************/
case "verifydeletedvalue":
	
	
	if(w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(p.getProperty(Obj))))==null)
			{
		
			System.out.println("Element deleted from table:"+Input);
			result="Pass";
			}
	else
	{
		 System.out.println("Element not deleted"+Input);
			result="Fail";
	}
	break;
	
		
	
	

/**********************************Custom classes*************************************/	

/*********************Custom class Execution***********************************/
case "runloginclass":
	
	 
	 
	 Reusable_Functions log=new Reusable_Functions(driver);
	 String classresult=log.login_gurukula();
	 System.out.println("Login Class is run syuccessfully");
	 result=classresult;
	 
	 break;
	 
/**********************************Custom class for View Branch*************************************/
	 case "runviewbranchtable":
	 	
	 	
	 	 Reusable_Functions viewbranch=new Reusable_Functions(driver);
	 	 String viewresult=viewbranch.branch_viewfunction(Input);
	 	 result=viewresult;
	 	 
	 	 break;
/******************************************************/	 
case "runeditbranchtable":
	
	
	 Reusable_Functions editbranch=new Reusable_Functions(driver);
	 String editresult=editbranch.branch_editfunction(Input);
	 result=editresult;
	 
	 break; 

case "rundeletebranchtable":
	
	
	 Reusable_Functions deletebranch=new Reusable_Functions(driver);
	 String deleteresult=deletebranch.branch_deletefunction(Input);
	 result=deleteresult;
	 
	 break; 
	 
case"runstaffviewtable" :

Reusable_Functions viewstaff=new Reusable_Functions(driver);
String viewstaffresult=viewstaff.staff_viewfunction(Input);
result=viewstaffresult;

break; 

case"runstaffedittable" :

Reusable_Functions editstaff=new Reusable_Functions(driver);
String editstaffresult=editstaff.staff_editfunction(Input);
result=editstaffresult;

break; 


case"runstaffdeletetable" :

Reusable_Functions deletestaff=new Reusable_Functions(driver);
String deletestaffresult=deletestaff.staff_deletefunction(Input);
result=deletestaffresult;
break;



/*******************Select By Value***************************************/	 
case "seletbyvalue":
	   
	  
	if(driver.findElement(By.xpath(p.getProperty(Obj))).isSelected()==false)
	{
		new Select(driver.findElement(By.xpath(p.getProperty(Obj)))).selectByVisibleText(Input);;
		System.out.println("Value Selected");
		result="Pass";
	}
	
	else
	{
		System.out.println("Value not Selected");
		result="Fail";
	}
		
	break;
	
	 
/****************************Action for wait*************************************/
			
		case "wait":
			
			driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
			System.out.println("Wait for Browser stability");
			result="Pass";
			break;

/****************************Action for wait*************************************/			
	default:
		System.out.println("'"+Obj+"'" +":obj not found");
		result="Fail";
		System.exit(0);
		break;
		
			
			
	}
		
		
		return result;
	
	}
	
	

}

