package ru.stqa;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.SoftAssertions;
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
	
	private SoftAssertions softAssertions;
	
	//Login credentials
	private final String userName = "admin";
	private final String password = "admin";	
	
	private final String homePageTitle = "My Store";
	private List<String> countriesList = new ArrayList<String>();
	private List<String> zonesCodesList = new ArrayList<String>();
	private List<String> geoZonesList = new ArrayList<String>();
	//---Locators
	
	//Login Page
	private By userNameInputLocator = By.name("username");
	private By passwordInputLocator = By.name("password");
	private By loginButtonLocator = By.name("login");
	
	//Home Page
	private By pageTitleLocator = By.cssSelector("td#content h1");
	
	//Navigation Page
	private By menuItemsLocator = By.cssSelector("li#app->a");
	private By menuSubItemsLocator = By.xpath("//li[starts-with(@id,'doc-')]/a");
	private By countryButtonLocator = By.xpath("//a/span[starts-with(.,'Countries')]");
	private By geoZonesButtonLocator = By.xpath("//a/span[starts-with(.,'Geo Zones')]");
	
	//Articles Page
	private By allArticlesLocator = By.cssSelector("div.content li[class^=product]");
	private By articleStickerLocator = By.xpath(".//div[starts-with(@class,'sticker')]");
	
	//Countries Page
	private By countriesTableRowsLocator = By.xpath("//tr[@class='row']");
	private By countryNameLocator = By.xpath(".//td/a[contains(@href,'country')][1]");
	private By countryZoneLocator = By.xpath(".//td[6]");
	
	//Edit Country Page
	private By countryZoneCodesLocator = By.xpath("//tr[not(@class='header')]/td[2]/input[@type='hidden']/..");
	
	//GeoZones Page
	private By geoZonesCountryLocator = By.xpath("//tr[@class='row']//td/a[not(@title)]"); 
	
	//Edit GeoZones Page
	private By geoZonesLocator = By.xpath("//table[@id]//tr[not(@class)]/td/select[not(@class)]");
	
	//---WebElements
	
	//Login Page
	private WebElement userNameInput;
	private WebElement passwordInput;
	private WebElement loginButton;
	
	//Navigation Page
	private WebElement countryButton;
	private WebElement geoZoneButton;
	private List<WebElement> menuItems = new ArrayList<WebElement>();
	private List<WebElement> menuSubItems = new ArrayList<WebElement>();
	
	//Articles Page
	private List<WebElement> allArticles = new ArrayList<WebElement>();
	
	//Countries Page
	private List<WebElement> countriesTableRows = new ArrayList<WebElement>();
	
	//Edit Country Page
	private List<WebElement> countryZoneCodes = new ArrayList<WebElement>();
	
	//GeoZones Page
	private List<WebElement> geoZonesCountry = new ArrayList<WebElement>();
	
	//Edit GeoZones Page
	private List<WebElement> geoZones = new ArrayList<WebElement>();
	
		
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
		userNameInput = driver.findElement(userNameInputLocator);
		userNameInput.clear();
		userNameInput.sendKeys(userName);
		
		//Type userName
		passwordInput = driver.findElement(passwordInputLocator);
		passwordInput.clear();
		passwordInput.sendKeys(password);
		
		//Type userName
		loginButton = driver.findElement(loginButtonLocator);
		loginButton.click();
		
		wait.until(ExpectedConditions.titleIs(homePageTitle));
	}
	
	@Test (dependsOnMethods = { "loginLiteCartWithCorrectCrendentials" })
	public void navigateMenuItemsTest(){
		driver.get("http://localhost/litecart/admin");
		
		menuItems = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(menuItemsLocator));

		for (int i = 0; i < menuItems.size(); i++){
			menuItems.get(i).click();
			
			if (FindElementsMethods.isElementPresent(driver, menuSubItemsLocator)){
				menuSubItems = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(menuSubItemsLocator));
			
				for (int j = 0; j < menuSubItems.size(); j++){
					menuSubItems.get(j).click();
					menuSubItems = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(menuSubItemsLocator));
				
					assert(FindElementsMethods.isElementPresent(driver, pageTitleLocator));
				}
			}
			else {
				assert(FindElementsMethods.isElementPresent(driver, pageTitleLocator));
			}
			menuItems = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(menuItemsLocator));
		}
	}
	
	@Test  (dependsOnMethods = { "loginLiteCartWithCorrectCrendentials" })
	public void checkNumberOfStickersTest(){
		driver.get("http://localhost/litecart/en/");
		
		allArticles = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(allArticlesLocator));
		
		for (WebElement article : allArticles){
			int numberOfStickers = 0;
			numberOfStickers = FindElementsMethods.getNumberOfElements(article, articleStickerLocator);
			assert(numberOfStickers == 1);
		}	
	}
	
	@Test (dependsOnMethods = { "loginLiteCartWithCorrectCrendentials" })
	public void checkCountriesAndZonesSortingTest(){
		softAssertions = new SoftAssertions();
		
		driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
		
		countriesTableRows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(countriesTableRowsLocator));
		
		for (int i = 0; i < countriesTableRows.size(); i++){
			int numberOfZones = 0;
			
			countriesList.add(countriesTableRows.get(i).findElement(countryNameLocator).getText());

			numberOfZones = Integer.parseInt(countriesTableRows.get(i).findElement(countryZoneLocator).getText());
			
			if(numberOfZones != 0){
				countriesTableRows.get(i).findElement(countryNameLocator).click();
				
				countryZoneCodes = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(countryZoneCodesLocator));

				for (WebElement zone : countryZoneCodes){
					zonesCodesList.add(zone.getText());
				}
				
				softAssertions.assertThat(zonesCodesList).isSorted();
				
				countryButton = driver.findElement(countryButtonLocator);
				countryButton.click();
				
				countriesTableRows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(countriesTableRowsLocator));	
			}
		}
		softAssertions.assertThat(countriesList).isSorted();
		
		softAssertions.assertAll();
	}
	
	@Test (dependsOnMethods = { "loginLiteCartWithCorrectCrendentials" })
	public void checkZonesSortingTest(){
		softAssertions = new SoftAssertions();
		
		driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
		
		geoZonesCountry = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(geoZonesCountryLocator));
		System.out.println("Number of found countries "+ geoZonesCountry.size());
		for (int i = 0; i < geoZonesCountry.size(); i++){
			geoZonesList = new ArrayList<String>();
			
			geoZonesCountry.get(i).click();
			
			geoZones = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(geoZonesLocator));
			
			for (WebElement gZ : geoZones){
				geoZonesList.add(gZ.getAttribute("value"));		
			}
			softAssertions.assertThat(geoZonesList).isSorted();
			
			geoZoneButton = driver.findElement(geoZonesButtonLocator);
			geoZoneButton.click();
			
			geoZonesCountry = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(geoZonesCountryLocator));
		}
		softAssertions.assertAll();
	}
	
	@AfterTest
	public void stop() {
		driver.quit();
		driver = null;
	}
}
