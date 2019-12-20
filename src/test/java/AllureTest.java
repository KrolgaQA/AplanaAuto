import org.junit.Before;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Title;
import steps.BaseSteps;
import steps.SberMainSteps;
import steps.SberSendSteps;
import steps.SberStrahSteps;

import java.util.LinkedHashMap;
import java.util.Map;

public class AllureTest extends BaseSteps {
    SberMainSteps sberMainSteps = new SberMainSteps();
    SberSendSteps sberSendSteps = new SberSendSteps();
    SberStrahSteps sberStrahSteps = new SberStrahSteps();
    Map<String, String> testData = new LinkedHashMap<>();
    Map<String, String> checkData = new LinkedHashMap<>();

    @Before
    public void setUpTestData(){
        testData.put("Фамилия застрахованного", "Petrov");
        testData.put("Имя застрахованного", "Petr");
        testData.put("Фамилия страхователя", "Иванов");
        testData.put("Имя страхователя", "Иван");
        testData.put("Отчество страхователя", "Иванович");
        testData.put("Серия паспорта страхователя", "1111");
        testData.put("Номер паспорта страхователя", "111111");
        testData.put("Данные о выдаче паспорта", "Кем-то выдан");


        checkData.put("Фамилия застрахованного", "Petrov");
        checkData.put("Имя застрахованного", "Petr");
        checkData.put("Дата рождения застрахованного", "22.08.1992");
        checkData.put("Фамилия страхователя", "Иванов");
        checkData.put("Имя страхователя", "Иван");
        checkData.put("Отчество страхователя", "Иванович");
        checkData.put("Дата рождения страхователя", "22.06.1995");
        checkData.put("Серия паспорта страхователя", "1111");
        checkData.put("Номер паспорта страхователя", "111111");
        checkData.put("Данные о выдаче паспорта", "Кем-то выдан");
        checkData.put("Дата выдачи паспорта","22.11.2015");
    }
    @Title("Заявка на страховку Путешественников")
    @Test
    public void newSberTest() throws InterruptedException {
        sberMainSteps.closeCookie();
        sberMainSteps.selectMainMenu();
        sberMainSteps.selectSubMenu();
        checkPageTitle("«Сбербанк» - Страхование путешественников");
        sberSendSteps.takeInsurance();
        switchWindow();
        checkPageTitle("Сбербанк страхование");
        sberStrahSteps.applyInsurance();

        sberStrahSteps.fillFields(testData);
        sberStrahSteps.fillCalendarPicker("Дата рождения застрахованного", "Авг","1992");
        sberStrahSteps.fillCalendarPicker("Дата рождения страхователя", "Июн","1995");
        sberStrahSteps.fillCalendarPicker("Дата выдачи паспорта", "Ноя","2015");

        sberStrahSteps.checkFillFields(checkData);
        sberStrahSteps.continueInsurance();
        sberStrahSteps.checkErrText("Заполнены не все обязательные поля");



    }
}
