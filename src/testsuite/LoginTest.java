package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class LoginTest extends Utility {

    String BaseUrl = "https://www.saucedemo.com/";

    @Before
    public void setUp() {
        openBrowser(BaseUrl);
    }

    // Verifying user should be able to log in with valid credentials
    @Test
    public void userShouldLoginSuccessfullyWithValid() {

        //Enter username
        sendTextToElement(By.id("user-name"), "standard_user");
        //enter password
        sendTextToElement(By.id("password"), "secret_sauce");

        //click on login button
        clickOnElement(By.xpath("//input[@id='login-button']"));
        verifyElements("Product not found", "PRODUCTS", By.xpath("//span[contains(text(),'Products')]"));

    }

    // Verifying 6 products are displayed after log in
    @Test
    public void verifyThatSixProductsAreDisplayedOnPage() {

        sendTextToElement(By.id("user-name"), "standard_user");
        //enter password
        sendTextToElement(By.id("password"), "secret_sauce");
        //click on login button
        clickOnElement(By.xpath("//input[@id='login-button']"));

        List<WebElement> productNo = driver.findElements(By.className("inventory_item"));
        int totalProducts = productNo.size();
        // This is from requirement
        int expectedNumber = 6;
        // Verifying actual number matching with expected number
        Assert.assertEquals("Products numbers not matching", expectedNumber, totalProducts);
    }
    @After
    public void tearDown() {
        closeBrowser();
    }
}

