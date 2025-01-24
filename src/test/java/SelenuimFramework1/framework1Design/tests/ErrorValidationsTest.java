package SelenuimFramework1.framework1Design.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import SeleniumFramework1.pageObjects.CartPage;
import SeleniumFramework1.pageObjects.CheckoutPage;
import SeleniumFramework1.pageObjects.ConfirmationPage;
import SeleniumFramework1.pageObjects.LandingPage;
import SeleniumFramework1.pageObjects.ProductCatalogue;
import SelenuimFramework1.framework1Design.TestComponent.BaseTest;
import SelenuimFramework1.framework1Design.TestComponent.Retry;

public class ErrorValidationsTest extends BaseTest {
		
	@Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException{
		landingPage.loginApplication("eepagrahari@gmail.com", "D1996@@");	
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMsg());
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException{
		String productName = "IPHONE 13 PRO";
		ProductCatalogue productCatalogue = landingPage.loginApplication("shivamagrahari@gmail.com", "Da1996@@");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName); 
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDiaplay("IPHONE 20 PRO");
		Assert.assertFalse(match);
		
				
	}

}
