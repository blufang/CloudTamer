package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class phptravelsHomePage {

	private WebDriver driver;
	
	public phptravelsHomePage (WebDriver pass) {	
			driver = pass;
	}
	
	public void launchPage() {
		driver.get("https://www.phptravels.net/home");
	}
	
	public String getURL() {
		return driver.getCurrentUrl();
	}
	public void selectTab (String type) {
		driver.findElement(By.xpath("//a[@data-name = '" + type + "']" )).click();;
	}
	
	public WebElement getHotelSubmitButton () {
		return driver.findElement(By.xpath("//*[@id=\"hotels\"]/div/div/form/div/div/div[4]/button"));
	}
	
	public WebElement getHotelsSearchBar() {
		
		driver.findElement(By.xpath("//*[@id=\"s2id_autogen1\"]")).click();
		return driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/div/input"));
	}
	
	public WebElement getSearchBar() {
		return driver.findElement(By.xpath("//*[@id=\"s2id_autogen1\"]"));
	}
	
	public WebElement getDestination() {
		return driver.findElement(By.xpath("/html/body/div[6]/div/input"));
	}
	
	public WebElement getCheckinDate() {
		return driver.findElement(By.xpath("//*[@id = 'checkin']"));
	}
	
	public WebElement getCheckOutDate() {
		return driver.findElement(By.xpath("//input[@id = 'checkout']"));
	}
	
	public WebElement adultCountPlus() {
		return driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[3]/div/div/div/div/div/div/div[1]/div/div/form/div/div/div[3]/div/div/div/div/div/div/div[1]/div/div[2]/div/span/button[1]"));
	}
	
	public WebElement childCountPlus() {
		return driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[3]/div/div/div/div/div/div/div[1]/div/div/form/div/div/div[3]/div/div/div/div/div/div/div[2]/div/div[2]/div/span/button[1]"));
	}
	
}
