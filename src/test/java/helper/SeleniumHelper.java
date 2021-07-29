package helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.IOException;

public class SeleniumHelper {

    public static void configureDriverPath() throws IOException {

        if(System.getProperty("os.name").startsWith("Linux")) {
            String firefoxDriverPath = System.getProperty("user.dir") + "/src/test/resources/drivers/linux/geckodriver";
            System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
            String chromeDriverPath = System.getProperty("user.dir") + "/src/test/resources/drivers/linux/chromedriver";
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        }
        if(System.getProperty("os.name").startsWith("Mac")) {
            String firefoxDriverPath = System.getProperty("user.dir") + "/src/test/resources/drivers/mac/geckodriver";
            System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
            String chromeDriverPath = System.getProperty("user.dir") + "/src/test/resources/drivers/mac/chromedriver";
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        }
        if(System.getProperty("os.name").startsWith("Windows")) {
            String firefoxDriverPath = System.getProperty("user.dir") + "//src//test//resources//drivers//windows//geckodriver.exe";
            System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
            String chromeDriverPath = System.getProperty("user.dir") + "//src//test//resources//drivers//windows//chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        }
    }

    public static WebDriver startApplication(WebDriver driver, String browserName) throws IOException {

        switch (browserName) {

            case TestConstants.BROWSER_FIREFOX:
                System.setProperty(TestConstants.FIREFOX_DRIVER_NAME, DataProviderFactory.getConfig().getFirefoxPath());
                driver = new FirefoxDriver();
                break;

            case TestConstants.BROWSER_HEADLESS_FIREFOX:
                System.setProperty(TestConstants.FIREFOX_DRIVER_NAME,  DataProviderFactory.getConfig().getFirefoxPath());
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments(TestConstants.BROWSER_HEADLESS);
                firefoxOptions.addArguments(TestConstants.BROWSER_WINDOW_SIZE);
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case TestConstants.BROWSER_CHROME:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-notifications");
                System.setProperty(TestConstants.CHROME_DRIVER_NAME,  DataProviderFactory.getConfig().getChromePath());
                driver = new ChromeDriver(options);
                break;

            case TestConstants.BROWSER_HEADLESS_CHROME:
                System.setProperty(TestConstants.FIREFOX_DRIVER_NAME,  DataProviderFactory.getConfig().getChromePath());
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments(TestConstants.BROWSER_HEADLESS);
                chromeOptions.addArguments(TestConstants.BROWSER_WINDOW_SIZE);
                driver = new ChromeDriver(chromeOptions);
                break;

            case TestConstants.BROWSER_IE:
                System.setProperty(TestConstants.IE_DRIVER_NAME,  DataProviderFactory.getConfig().getIEPath());
                driver = new InternetExplorerDriver();
                break;

            default:

                System.out.println("Sorry We do not support this browser");

        }

        return driver;

    }
}
