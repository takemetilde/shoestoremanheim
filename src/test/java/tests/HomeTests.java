package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageobjects.HomeObjects;

/**
 * Created by HHER on 9/18/2017.
 */
public class HomeTests  extends BaseTests{

//~ Variables/Instances --------------------------------------------------------------------------------------------

    private HomeObjects homeObjects;

    //~ Setup and Initialization ---------------------------------------------------------------------------------------

    /**
     * Initialize HomeObjects by passing WebDriver from TestBase
     */
    @BeforeTest
    public void setUp(){
        homeObjects = new HomeObjects(webDriver);
        homeObjects.visit(homeObjects.getBaseUrl());
    }

    //~ Tests ----------------------------------------------------------------------------------------------------------

    @Test
    public void TC_0201_verifyValidEmailAddress() {
        String validEmail = homeObjects.getValidEmailAddress();
        Assert.assertTrue(homeObjects.checkForValidEmailAddress(validEmail),
                "The email address entered was invalid: " + validEmail);
        homeObjects.enterEmailReminderText(validEmail);
        homeObjects.submit(homeObjects.getEmailReminderTextLocator());
        Assert.assertTrue(homeObjects.flashNoticeIsDisplayed());
        String expFlashNotice = homeObjects.getFlashNoticeMessage() + homeObjects.getValidEmailAddress();
        String actFlashNotice = homeObjects.getFlashNoticeMessageDisplayed();
        Assert.assertTrue(expFlashNotice.equals(actFlashNotice),
                "The flash notice does not match: \nExpected: " + expFlashNotice +
                "\nActual: " + actFlashNotice);
    }

    @Test
    public void TC_0201_verifyInvalidEmailAddress() {
        String invalidEmail = homeObjects.getInvalidEmailAddress();
        Assert.assertFalse(homeObjects.checkForValidEmailAddress(invalidEmail),
                "The email address entered was valid: " + invalidEmail);
    }

}
