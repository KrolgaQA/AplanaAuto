package ru.aplana.auto.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.aplana.auto.steps.BaseSteps;

public class SberSendPage {
    WebDriver driver;

    @FindBy(xpath = "//p/a[contains(@class, 'btn') and contains(text(), 'Оформить сейчас')]")
    WebElement takeInsuranceBtn;

//    public SberSendPage(WebDriver driver) {
//        PageFactory.initElements(driver, this);
//        this.driver = driver;
//    }
    public SberSendPage(){
        PageFactory.initElements(BaseSteps.getDriver(), this);
        this.driver = BaseSteps.getDriver();
    }

    public void takeInsurance(){
        Wait<WebDriver> chromeWait = new WebDriverWait(driver, 50, 50000);

        chromeWait.until(ExpectedConditions.visibilityOf(takeInsuranceBtn)).click();
    }
}
