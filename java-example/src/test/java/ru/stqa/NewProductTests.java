package ru.stqa;

import java.util.NavigableMap;
import java.util.TreeMap;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utils.ManipulateFormElements;

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
	private By dateValidFromLocator = By.cssSelector("input[name=\"date_valid_from\"]");
	private By dateValidToLocator = By.cssSelector("input[name=\"date_valid_to\"]");
	
	private By manufacturerSelectLocator = By.cssSelector("select[name='manufacturer_id']");
	//private By supplierSelectLocator = By.cssSelector("select[name='supplier_id']");
	private By keywordsInputLocator = By.cssSelector("input[name='keywords']");
	private By shortDescriptionInputLocator = By.cssSelector("input[name='short_description[en]']");
	private By descriptionInputLocator = By.cssSelector("textarea[name='description[en]']");
	private By headTitleInputLocator = By.cssSelector("input[name='head_title[en]']");
	private By metaDescriptionInputLocator = By.cssSelector("input[name='meta_description[en]']");
	
	private By skuInputLocator = By.cssSelector("input[name='sku']");
	private By gtinInputLocator = By.cssSelector("input[name='gtin']");
	private By taricInputLocator = By.cssSelector("input[name='taric']");
	private By weightValueInputLocator = By.cssSelector("input[name='weight']");
	private By weightUnitSelectLocator = By.cssSelector("select[name='weight_class']");
	private By xDimInputLocator = By.cssSelector("input[name='dim_x']");
	private By yDimInputLocator = By.cssSelector("input[name='dim_y']");
	private By zDimInputLocator = By.cssSelector("input[name='dim_z']");
	private By dimUnitSelectLocator = By.cssSelector("select[name='dim_class']");
	private By attributesTextAreaLocator = By.cssSelector("textarea[name^='attributes']");
	
	private By purchasePriceValueInputLocator = By.cssSelector("input[name='purchase_price']");
	private By purchasePriceCurrencySelectLocator = By.cssSelector("select[name='purchase_price_currency_code']");
	private By usdPriceValueInputLocator = By.cssSelector("input[name='gross_prices[USD]']");
	private By eurPriceValueInputLocator = By.cssSelector("input[name='gross_prices[EUR]']");
	
	private By plusIconLocator = By.cssSelector("a[title='Insert before'] i[class='fa fa-plus-circle']");
	private By groupSelectLocator = By.cssSelector("select[name='options[new_1][group_id]']");
	//private By valueSelectLocator = By.cssSelector("select[name='options[new_1][value_id]']");
	private By priceOperatorSelectLocator = By.cssSelector("select[name='options[new_1][price_operator]']");
	private By priceAdjustUsdInputLocator = By.cssSelector("input[name='options[new_1][USD]']");
	private By priceAdjustEurInputLocator = By.cssSelector("input[name='options[new_1][EUR]']");
	
	private By combinationGroupSelectLocator = By.cssSelector("select[name='new_option[new_1][group_id]']");
	//private By combinationValueSelectLocator = By.cssSelector("select[name='new_option[new_1][value_id]']");
	
	private By saveButtonLocator = By.cssSelector("button[name='save']");
	
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
	private WebElement plusIcon;
	
	private WebElement saveButton;

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
		
		NavigableMap<String,String> newProductData = new TreeMap<String,String>();
		newProductData.put("skuInput", "SKU test");
		newProductData.put("gtinInput", "GTIN test");
		newProductData.put("taricInput", "TARIC test");
		newProductData.put("weightValueInput", "123");
		newProductData.put("weightUnitSelectLocator", "st");
		newProductData.put("xDimInput", "1");
		newProductData.put("yDimInput", "2");
		newProductData.put("zDimInput", "3");
		newProductData.put("dimUnitSelect", "ft");
		newProductData.put("attributesTextArea", "Attributes text test.");
		
		NavigableMap<String,String> newProductPricesData = new TreeMap<String,String>();
		newProductPricesData.put("purchasePriceValue", "99");
		newProductPricesData.put("purchasePriceCurrency", "Euros");
		newProductPricesData.put("usdPriceValue", "10.23");
		newProductPricesData.put("eurPriceValue", "12.30");
		
		NavigableMap<String,String> newProductOptionsData = new TreeMap<String,String>();
		newProductOptionsData.put("group", "Size [select]");
		newProductOptionsData.put("value", "2");
		newProductOptionsData.put("priceOperator", "*");
		newProductOptionsData.put("priceAdjustUsd", "78");
		newProductOptionsData.put("priceAdjustEur", "67");
		
		NavigableMap<String,String> newProductOptionsStockData = new TreeMap<String,String>();
		newProductOptionsStockData.put("group", "Size [select]");
		newProductOptionsStockData.put("value", "3");
		
		
		generalTabButton = wait.until(ExpectedConditions.presenceOfElementLocated(generalTabButtonLocator));
		generalTabButton.click();
		
		this.addNewProductGeneralData(newProductGeneralData);
		
		informationTabButton = wait.until(ExpectedConditions.presenceOfElementLocated(informationTabButtonLocator));
		informationTabButton.click();
		
		this.addNewProductInformationData(newProductInformationData);
		
		dataTabButton = wait.until(ExpectedConditions.presenceOfElementLocated(dataTabButtonLocator));
		dataTabButton.click();
		
		this.addNewProductData(newProductData);
		
		pricesTabButton = wait.until(ExpectedConditions.presenceOfElementLocated(pricesTabButtonLocator));
		pricesTabButton.click();
		
		this.addNewProductPricesData(newProductPricesData);
		
		optionsTabButton = wait.until(ExpectedConditions.presenceOfElementLocated(optionsTabButtonLocator));
		optionsTabButton.click();
		
		this.addNewProductOptionData(newProductOptionsData);
		
		optionsStockTabButton = wait.until(ExpectedConditions.presenceOfElementLocated(optionsStockTabButtonLocator));
		optionsStockTabButton.click();
		
		this.addNewProductOptionStockData(newProductOptionsStockData);
		
		saveButton = wait.until(ExpectedConditions.presenceOfElementLocated(saveButtonLocator));
		saveButton.click();
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
		ManipulateFormElements.fillInElementWithText(wait, productNameLocator, newProductGeneralData.get("Name"));
		
		ManipulateFormElements.fillInElementWithText(wait, productCodeLocator, newProductGeneralData.get("Code"));
		
		ManipulateFormElements.manipulateCheckBox(wait, rootFolderCheckboxLocator, newProductGeneralData.get("RootFolder"));
		
		ManipulateFormElements.manipulateCheckBox(wait, rubberDucksFolderCheckboxLocator, newProductGeneralData.get("RuberDucksFolder"));
	
		ManipulateFormElements.manipulateCheckBox(wait, subcategoryFolderCheckboxLocator, newProductGeneralData.get("SubcategoryFolder"));
		
		ManipulateFormElements.manipulateSelectText(wait, defaultCategorySelectLocator, newProductGeneralData.get("DefaultCategory"));
		
		ManipulateFormElements.manipulateCheckBox(wait, genderFemaleCheckBoxLocator, newProductGeneralData.get("Female"));
		
		ManipulateFormElements.manipulateCheckBox(wait, genderMaleCheckBoxLocator, newProductGeneralData.get("Male"));
		
		ManipulateFormElements.manipulateCheckBox(wait, genderUnisexCheckBoxLocator, newProductGeneralData.get("Unisex"));
		
		ManipulateFormElements.fillInElementWithText(wait, quantityLocator, newProductGeneralData.get("Quantity"));
		
		ManipulateFormElements.manipulateSelectText(wait, quantityUnitLocator, newProductGeneralData.get("QuantityUnit"));
		
		ManipulateFormElements.manipulateSelectText(wait, deliveryStatusLocator, newProductGeneralData.get("DeliveryStatus"));
		
		ManipulateFormElements.manipulateSelectText(wait, soldOutStatusLocator, newProductGeneralData.get("SoldOutStatus"));
		
		ManipulateFormElements.fillInElementWithText(wait, dateValidFromLocator, newProductGeneralData.get("DateValidFrom"));
		
		ManipulateFormElements.fillInElementWithText(wait, dateValidToLocator, newProductGeneralData.get("DateValidTo"));
	}
	
	public void addNewProductInformationData(NavigableMap<String,String> newProductInformationData){
		ManipulateFormElements.manipulateSelectText(wait, manufacturerSelectLocator, newProductInformationData.get("Manufacturer"));

		ManipulateFormElements.fillInElementWithText(wait, keywordsInputLocator, newProductInformationData.get("Keywords"));

		ManipulateFormElements.fillInElementWithText(wait, shortDescriptionInputLocator, newProductInformationData.get("ShortDescription"));

		ManipulateFormElements.fillInElementWithText(wait, descriptionInputLocator, newProductInformationData.get("Description"));

		ManipulateFormElements.fillInElementWithText(wait, headTitleInputLocator, newProductInformationData.get("HeadTitle"));

		ManipulateFormElements.fillInElementWithText(wait, metaDescriptionInputLocator, newProductInformationData.get("MetaDescription"));
	}
	
	public void addNewProductData(NavigableMap<String,String> newProductData){
		
		ManipulateFormElements.fillInElementWithText(wait, skuInputLocator, newProductData.get("skuInput"));
		
		ManipulateFormElements.fillInElementWithText(wait, gtinInputLocator, newProductData.get("gtinInput"));
		
		ManipulateFormElements.fillInElementWithText(wait, taricInputLocator, newProductData.get("taricInput"));
		
		ManipulateFormElements.fillInElementWithText(wait, weightValueInputLocator, newProductData.get("weightValueInput"));
		
		ManipulateFormElements.manipulateSelectText(wait, weightUnitSelectLocator, newProductData.get("weightUnitSelectLocator"));
		
		ManipulateFormElements.fillInElementWithText(wait, xDimInputLocator, newProductData.get("xDimInput"));
		
		ManipulateFormElements.fillInElementWithText(wait, yDimInputLocator, newProductData.get("yDimInput"));
		
		ManipulateFormElements.fillInElementWithText(wait, zDimInputLocator, newProductData.get("zDimInput"));
		
		ManipulateFormElements.manipulateSelectText(wait, dimUnitSelectLocator, newProductData.get("dimUnitSelect"));
		
		ManipulateFormElements.fillInElementWithText(wait, attributesTextAreaLocator, newProductData.get("attributesTextArea"));
	}
	
	public void addNewProductPricesData(NavigableMap<String,String> newProductPricesData){
		ManipulateFormElements.fillInElementWithText(wait, purchasePriceValueInputLocator, newProductPricesData.get("purchasePriceValue"));
		
		ManipulateFormElements.manipulateSelectText(wait, purchasePriceCurrencySelectLocator, newProductPricesData.get("purchasePriceCurrency"));
		
		ManipulateFormElements.fillInElementWithText(wait, usdPriceValueInputLocator, newProductPricesData.get("usdPriceValue"));

		ManipulateFormElements.fillInElementWithText(wait, eurPriceValueInputLocator, newProductPricesData.get("eurPriceValue"));
	}
	
	public void addNewProductOptionData(NavigableMap<String,String> newProductOptionData){
		plusIcon = wait.until(ExpectedConditions.presenceOfElementLocated(plusIconLocator));
		plusIcon.click();
		
		ManipulateFormElements.manipulateSelectText(wait, groupSelectLocator, newProductOptionData.get("group"));
		
		ManipulateFormElements.manipulateSelectText(wait, priceOperatorSelectLocator, newProductOptionData.get("priceOperator"));
		
		ManipulateFormElements.fillInElementWithText(wait, priceAdjustUsdInputLocator, newProductOptionData.get("priceAdjustUsd"));

		ManipulateFormElements.fillInElementWithText(wait, priceAdjustEurInputLocator, newProductOptionData.get("priceAdjustEur"));	
	}
	
	public void addNewProductOptionStockData(NavigableMap<String,String> newProductOptionStockData){
		ManipulateFormElements.manipulateSelectText(wait, combinationGroupSelectLocator, newProductOptionStockData.get("group"));
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


