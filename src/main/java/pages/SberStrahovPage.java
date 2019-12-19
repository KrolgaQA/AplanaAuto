package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SberStrahovPage {
    WebDriver driver;
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



    public SberStrahovPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void applyInsurance(){
        stepOneBtn.click();
    }

    public void continueInsurance(){
        continueBtn.click();
    }

}
