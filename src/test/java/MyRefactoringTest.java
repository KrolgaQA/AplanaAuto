import org.junit.Test;
import pages.SberMainPage;
import pages.SberSendPage;
import pages.SberStrahovPage;


public class MyRefactoringTest extends BaseTest {


    @Test
    public void newSberTest() throws InterruptedException {
        SberMainPage mainPage = new SberMainPage(driver);
        mainPage.closeCookie();
        mainPage.selectMainMenu();
        mainPage.selectSubMenu();
        checkPageTitle("«Сбербанк» - Страхование путешественников");
        SberSendPage sendPage = new SberSendPage(driver);
        sendPage.takeInsurance();
        takeNewWindow();
        checkPageTitle("Сбербанк страхование");
        SberStrahovPage strahovPage = new SberStrahovPage(driver);
        strahovPage.applyInsurance();
        fillField(strahovPage.insuredSurnameItem, "Petrov");
        fillField(strahovPage.insuredNameItem, "Petr");
        calendarPickDate(strahovPage.insuredBirthDateItem, "Авг","1992");
        fillField(strahovPage.surnameItem, "Иванов");
        fillField(strahovPage.nameItem, "Иван");
        fillField(strahovPage.middlenameItem, "Иванович");
        calendarPickDate(strahovPage.birthDateItem, "Июн","1995");
        fillField(strahovPage.passportSeriesItem,"1111");
        fillField(strahovPage.passportNumberItem,"111111");
        fillField(strahovPage.issuePlaceItem,"Кем-то выдан");
        calendarPickDate(strahovPage.issueDateItem, "Ноя","2015");
        checkFillField(strahovPage.insuredSurnameItem, "Petrov");
        checkFillField(strahovPage.insuredNameItem, "Petr");
        checkFillField(strahovPage.insuredBirthDateInput, "22.08.1992");
        checkFillField(strahovPage.surnameItem, "Иванов");
        checkFillField(strahovPage.nameItem, "Иван");
        checkFillField(strahovPage.middlenameItem, "Иванович");
        checkFillField(strahovPage.birthDateInput, "22.06.1995");
        checkFillField(strahovPage.passportSeriesItem, "1111");
        checkFillField(strahovPage.passportNumberItem, "111111");
        checkFillField(strahovPage.issuePlaceItem, "Кем-то выдан");
        checkFillField(strahovPage.issueDateInput, "22.11.2015");
        strahovPage.continueInsurance();
        checkErrText(strahovPage.errText, "Заполнены не все обязательные поля");
    }
}
