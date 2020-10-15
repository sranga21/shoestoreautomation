package com.sl.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.sl.ExtentListeners.ExtentListeners;
import com.sl.assertion.SoftAssertion;
import com.sl.utilities.DriverFactory;
import com.sl.utilities.DriverManager;


public class BaseTest {

	private WebDriver driver;
	public static Properties envProps = new Properties();
	private FileInputStream fis;
	public Logger log = Logger.getLogger(BaseTest.class);
	public boolean grid=false;
	public SoftAssertion softAssertion = new SoftAssertion();
	
	public SoftAssert sa = new SoftAssert();

	
	@BeforeSuite(alwaysRun = true)
	@Parameters({ "environment" })
	public void setUpFramework(String environment) {

		configureLogging();
		DriverFactory.setGridPath("http://localhost:4444/wd/hub");
		DriverFactory.setConfigPropertyFilePath(System.getProperty("user.dir") + String.format("//src//test//resources//properties//env//%s.properties",environment));	
		
        if (System.getProperty("os.name").equalsIgnoreCase("mac")) {        	
        	DriverFactory.setChromeDriverExePath(System.getProperty("user.dir") + "//src//test//resources//executables//chromedriver");
    		DriverFactory.setGeckoDriverExePath(System.getProperty("user.dir") + "//src//test//resources//executables//geckodriver");    	
        }else {		
		DriverFactory.setChromeDriverExePath(System.getProperty("user.dir") + "//src//test//resources//executables//chromedriver.exe");
		//DriverFactory.setGeckoDriverExePath(System.getProperty("user.dir") + "//src//test//resources//executables//geckodriver.exe");
		//DriverFactory.setIeDriverExePath(System.getProperty("user.dir") + "//src//test//resources//executables//IEDriverServer.exe");
        }
        
        
		/*
		 * Initialize properties Initialize logs load executables
		 * 
		 */
		try {
			fis = new FileInputStream(DriverFactory.getConfigPropertyFilePath());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			envProps.load(fis);	
			log.info("Config properties file loaded");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public void logInfo(String message) {
		
		ExtentListeners.testReport.get().info(message);
	}

	public void configureLogging() {
		String log4jConfigFile = System.getProperty("user.dir") + File.separator + "\\src\\test\\\\resources\\properties" + File.separator + "log4j.properties";
		PropertyConfigurator.configure(log4jConfigFile);
	}

	
	@BeforeMethod
	@Parameters({"browser"})
	public void openBrowser(String browser) {
		
	
		if(System.getenv("ExecutionType")!=null && System.getenv("ExecutionType").equals("Grid")) {
			
			grid=false;
		}
		

		DriverFactory.setRemote(grid);

		if (DriverFactory.isRemote()) {
			DesiredCapabilities cap = null;

			if (browser.equals("firefox")) {

				cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				cap.setPlatform(Platform.ANY);

			} else if (browser.equals("chrome")) {

				cap = DesiredCapabilities.chrome();
				cap.setBrowserName("chrome");
				cap.setPlatform(Platform.ANY);
			} else if (browser.equals("ie")) {

				cap = DesiredCapabilities.internetExplorer();
				cap.setBrowserName("iexplore");
				cap.setPlatform(Platform.WIN10);
			}

			try {
				driver = new RemoteWebDriver(new URL(DriverFactory.getGridPath()), cap);
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else

		if (browser.equalsIgnoreCase("chrome")) {
			System.out.println("Launching : " + browser);
			System.setProperty("webdriver.chrome.driver",
					DriverFactory.getChromeDriverExePath());
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.out.println("Launching : " + browser);
			System.setProperty("webdriver.gecko.driver",
					DriverFactory.getGeckoDriverExePath());
			driver = new FirefoxDriver();

		}

		DriverManager.setWebDriver(driver);
		log.info("Driver Initialized !!!");
		DriverManager.getDriver().manage().window().maximize();
		DriverManager.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		DriverManager.getDriver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void tearDown() throws Exception {
		DriverManager.getDriver().quit();
		log.info("Test Case Execution Completed !!!");
		//softAssertion.verifyAll();
	}
	
	
}
