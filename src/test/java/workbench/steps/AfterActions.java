package workbench.steps;
import cucumber.api.java.After;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import cucumber.api.Scenario;
import runners.CucumberRunner;

public class AfterActions {

    static WebDriver driver=CucumberRunner.getDriver();

    @After
    public static void tearDown(Scenario scenario){

        if (scenario.isFailed()) {
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshotBytes, "image/png");
        }
    }
}
