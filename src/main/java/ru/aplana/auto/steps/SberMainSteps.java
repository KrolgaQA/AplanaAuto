package ru.aplana.auto.steps;

import ru.aplana.auto.pages.SberMainPage;
import ru.yandex.qatools.allure.annotations.Step;

public class SberMainSteps {
    @Step("Закрываем куки")
    public void closeCookie() throws InterruptedException {
        new SberMainPage().closeCookie();
    }
    @Step("Кликаем на пункт меню Страхование")
    public void selectMainMenu(){
        new SberMainPage().selectMainMenu();
    }
    @Step("Кликаем пункт меню Страхование путешественников")
    public void selectSubMenu(){
        new SberMainPage().selectSubMenu();
    }
}
