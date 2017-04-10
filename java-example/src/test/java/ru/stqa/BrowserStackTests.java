package ru.stqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserStackTests {
	public static final String USERNAME = "wojtasdr1";
	public static final String AUTOMATE_KEY = "7woZtVn31szLtQs6mhjp";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	
	private WebDriver driver;
	private WebDriverWait wait;

	@BeforeTest
	public void start() throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("browser", "chrome");
		caps.setCapability("browserstack.debug", "true");
		caps.setCapability("build", "First build");
	    caps.setCapability("os", "Windows");
	    caps.setCapability("os_version", "XP");
	    caps.setCapability("browserstack.debug", "true");

		driver = new RemoteWebDriver(new URL(URL), caps);
		wait = new WebDriverWait(driver, 10);

	}

	@Test
	public void loginLiteCartWithCorrectCrendentials() {
		driver.get("http://www.google.com");
		driver.findElement(By.name("q")).sendKeys("webdriver");
		driver.findElement(By.name("btnG")).click();
		wait.until(ExpectedConditions.titleIs("webdriver - Google zoeken"));
	}

	@AfterTest
	public void stop() {
		driver.quit();
		driver = null;
	}
}
