package workbench.steps;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import workbench.pages.assignmentPage;
import runners.CucumberRunner;

public class assignmentSteps {

    assignmentPage myCarAuction = new assignmentPage();
    CucumberRunner c = new CucumberRunner();

    @Given("^I am on page \"([^\"]*)\"$")
    public void i_am_on_page(String url) throws Throwable {
        c.setEnv(url);
    }

    @Then("I verify the blank image in slide bar")
    public void iVerifyTheBlankImageInSlideBar() {
        myCarAuction.verifyBlankImage();
    }
}
