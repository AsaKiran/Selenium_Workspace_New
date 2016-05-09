package com.gurukula;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Gurukula_Project {

	WebDriver driver;
	WebDriverWait wait;
	
	@Test
	
	public void navigate_url()
	{
	  try
	  {
		
	   driver = new FirefoxDriver();
	   wait=new WebDriverWait(driver,50);
	   String title="gurukula";
	   driver.get("http://localhost:8080");
	   String gettitle=driver.getTitle();
       if(title.contentEquals(gettitle))
	   {
		   System.out.println("Pass:Website Navigation Successfull");
		   
	   }
	   
	   else
	   {
		   System.out.println("Fail:Wesite Navigation Fail");
		   driver.close();
	   }
	  // System.out.print(gettitle);
	  
	     Thread.sleep(2000);
       driver.findElement(By.xpath("//div/a[contains(@href,'login')]")).click();
	}
	catch(Exception e)
	{
		System.out.println("Exception"+e);
	}
 
}

}