package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by HHER on 9/18/2017.
 */
public class HomeObjects extends BaseObjects {

    //~ Variables/Instances --------------------------------------------------------------------------------------------

    By emailReminderTextLocator = By.id("remind_email_input");
    By flashNoticeLocator = By.id("flash");
    String flashNoticeMessageDisplayed = "";
    String flashNoticeMessage = "Thanks! We will notify you of our new shoes at this email: ";
    String validEmailAddress = "abc123@qwerty.com";
    String invalidEmailAddress = "abc123.com";

    //~ Constructors ---------------------------------------------------------------------------------------------------

    /**
     * Default Constructor to pass WebDriver from BaseTests to BaseObjects
     * @param driver
     */
    public HomeObjects(WebDriver driver) {
        super(driver);
    }

    //~ Methods --------------------------------------------------------------------------------------------------------


    public By getEmailReminderTextLocator() {
        return emailReminderTextLocator;
    }

    public String getFlashNoticeMessage() {
        return flashNoticeMessage;
    }

    public String getFlashNoticeMessageDisplayed() {
        return flashNoticeMessageDisplayed;
    }

    public void setFlashNoticeMessageDisplayed(String flashNoticeMessageDisplayed) {
        this.flashNoticeMessageDisplayed = flashNoticeMessageDisplayed;
    }

    public String getValidEmailAddress() {
        return validEmailAddress;
    }

    public String getInvalidEmailAddress() {
        return invalidEmailAddress;
    }

    public Boolean checkForValidEmailAddress(String input){
        // Generic regex to check, too many combinations
        Pattern pattern = Pattern.compile("(.+)(@)(.+)(\\.)(.+)");
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            return true;
        } else
            return false;
    }

    public Boolean flashNoticeIsDisplayed() {
        setFlashNoticeMessageDisplayed(find(flashNoticeLocator).getText());
        return isDisplayed(flashNoticeLocator);
    }

    public void enterEmailReminderText(String input) {
        type(emailReminderTextLocator, input);
    }

}
