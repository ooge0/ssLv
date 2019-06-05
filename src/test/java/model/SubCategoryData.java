package model;

public class SubCategoryData {
    String subCategoryId;
    String subCategoryDataElements;
    String subCategoryCompanyElements;
    String subCategoryHours;
    int position;


    public int getPosition() {
        return position;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public String getSubCategoryDataElements() {
        return subCategoryDataElements;
    }

    public String getSubCategoryCompanyElements() {
        return subCategoryCompanyElements;
    }

    public String getSubCategoryHours() {
        return subCategoryHours;
    }

    public SubCategoryData withPosition(int position) {
        this.position = position;
        return this;
    }

    public SubCategoryData withSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
        return this;
    }

    public SubCategoryData withSubCategoryDataElements(String subCategoryDataElements) {
        this.subCategoryDataElements = subCategoryDataElements;
        return this;
    }

    public SubCategoryData withSubCategoryCompanyElements(String subCategoryCompanyElements) {
        this.subCategoryCompanyElements = subCategoryCompanyElements;
        return this;
    }

    public SubCategoryData withSubCategoryHours(String subCategoryHours) {
        this.subCategoryHours = subCategoryHours;
        return this;
    }

    @Override
    public String toString() {
        return "SubCategoryData{" +
                "position=" + position +
                ", subCategoryId='" + subCategoryId + '\'' +
                ", subCategoryDataElements='" + subCategoryDataElements + '\'' +
                ", subCategoryCompanyElements='" + subCategoryCompanyElements + '\'' +
                ", subCategoryHours='" + subCategoryHours + '\'' +
                '}';
    }
}

