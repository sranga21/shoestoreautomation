package com.sl.shoestore;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.sl.base.BasePage;


public class ShoeStoreResultPage extends BasePage{
	
	
	public static By searchResultsPageTitle = By.cssSelector(".title");
	
	
	public String getSearchResultPageTitle(){		
		String searchResultPageTitle = driver.findElement(searchResultsPageTitle).getText();		
		return searchResultPageTitle;		
	}
	

}
