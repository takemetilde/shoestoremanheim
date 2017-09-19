package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by HHER on 9/18/2017.
 */
public class BaseObjects implements Config{

    //~ Variables/Instances --------------------------------------------------------------------------------------------

    /**
     * Create instance of Selenium WebDriver
     */
    protected WebDriver driver;
    Logger logger = LoggerFactory.getLogger(BaseObjects.class);

    /**
     * Common selectors
     */
    private String baseUrl = "http://shoestore-manheim.rhcloud.com/";
    private String currentUrl = "";
    private By homeLinkLocator = By.linkText("Home");
    private By januaryLinkLocator = By.linkText("January");

    //~ Constructors ---------------------------------------------------------------------------------------------------

    /**
     * Default Constructor to pass in instantiated driver from TestBase
     * @param driver
     */
    public BaseObjects(WebDriver driver) {
        this.driver = driver;
    }

    //~ Methods ---------------------------------------------------------------------------------------------------------

    public void navigateToHome() {
        visit(baseUrl);
        click(homeLinkLocator);
    }

    // TODO: Adding DataProviders here can help make the program more dynamic by cycling through all of the months checking each page
    public void clickOnJanuary() {
        currentUrl = baseUrl + "months/january";
        click(januaryLinkLocator);
    }

    //~ Methods (For usability) ----------------------------------------------------------------------------------------


    public WebDriver getDriver() {
        return driver;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getCurrentUrl() {
        return currentUrl;
    }

    /**
     * Navigate to page by passing
     * @param url as String
     */
    public void visit(String url) {

        logger.info("Getting page: " + url);
        driver.get(url);
    }

    /**
     * Finds element
     * @param locator as By element and
     * @return WebElement to be used
     */
    public WebElement find(By locator) {
        return driver.findElement(locator);
    }

    /**
     * Clicks on element
     * @param locator
     */
    public void click(By locator) {

        logger.info("Clicking on element: " + locator);
        find(locator).click();
    }

    /**
     * Types into element
     * @param locator and sendKeys
     * @param inputText
     */
    public void type(By locator, String inputText) {

        logger.info("Typing: " + inputText + " into: " + locator);
        find(locator).sendKeys(inputText);
    }

    /**
     * Submits element
     * @param locator
     */
    public void submit(By locator) {

        logger.info("Submitting: " + locator);
        find(locator).submit();
    }

    /**
     * Hovers dynamically over
     * @param locator
     */
    public void hover(By locator) {

        logger.info("Hovering over: " + locator);
        Actions hover = new Actions(driver);
        hover.moveToElement(find(locator));
    }

    /**
     * Selects element
     * @param locator from list by matching
     * @param input
     */
    public void select(By locator, String input) {

        logger.info("Selecting: " + input + " From: " + locator);
        Select select = new Select(find(locator));
        select.selectByVisibleText(input);
    }

    /**
     * Checks if element
     * @param locator is visible
     * @return Boolean
     */
    public Boolean isDisplayed(By locator) {
        try {
            logger.info("Looking for: " + locator);
            return find(locator).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException exception) {
            return false;
        }
    }

    /**
     * Waits for element
     * @param locator for time
     * @param seconds until exception is thrown
     */
    public void waitFor(By locator, Integer seconds){
        try {
            logger.info("Waiting: " + seconds + " seconds for: " + locator);
            WebDriverWait waitFor = new WebDriverWait(driver, seconds);
            waitFor.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException exception) {
        }
    }
}
