package managers.ssManagers;

import model.SubCategoryData;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebElement;
import pagesSample.ssLv.SsLvMain;

import java.util.ArrayList;
import java.util.List;

import static pagesSample.ssLv.SsLvMain.selectedSubCategoryItemList;
import static pagesSample.ssLv.SsLvMain.subCategoryObjectList;

public class SsManager {
    public static void printSubCategory(String id, String data, String company, String hours) {
        System.out.println("subCategoryId: " + id
                + "\nsubCategoryDataElements: " + data
                + "\nsubCategoryCompanyElements: " + company
                + "\nsubCategoryHours: " + hours + "\n\n");
    }

    public static void printSubCategory(String id, String data, String company) {
        System.out.println("subCategoryId: " + id
                + "\nsubCategoryDataElements: " + data
                + "\nsubCategoryCompanyElements: " + company + "\n\n");
    }

    public void collectPageData(String option, String value) {
        switch (option) {
            case "main category":
                SsLvMain.collectMainCategory();
                break;
            case "subcategory":
                SsLvMain.collectSubСategory();
                break;
            case "selected":
                SsLvMain.collectSelectedСategoryData(value);
                break;
            default:
                System.out.println("please check input parameter for 'breadcrumbValidation'");
        }
    }

    public static List <SubCategoryData> makeSubCategoryObject(List <WebElement> dataElements, ArrayList <String> subCategoryId, ArrayList <String> subCategoryDataElements, ArrayList <String> subCategoryCompanyElements, ArrayList <String> subCategoryHours, List <SubCategoryData> subCategoryDataList) {
        for (int i = 0; i < dataElements.size(); i++) {
            SubCategoryData subCategoryDataCollection = new SubCategoryData();
            String id = subCategoryId.get(i);
            String data = subCategoryDataElements.get(i);
            String company = subCategoryCompanyElements.get(i);
            String hours = subCategoryHours.get(i);
            subCategoryDataList.add(
                    subCategoryDataCollection.withPosition(i)
                            .withSubCategoryId(id)
                            .withSubCategoryDataElements(data)
                            .withSubCategoryCompanyElements(company)
                            .withSubCategoryHours(hours)
            );
        }
        return subCategoryDataList;
    }


    public static List <SubCategoryData> makeSubCategorySelectedObject(List <SubCategoryData> listObj, Integer[] selectedItems, List <SubCategoryData> selectedSubCategoryItemList) {
        for (Integer itemPosition : selectedItems) {
            SubCategoryData obj = listObj.get(itemPosition-1);
            selectedSubCategoryItemList.add(obj);
        }
        return selectedSubCategoryItemList;
    }

    public static void makeMainCategoryObject(List <WebElement> dataElements, ArrayList <String> mainCategoryId, ArrayList <String> subCategoryDataElements, ArrayList <String> subCategoryCompanyElements, ArrayList <String> subCategoryHours, List <SubCategoryData> subCategoryDataList) {
        for (int i = 0; i < dataElements.size(); i++) {
            SubCategoryData subCategoryDataCollection = new SubCategoryData();
            String id = mainCategoryId.get(i);
            String data = subCategoryDataElements.get(i);
            String company = subCategoryCompanyElements.get(i);
            SsManager.printSubCategory(id, data, company);
            subCategoryDataList.add(
                    subCategoryDataCollection.withPosition(i)
                            .withSubCategoryId(id)
                            .withSubCategoryDataElements(data)
                            .withSubCategoryCompanyElements(company)
            );
        }
    }

    public static void storeSubCategoryDataList(List <WebElement> idElements,
                                                List <WebElement> dataElements,
                                                List <WebElement> companyElements,
                                                List <WebElement> workingHoursElements,
                                                ArrayList <String> subCategoryId,
                                                ArrayList <String> subCategoryDataElements,
                                                ArrayList <String> subCategoryCompanyElements,
                                                ArrayList <String> subCategoryHours) {
        for (WebElement ele : idElements) {
            subCategoryId.add(ele.getAttribute("id"));
        }

        for (WebElement ele : dataElements) {
            subCategoryDataElements.add(ele.getText());
        }
        for (WebElement ele : companyElements) {
            subCategoryCompanyElements.add(ele.getText());
        }
        for (WebElement ele : workingHoursElements) {
            subCategoryHours.add(ele.getText());
        }
        subCategoryId.remove(0); // 1st element contains the placeholder on the subCategory page
    }

    public static void printData(List <SubCategoryData> subCategoryDataList) {
        for (SubCategoryData subCategoryData : subCategoryDataList) {
            System.out.println(subCategoryData.toString());
        }
    }

    public static Integer[] convertToIntArray(String value) {
        String arr[] = value.replace(" ", "").split(",");
        Integer [] intArr = new Integer[arr.length];
        for (int i = 0; i<arr.length; i++) {
            intArr[i] = Integer.parseInt(arr[i]);
        }
        return intArr;
    }

    public void comparingMemoCollections(String position) {
        Integer[] positionArray = SsManager.convertToIntArray(position);
        List <SubCategoryData> referenceCollection = subCategoryObjectList;
        List <SubCategoryData> memoCollection = selectedSubCategoryItemList;
        for (Integer num : positionArray) {
            num = num -1;
            SubCategoryData memoObj = memoCollection.get(num);
            SubCategoryData refObj = referenceCollection.get(num);
            SoftAssertions softly = new SoftAssertions();
            softly.assertThat(memoObj).isEqualTo(refObj);
            softly.assertThat(memoObj.getSubCategoryId()).isEqualTo(refObj.getSubCategoryId());
            softly.assertThat(memoObj).isNotNull();
            softly.assertThat(refObj).isNotNull();
            softly.assertAll();

        }
    }

}
