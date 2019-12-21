package ru.aplana.auto.steps;

import ru.aplana.auto.pages.SberStrahovPage;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.Map;

public class SberStrahSteps {
    @Step("Выбрать страховку")
    public void applyInsurance(){
        new SberStrahovPage().applyInsurance();
    }

    @Step("Подтвердить страховку")
    public void continueInsurance(){
        new SberStrahovPage().continueInsurance();
    }

    @Step("Заполнение календаря {0} месяцем {1} и годом {2}")
    public void fillCalendarPicker(String calendarName, String month, String year){
        new SberStrahovPage().fillCalendarPicker(calendarName, month, year);
    }

    @Step("Заполнение поля {0} значением {1}")
    public void fillField(String fieldName, String value){
        new SberStrahovPage().fillFieldByName(fieldName, value);
    }

    @Step("Заполнение полей:")
    public void fillFields(Map<String, String> fields){
        fields.forEach(this::fillField);

//        for (Map.Entry<String, String> entry: fields.entrySet()
//             ) {
//            fillField(entry.getKey(), entry.getValue());
//        }

    }


    @Step("Проверка заполненности поля {0} значением {1}")
    public void checkFillField(String fieldName, String value){
        new SberStrahovPage().checkFieldByName(fieldName, value);
    }

    @Step("Проверка заполненности полей")
    public void checkFillFields(Map<String, String> fields){
        fields.forEach(this::checkFillField);
    }


    @Step("Проверка появления поля с ошибкой {0}")
    public void checkErrText(String value){
        new SberStrahovPage().checkErrText(value);
    }
}
