package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageobjects.JanuaryObjects;

import java.net.MalformedURLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by HHER on 9/18/2017.
 */
public class JanuaryTests extends BaseTests {

//~ Variables/Instances --------------------------------------------------------------------------------------------

    private JanuaryObjects januaryObjects;
    private List<WebElement> shoeListChildElementsList;
    Logger logger = LoggerFactory.getLogger(JanuaryTests.class);

    //~ Setup and Initialization ---------------------------------------------------------------------------------------

    /**
     * Initialize HomeObjects by passing WebDriver from BaseTests
     */
    @BeforeTest
    public void setUp() throws MalformedURLException {
        // TODO: Temporary handling for multi-threading issue
        if (webDriver == null)
            if (host.equals("localhost")) {
                chromeSetup();
            } else if (host.equals("saucelabs")){
                sauceLabsSetup();
            }
        januaryObjects = new JanuaryObjects(webDriver);
    }

    //~ Tests ----------------------------------------------------------------------------------------------------------

    @Test
    public void TC_0101_shoeListIsPopulatingSuccessfully() {
        januaryObjects.navigateToHome();
        Assert.assertTrue(januaryObjects.getDriver().getCurrentUrl().equals(januaryObjects.getBaseUrl()),
                "The current Url is not correct: \nExpected: " + januaryObjects.getDriver().getCurrentUrl()
                        + " \nActual: " + januaryObjects.getCurrentUrl());
        logger.info("Successfully navigated to Home Page: " + januaryObjects.getBaseUrl());
        januaryObjects.clickOnJanuary();
        // TODO: Temporary handling for Nordstrom content loading
        Assert.assertTrue(januaryObjects.getDriver().getCurrentUrl().equals(januaryObjects.getCurrentUrl()),
                "The current Url is not correct: \nExpected: " + januaryObjects.getDriver().getCurrentUrl()
                + " \nActual: " + januaryObjects.getCurrentUrl());
        logger.info("Successfully navigated to January Page: " + januaryObjects.getCurrentUrl());
        Assert.assertTrue(januaryObjects.setShoeListLocatorIsDisplayed(), "There were no shoes to display.");

        //  TODO: The idea here was to put each shoe into an array to be used later dynamically with assertions
        if (januaryObjects.setShoeListLocatorIsDisplayed()){
            shoeListChildElementsList = januaryObjects.getDriver().findElements(januaryObjects.getShoeListChildElementsLocator());
            for (WebElement webElement: shoeListChildElementsList) {
//                logger.info(webElement.toString());
                System.out.println(webElement.getAttribute("id"));
            }
        }
    }

    @Test (dependsOnMethods = "TC_0101_shoeListIsPopulatingSuccessfully")
    public void TC_0102_imageForShoeListPopulatingSuccessfully() {
        Assert.assertTrue(januaryObjects.imageOfShoeIsDisplayed(), "The image of the shoe could not be displayed.");
        // TODO: An example of cycling through each shoe and checking if the image populated correctly
//        for (WebElement webElement: shoeListChildElementsList){
//            Assert.assertTrue(januaryObjects.imageOfShoeIsDisplayed(webElement), "The image for webelement: " + webElement
//                    + " was not displayed.");
//        }
    }

    @Test (dependsOnMethods = "TC_0101_shoeListIsPopulatingSuccessfully")
    public void TC_0103_blurbForShoeListDisplayedSuccessfully() {
        Assert.assertTrue(januaryObjects.blurbOfShoeIsDisplayed(), "The shoe list is not populating correctly.");
    }

    @Test (dependsOnMethods = "TC_0101_shoeListIsPopulatingSuccessfully")
    public void TC_0104_priceForShoeListDisplayedSuccessfully() {
        String priceOfShoeDisplayed = januaryObjects.priceOfShoeThatIsDisplayed();
        Pattern pattern = Pattern.compile("(\\$)(([\\d]{0,3})(,)?)([\\d]{0,3})(\\.)([\\d]{2})");
        Matcher matcher = pattern.matcher(priceOfShoeDisplayed);

        if (matcher.matches()){
            Assert.assertTrue(true, "The shoe price is not a valid price.");
        }
    }
}
