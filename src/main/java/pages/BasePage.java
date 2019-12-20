package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.BaseSteps;

import java.util.Set;


public class BasePage {

    WebDriver driver;

    public BasePage() {
        this.driver = BaseSteps.getDriver();
        PageFactory.initElements(driver, this);
    }


    //Заполнение полей
    public void fillField(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

    public void calendarPickDate(WebElement element, String month, String year) {
        element.click();
        new Select(driver.findElement(By.className("ui-datepicker-month"))).selectByVisibleText(month);
        new Select(driver.findElement(By.className("ui-datepicker-year"))).selectByVisibleText(year);
        driver.findElement(By.xpath("//a[contains(text(), '22')]")).click();

    }


    //Переключение на активное окно
    public void takeNewWindow() {
        //Присваиваем переменной значений окон до того, как было открыто окно со ссылкой
        String currentWind = driver.getWindowHandle();
        //Ожидание появления нового окна и переключение на него
        Wait<WebDriver> chromeWait = new WebDriverWait(driver, 50, 50000);
        chromeWait.until(ExpectedConditions.numberOfWindowsToBe(2));
        System.out.println(driver.getWindowHandles());
        Set<String> newWind = driver.getWindowHandles();
        for (String window : newWind) {
            if (!window.equals(currentWind)) {
                driver.switchTo().window(window);
            }
        }
    }

    public void checkPageTitle(String pageTitle){
        Assert.assertEquals(driver.getTitle(), pageTitle);
    }

    public void checkFillField(WebElement element, String fieldFiller){
        Assert.assertEquals(element.getAttribute("value"), fieldFiller);
    }

    public void checkErrText(WebElement element, String fieldFiller){
        Assert.assertEquals(element.getText(), fieldFiller);
    }

}
