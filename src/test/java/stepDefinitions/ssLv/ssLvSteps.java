package stepDefinitions.ssLv;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import managers.ssManagers.SsManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pagesSample.ssLv.SsLvMain;
import pagesSample.ssLv.SsLvMemo;
import stepDefinitions.Hooks;


public class ssLvSteps {
    private WebDriver driver;
    static SsLvMain ssSite;
    static SsLvMemo ssMemo;
    static SsManager ssManager;

    public ssLvSteps() {
        this.driver = Hooks.driver;
        ssSite = PageFactory.initElements(Hooks.driver, SsLvMain.class);
        ssMemo = PageFactory.initElements(Hooks.driver, SsLvMemo.class);
        ssManager= PageFactory.initElements(Hooks.driver, SsManager.class);
    }

    @Then("^I open \"(\\d)\" category$")
    public void iAmOpenCategory(int categoryNumber) throws Throwable {
        ssSite.openingCategory(categoryNumber);
    }

    @When("^I open \"([^\"]*)\" (subcategory|main category)$")
    public void openingSsCategories(String subCategoryName, String option) throws Throwable {
        ssSite.openingSubCategory(subCategoryName);
    }

    @Then("^I open \"(memo|search)\" page")
    public void iAmOpenSection(String pageName) throws Throwable {
        ssSite.openPage(pageName);
    }

    @And("^I (select|open) \"([^\"]*)\" item in the list$")
    public void workingWithItem(String option, int count) throws Throwable {
        switch (option) {
            case "open":
                ssSite.openSingleSelectedItem(count);
                break;
            case "select":
                ssSite.selectSingleMemoCheckBox(count);
                break;
            default:
                System.out.println("please check input parameter for 'memoCounting'");
        }

    }

    @Then("^I add selected (item|items) to the memo$")
    public void addNewMemo(String option) throws Throwable {
        switch (option) {
            case "item":
                ssSite.addSingleItemToMemo();
                break;
            case "items":
                ssSite.addMuliItemToMemo();
                break;
            default:
                System.out.println("please check input parameter for 'memoCounting'");
        }

    }

    @And("^I delete all memo from the list$")
    public void deeleteAllMemo() throws Throwable {
        ssMemo.deleteAllMemo();
    }

    @And("^Memo (menu is showing|page has) \"([^\"]*)\" ad\\(s\\)$")
    public void memoCounting(String option, int memoCount) throws Throwable {
        switch (option) {
            case "menu is showing":
                ssMemo.memoCountValidation(memoCount, ssSite.totalMemoCount());
                break;
            case "page has":
                ssMemo.memoCountValidation(memoCount, ssMemo.totalMemoItemOnPage());
                break;
            default:
                System.out.println("please check input parameter for 'memoCounting'");
        }
    }

    @Then("^Memo menu (is|isn't) empty$")
    public void memoMenuValidation(String option) {
        ssSite.memoValidation(option);
    }

    @And("^I select \"([^\"]*)\" items in the list$")
    public void iSelectItemsInTheList(String memoPositions) throws Throwable {
        ssSite.selectingMultiMemoCheckBox(memoPositions);
    }

    @Then("^I check that I'm on the \"([^\"]*)\" (subcategory|main category) page$")
    public void breadcrumbValidation (String refValue, String option) throws Throwable {
        ssSite.bredcrumbValidation(refValue, option);
    }

    @Then("^I store data for (main category|subcategory|selected)([^\"]*)$")
    public void storingData(String option, String value) {
        /**
         * This method is storing data only on current page.
         * Here is no implementation for checking all pages using page
         * selector in the bottom of web page
         * */
        ssManager.collectPageData(option, value);
    }

    @Then("^I compare data for \"([^\"]*)\" ad\\(s\\) with stored data$")
    public void iCompareDataForAdSWithStoredData(String position) throws Throwable {
                ssManager.comparingMemoCollections(position);
    }

}
