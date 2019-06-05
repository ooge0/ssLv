package pagesSample.ssLv;

import helpers.Resources;
import managers.ssManagers.SsManager;
import model.SubCategoryData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import stepDefinitions.Hooks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static stepDefinitions.Hooks.driver;

public class SsLvMain {
    static SsLvMemo ssMemo;

    public SsLvMain() {
        ssMemo = PageFactory.initElements(Hooks.driver, SsLvMemo.class);
    }


    @FindBy(how = How.CSS, using = "#a_fav")
    private WebElement favoriteLink;

    @FindBy(how = How.CSS, using = "#a_fav_sel")
    private WebElement favoriteLinkMulti;

    @FindBy(how = How.CSS, using = ".headtitle")
    private WebElement secondPageBredcramb;

    @FindBy(how = How.CSS, using = ".a14")
    private WebElement firstPageBredcramb;


    @FindBy(how = How.CSS, using = "#main_table > span.page_header_menu > span > b > a")
    private WebElement memoLink;

    @FindBy(how = How.CSS, using = "#main_table > span.page_header_menu > b:nth-child(3) > a")
    private WebElement searchLink;

    @FindBy(how = How.CSS, using = "#mtd_14080")
    private WebElement vacanciesCategory;

    @FindBy(how = How.CSS, using = "#ahc_321")
    private WebElement administratorCategory;

    @FindBy(how = How.CSS, using = "#ahc_379")
    private WebElement bakerCategory;


    @FindBy(how = How.CSS, using = "#mnu_fav_id")
    private WebElement memoCountLabel;

    public static List <SubCategoryData> selectedSubCategoryItemList;
    public static List <SubCategoryData> subCategoryObjectList;
    int category = 1;


    public String getPageUrl() throws IOException {
        return Resources.getEnvValue();
    }

    public void openingCategory(int categoryNumber) {
        /**
         * Here is possible to create an additional method that will use
         * a list of names for opening of different categories. Best solution
         * is creating an additional method that will use some atribute for generating link
         * for webdriver
         * */
        WebElement categoryLinkButton = driver.findElement(By.cssSelector("#td_" + category + ">div>div>div>div"));
        categoryLinkButton.click();
    }

    public void openingSubCategory(String subCategoryName) {
        /**
         * Here is possible to create an additional method that will use
         * a list of names for opening of different links that are available on the page.
         * Best solution is a switch/case statement for simple examples but for big cases
         * will be much better to make a Collection with all items for current category
         * */
        switch (subCategoryName) {
            case "Vacancies":
                vacanciesCategory.click();
                break;
            case "Administrator":
                administratorCategory.click();
                break;
            case "Baker":
                bakerCategory.click();
                break;
            default:
                System.out.println("Check 'openingSubCategory' parameters");
        }
    }

    public void openPage(String pageName) {
        switch (pageName) {
            case "memo":
                memoLink.click();
                break;
            case "search":
                searchLink.click();
                break;
            default:
                System.out.println("Check 'openPage' parameters");
        }

    }

    private ArrayList <String> getItemIdFullList() {
        List <WebElement> id_elements = driver.findElements(By.xpath("//*[@id=\"filter_frm\"]/table[2]//tr"));
        ArrayList <String> trId = new ArrayList <String>();
        for (WebElement ele : id_elements) {
            String id = ele.getAttribute("id");
            trId.add(id);
        }
        return trId;
    }

    public void selectSingleMemoCheckBox(int count) {
        String currentId = getItemIdFullListByCount(count);
        WebElement selectedCheckbox = driver.findElement(By.cssSelector("#" + currentId + ">td:nth-child(1)"));
        selectedCheckbox.click();
    }

    public void selectingMultiMemoCheckBox(String memoPositions) {
        List <String> selectedMemo = multiMemoList(memoPositions);
        for (String position : selectedMemo) {
            selectSingleMemoCheckBox(Integer.parseInt(position));
        }
    }

    private String getItemIdFullListByCount(int count) {
        ArrayList <String> tr_id = getItemIdFullList();
        String selectedId = tr_id.get(count);
        return selectedId;
    }

    public void addSingleItemToMemo() {
        Actions actions = new Actions(driver);
        actions.moveToElement(favoriteLink);
        actions.perform();
        favoriteLink.click();
    }

    public void addMuliItemToMemo() {
        Actions actions = new Actions(driver);
        actions.moveToElement(favoriteLinkMulti);
        actions.perform();
        favoriteLinkMulti.click();
    }

    public void openSingleSelectedItem(int count) {
        String currentId = getItemIdFullListByCount(count);
        WebElement selectedCheckbox = driver.findElement(By.cssSelector("#" + currentId + ">td:nth-child(3)"));
        selectedCheckbox.click();
    }

    public int totalMemoCount() {
        return Integer.parseInt(memoCountLabel.getText().replace("(", "").replace(")", "").trim());
    }

    public List <String> multiMemoList(String memoPositions) {
        List <String> selectedItemList = Arrays.asList(memoPositions.split(","));
        return selectedItemList;
    }

    public void bredcrumbValidation(String refValue, String option) {
        String currentLabel = null;
        String[] lable;
        switch (option) {
            case "main category":
                lable = firstPageBredcramb.getText().split(" ");
                currentLabel = lable[0];
                break;
            case "subcategory":
                lable = secondPageBredcramb.getText().split("/");
                int sizeArray = lable.length;
                currentLabel = lable[sizeArray - 1].replace(" ", "");
                break;
            default:
                System.out.println("please check input parameter for 'bredcrumbValidation'");
        }
        assertThat(refValue).isEqualTo(currentLabel);
    }

    public static void collectMainCategory() {
        List <WebElement> idElements = driver.findElements(By.className("category"));
        ArrayList <String> trId = new ArrayList <String>();
        for (WebElement ele : idElements) {
            String idCategory = ele.getAttribute("id");
            String[] fullTitleCategory = ele.getText().split("\\(");
            String titleCategory = fullTitleCategory[0];
            String totalVacancy = fullTitleCategory[1].replace(")", "");
            trId.add(idCategory);
        }
//        System.out.println(trId);
    }

    public static List <SubCategoryData> collectSubСategory() {
        List <WebElement> idElements = driver.findElements(By.cssSelector("#filter_frm > table:nth-child(3) > tbody>tr"));
        List <WebElement> dataElements = driver.findElements(By.cssSelector("td.msg2"));
        List <WebElement> companyElements = driver.findElements(By.cssSelector(".msga2-o.pp6:nth-child(4)"));
        List <WebElement> workingHoursElements = driver.findElements(By.cssSelector(".msga2-o.pp6:nth-child(5)"));
        ArrayList <String> subCategoryId = new ArrayList <String>();
        ArrayList <String> subCategoryDataElements = new ArrayList <String>();
        ArrayList <String> subCategoryCompanyElements = new ArrayList <String>();
        ArrayList <String> subCategoryHours = new ArrayList <String>();
        List <SubCategoryData> subCategoryDataList = new ArrayList <>();

        SsManager.storeSubCategoryDataList(idElements, dataElements, companyElements, workingHoursElements, subCategoryId, subCategoryDataElements, subCategoryCompanyElements, subCategoryHours);
        subCategoryObjectList = SsManager.makeSubCategoryObject(dataElements, subCategoryId, subCategoryDataElements, subCategoryCompanyElements, subCategoryHours, subCategoryDataList);
        return subCategoryObjectList;
    }

    public static List <SubCategoryData> collectSelectedСategoryData(String value) {
        Integer[] selectedItems = SsManager.convertToIntArray(value);
        List <SubCategoryData> listObj = collectSubСategory();
        List <SubCategoryData> selectedSubCategoryDataList = new ArrayList <>();
        selectedSubCategoryItemList = SsManager.makeSubCategorySelectedObject(listObj, selectedItems, selectedSubCategoryDataList);
//        SsManager.printData(selectedSubCategoryItemList);
        return selectedSubCategoryItemList;
    }


    public void memoValidation(String option) {
        switch (option) {
            case "is":
                assertThat(memoCountLabel.isDisplayed()).as("Memo counter is visible").isFalse();
                break;
            case "isn't":
                assertThat(memoCountLabel.isDisplayed()).as("Memo counter is visible").isTrue();
                break;
            default:
                System.out.println("please check input parameter for 'memoValidation'");
        }
    }
}
