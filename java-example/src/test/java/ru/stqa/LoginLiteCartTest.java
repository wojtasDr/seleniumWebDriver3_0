package ru.stqa;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utils.FindElementsMethods;

public class LoginLiteCartTest {
	private WebDriver driver;
	private WebDriverWait wait;
	
	//Login Page WebElements
	private WebElement userNameInput;
	private WebElement passwordInput;
	private WebElement loginButton;
	
	//Login credentials
	private final String userName = "admin";
	private final String password = "admin";	
	
	private final String homePageTitle = "My Store";
	
	//Locators
	private By pageTitleLocator = By.cssSelector("td#content h1");
	private By menuItemsLocator = By.cssSelector("li#app->a");
	private By menuSubItemsLocator = By.xpath("//li[starts-with(@id,'doc-')]/a");
	
	//WebElements
	private WebElement pageTitle;
	private List<WebElement> menuItems = new ArrayList<WebElement>();
	private List<WebElement> menuSubItems = new ArrayList<WebElement>();
	
	@BeforeTest
	public void start() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\wojtas_dr\\.m2\\repository\\chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10);
	}

	@Test
	public void loginLiteCartWithCorrectCrendentials() {
		driver.get("http://localhost/litecart/admin/login.php");
		
		//Type password
		userNameInput = driver.findElement(By.name("username"));
		userNameInput.clear();
		userNameInput.sendKeys(userName);
		
		//Type userName
		passwordInput = driver.findElement(By.name("password"));
		passwordInput.clear();
		passwordInput.sendKeys(password);
		
		//Type userName
		loginButton = driver.findElement(By.name("login"));
		loginButton.click();
		
		wait.until(ExpectedConditions.titleIs(homePageTitle));
	}
	
	@Test
	public void navigateMenuItemsTest(){
		menuItems = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(menuItemsLocator));

		for (int i = 0; i < menuItems.size(); i++){
			menuItems.get(i).click();
			
			if (FindElementsMethods.isElementPresent(driver, menuSubItemsLocator)){
				menuSubItems = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(menuSubItemsLocator));
			
				for (int j = 0; j < menuSubItems.size(); j++){
					menuSubItems.get(j).click();
					menuSubItems = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(menuSubItemsLocator));
				
					//pageTitle = wait.until(ExpectedConditions.presenceOfElementLocated(pageTitleLocator));
					//System.out.println("Title1: " + pageTitle.getText());
					assert(FindElementsMethods.isElementPresent(driver, pageTitleLocator));
				}
			}
			else {
				//pageTitle = wait.until(ExpectedConditions.presenceOfElementLocated(pageTitleLocator));
				//System.out.println("Title: " + pageTitle.getText());
				assert(FindElementsMethods.isElementPresent(driver, pageTitleLocator));
			}
			menuItems = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(menuItemsLocator));
		}
	}

	@AfterTest
	public void stop() {
		driver.quit();
		driver = null;
	}
}
