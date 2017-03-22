package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ManipulateFormElements {
	
	public static void manipulateCheckBox(WebDriverWait wait, By wELocator, String status){
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
	
	public static void fillInElementWithText(WebDriverWait wait, By wELocator, String textData){
		WebElement wE;
		
		wE = wait.until(ExpectedConditions.presenceOfElementLocated(wELocator));
		wE.sendKeys(textData);
	}
	
	public static void manipulateSelectText(WebDriverWait wait, By wELocator, String selectValue){
		WebElement wE;
		Select s;
		
		wE = wait.until(ExpectedConditions.presenceOfElementLocated(wELocator));
		
		s = new Select(wE);
		s.selectByVisibleText(selectValue);
	}
	
	public static void setDatepicker(WebDriverWait wait, WebDriver driver, String cssSelector, String date) {
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
}
