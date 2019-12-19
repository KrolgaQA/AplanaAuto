package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SberMainPage extends BasePage {
    WebDriver driver;

    @FindBy(xpath = "//a[contains(@class, 'cookie-warning__close') and contains(@title, 'Закрыть предупреждение')]")
    WebElement cookieCloseButton;

    @FindBy(xpath = "//button[contains(@class, 'lg-menu__link') and contains (@aria-controls, 'submenu-5')]")
    WebElement mainMenu;

    @FindBy(xpath = "//a[contains(@class, 'lg-menu__sub-link') and contains(text(), 'Страхование путешественников')]")
    WebElement subMenu;

    public SberMainPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;

    }

    public void closeCookie() throws InterruptedException {
        Wait<WebDriver> chromeWait = new WebDriverWait(driver, 50, 50000);
        int n = 5;
        for (int i = 1; i <= n; i++) {
            try {
                chromeWait.until(ExpectedConditions.elementToBeClickable(cookieCloseButton)).click();
                break;
            } catch (WebDriverException driverException) {
                System.out.println("Click on element failed. Attempt: " + i + "/" + n);
                Thread.sleep(1000);
            }
            if (i == n) {
                Assert.fail("Failed to click " + n + " times");
            }
        }
    }

    public void selectMainMenu(){
        mainMenu.click();
    }

    public void selectSubMenu(){
        subMenu.click();
    }
}
