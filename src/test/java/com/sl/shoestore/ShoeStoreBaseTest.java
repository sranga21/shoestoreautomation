package com.sl.shoestore;

import java.util.Hashtable;

import org.testng.Assert;

import com.sl.base.BaseTest;
import com.sl.utilities.DriverManager;

public class ShoeStoreBaseTest extends BaseTest{
	
	public ShoeStoreHomePage shoeStoreHomePage;
	public ShoeStoreResultPage shoeStoreResultPage;
	
	
		
	public void openApplication(String url) {
		DriverManager.getDriver().navigate().to(url);
		shoeStoreHomePage =new ShoeStoreHomePage();
	 }
	
	public ShoeStoreResultPage searchByBrandName(Hashtable<String, String> data){
		return shoeStoreResultPage= shoeStoreHomePage.searchByBrand(data.get("Brand"));
	}
	
	public void validateShoeStoreResultPageTitle(Hashtable<String, String> data){
		Assert.assertTrue(shoeStoreResultPage.getSearchResultPageTitle().contains(data.get("Brand")));		
	}
		
	public void remindMeForNewShoes(Hashtable<String, String> data){
		shoeStoreHomePage.remindMeForNewShoes(data.get("Email"));
	}
	
	public void validateRemindMeForNewShoesSuccessMsg(Hashtable<String, String> data){
		Assert.assertTrue(shoeStoreHomePage.getRemindForNewShoesSuccessMessage().contains(data.get("RemindMeForNewShoeSuccessMsg")));		
	}
	
	
	public void remindMeForThisShoe(Hashtable<String, String> data) throws InterruptedException{
		shoeStoreHomePage.remindMeForThisShoe(data.get("Email"));
	}
	
	public void validateRemindMeForThisShoesSuccessMsg(Hashtable<String, String> data){
		Assert.assertTrue(shoeStoreHomePage.getRemindForThisShoesSuccessMessage().contains(data.get("RemindMeForThisShoeSuccessMsg")));		
	}


}
