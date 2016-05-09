package com.gurukula;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Reusable_Functions {
	
	 private static final String List = null;
	WebDriver driver;
	WebDriverWait wt;
	String class_r;
	//public Login(WebDriver driver)
	//{
		
//}
	
	
public Reusable_Functions()
{
	
}






	public Reusable_Functions(WebDriver driver) {
		this.driver=driver;
	}





	public String  login_gurukula()
	{
		driver.get("http://localhost:8080");
		
		wt=new WebDriverWait(driver,100);
		
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/a[contains(@href,'login')]")));
		driver.findElement(By.xpath("//div/a[contains(@href,'login')]")).click();
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='username']")));
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("admin");
		driver.findElement(By.xpath("//button[contains(text(),'Authenticate')]")).click();
		if(wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@translate='main.logged.message']" )))!=null)
				{
			 
			System.out.println("Login Successfull");
			class_r="Pass";
				}
		
		else
		{
			System.exit(0);
			class_r="Fail";
			
		}
		return class_r;
	
	}
	
	
	

public int table_branch(String IDValue)
{
	
	int a=0;
	System.out.println(IDValue);
	WebElement table_details=driver.findElement(By.xpath("//div[@id='deleteBranchConfirmation']/following-sibling::div/table/tbody"));
	
	
	
	java.util.List<WebElement> rorws=table_details.findElements(By.tagName("tr"));
	
	System.out.println(rorws.size());
	
	for(int i =1;i<=rorws.size();i++)
	{
	
		//System.out.println(i);
		By id_path=By.xpath("//div[@id='deleteBranchConfirmation']/following-sibling::div/table/tbody/tr["+i+"]/td[1]/a");
		//By name_path=By.xpath("//div[@id='deleteBranchConfirmation']/following-sibling::div/table/tbody/tr["+i+"]/td[2]");
		//By name_code=By.xpath("//div[@id='deleteBranchConfirmation']/following-sibling::div/table/tbody/tr["+i+"]/td[3]");
		
	
	
	String ID=driver.findElement(id_path).getText();
	
	//System.out.println(ID);
	
	if(IDValue.contentEquals(ID))
	{
		
		a=i;
		//System.out.println("Value of a "+i);
		
		break;
	}
	}
	return a;
	
	
}


public String branch_viewfunction(String Value1)
{
	//System.out.println("In Fun");
	int v=table_branch(Value1);
	//System.out.println(v);
	String viewresult=null;
	WebDriverWait wr=new WebDriverWait(driver,10);
	driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
	WebElement Branch_TableButtonview=driver.findElement(By.xpath("//div[@id='deleteBranchConfirmation']/following-sibling::div/table/tbody/tr["+v+"]/td[4]/button[1]/span[2]"));
	String Branch_Name=driver.findElement(By.xpath("//div[@id='deleteBranchConfirmation']/following-sibling::div/table/tbody/tr["+v+"]/td[2]")).getText();
	String Branch_Code=driver.findElement(By.xpath("//div[@id='deleteBranchConfirmation']/following-sibling::div/table/tbody/tr["+v+"]/td[3]")).getText();
	
	Branch_TableButtonview.click();
	wr.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@href='#/branch']")));
	String heading=driver.findElement(By.xpath("//h2[@class='ng-binding']")).getText();
	//System.out.println(heading);
	String[] branchid=heading.split(" ");
	//System.out.println(branchid[1]);
	String view_name=driver.findElement(By.xpath("//div[@class='table-responsive']/table/tbody/tr[1]/td[2]/input")).getAttribute("value");
	//System.out.println(view_name);
	String view_code=driver.findElement(By.xpath("//div[@class='table-responsive']/table/tbody/tr[2]/td[2]/input")).getAttribute("value");	
	//System.out.println(view_code);
	if(branchid[1].contentEquals(Value1))
	{
		//System.out.println("Correct ID displayed in view screen");
		if(Branch_Name.contentEquals(view_name))
		{
			//System.out.println("Correct Name displayed in view screen");
		if(Branch_Code.contentEquals(Branch_Code))
		{
			//System.out.println("Correct Code displayed in view screen");
		}
		}
		
		viewresult="Pass";
	}
	
	else
	{
		viewresult="Fail";
	}
	driver.findElement(By.xpath("//button[@href='#/branch']")).click();
	return viewresult;
		
		
}


public String branch_editfunction(String Value2)
{
	int e=table_branch(Value2);
	String viewresult=null;
	WebDriverWait wr=new WebDriverWait(driver,50);
	driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);

	WebElement Branch_TableButtonedit=driver.findElement(By.xpath("//div[@id='deleteBranchConfirmation']/following-sibling::div/table/tbody/tr["+e+"]/td[4]/button[2]/span[2]"));
	

	if(isElementPresent("//div[@id='deleteBranchConfirmation']/following-sibling::div/table/tbody/tr["+e+"]/td[4]/button[2]/span[2]"))
	{
		Branch_TableButtonedit.click();
		wr.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='saveBranchModal']/div/div/form/div[3]/button[2]/span[2]")));
	   
		viewresult="Pass";
		
	}
	else
	{
		viewresult="Fail";
	}
	
	return viewresult;
}


	
public String branch_deletefunction(String Value3)

{
	int d=table_branch(Value3);
	String viewresult=null;
	WebDriverWait wr=new WebDriverWait(driver,50);
	driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	WebElement Branch_TableButtonedit=driver.findElement(By.xpath("//div[@id='deleteBranchConfirmation']/following-sibling::div/table/tbody/tr["+d+"]/td[4]/button[3]/span[2]"));
	if(isElementPresent("//div[@id='deleteBranchConfirmation']/following-sibling::div/table/tbody/tr["+d+"]/td[4]/button[3]/span[2]"))
		{
		Branch_TableButtonedit.click();
		
		wr.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='deleteBranchConfirmation']/div/div/form/div[3]/button[2]/span[2]")));
		driver.switchTo().activeElement();
		String Message=driver.findElement(By.xpath("//form[@name='deleteForm']/div[2]/p")).getText();
         
		
		String expected="Are you sure you want to delete Branch"+" "+Value3+"?";
		if(Message.contentEquals(expected))
		{
			
			driver.switchTo().activeElement();
			driver.findElement(By.xpath(".//*[@id='deleteBranchConfirmation']/div/div/form/div[3]/button[2]/span[2]")).click();
			viewresult="Pass";
			driver.switchTo().activeElement();
			wr.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='searchQuery']")));
		}
		else
		{
			viewresult="Fail";
		}
		
		
		
		
		} 
	
return viewresult;
}


public int table_staff(String IDValue) 
{

	boolean f1=false;
	
	boolean f2=false;
	int t=0;
	
	
	System.out.println(IDValue);
	WebDriverWait w= new WebDriverWait(driver,10);
	WebElement table_details=driver.findElement(By.xpath("//div[@id='deleteStaffConfirmation']/following-sibling::div/table/tbody"));
	
	
	
	while(!f1)
	{
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		java.util.List<WebElement> rorws=table_details.findElements(By.tagName("tr"));
	
	//System.out.println(rorws.size());
	
	for(int i =1;i<=rorws.size();i++)
	{
	
		
		//System.out.println(i);
		By id_path=By.xpath("//div[@id='deleteStaffConfirmation']/following-sibling::div/table/tbody/tr["+i+"]/td[1]/a");
		//By name_path=By.xpath("//div[@id='deleteBranchConfirmation']/following-sibling::div/table/tbody/tr["+i+"]/td[2]");
		//By name_code=By.xpath("//div[@id='deleteBranchConfirmation']/following-sibling::div/table/tbody/tr["+i+"]/td[3]");
		
	
	
	String ID=driver.findElement(id_path).getText();
	
	//System.out.println(ID);
	
	if(IDValue.contentEquals(ID))
	{
		System.out.println("matching");
		t=i;
		f1=true;
		f2=true;
		break;
		
	}
	
	
	}
	if(!f2)
	{
	
	if(isElementPresent("//div[@id='deleteStaffConfirmation']/following-sibling::div[1]/nav/ul/li[3]/a"))
	{
	driver.findElement(By.xpath("//div[@id='deleteStaffConfirmation']/following-sibling::div[1]/nav/ul/li[3]/a")).click();
	//driver.manage().wait(30);
	w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='searchQuery']")));
	}
	
	}
	
}
	
	return t;
	
	
}
	







public String staff_viewfunction(String Value1) 
{
	//System.out.println("In Fun");
	int v=table_staff(Value1);
	//System.out.println(v);
	String viewresult=null;
	WebDriverWait wr=new WebDriverWait(driver,10);

	WebElement Branch_TableButtonview=driver.findElement(By.xpath("//div[@id='deleteStaffConfirmation']/following-sibling::div/table/tbody/tr["+v+"]/td[4]/button[1]/span[2]"));
	String Staff_Name=driver.findElement(By.xpath("//div[@id='deleteStaffConfirmation']/following-sibling::div/table/tbody/tr["+v+"]/td[2]")).getText();
	String Staff_Branch=driver.findElement(By.xpath("//div[@id='deleteStaffConfirmation']/following-sibling::div/table/tbody/tr["+v+"]/td[3]")).getText();
	
	Branch_TableButtonview.click();
	wr.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@href='#/staff']")));
	String heading=driver.findElement(By.xpath("//h2[@class='ng-binding']")).getText();
	//System.out.println(heading);
	String[] staffid=heading.split(" ");
	//System.out.println(branchid[1]);
	String view_name=driver.findElement(By.xpath("//div[@class='table-responsive']/table/tbody/tr[1]/td[2]/input")).getAttribute("value");
	//System.out.println(view_name);
	String view_branch=driver.findElement(By.xpath("//div[@class='table-responsive']/table/tbody/tr[2]/td[2]/input")).getAttribute("value");	
	//System.out.println(view_code);
	if(staffid[1].contentEquals(Value1))
	{
		//System.out.println("Correct ID displayed in view screen");
		if(Staff_Name.contentEquals(view_name))
		{
			//System.out.println("Correct Name displayed in view screen");
		if(Staff_Branch.contentEquals(view_branch))
		{
			//System.out.println("Correct Code displayed in view screen");
		}
		}
		
		viewresult="Pass";
	}
	
	else
	{
		viewresult="Fail";
	}
	driver.findElement(By.xpath("//button[@href='#/staff']")).click();
	return viewresult;
		
		
}

public String staff_editfunction(String Value2)
{
	int e=table_staff(Value2);
	String viewresult=null;
	WebDriverWait wr=new WebDriverWait(driver,50);

	WebElement Branch_TableButtonedit=driver.findElement(By.xpath("//div[@id='deleteStaffConfirmation']/following-sibling::div/table/tbody/tr["+e+"]/td[4]/button[2]/span[2]"));
	

	if(isElementPresent("//div[@id='deleteStaffConfirmation']/following-sibling::div/table/tbody/tr["+e+"]/td[4]/button[2]/span[2]"))
	{
		Branch_TableButtonedit.click();
		wr.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='saveStaffModal']/div/div/form/div[3]/button[2]/span[2]")));
	   
		viewresult="Pass";
		
	}
	else
	{
		viewresult="Fail";
	}
	
	return viewresult;
}


public String staff_deletefunction(String Value3)

{
	int d=table_staff(Value3);
	String viewresult=null;
	WebDriverWait wr=new WebDriverWait(driver,50);
	WebElement Branch_TableButtonedit=driver.findElement(By.xpath("//div[@id='deleteStaffConfirmation']/following-sibling::div/table/tbody/tr["+d+"]/td[4]/button[3]/span[2]"));
	if(isElementPresent("//div[@id='deleteStaffConfirmation']/following-sibling::div/table/tbody/tr["+d+"]/td[4]/button[3]/span[2]"))
		{
		if(Branch_TableButtonedit.isEnabled())
		{
		Branch_TableButtonedit.click();
		}
		
		
		driver.switchTo().activeElement();
		wr.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='deleteStaffConfirmation']/div/div/form/div[3]/button[2]")));
		String Message=driver.findElement(By.xpath("//form[@name='deleteForm']/div[2]/p")).getText();
         
		
		String expected="Are you sure you want to delete Staff"+" "+Value3+"?";
		if(Message.contentEquals(expected))
		{
			
			driver.switchTo().activeElement();
			driver.findElement(By.xpath(".//*[@id='deleteStaffConfirmation']/div/div/form/div[3]/button[2]")).click();
			viewresult="Pass";
			driver.switchTo().activeElement();
			wr.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='searchQuery']")));
		}
		else
		{
			viewresult="Fail";
		}
		
		
		
		
		} 
	
return viewresult;
}





























public boolean isElementPresent(String locator)
{
	try
	{
		driver.findElements(By.xpath(locator));
		//System.out.println("iselement");
		return true;
	}
	
	catch(Exception e)
	{
		System.out.println("Exception"+e);
		driver.close();
		
		return false;
	}
}

}



























































/*
 * 
 * 
 * 
	public String view_branchtable(String Value)
	{
	
		String branchresult = null;
		wt=new WebDriverWait(driver,50);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		
		WebElement Branch_TableButtonview=driver.findElement(By.xpath("//a[text()='"+Value+"']/parent::td/parent::tr/td[4]/button[1]/span[2]"));
		String Branch_TableName=driver.findElement(By.xpath("//a[text()='"+Value+"']/parent::td/following-sibling::td[1]")).getText();
		//System.out.println(Branch_TableName);
		//System.out.println(Branch_TableCode);
		String Branch_TableCode=driver.findElement(By.xpath("//a[text()='"+Value+"']/parent::td/following-sibling::td[2]")).getText();
		//System.out.println(Branch_TableCode);
		String Branch_TableID=driver.findElement(By.xpath("//a[text()='"+Value+"']/parent::td/a")).getText();
		//System.out.println(Branch_TableID);
		
		if( wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='"+Value+"']/parent::td/parent::tr/td[4]/button[1]/span[2]"))) != null)
				
		{
			Branch_TableButtonview.click();
			wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@href='#/branch']")));
			String heading=driver.findElement(By.xpath("//h2[@class='ng-binding']")).getText();
			//System.out.println(heading);
			String[] branchid=heading.split(" ");
			//System.out.println(branchid[1]);
			String view_name=driver.findElement(By.xpath("//div[@class='table-responsive']/table/tbody/tr[1]/td[2]/input")).getAttribute("value");
			//System.out.println(view_name);
			String view_code=driver.findElement(By.xpath("//div[@class='table-responsive']/table/tbody/tr[2]/td[2]/input")).getAttribute("value");	
			//System.out.println(view_code);
			if(branchid[1].contentEquals(Branch_TableID))
			{
				//System.out.println("Correct ID displayed in view screen");
				if(view_name.contentEquals(Branch_TableName))
				{
					//System.out.println("Correct Name displayed in view screen");
				if(view_code.contentEquals(Branch_TableCode))
				{
					//System.out.println("Correct Code displayed in view screen");
				}
				}
				
				
				branchresult="Pass";
			}
			
			
			else
			{
				//System.out.println("View details are not matching");
				branchresult="Fail";
			}
			
			
			driver.findElement(By.xpath("//button[@href='#/branch']")).click();
			
		}
		
		return branchresult;
	}
	


	
	public String view_staff(String Value)
	{
	
		String staffviewresult = null;
		wt=new WebDriverWait(driver,50);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		//String obj1=Value.replaceFirst("searchstring", Value);
		
		System.out.println("get");
		//WebElement Staff_TableButtonview=driver.findElement(By.xpath("//td[contains(text(),'"+Value+"')]/parent::tr/td[4]/button[1]/span[2]"));
		
		
		
		//String Staff_TableName=driver.findElement(By.xpath("//td[text()='"+Value+"']/parent::tr/td[2]")).getText();
		//System.out.println(Branch_TableName);
		//System.out.println(Branch_TableCode);
		
		
		//String Staff_TableBranch=driver.findElement(By.xpath("//td[text()='"+Value+"']/parent::tr/td[3]")).getText();
		//System.out.println(Branch_TableCode);
		
		
		//String Staff_TableID=driver.findElement(By.xpath("//td[text()='"+Value+"']/parent::tr/td[1]/a")).getText();
		//System.out.println(Branch_TableID);
		boolean f1=false;
		
		while(!f1)
		{
		System.out.println("inwhile");
		boolean isele=isElementPresent("//a[text()='"+Value+"']/parent::td/parent::tr/td[4]/button[1]/span[2]");
		System.out.println(isele);
		if(isele)
			
		{
			//wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='"+Value+"']/parent::td/parent::tr/td[4]/button[1]/span[2]")));				
		
			WebElement Staff_TableButtonview=driver.findElement(By.xpath("//a[text()='"+Value+"']/parent::td/parent::tr/td[4]/button[1]/span[2]"));
			String Staff_TableName=driver.findElement(By.xpath("//a[text()='"+Value+"']/parent::td/following-sibling::td[1]")).getText();
			String Staff_TableBranch=driver.findElement(By.xpath("//a[text()='"+Value+"']/parent::td/following-sibling::td[2]")).getText();
			String Staff_TableID=driver.findElement(By.xpath("//a[text()='"+Value+"']/parent::td/a")).getText();
			Staff_TableButtonview.click();
			wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@href='#/staff']")));
			String heading=driver.findElement(By.xpath("//h2[@class='ng-binding']")).getText();
			System.out.println(heading);
			String[] staffid=heading.split(" ");
			System.out.println(staffid[1]);
			String view_name=driver.findElement(By.xpath("//div[@class='table-responsive']/table/tbody/tr[1]/td[2]/input")).getAttribute("value");
			System.out.println(view_name);
			String view_branch=driver.findElement(By.xpath("//div[@class='table-responsive']/table/tbody/tr[2]/td[2]/input")).getAttribute("value");	
			System.out.println(view_branch);
			if(staffid[1].contentEquals(Staff_TableID))
			{
				System.out.println("Correct ID displayed in view screen");
				if(view_name.contentEquals(Staff_TableName))
				{
					System.out.println("Correct Name displayed in view screen");
				if(view_branch.contentEquals(Staff_TableBranch))
				{
					System.out.println("Correct Code displayed in view screen");
				}

				staffviewresult="Pass";
				f1=true;
				}
				
				
			}
			
		//	else
			//{
			//	staffviewresult="Fail";
			//	System.out.println("ID not matching");
			//}
			
			
		}
			else
			{
				System.out.println("View");
				driver.findElement(By.xpath("//ul[@class='pager']/li[3]/a")).isDisplayed();
				
					System.out.println("all");
					driver.findElement(By.xpath("//ul[@class='pager']/li[3]/a")).click();
				   //wt.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[@href='#/staff']")));
					//System.out.println("View details are not matching");
			}
			
		}
			
			
		
			driver.findElement(By.xpath("//button[@href='#/staff']")).click();
			
		
		
		return staffviewresult;
	}
	






public String delete_branchtable(String Value)
{

	String branchresult = null;
	wt=new WebDriverWait(driver,50);
	driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	WebElement Branch_TableButtondelete=driver.findElement(By.xpath("//td[text()='"+Value+"']/parent::tr/td[4]/button[3]/span[2]"));
	String Branch_TableName=driver.findElement(By.xpath("//td[text()='"+Value+"']/parent::tr/td[2]")).getText();
	System.out.println(Branch_TableName);
	//System.out.println(Branch_TableCode);
	String Branch_TableCode=driver.findElement(By.xpath("//td[text()='"+Value+"']/parent::tr/td[3]")).getText();
	System.out.println(Branch_TableCode);
	String Branch_TableID=driver.findElement(By.xpath("//td[text()='"+Value+"']/parent::tr/td[1]/a")).getText();
	System.out.println(Branch_TableID);
	
	if( wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='"+Value+"']/parent::tr/td[4]/button[3]/span[2]"))) != null)
			
	{
		System.out.println("hiassa");
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		Branch_TableButtondelete.click();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.switchTo().activeElement();
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@name='deleteForm']/div[@class='modal-footer']/button[2]/span[2]")));
		String delete_msg="Are you sure you want to delete Branch"+" "+Branch_TableID+"?";
		
		//driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		String conf_msg=driver.findElement(By.xpath("//form[@name='deleteForm']/div[2]/p")).getText();
		
		//Alert a =driver.switchTo().alert();
		//String msg=a.getText();
		System.out.println(delete_msg);
		System.out.println(conf_msg);
		if(delete_msg.contentEquals(conf_msg))
		{
			System.out.println("hi kiran");
			wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@name='deleteForm']/div[3]/button[2]/span[2]")));
			wt.until(ExpectedConditions.elementToBeClickable(By.xpath("//form[@name='deleteForm']/div[3]/button[2]")));
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//form[@name='deleteForm']/div[3]/button[2]")).click();
			wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Home')]")));
			driver.findElement(By.linkText("searchQuery")).sendKeys(Value);
			driver.findElement(By.xpath("//button[@ng-click='search()']")).click();
			if(wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='table-responsive']/table/tbody/tr/td[3]")))==null)
			{
				System.out.println("Branch Deleted Successfully");
				branchresult="Pass";
			}
 
		}	
		
		else
		{
			driver.findElement(By.xpath("//form[@name='deleteForm']/div[3]/button[1]/span[2]")).click();
			wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Home')]")));
			System.out.println("Branch not Deleted Successfully");
			branchresult="Fail";
		}
		
			
			
		}
		
		
		
		
	
	
	return branchresult;
}*/
