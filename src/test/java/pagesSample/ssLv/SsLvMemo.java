package pagesSample.ssLv;

import helpers.HelperBase;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static stepDefinitions.Hooks.driver;

public class SsLvMemo extends HelperBase {
    public SsLvMemo(WebDriver wd) throws IOException {
        super(wd);
    }


    @FindBy(how = How.CSS, using = "#mnu_fav_id")
    private WebElement memoTotalCounter;

    @FindBy(how = How.CSS, using = "#main_table > span.page_header_menu > b:nth-child(3) > a")
    private WebElement searchLink;

    @FindBy(how = How.CSS, using = "#[type=\"checkbox\"]")
    private WebElement memoTable;

    @FindBy(how = How.CSS, using = "#del_selected_a")
    private WebElement removeLink;

    private void awaitingElementAppearance(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public ArrayList<String> getMemoIdList() {
        List<WebElement> idMemo = driver.findElements(By.xpath("//*[@id=\"filter_frm\"]/table//tr"));
        ArrayList<String> trId = new ArrayList<String>();
        for (WebElement ele : idMemo) {
            String id = ele.getAttribute("id");
            trId.add(id);
        }
        trId.remove(0);

        return trId;
    }

    public int totalMemoItemOnPage() {
        List<WebElement> memoAmount =  driver.findElements(By.cssSelector("[type=\"checkbox\"]"));
        return memoAmount.size();
    }

    public void deleteAllMemo() {
        ArrayList<String> currentIdList = getMemoIdList();
        for (String idMemo : currentIdList) {
            WebElement selectedCheckbox = driver.findElement(By.cssSelector("#" + idMemo + ">td:nth-child(1)"));
            selectedCheckbox.click();
            awaitingElementAppearance(removeLink);
            removeLink.click();
        }
    }

    public void memoCountValidation(int memoCount, int totalMemoCount) {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(totalMemoCount).as("Current account has: ", totalMemoCount, "memo").isEqualTo(memoCount);
        softly.assertThat(totalMemoCount).isNotNull();
        softly.assertAll();
    }

}
