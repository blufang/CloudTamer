package testScripts;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.phptravelsHomePage;

public class phpTravelTestScript {
	
	WebDriver driver;
	phptravelsHomePage php;
	
	// this method sets up our test environment
	@BeforeTest
	
	public void setupTestEnvironment() {
		
		System.setProperty("webdriver.chrome.driver", "/Users/modollas/Documents/GitHub/AutomationTestingFall2020/Selenium_JAR/WebDriver/chromedriver");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		php = new phptravelsHomePage(driver);
		
	}
	
	
	// Test Case #1: On the Hotels tab, search for the Alzer Hotel Istanbul. Check-in for a week from the current date with 2 adults and 2 children then click submit
	@Test
	
	public void testHotelCheckIn() {
	
		php.launchPage();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		php.selectTab("hotels");
		
		// select the Alzer Hotel, Istanbul in the search bar

		php.getHotelsSearchBar().sendKeys("Alzer Hotel Istanbul");
		driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/ul/li/ul/li")).click();
		
		// Enter Checkin Date for a week
		Assert.assertTrue(php.getCheckinDate().isDisplayed());
		php.getCheckinDate().clear();
		php.getCheckinDate().sendKeys("07/08/2021");
		//Enter Checout date a week after checkin
		Assert.assertTrue(php.getCheckOutDate().isDisplayed());
		php.getCheckOutDate().clear();
		php.getCheckOutDate().sendKeys("14/08/2021");
		//Add 2 children to the ticket
		Assert.assertTrue(php.childCountPlus().isDisplayed());
		php.childCountPlus().click();
		php.childCountPlus().click();
		//hit submit
		php.getHotelSubmitButton().click();
		
		Assert.assertEquals(php.getURL(), "https://www.phptravels.net/notfound");
	}
	
	@Test
	
	public void testFlightTab() {
	
		php.launchPage();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		//switch to flights tab
		php.selectTab("flights");
		//check if "one-way" button is selected
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"flightSearchRadio-2\"]")).isSelected());
		
		//Check Labels
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"flights\"]/div/div/form/div/div/div[3]/div[1]/div/div[1]/div/label")).getText(), "FROM");
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"flights\"]/div/div/form/div/div/div[3]/div[1]/div/div[2]/div/label")).getText(), "TO");
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"airDatepickerRange-flight\"]/div[1]/div/label")).getText(), "DEPART");
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"flights\"]/div/div/form/div/div/div[3]/div[3]/div/div/div[1]/div/label")).getText(), "ADULTS");
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"flights\"]/div/div/form/div/div/div[3]/div[3]/div/div/div[2]/div/label")).getText(), "CHILD");
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"flights\"]/div/div/form/div/div/div[3]/div[3]/div/div/div[3]/div/label")).getText(), "INFANT");
		
		// set cabin class to first
		WebElement s = driver.findElement(By.xpath("//select[@name = 'cabinclass']"));
		Select select = new Select(s);
		// element not visibile by default, make it visible
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', '/* display: none; */')", s);
		//perform click on "first"
		select.selectByIndex(0);

	}
	
	@Test
	
	public void testFlightTab2() {
		php.launchPage();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		//switch to flights tab
		php.selectTab("flights");
		
		WebDriverWait w = new WebDriverWait(driver, 10);
		//click on "round trip" radio button
		WebElement roundTrip = w.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"flights\"]/div/div/form/div/div/div[1]/div[1]/div/div[2]/label")));
		roundTrip.click();
		WebElement x = driver.findElement(By.xpath("//*[@id=\"FlightsDateEnd\"]"));
		//Assert that return date option is visible
		Assert.assertTrue(x.isDisplayed());
		//Re click the "one way" tab
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		WebElement oneWay = driver.findElement(By.xpath("//*[@id=\"flights\"]/div/div/form/div/div/div[1]/div[1]/div/div[1]/label"));
		oneWay.click();
		//check that the return date is no longer visible
		Assert.assertFalse(x.isDisplayed());
	}
	
	@Test
		
		public void testTabs() {
			php.launchPage();
			
			String[] tabs = {"flights", "cars", "rentals", "tours", "boats","hotels"};
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			
			for (int i = 0; i < tabs.length; i++) {
				php.selectTab(tabs[i]);
				driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
			}
			Assert.assertTrue(php.getSearchBar().isDisplayed());
		}

	@AfterTest
	
	public void postTestBehavior() {
		
		driver.quit();
		php = null;
	}
	
	
}
