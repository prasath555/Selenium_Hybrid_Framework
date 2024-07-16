package prasath.Testcase;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import prasath.Base.BaseTest;

public class BasicflowTest extends BaseTest {

	@Test(priority = 0)
    public void login() {
        driver.findElement(By.id("userEmail")).sendKeys("prasathrsacademy@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Rsacademy@23");
        driver.findElement(By.id("login")).click();
    }

	 @Test(priority = 1)
	    public void addProductToCart() {
	        String productName = "ZARA COAT 3";

	        // Login
	        driver.findElement(By.id("userEmail")).sendKeys("prasathrsacademy@gmail.com");
	        driver.findElement(By.id("userPassword")).sendKeys("Rsacademy@23");
	        driver.findElement(By.id("login")).click();

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

	        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	        WebElement prod = products.stream()
	                .filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName))
	                .findFirst().orElse(null);
	        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	    }
	 
	  @Test(priority = 2)
	    public void verifyProductInCart() {
	        String productName = "ZARA COAT 3";

	        // Login and add product to cart
	        driver.findElement(By.id("userEmail")).sendKeys("prasathrsacademy@gmail.com");
	        driver.findElement(By.id("userPassword")).sendKeys("Rsacademy@23");
	        driver.findElement(By.id("login")).click();
	        // ... Add product steps as in AddProductToCartTest

	        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
	        Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
	        Boolean producttxt = match;
	        Assert.assertEquals(match,producttxt);
	    }
	  
	
	  @Test(priority = 3)
	    public void completePurchase() {
	        String productName = "ZARA COAT 3";

	        // Login
	        driver.findElement(By.id("userEmail")).sendKeys("prasathrsacademy@gmail.com");
	        driver.findElement(By.id("userPassword")).sendKeys("Rsacademy@23");
	        driver.findElement(By.id("login")).click();

	        // Wait for login to complete
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[routerlink*='dashboard']")));

	        // Add product to cart
	        List<WebElement> products = driver.findElements(By.cssSelector(".product-card"));
	        WebElement desiredProduct = products.stream()
	                .filter(product -> product.findElement(By.cssSelector(".product-name")).getText().equals(productName))
	                .findFirst().orElse(null);
	        Assert.assertNotNull(desiredProduct, "Desired product not found: " + productName);
	        desiredProduct.findElement(By.cssSelector(".product-action button")).click();


	        // Verify product in cart
	        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart-item")));
	        List<WebElement> cartItems = driver.findElements(By.cssSelector(".cart-item"));
	        boolean found = cartItems.stream().anyMatch(item -> item.findElement(By.cssSelector(".product-name")).getText().equals(productName));
	        Assert.assertTrue(found, "Product not found in cart");

	        // Proceed to checkout
	        Actions actions = new Actions(driver);
	        WebElement countryInput = driver.findElement(By.cssSelector("[placeholder='Select Country']"));
	        actions.sendKeys(countryInput, "india").build().perform();

	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
	        driver.findElement(By.cssSelector(".action__submit")).click();

	        // Verify order confirmation
	        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
	        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANK YOU FOR THE ORDER."));
	    }
	
}
