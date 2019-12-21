package ru.aplana.auto.steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ScenarioSteps {
    BaseSteps baseSteps = new BaseSteps();
    SberMainSteps sberMainSteps = new SberMainSteps();
    SberSendSteps sberSendSteps = new SberSendSteps();
    SberStrahSteps sberStrahSteps = new SberStrahSteps();

    @When("^Закрыты куки$")
    public void closeCookie() throws InterruptedException {
        sberMainSteps.closeCookie();
    }

    @When("^Выбран пункт меню Страхование$")
    public void selectMainMenu() {
        sberMainSteps.selectMainMenu();
    }

    @When("^Выбран подпункт меню Страхование путешественников$")
    public void selectSubMenu() {
        sberMainSteps.selectSubMenu();
    }

    @Then("^Заголовок страницы равен \"(.+)\"$")
    public void checkPageTitle(String title) {
        baseSteps.checkPageTitle(title);
    }

    @When("^Нажата кнопка \"Оформить сейчас\"$")
    public void takeInsurance() {
        sberSendSteps.takeInsurance();
    }

    @When("^Произошло переключение на вкладку \"Страхование путешественников\"$")
    public void switchWindow() {
        baseSteps.switchWindow();
    }

    @When("^Нажата кнопка \"Оформить\"$")
    public void applyInsurance() {
        sberStrahSteps.applyInsurance();
    }

    @When("^Нажата кнопка \"Продолжить\"$")
    public void continueInsurance() {
        sberStrahSteps.continueInsurance();
    }

    @Then("^Сообщение об ошибке равно: \"(.+)\"$")
    public void checkErrText(String errText) {
        sberStrahSteps.checkErrText(errText);
    }

    @When("^Заполняются поля:$")
    public void fillFields(DataTable dataTable) {
//        dataTable.asMap(String.class, String.class)
//                .forEach((field, value)->sberStrahSteps.fillField(field, value));
        sberStrahSteps.fillFields(dataTable.asMap(String.class, String.class));
    }

    @When("^Заполняется календарь:\"(.+)\" месяцем \"(.+)\" годом \"(.+)\"$")
    public void fillCalendar(String calendarName, String month, String year) {
        sberStrahSteps.fillCalendarPicker(calendarName, month, year);
    }

    @Then("^Проверяются поля:$")
    public void checkFields(DataTable dataTable) {
//        dataTable.asMap(String.class, String.class)
//                .forEach((field, value)->sberStrahSteps.fillField(field, value));
        sberStrahSteps.checkFillFields(dataTable.asMap(String.class, String.class));
    }
}
