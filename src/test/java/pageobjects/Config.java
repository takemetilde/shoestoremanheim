package pageobjects;

/**
 * Created by HHER on 9/18/2017.
 */
public interface Config {

    /**
     *  Change host to "saucelabs" to run  in SauceLabs
     */
    String host = System.getProperty("host", "localhost");
    String browser = System.getProperty("browser", "chrome");
    String browserVersion = System.getProperty("browserVersion", "56");
    String platform = System.getProperty("platform", "Windows 7");

    /**
     * SauceLab Credentials
     */
    String sauceUser = "hongiaher";
    String sauceKey = "21c4f4cc-4f12-4e5d-a7ec-63664a04730c";

}
