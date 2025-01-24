 package SeleniumFramework1.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SeleniumFramework1.pageObjects.CartPage;
import SeleniumFramework1.pageObjects.CheckoutPage;
import SeleniumFramework1.pageObjects.ConfirmationPage;
import SeleniumFramework1.pageObjects.LandingPage;
import SeleniumFramework1.pageObjects.ProductCatalogue;
import SelenuimFramework1.framework1Design.TestComponent.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public CheckoutPage checkoutpage;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password) {
		productCatalogue = landingPage.loginApplication(username, password); 
	}
	
	@When("^I add product (.+) from Cart$")
	public void i_add_product_from_cart(String productName) throws InterruptedException {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName); 
	}
	
	@When("^Checkout  (.+) and submit the order$")
	public void checkout_and_submit_order(String productName) {
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDiaplay(productName);
		Assert.assertTrue(match);
		checkoutpage = cartPage.goToCheckout(driver);
		checkoutpage.selectCountry("india");
		confirmationPage = checkoutpage.submitOrder();
	}

	 
	 @Then("{string} message is displayed on ConfirmationPage")
	 public void message_displayed_confirmationPage(String string) {
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	 }
	 
	 @Then("{string} message is displayed ")
	 public void something_message_is_displayed(String string) {
		 Assert.assertEquals(string, landingPage.getErrorMsg());	
		 driver.close();
		 }
}
