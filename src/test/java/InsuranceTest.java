import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.concurrent.TimeUnit;


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
        //Создаем ожидание
        Wait<WebDriver> chromeWait = new WebDriverWait(chromeDriver, 50, 50000);

        //Ждем, пока появится окно с подтверждением куки и закрываем его
        chromeWait.until(ExpectedConditions.elementToBeClickable(chromeDriver.
                findElement(By.xpath("//a[contains(@class, 'cookie-warning__close') and contains(@title, 'Закрыть предупреждение')]"))));

        //Поиск и клик по кнопке "Страхование"
        chromeDriver.
                findElement(By.xpath("//button[contains(@class, 'lg-menu__link') and contains (@aria-controls, 'submenu-5')]")).click();
        chromeDriver.
                findElement(By.xpath("//a[contains(@class, 'lg-menu__sub-link') and contains(text(), 'Страхование путешественников')]")).click();

        //Проверка, что Title соответствует заявленному «Сбербанк» - Страхование путешественников
        Assert.assertEquals(chromeDriver.getTitle(), "«Сбербанк» - Страхование путешественников");



        //Ждем, пока появится элемент - кнопка "Оформить сейчас" и кликаем по ней
        WebElement btnSendOnlineChrome = chromeDriver.
                findElement(By.xpath("//p/a[contains(@class, 'btn') and contains(text(), 'Оформить сейчас')]"));
        chromeWait.until(ExpectedConditions.visibilityOf(btnSendOnlineChrome));
        btnSendOnlineChrome.click();

        //Присваиваем переменной значений окон до того, как было открыто окно со ссылкой
        String currentWind = chromeDriver.getWindowHandle();

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

        //TODO: Работа с календарем, выбор даты рождения застрахованного
        //TODO: Уникально идентифицировать селектор
        chromeDriver.findElement(By.xpath("//section[contains(@class, 'b-form-main-section')]//section[1]//img[contains(@class, 'ui-datepicker-trigger')]")).click();
        new Select(chromeDriver.findElement(By.className("ui-datepicker-month"))).selectByVisibleText("Авг");
        new Select(chromeDriver.findElement(By.className("ui-datepicker-year"))).selectByVisibleText("1992");
        chromeDriver.findElement(By.xpath("//a[contains(text(), '22')]")).click();


        //Заполняем ФИО и ДР Страхователя
        fillField(By.name("surname"), "Иванов");
        fillField(By.name("name"), "Иван");
        fillField(By.name("middlename"), "Иванович");

        //Работа с календарем, выбор даты рождения страхователя
        chromeDriver.findElement(By.xpath("//section[contains(@class, 'b-form-main-section')]//section[2]//img[contains(@class, 'ui-datepicker-trigger')]")).click();
        new Select(chromeDriver.findElement(By.className("ui-datepicker-month"))).selectByVisibleText("Авг");
        new Select(chromeDriver.findElement(By.className("ui-datepicker-year"))).selectByVisibleText("1992");
        chromeDriver.findElement(By.xpath("//a[contains(text(), '22')]")).click();


        //Заполняем паспортные данные страхователя
        fillField(By.name("passport_series"), "1111");
        fillField(By.name("passport_number"), "111111");
        fillField(By.name("issuePlace"), "Kem-to vidan");

        //Работа с календарем, выбор даты выдачи паспорта
        chromeDriver.findElement(By.xpath("//section[contains(@class, 'b-form-main-section')]//section[3]//img[contains(@class, 'ui-datepicker-trigger')]")).click();
        new Select(chromeDriver.findElement(By.className("ui-datepicker-month"))).selectByVisibleText("Авг");
        new Select(chromeDriver.findElement(By.className("ui-datepicker-year"))).selectByVisibleText("1992");
        chromeDriver.findElement(By.xpath("//a[contains(text(), '22')]")).click();


        //Проверяем, что все поля заполнены правильно
        Assert.assertEquals("Petrov", chromeDriver.findElement(By.name("insured0_surname")).getAttribute("value"));
        Assert.assertEquals("Petr", chromeDriver.findElement(By.name("insured0_name")).getAttribute("value"));
        Assert.assertEquals("22.08.1992", chromeDriver.findElement(By.name("insured0_birthDate")).getAttribute("value"));

        Assert.assertEquals("Иванов", chromeDriver.findElement(By.name("surname")).getAttribute("value"));
        Assert.assertEquals("Иван", chromeDriver.findElement(By.name("name")).getAttribute("value"));
        Assert.assertEquals("Иванович", chromeDriver.findElement(By.name("middlename")).getAttribute("value"));
        Assert.assertEquals("22.08.1992", chromeDriver.findElement(By.name("birthDate")).getAttribute("value"));

        Assert.assertEquals("1111", chromeDriver.findElement(By.name("passport_series")).getAttribute("value"));
        Assert.assertEquals("111111", chromeDriver.findElement(By.name("passport_number")).getAttribute("value"));
        Assert.assertEquals("Kem-to vidan", chromeDriver.findElement(By.name("issuePlace")).getAttribute("value"));
        Assert.assertEquals("22.08.1992", chromeDriver.findElement(By.name("issueDate")).getAttribute("value"));

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
    public void fillField(By locator, String value) {
        chromeDriver.findElement(locator).clear();
        chromeDriver.findElement(locator).sendKeys(value);
    }


    @After
    public void afterTest() {

        chromeDriver.quit();
    }
}

