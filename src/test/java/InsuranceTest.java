import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.JavascriptExecutor;

public class InsuranceTest {

    WebDriver chromeDriver;
    String baseURL;

    @Before
    public void beforeTest() {
        //Устанавливаем стартовую страницу, драйвер, открываем браузер, переходим в полный экран, переходим на стартовую страницу
        baseURL = "http://www.sberbank.ru/ru/person";
        System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        chromeDriver.manage().window().maximize();
        chromeDriver.get(baseURL);
    }


    @Test
    public void testInsurance() {
        //Поиск и клик по кнопке "Страхование"
        chromeDriver.
                findElement(By.xpath("//button[contains(@class, 'lg-menu__link') and contains (@aria-controls, 'submenu-5')]")).click();
        chromeDriver.
                findElement(By.xpath("//div[contains(@id, 'submenu-5')]//a[contains(text(), 'Страхование путешественников')]")).click();

        //Проверка, что Title соответствует заявленному «Сбербанк» - Страхование путешественников
        Assert.assertEquals(chromeDriver.getTitle(), "«Сбербанк» - Страхование путешественников");

        //Создаем ожидание
        Wait<WebDriver> chromeWait = new WebDriverWait(chromeDriver, 50, 5000);

        //Присваиваем переменной значений окон до того, как было открыто окно со ссылкой
        String currentWind = chromeDriver.getWindowHandle();

        //Ждем, пока появится окно с подтверждением куки и закрываем его
//        WebElement cookieCloseBtn = chromeDriver.
//                findElement(By.xpath("//div[contains(@class, 'kitt-col kitt-col_xs_1')]//a[contains(@class, 'cookie-warning__close')]"));
//        chromeWait.until(ExpectedConditions.visibilityOf(cookieCloseBtn)).click();
//        WebElement cookieCloseBtn = chromeDriver.
//                findElement(By.className("cookie-warning__close"));
        chromeWait.until(ExpectedConditions.visibilityOf(chromeDriver.findElement(By.className("cookie-warning__close")))).click();

        //Ждем, пока появится элемент - кнопка "Оформить сейчас" и кликаем по ней
        WebElement btnSendOnlineChrome = chromeDriver.
                findElement(By.xpath("//a[contains(@class, 'btn') and contains(text(), 'Оформить сейчас')]"));
        chromeWait.until(ExpectedConditions.visibilityOf(btnSendOnlineChrome)).click();

        //Ожидание появления нового окна и переключение на него
        chromeWait.until(ExpectedConditions.numberOfWindowsToBe(2));
        System.out.println(chromeDriver.getWindowHandles());
        Set<String> newWind = chromeDriver.getWindowHandles();
        for (String window : newWind) {
            if (!window.equals(currentWind)) {
                chromeDriver.switchTo().window(window);
            }
        }

        //Проверка, что окно соответствует ожидаемому (Title "Сбербанк страхование"
        Assert.assertEquals(chromeDriver.getTitle(), "Сбербанк страхование");

        //Не выбирая ничего на первом шаге, кликаем "Оформить"
        WebElement stepOneBtn = chromeDriver.
                findElement(By.xpath("//span[contains(@class, 'b-button-block-center')]//span[contains(text(), 'Оформить')]"));
        stepOneBtn.click();

        //Заполняем ФИ и ДР застрахованных
        fillField(By.name("insured0_surname"), "Petrov");
        fillField(By.name("insured0_name"), "Petr");
//        WebElement lastNameInput = chromeDriver.
//                findElement(By.xpath("//input[contains(@name, 'insured0_surname') and contains(@placeholder, 'PETROV')]"));
//        lastNameInput.sendKeys("Petrov");
//        WebElement firstNameInput = chromeDriver.
//                findElement(By.xpath("//input[contains(@name, 'insured0_name') and contains(@placeholder , 'PETR')]"));
//        firstNameInput.sendKeys("Petr");

        //TODO: Работа с календарем, выбор даты рождения застрахованного
        chromeDriver.findElement(By.xpath("//section[contains(@class, 'b-form-section b-form-contacts-section b-form-triple-section g-cleared')]//*[contains(@class, 'ui-datepicker-trigger')]")).click();
//        chromeDriver.switchTo().frame(
//                chromeDriver.findElement(By.className("ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all")));
//        setDatepicker(chromeDriver, "#datepicker", "02.20.2002");


        //Заполняем ФИО и ДР Страхователя
        fillField(By.name("surname"), "Ivanov");
        fillField(By.name("name"), "Ivan");
        fillField(By.name("middlename"), "Ivanovich");
//        WebElement surnameInput = chromeDriver.
//                findElement(By.xpath("//input[contains(@name, 'surname') and contains(@placeholder, 'Фамилия')]"));
//        surnameInput.sendKeys("Ivanov");
//        WebElement nameInput = chromeDriver.
//                findElement(By.xpath("//input[contains(@name, 'name') and contains(@placeholder, 'Имя')]"));
//        nameInput.sendKeys("Ivan");
//        WebElement middlenameInput = chromeDriver.findElement(By.xpath("//input[contains(@name, 'middlename') and contains(@placeholder, 'Отчество')]"));
//        middlenameInput.sendKeys("Ivanovich");

        //TODO: Работа с календарем, выбор даты рождения страхователя

        //Заполняем паспортные данные страхователя
        fillField(By.name("passport_series"), "1111");
        fillField(By.name("passport_number"), "111111");
        fillField(By.name("issuePlace"), "Kem-to vidan");
//        WebElement passportSerieInput = chromeDriver.
//                findElement(By.xpath("//input[contains(@name, 'passport_series') and contains(@placeholder, 'Серия')]"));
//        passportSerieInput.sendKeys("1111");
//        WebElement passportNumberInput = chromeDriver.
//                findElement(By.xpath("//input[contains(@name, 'passport_number') and contains(@placeholder, 'Номер')]"));
//        passportNumberInput.sendKeys("111111");
//        WebElement passportIssuePlaceInput = chromeDriver.
//                findElement(By.xpath("//textarea[contains(@name, 'issuePlace') and contains(@placeholder, 'Кем выдан')]"));
//        passportIssuePlaceInput.sendKeys("Kem-to vidan");

        //TODO: Работа с календарем, выбор даты выдачи паспорта


        //Проверяем, что все поля заполнены правильно
        Assert.assertEquals("Petrov", chromeDriver.findElement(By.name("insured0_surname")));
        Assert.assertEquals("Petr", chromeDriver.findElement(By.name("insured0_name")));

        Assert.assertEquals("Ivanov", chromeDriver.findElement(By.name("surname")));
        Assert.assertEquals("Ivan", chromeDriver.findElement(By.name("name")));
        Assert.assertEquals("Ivanovich", chromeDriver.findElement(By.name("middlename")));

        Assert.assertEquals("1111", chromeDriver.findElement(By.name("passport_series")));
        Assert.assertEquals("111111", chromeDriver.findElement(By.name("passport_number")));
        Assert.assertEquals("Kem-to vidan", chromeDriver.findElement(By.name("issuePlace")));

        //Нажимаем на кнопку "Продолжить"
        WebElement stepTwoBtn = chromeDriver.
                findElement(By.xpath("//span[contains(@class, 'b-continue-btn') and contains(text(), 'Продолжить')]"));
        stepTwoBtn.click();

        //Проверяем, что появилось сообщение о незаполненности полей
        WebElement alertBar = chromeDriver.
                findElement(By.xpath("//div[contains(@class, 'b-form-center-pos b-form-error-message')]//div[contains(text(), 'Заполнены не все обязательные поля')]"));
        Assert.assertEquals(alertBar.getText(), "Заполнены не все обязательные поля");


    }

    //Заполнение полей
    public void fillField(By locator, String value){
        chromeDriver.findElement(locator).clear();
        chromeDriver.findElement(locator).sendKeys(value);
    }

//    public void setDatepicker(WebDriver driver, String cssSelector, String date) {
//        new WebDriverWait(driver, 30000).until(
//                (WebDriver d) -> d.findElement(By.cssSelector(cssSelector)).isDisplayed());
//        JavascriptExecutor.class.cast(driver).executeScript(
//                String.format("$('%s').datepicker('setDate', '%s')", cssSelector, date));
//    }


    @After
    public void afterTest() {

        chromeDriver.quit();
    }
}
