package stepDefinitions;

import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pagesSample.ssLv.SsLvMain;

public class MainSteps {
    private WebDriver driver;
    static SsLvMain ssLvPage;

    public MainSteps() {
        this.driver = Hooks.driver;
        ssLvPage = PageFactory.initElements(Hooks.driver, SsLvMain.class);
    }

    /***
     *  below are steps definition for all sources in the project web site
     * */

    @When("^I am on \"(somePage|SS)\" homepage$")
    public void iAmOnHomepage(String siteName) throws Throwable {
        switch (siteName) {
            case "somePage":
//                driver.get(nextUrl.getPageUrl());
                break;
            case "SS":
                driver.get(ssLvPage.getPageUrl());
                break;
            default:
                System.out.println("please check input parameter for iAmOnHomepage");
        }
    }
}