package com.sl.shoestore;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sl.ExtentListeners.ExtentListeners;
import com.sl.base.BasePage;
import com.sl.utilities.DriverManager;


public class ShoeStoreHomePage extends BasePage{
	

	
	public static By homePageSearchBtn = By.id("search_button");

	public static By brandDropDown = By.id("brand");
	
	public static By remindMeNewShoesEmailTxBx = By.id("remind_email_input");
	
	public static By remindEmailSubmitBtn = By.id("remind_email_submit");
	
	public static By remindForNewShoessuccessMsg = By.cssSelector(".flash.flash_success");
	
	public static By topMenuMonthLink = By.xpath("//a[@href='/months/november']");
	
	public static By remindMeThisShoesEmailTxBx = By.xpath("//input[@name='email']");
	
	public static By remindMeThisShoeSubmitBtn = By.xpath("//input[@type='submit']");
		
	public static By remindMeThisShoesSuccessMsg = By.cssSelector(".flash.notice");
	
	
	//a[@href='/months/november']
	
		
	public ShoeStoreResultPage searchByBrand(String brand) {
		setDropDownValue(brandDropDown,brand);
		click(homePageSearchBtn, "Search Button");	
		return new ShoeStoreResultPage();
	}
		
	public void remindMeForNewShoes(String email) {
		WebElement remindMeNewShoesTxBxElement  = driver.findElement(remindMeNewShoesEmailTxBx);	
		remindMeNewShoesTxBxElement.sendKeys(email);
		click(remindEmailSubmitBtn, "Remind For New Shoes Submit Button");
	}
	
	public String getRemindForNewShoesSuccessMessage(){		
		String remindForNewShoesSuccessMsg = driver.findElement(remindForNewShoessuccessMsg).getText();		
		return remindForNewShoesSuccessMsg;		
	}
	
	public void remindMeForThisShoe(String email) throws InterruptedException {		
		driver.findElement(topMenuMonthLink).click();
		
		List<WebElement> emailTxBoxList = driver.findElements(remindMeThisShoesEmailTxBx);
		WebDriverWait wait = new WebDriverWait(driver, 5, 500);
		wait.until(ExpectedConditions.visibilityOf(emailTxBoxList.get(1)));
		Thread.sleep(2000);
		emailTxBoxList.get(1).sendKeys(email);		
		List<WebElement> submitBtnList = driver.findElements(remindMeThisShoeSubmitBtn);
		submitBtnList.get(2).isEnabled();
		submitBtnList.get(2).click();
	}
	
	public String getRemindForThisShoesSuccessMessage(){		
		String remindMeThisShoesSuccessMsgStr = driver.findElement(remindMeThisShoesSuccessMsg).getText();		
		return remindMeThisShoesSuccessMsgStr;		
	}
	
}
