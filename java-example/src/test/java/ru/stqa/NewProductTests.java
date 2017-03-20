package ru.stqa;

import java.util.NavigableMap;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
	private By generalTabButtonLocator = By.cssSelector("a[href$='general']");
	private By informationTabButtonLocator = By.cssSelector("a[href$='information']");
	private By dataTabButtonLocator = By.cssSelector("a[href$='data']");
	private By pricesTabButtonLocator = By.cssSelector("a[href$='prices']");
	private By optionsTabButtonLocator = By.cssSelector("a[href$='options']");
	private By optionsStockTabButtonLocator = By.cssSelector("a[href$='stock']");
	
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
	private String dateValidFromLocator = "input[name=\"date_valid_from\"]";
	private String dateValidToLocator = "input[name=\"date_valid_to\"]";
	
	private By manufacturerSelectLocator = By.cssSelector("select[name='manufacturer_id']");
	private By supplierSelectLocator = By.cssSelector("select[name='supplier_id']");
	private By keywordsInputLocator = By.cssSelector("input[name='keywords']");
	private By shortDescriptionInputLocator = By.cssSelector("input[name='short_description[en]']");
	private By descriptionInputLocator = By.cssSelector("textarea[name='description[en]']");
	private By headTitleInputLocator = By.cssSelector("input[name='head_title[en]']");
	private By metaDescriptionInputLocator = By.cssSelector("input[name='meta_description[en]']");
	
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
	private WebElement generalTabButton;
	private WebElement informationTabButton;
	private WebElement dataTabButton;
	private WebElement pricesTabButton;
	private WebElement optionsTabButton;
	private WebElement optionsStockTabButton;
	
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
	
	private WebElement keywordsInput;
	private WebElement shortDescriptionInput;
	private WebElement descriptionInput;
	private WebElement headTitleInput;
	private WebElement metaDescriptionInput;
	
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
		newProductGeneralData.put("DateValidFrom", "2016-03-20");
		newProductGeneralData.put("DateValidTo", "2016-03-30");
		
		NavigableMap<String,String> newProductInformationData = new TreeMap<String,String>();
		newProductInformationData.put("Manufacturer", "ACME Corp.");
		newProductInformationData.put("Supplier", "testName");
		newProductInformationData.put("Keywords", "keywords test");
		newProductInformationData.put("ShortDescription", "short description test");
		newProductInformationData.put("Description", "description test");
		newProductInformationData.put("HeadTitle", "head title test");
		newProductInformationData.put("MetaDescription", "meta description test");
		
		generalTabButton = wait.until(ExpectedConditions.presenceOfElementLocated(generalTabButtonLocator));
		generalTabButton.click();
		
		this.addNewProductGeneralData(newProductGeneralData);
		
		System.out.println("After general");
		
		informationTabButton = wait.until(ExpectedConditions.presenceOfElementLocated(informationTabButtonLocator));
		informationTabButton.click();
		
		this.addNewProductInformationData(newProductInformationData);
			
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
		
//		this.setDatepicker(driver, dateValidFromLocator, newProductGeneralData.get("DateValidFrom"));
//		
//		this.setDatepicker(driver, dateValidToLocator, newProductGeneralData.get("DateValidTo"));
		
		this.manipulateSelectText(quantityUnitLocator, newProductGeneralData.get("QuantityUnit"));
		
		this.manipulateSelectText(deliveryStatusLocator, newProductGeneralData.get("DeliveryStatus"));
		
		this.manipulateSelectText(soldOutStatusLocator, newProductGeneralData.get("SoldOutStatus"));
	}
	
	public void addNewProductInformationData(NavigableMap<String,String> newProductInformationData){
		this.manipulateSelectText(manufacturerSelectLocator, newProductInformationData.get("Manufacturer"));

		//this.manipulateSelectText(supplierSelectLocator, newProductInformationData.get("Supplier"));
		
		keywordsInput = wait.until(ExpectedConditions.presenceOfElementLocated(keywordsInputLocator));
		keywordsInput.sendKeys(newProductInformationData.get("Keywords"));

		shortDescriptionInput = wait.until(ExpectedConditions.presenceOfElementLocated(shortDescriptionInputLocator));
		shortDescriptionInput.sendKeys(newProductInformationData.get("ShortDescription"));

		descriptionInput = wait.until(ExpectedConditions.presenceOfElementLocated(descriptionInputLocator));
		descriptionInput.sendKeys(newProductInformationData.get("Description"));

		headTitleInput = wait.until(ExpectedConditions.presenceOfElementLocated(headTitleInputLocator));
		headTitleInput.sendKeys(newProductInformationData.get("HeadTitle"));

		metaDescriptionInput = wait.until(ExpectedConditions.presenceOfElementLocated(metaDescriptionInputLocator));
		metaDescriptionInput.sendKeys(newProductInformationData.get("MetaDescription"));
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
	
	public void setDatepicker(WebDriver driver, String cssSelector, String date) {
		WebElement wE;
		
		wE = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
		
		JavascriptExecutor.class.cast(driver).executeScript(String.format("$('%s').datepicker('setDate', '%s')", cssSelector, date));
		
//		  new WebDriverWait(driver, 30000).until(
//
//		    (WebDriver d) -> d.findElement(By.cssSelector(cssSelector)).isDisplayed());
//
//		  JavascriptExecutor.class.cast(driver).executeScript(
//
//		    String.format("$('%s').datepicker('setDate', '%s')", cssSelector, date));

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


