import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class SberTest extends BaseTest{


    @Test
    @Ignore
    public void testInsurance() throws InterruptedException {

        //Создаем ожидание
        Wait<WebDriver> chromeWait = new WebDriverWait(driver, 50, 50000);

        //Ждем, пока появится окно с подтверждением куки и закрываем его
        int n = 5;
        for (int i = 1; i <= n; i++) {
            try {
                chromeWait.until(ExpectedConditions.elementToBeClickable(driver.
                        findElement(By.xpath("//a[contains(@class, 'cookie-warning__close') and contains(@title, 'Закрыть предупреждение')]")))).click();
                break;
            } catch (WebDriverException driverException) {
                System.out.println("Click on element failed. Attempt: " + i + "/" + n);
                Thread.sleep(1000);
            }
            if (i == n) {
                Assert.fail("Failed to click " + n + " times");
            }
        }


        //Поиск и клик по кнопке "Страхование"
        driver.
                findElement(By.xpath("//button[contains(@class, 'lg-menu__link') and contains (@aria-controls, 'submenu-5')]")).click();
        driver.
                findElement(By.xpath("//a[contains(@class, 'lg-menu__sub-link') and contains(text(), 'Страхование путешественников')]")).click();

        //Проверка, что Title соответствует заявленному «Сбербанк» - Страхование путешественников
        Assert.assertEquals(driver.getTitle(), "«Сбербанк» - Страхование путешественников");


        //Ждем, пока появится элемент - кнопка "Оформить сейчас" и кликаем по ней
        WebElement btnSendOnlineChrome = driver.
                findElement(By.xpath("//p/a[contains(@class, 'btn') and contains(text(), 'Оформить сейчас')]"));
        chromeWait.until(ExpectedConditions.visibilityOf(btnSendOnlineChrome));
        btnSendOnlineChrome.click();

        //Присваиваем переменной значений окон до того, как было открыто окно со ссылкой
        String currentWind = driver.getWindowHandle();

        //Ожидание появления нового окна и переключение на него
        chromeWait.until(ExpectedConditions.numberOfWindowsToBe(2));
        System.out.println(driver.getWindowHandles());
        Set<String> newWind = driver.getWindowHandles();
        for (String window : newWind) {
            if (!window.equals(currentWind)) {
                driver.switchTo().window(window);
            }
        }

        //Проверка, что окно соответствует ожидаемому (Title "Сбербанк страхование"
        Assert.assertEquals(driver.getTitle(), "Сбербанк страхование");

        //Не выбирая ничего на первом шаге, кликаем "Оформить"
        WebElement stepOneBtn = driver.
                findElement(By.xpath("//span[contains(@class, 'b-button-block-center')]//span[contains(text(), 'Оформить')]"));
        stepOneBtn.click();

        //Заполняем ФИ и ДР застрахованных
        fillField(By.name("insured0_surname"), "Petrov");
        fillField(By.name("insured0_name"), "Petr");

        //Работа с календарем, выбор даты рождения застрахованного
        driver.findElement(By.xpath("//section[contains(@class, 'b-form-main-section')]//section[1]//img[contains(@class, 'ui-datepicker-trigger')]")).click();
        new Select(driver.findElement(By.className("ui-datepicker-month"))).selectByVisibleText("Авг");
        new Select(driver.findElement(By.className("ui-datepicker-year"))).selectByVisibleText("1992");
        driver.findElement(By.xpath("//a[contains(text(), '22')]")).click();


        //Заполняем ФИО и ДР Страхователя
        fillField(By.name("surname"), "Иванов");
        fillField(By.name("name"), "Иван");
        fillField(By.name("middlename"), "Иванович");

        //Работа с календарем, выбор даты рождения страхователя
        driver.findElement(By.xpath("//section[contains(@class, 'b-form-main-section')]//section[2]//img[contains(@class, 'ui-datepicker-trigger')]")).click();
        new Select(driver.findElement(By.className("ui-datepicker-month"))).selectByVisibleText("Авг");
        new Select(driver.findElement(By.className("ui-datepicker-year"))).selectByVisibleText("1992");
        driver.findElement(By.xpath("//a[contains(text(), '22')]")).click();


        //Заполняем паспортные данные страхователя
        fillField(By.name("passport_series"), "1111");
        fillField(By.name("passport_number"), "111111");
        fillField(By.name("issuePlace"), "Kem-to vidan");

        //Работа с календарем, выбор даты выдачи паспорта
        driver.findElement(By.xpath("//section[contains(@class, 'b-form-main-section')]//section[3]//img[contains(@class, 'ui-datepicker-trigger')]")).click();
        new Select(driver.findElement(By.className("ui-datepicker-month"))).selectByVisibleText("Авг");
        new Select(driver.findElement(By.className("ui-datepicker-year"))).selectByVisibleText("1992");
        driver.findElement(By.xpath("//a[contains(text(), '22')]")).click();


        //Проверяем, что все поля заполнены правильно
        Assert.assertEquals("Petrov", driver.findElement(By.name("insured0_surname")).getAttribute("value"));
        Assert.assertEquals("Petr", driver.findElement(By.name("insured0_name")).getAttribute("value"));
        Assert.assertEquals("22.08.1992", driver.findElement(By.name("insured0_birthDate")).getAttribute("value"));

        Assert.assertEquals("Иванов", driver.findElement(By.name("surname")).getAttribute("value"));
        Assert.assertEquals("Иван", driver.findElement(By.name("name")).getAttribute("value"));
        Assert.assertEquals("Иванович", driver.findElement(By.name("middlename")).getAttribute("value"));
        Assert.assertEquals("22.08.1992", driver.findElement(By.name("birthDateItem")).getAttribute("value"));

        Assert.assertEquals("1111", driver.findElement(By.name("passport_series")).getAttribute("value"));
        Assert.assertEquals("111111", driver.findElement(By.name("passport_number")).getAttribute("value"));
        Assert.assertEquals("Kem-to vidan", driver.findElement(By.name("issuePlace")).getAttribute("value"));
        Assert.assertEquals("22.08.1992", driver.findElement(By.name("issueDate")).getAttribute("value"));

        //Нажимаем на кнопку "Продолжить"
        WebElement stepTwoBtn = driver.
                findElement(By.xpath("//span[contains(@class, 'b-continue-btn') and contains(text(), 'Продолжить')]"));
        stepTwoBtn.click();

        //Проверяем, что появилось сообщение о незаполненности полей
        WebElement alertBar = driver.
                findElement(By.xpath("//div[contains(@class, 'b-form-center-pos b-form-error-message')]//div[contains(text(), 'Заполнены не все обязательные поля')]"));
        Assert.assertEquals(alertBar.getText(), "Заполнены не все обязательные поля");
    }
}

