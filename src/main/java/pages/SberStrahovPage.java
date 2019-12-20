package pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SberStrahovPage extends BasePage {
    @FindBy(xpath = "//span[contains(@class, 'b-button-block-center')]//span[contains(text(), 'Оформить')]")
    WebElement stepOneBtn;

    @FindBy(name = "insured0_surname")
    public WebElement insuredSurnameItem;

    @FindBy(name = "insured0_name")
    public WebElement insuredNameItem;

    @FindBy(xpath = "//section[contains(@class, 'b-form-main-section')]//section[1]//img[contains(@class, 'ui-datepicker-trigger')]")
    public WebElement insuredBirthDateItem;

    @FindBy(name = "surname")
    public WebElement surnameItem;

    @FindBy(name = "name")
    public WebElement nameItem;

    @FindBy(name = "middlename")
    public WebElement middlenameItem;

    @FindBy(xpath = "//section[contains(@class, 'b-form-main-section')]//section[2]//img[contains(@class, 'ui-datepicker-trigger')]")
    public WebElement birthDateItem;

    @FindBy(name = "passport_series")
    public WebElement passportSeriesItem;
    @FindBy(name = "passport_number")
    public WebElement passportNumberItem;
    @FindBy(name = "issuePlace")
    public WebElement issuePlaceItem;

    @FindBy(xpath = "//section[contains(@class, 'b-form-main-section')]//section[3]//img[contains(@class, 'ui-datepicker-trigger')]")
    public WebElement issueDateItem;

    @FindBy(xpath = "//span[contains(@class, 'b-continue-btn') and contains(text(), 'Продолжить')]")
    public WebElement continueBtn;

    @FindBy(xpath = "//div[contains(@class, 'b-form-center-pos b-form-error-message')]//div[contains(text(), 'Заполнены не все обязательные поля')]")
    public WebElement errText;

    @FindBy(name = "insured0_birthDate")
    public WebElement insuredBirthDateInput;

    @FindBy(name = "birthDate")
    public WebElement birthDateInput;

    @FindBy(name = "issueDate")
    public WebElement issueDateInput;


//    public SberStrahovPage(WebDriver driver) {
//        PageFactory.initElements(driver, this);
//        this.driver = driver;
//    }
//
//    public SberStrahovPage(){
//        PageFactory.initElements(BaseSteps.getDriver(), this);
//        this.driver = BaseSteps.getDriver();
//
//    }

    public void applyInsurance() {
        stepOneBtn.click();
    }

    public void continueInsurance() {
        continueBtn.click();
    }


    public void fillCalendarPicker(String calendarName, String month, String year){
        switch (calendarName) {
            case "Дата рождения застрахованного":
                calendarPickDate(insuredBirthDateItem, month, year);
            case "Дата рождения страхователя":
                calendarPickDate(birthDateItem, month, year);
            case "Дата выдачи паспорта":
                calendarPickDate(issueDateItem, month, year);
        }
    }

    public void fillFieldByName(String fieldName, String value) {
        switch (fieldName) {
            case "Фамилия застрахованного":
                fillField(insuredSurnameItem, value);
                break;
            case "Имя застрахованного":
                fillField(insuredNameItem, value);
                break;
            case "Фамилия страхователя":
                fillField(surnameItem, value);
                break;
            case "Имя страхователя":
                fillField(nameItem, value);
                break;
            case "Отчество страхователя":
                fillField(middlenameItem, value);
                break;
            case "Серия паспорта страхователя":
                fillField(passportSeriesItem, value);
                break;
            case "Номер паспорта страхователя":
                fillField(passportNumberItem, value);
                break;
            case "Данные о выдаче паспорта":
                fillField(issuePlaceItem, value);
                break;
            default:
                throw new AssertionError("Поле '" + fieldName + "' не объявлено на странице");
        }
    }

    public void checkFieldByName(String fieldName, String value) {
        switch (fieldName) {
            case "Фамилия застрахованного":
                checkFillField(insuredSurnameItem, value);
                break;
            case "Имя застрахованного":
                checkFillField(insuredNameItem, value);
                break;
            case "Дата рождения застрахованного":
                checkFillField(insuredBirthDateInput, value);
                break;
            case "Фамилия страхователя":
                checkFillField(surnameItem, value);
                break;
            case "Имя страхователя":
                checkFillField(nameItem, value);
                break;
            case "Отчество страхователя":
                checkFillField(middlenameItem, value);
                break;
            case "Дата рождения страхователя":
                checkFillField(birthDateInput, value);
                break;
            case "Серия паспорта страхователя":
                checkFillField(passportSeriesItem, value);
                break;
            case "Номер паспорта страхователя":
                checkFillField(passportNumberItem, value);
                break;
            case "Данные о выдаче паспорта":
                checkFillField(issuePlaceItem, value);
                break;
            case "Дата выдачи паспорта":
                checkFillField(issueDateInput, value);
                break;
            default:
                throw new AssertionError("Поле '" + fieldName + "' не объявлено на странице");
        }
    }

    public void checkErrText(String value){
        checkErrText(errText,value);
    }

}
