package SelenuimFramework1.framework1Design.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumFramework1.pageObjects.CartPage;
import SeleniumFramework1.pageObjects.CheckoutPage;
import SeleniumFramework1.pageObjects.ConfirmationPage;
import SeleniumFramework1.pageObjects.LandingPage;
import SeleniumFramework1.pageObjects.OrderPage;
import SeleniumFramework1.pageObjects.ProductCatalogue;
import SelenuimFramework1.framework1Design.TestComponent.BaseTest;
import SelenuimFramework1.framework1Design.TestComponent.Retry;

public class SubmitOrderTest extends BaseTest {
	
	String productName = "IPHONE 13 PRO";
		
	@Test(dataProvider = "getData", groups = {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException{
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product")); 
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDiaplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutpage = cartPage.goToCheckout(driver);
		checkoutpage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutpage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
				
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("deepagrahari@gmail.com", "Da1996@@");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDiaplay(productName));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException{
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//SeleniumFramework1//data//purchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}}; 
	}
	
//	@DataProvider
//	public Object[][] getData() {
//		return new Object[][] {{"deepagrahari@gmail.com", "Da1996@@", "IPHONE 13 PRO"}, {"shivamagrahari@gmail.com", "Da1996@@", "QWERTY"}};
//	}
}
