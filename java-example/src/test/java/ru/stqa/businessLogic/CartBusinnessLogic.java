package ru.stqa.businessLogic;

import java.util.ArrayList;
import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.stqa.pageObjects.CartPage;
import ru.stqa.pageObjects.ProductsPage;
import ru.stqa.pageObjects.SingleProductPage;

public class CartBusinnessLogic {
	private WebDriver driver;
	private WebDriverWait wait;
	
	private List<WebElement> mostPopularProducts = new ArrayList<WebElement>();
	
	public CartBusinnessLogic(WebDriver driver, WebDriverWait wait){
		this.driver = driver;
		this.wait = wait;	
	}
	
	private ProductsPage productsPage;
	private SingleProductPage singleProductPage;
	private CartPage cartPage;
	
	private WebElement productsSummaryTable;

	public void addProductsToCart(int numberOfProducts){
		productsPage = new ProductsPage(driver, wait);
		mostPopularProducts = productsPage.getMostPopularProducts();

		for (int i = 1; i <= numberOfProducts; i++) {
			singleProductPage = productsPage.chooseProduct(mostPopularProducts.get(i));
			
			if (singleProductPage.isSizeSelectPresent()) {
				singleProductPage.setSizeSelect("Small");
			}
			singleProductPage.addProductToCart();

			singleProductPage.checkIfCartUpdated(i);

			singleProductPage.returnHomePage();

			mostPopularProducts = productsPage.getMostPopularProducts();
		}
	}
	
	public void removeAllProductsFromCart(){
		Boolean isProductsTableVisible = false;
		
		singleProductPage = new SingleProductPage(driver, wait);
		
		cartPage = singleProductPage.openCart();
		
		do {
			isProductsTableVisible = cartPage.isProductSummaryTablePresent();
			
			if (isProductsTableVisible) {
				productsSummaryTable = cartPage.getProductsSummaryTable();
				
				cartPage.removeProductFromCart();
				
				cartPage.waitUntilSummaryTableIsReloaded(productsSummaryTable);
			}
		} while (isProductsTableVisible);
	}
}
