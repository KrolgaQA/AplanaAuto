import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class BaseTest {
    protected static WebDriver driver;
    protected static String baseURL;
    public static Properties properties = TestProperties.getINSTANCE().getProperties();

    @BeforeClass
    public static void setUp() {

        switch (properties.getProperty("browser")) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "drv/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            default:
                System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");
                driver = new ChromeDriver();

        }
        baseURL = properties.getProperty("app.url");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseURL);

    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    //Заполнение полей
    public void fillField(By locator, String value) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(value);
    }

    //Заполнение полей
    public void fillField(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

    //Проверка заполнения полей
    protected void checkFillField(String value, By locator) {
        assertEquals(value, driver.findElement(locator).getAttribute("value"));
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

    //Работа с календарем, выбор даты рождения застрахованного
    public void calendarPickDate(WebElement element, String month, String year){
        element.click();
        new Select(driver.findElement(By.className("ui-datepicker-month"))).selectByVisibleText(month);
        new Select(driver.findElement(By.className("ui-datepicker-year"))).selectByVisibleText(year);
        driver.findElement(By.xpath("//a[contains(text(), '22')]")).click();


//        driver.findElement(By.xpath("//section[contains(@class, 'b-form-main-section')]//section[1]//img[contains(@class, 'ui-datepicker-trigger')]")).click();
//        new Select(driver.findElement(By.className("ui-datepicker-month"))).selectByVisibleText("Авг");
//        new Select(driver.findElement(By.className("ui-datepicker-year"))).selectByVisibleText("1992");
//        driver.findElement(By.xpath("//a[contains(text(), '22')]")).click();
    }


}
