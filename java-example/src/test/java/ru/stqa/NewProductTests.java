package ru.stqa;

import java.util.NavigableMap;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NewProductTests {
	private WebDriver driver;
	private WebDriverWait wait;
	
	private final String userName = "admin";
	private final String password = "admin";	
	private final String homePageTitle = "My Store";

	//---Locators
	//Login Page
	private By userNameInputLocator = By.name("username");
	private By passwordInputLocator = By.name("password");
	private By loginButtonLocator = By.name("login");
	
	//Admin Page
	private By catalogButtonLocator = By.xpath("//a/span[text()='Catalog']/..");
	
	//Add new product page
	private By statusRadioEnabledLocator = By.xpath("//label[text()=' Enabled']/input[@type='radio']");
	private By statusRadioDisabledLocator = By.xpath("//label[text()=' Disabled']/input[@type='radio']");
	private By productNameLocator = By.xpath("//input[contains(@name,'name')]");
	private By productCodeLocator = By.xpath("//input[contains(@name,'code')]");
	private By rootFolderCheckboxLocator = By.xpath("//input[@data-name='Root']");
	private By rubberDucksFolderCheckboxLocator = By.xpath("//input[@data-name='Rubber Ducks']");
	private By subcategoryFolderCheckboxLocator = By.xpath("//input[@data-name='Subcategory']");
	private By defaultCategorySelectLocator = By.cssSelector("select[name='default_category_id']");
	private By genderFemaleCheckBoxLocator = By.xpath("//td[text()='Female']/../td/input");
	private By genderMaleCheckBoxLocator = By.xpath("//td[text()='Male']/../td/input");
	private By genderUnisexCheckBoxLocator = By.xpath("//td[text()='Unisex']/../td/input");
	private By quantityLocator = By.xpath("//td/strong[text()='Quantity']/../input");
	private By quantityUnitLocator = By.xpath("//td/strong[text()='Quantity Unit']/../select");
	private By deliveryStatusLocator = By.xpath("//td/strong[text()='Delivery Status']/../select");
	private By soldOutStatusLocator = By.xpath("//td/strong[text()='Sold Out Status']/../select");
	private By dateValidFromLocator = By.xpath("//td/strong[text()='Date Valid From']/../input");
	private By dateValidToLocator = By.xpath("//td/strong[text()='Date Valid To']/../input");
	
	//Catalog Page 
	private By addNewProductLocator = By.xpath("//a[text()=' Add New Product']");

	
	//---WebElements
	//Login Page
	private WebElement userNameInput;
	private WebElement passwordInput;
	private WebElement loginButton;
	//Admin Page
	private WebElement catalogButton;
	//Catalog Page 
	private WebElement addNewProduct;
	//Add new product page
	private WebElement statusRadioEnabled;
	private WebElement statusRadioDisabled;
	private WebElement productName;
	private WebElement productCode;
	private WebElement rootFolderCheckbox;
	private WebElement rubberDucksFolderCheckbox;
	private WebElement subcategoryFolderCheckbox;
	private WebElement defaultCategorySelect;
	private WebElement genderFemaleCheckBox;
	private WebElement genderMaleCheckBox;
	private WebElement genderUnisexCheckBox;
	private WebElement quantity;
	private WebElement quantityUnit;
	private WebElement deliveryStatus;
	private WebElement soldOutStatus;
	private WebElement dateValidFrom;
	private WebElement dateValidTo;
	
	@BeforeTest
	public void start() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\wojtas_dr\\.m2\\repository\\chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10);
	}
	
	@Test
	public void checkZonesSortingTest(){
		driver.get("http://localhost/litecart/admin/");
		
		this.loginLiteCartWithCorrectCrendentials(userName, password);
		
		catalogButton = wait.until(ExpectedConditions.presenceOfElementLocated(catalogButtonLocator));
		catalogButton.click();
		
		addNewProduct = wait.until(ExpectedConditions.presenceOfElementLocated(addNewProductLocator));
		addNewProduct.click();
		
		NavigableMap<String,String> newProductGeneralData = new TreeMap<String,String>();
		newProductGeneralData.put("StatusEnabled", "enabled");
		newProductGeneralData.put("Name", "testName");
		newProductGeneralData.put("Code", "testCode");
		newProductGeneralData.put("RootFolder", "Checked");
		newProductGeneralData.put("RuberDucksFolder", "Checked");
		newProductGeneralData.put("SubcategoryFolder", "Checked");
		newProductGeneralData.put("DefaultCategory", "Rubber Ducks");
		newProductGeneralData.put("Female", "Checked");
		newProductGeneralData.put("Male", "Checked");
		newProductGeneralData.put("Unisex", "Checked");
		newProductGeneralData.put("Quantity", "8");
		newProductGeneralData.put("QuantityUnit", "pcs");
		newProductGeneralData.put("DeliveryStatus", "3-5 days");
		newProductGeneralData.put("SoldOutStatus", "Sold out");
		
		this.addNewProductGeneralData(newProductGeneralData);
			
	}

	@AfterTest
	public void stop() {
		driver.quit();
		driver = null;
	}
	
	public void addNewProductGeneralData(NavigableMap<String,String> newProductGeneralData){
		
		if(newProductGeneralData.get("StatusEnabled").equals("enabled")){
			statusRadioEnabled = wait.until(ExpectedConditions.presenceOfElementLocated(statusRadioEnabledLocator));
			statusRadioEnabled.click();
		} else {
			statusRadioDisabled = wait.until(ExpectedConditions.presenceOfElementLocated(statusRadioDisabledLocator));
			statusRadioDisabled.click();
		}
		
		productName = wait.until(ExpectedConditions.presenceOfElementLocated(productNameLocator));
		productName.sendKeys(newProductGeneralData.get("Name"));
		
		productCode = wait.until(ExpectedConditions.presenceOfElementLocated(productCodeLocator));
		productCode.sendKeys(newProductGeneralData.get("Code"));
		
		this.manipulateCheckBox(rootFolderCheckboxLocator, newProductGeneralData.get("RootFolder"));
		
		this.manipulateCheckBox(rubberDucksFolderCheckboxLocator, newProductGeneralData.get("RuberDucksFolder"));
	
		this.manipulateCheckBox(subcategoryFolderCheckboxLocator, newProductGeneralData.get("SubcategoryFolder"));
		
		this.manipulateSelectText(defaultCategorySelectLocator, newProductGeneralData.get("DefaultCategory"));
		
		this.manipulateCheckBox(genderFemaleCheckBoxLocator, newProductGeneralData.get("Female"));
		
		this.manipulateCheckBox(genderMaleCheckBoxLocator, newProductGeneralData.get("Male"));
		
		this.manipulateCheckBox(genderUnisexCheckBoxLocator, newProductGeneralData.get("Unisex"));
		
		quantity = wait.until(ExpectedConditions.presenceOfElementLocated(quantityLocator));
		quantity.sendKeys(newProductGeneralData.get("Quantity"));
		
		this.manipulateSelectText(quantityUnitLocator, newProductGeneralData.get("QuantityUnit"));
		
		this.manipulateSelectText(deliveryStatusLocator, newProductGeneralData.get("DeliveryStatus"));
		
		this.manipulateSelectText(soldOutStatusLocator, newProductGeneralData.get("SoldOutStatus"));
		
	}
	
	public void manipulateCheckBox(By wELocator, String status){
		WebElement wE;
		wE = wait.until(ExpectedConditions.presenceOfElementLocated(wELocator));
		
		if(status.equals("Checked") ){
			if(!wE.isSelected()){	
				wE.click();
			}
		}
		else {
			if(wE.isSelected()){	
				wE.click();
			}
		}
	}
	
	public void manipulateSelectText(By wELocator, String selectValue){
		WebElement wE;
		Select s;
		
		wE = wait.until(ExpectedConditions.presenceOfElementLocated(wELocator));
		
		s = new Select(wE);
		s.selectByVisibleText(selectValue);
	}
	
	public void loginLiteCartWithCorrectCrendentials(String userName, String password) {
		userNameInput = driver.findElement(userNameInputLocator);
		userNameInput.clear();
		userNameInput.sendKeys(userName);
		
		passwordInput = driver.findElement(passwordInputLocator);
		passwordInput.clear();
		passwordInput.sendKeys(password);

		loginButton = driver.findElement(loginButtonLocator);
		loginButton.click();
		
		wait.until(ExpectedConditions.titleIs(homePageTitle));
	}
}


