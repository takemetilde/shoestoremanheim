package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by HHER on 9/18/2017.
 * TODO: The idea would be to re-factor this class to a generic month.Class and use array lists to traverse through it dynamically
 */
public class JanuaryObjects extends  BaseObjects{

    //~ Variables/Instances --------------------------------------------------------------------------------------------

    private By shoeListLocator = By.id("shoe_list");
    private By shoeListChildElementsLocator = By.xpath("//li[@id='...']");
    private By shoeResultLocator = By.className("shoe_result");
    private By shoeResultValueShoePriceLocator = By.cssSelector("shoe_result_value.shoe_price");
    private By shoeResultImageLocator = By.className("shoe_image");

    //~ Constructors ---------------------------------------------------------------------------------------------------

    /**
     * Default Constructor to pass WebDriver from BaseTests to BaseObjects
     * @param webDriver
     */
    public JanuaryObjects(WebDriver webDriver) {
        super(webDriver);
    }

    //~ Methods --------------------------------------------------------------------------------------------------------


    public By getShoeListLocator() {
        return shoeListLocator;
    }

    public By getShoeListChildElementsLocator() {
        return shoeListChildElementsLocator;
    }

    public Boolean setShoeListLocatorIsDisplayed(){
        return isDisplayed(shoeListLocator);
    }

    public Boolean blurbOfShoeIsDisplayed(){
        return isDisplayed(shoeResultLocator);
    }

    public String priceOfShoeThatIsDisplayed() {

        // TODO: Need logic to handle multiple locators
        List<WebElement> priceList = getDriver().findElements(shoeResultValueShoePriceLocator);
        for(WebElement webElement: priceList)
            logger.info("Price of the shoe: " + priceList);
        return priceList.get(0).getText();
    }

    public Boolean imageOfShoeIsDisplayed() {
        return isDisplayed(shoeResultImageLocator);
    }
}
