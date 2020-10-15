package com.sl.base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sl.ExtentListeners.ExtentListeners;
import com.sl.utilities.DriverManager;

public abstract class BasePage {

	
	
	protected WebDriver driver;
	

	    public BasePage() {
	        this.driver = DriverManager.getDriver();
	    }


		
		public void click(By element, String elementName) {			
			WebElement btnElement  = driver.findElement(element);			
			btnElement.click();
			ExtentListeners.testReport.get().info("Clicking on : "+elementName);			
		}
		
		public void type(WebElement element, String value, String elementName) {
			
			element.sendKeys(value);
			ExtentListeners.testReport.get().info("Typing in : "+elementName+" entered the value as : "+value);
		
		}
		
		public void switchToiFrame(WebElement iframe1, WebElement iframe2, String elementName) throws InterruptedException {
			driver.switchTo().defaultContent();
			driver.switchTo().frame(iframe1);
			driver.switchTo().frame(iframe2);
			ExtentListeners.testReport.get().info("Switching to  : "+elementName);
		}
		
		public void setDropDownValue(By element, String valuetoSet){
			
			WebElement dropdownElement  = driver.findElement(element);			
			boolean optionFound = false;
			
			Select dropdown = new Select(dropdownElement);
			String valuedis = dropdownElement.getAttribute("value");
			List<WebElement> options = dropdown.getOptions();
			for (WebElement option : options) {
				String strOption = option.getText().trim();
				strOption = strOption.replace("\n", "");
				if (strOption.equalsIgnoreCase(valuetoSet)) {
					option.click();
					optionFound = true;
					break;
				}
			}

			if (optionFound == true) {
				WebElement selection = getWhenVisible(dropdown.getFirstSelectedOption());
				valuedis = selection.getText();
				valuedis = valuedis.replace("\n", "");
			} else {
				System.out.println("Option not found in weblist: " + valuetoSet);
			}
			
		}
		
		public WebElement getWhenVisible(WebElement element) {		
				WebDriverWait wait = new WebDriverWait(driver, 10);
				WebElement e = wait.until(ExpectedConditions.visibilityOf(element));
				return e;
		}
		
	
	
}
