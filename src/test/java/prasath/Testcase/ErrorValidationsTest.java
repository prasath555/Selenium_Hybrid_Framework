package prasath.Testcase;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import prasath.Base.BaseTest;
import prasath.TestComponents.Retry;
import prasath.pageobjects.CartPage;
import prasath.pageobjects.ProductCatalogue;


public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {

		LOGGER.info("****** error validation test entry  *****");
		landingPage.loginApplication("anshika@gmail.com", "Iamki000");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		LOGGER.info("****** error validation test exit  *****");
	}
	

	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	{
 
		LOGGER.info("****** product error validation entry *****");
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("prasathrsacademy@gmail.com", "Rsacademy@23");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
		LOGGER.info("****** product error validation exit  *****");
	

	}
	
	

}
