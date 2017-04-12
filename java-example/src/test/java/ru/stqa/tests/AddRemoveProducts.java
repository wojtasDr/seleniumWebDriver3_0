package ru.stqa.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ru.stqa.businessLogic.CartBusinnessLogic;
import ru.stqa.pageObjects.ProductsPage;


public class AddRemoveProducts {
	private WebDriver driver;
	private WebDriverWait wait;
	
	private CartBusinnessLogic cartBusinnessLogic;
	private ProductsPage productsPage;
	
	@BeforeTest
	public void start() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\wojtas_dr\\.m2\\repository\\chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10);
		driver.manage().window().maximize();
	}
	
	@Test
	public void addRemoveProductsFromCart() {
		cartBusinnessLogic = new CartBusinnessLogic(driver, wait);
		productsPage = new ProductsPage(driver, wait);

		productsPage.openProductsPage();

		cartBusinnessLogic.addProductsToCart(3);
		
		cartBusinnessLogic.removeAllProductsFromCart();
	}

	@AfterTest
	public void stop() {
		driver.quit();
		driver = null;
	}
}

