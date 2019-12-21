//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.Wait;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import java.ru.aplana.auto.util.concurrent.TimeUnit;
//
//public class FirstScript {
//
//        WebDriver chromeDriver;
//        String baseURL;
//        Wait<WebDriver> chromeWait;
//
//        @Before
//        public void beforeTest() {
//            //Устанавливаем стартовую страницу, драйвер, открываем браузер, переходим в полный экран, переходим на стартовую страницу
//            baseURL = "https://yandex.ru/";
//            System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");
//            chromeDriver = new ChromeDriver();
//            chromeDriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
//            chromeDriver.manage().window().maximize();
//            chromeDriver.get(baseURL);
//            //Создаем ожидание
//            chromeWait = new WebDriverWait(chromeDriver, 50, 50000);
//        }
//
//        @Test
//        public void testFirstScript(){
//            //Переходим на страницу "Маркет"
//            chromeDriver.findElement(By.xpath("//a[contains(@data-id, 'market') and contains(text(), 'Маркет')]")).click();
//            //Переходим на страницу "Электроника"
//            chromeDriver.findElement(By.xpath("//span[contains(@class, 'n-w-tab__control-caption') and contains(text(), 'Электроника')]")).click();
//            //Переходим в категорию "Телевизоры"
//            chromeDriver.findElement(By.xpath("//a[contains(@class, '_2qvOOvezty _2x2zBaVN-3 _9qbcyI_fyS') and contains(text(), 'Телевизоры')]")).click();
//            //Выбираем цену от 20000 р
//            fillField(By.name("Цена от"), "20000");
//            //Выбираем производителей Samsung, LG
//            chromeDriver.findElement(By.xpath("//input[contains(@type, 'checkbox') and contains(@name, 'Производитель Samsung')]")).click();
//            chromeDriver.findElement(By.xpath("//input[contains(@type, 'checkbox') and contains(@name, 'Производитель LG')]")).click();
//            //Находим и запоминаем наименование первого элемента по фильтру
//            WebElement firstItem = chromeDriver.
//                    findElement(By.xpath("//div[contains(@class, 'n-snippet-list n-snippet-list_type_vertical metrika b-zone b-spy-init b-spy-events i-bem metrika_js_inited snippet-list_js_inited b-spy-init_js_inited b-zone_js_inited')]//div[1]//div[3]//div//a"));
//            String itemName = firstItem.getAttribute("title");
//            System.out.println(itemName);
////            //Заполняем поле поиска наименованием первого элемента из предыдущего шага
////            fillField(By.name(""), itemName);
////            //Кликаем на поиск
////            chromeDriver.findElement(By.xpath("")).click();
////            //Проверяем, что наименования совпадают
////            Assert.assertEquals(itemName, chromeDriver.findElement(By.xpath("")));
//        }
//
//    //Заполнение полей
//    public void fillField(By locator, String value) {
//        chromeDriver.findElement(locator).clear();
//        chromeDriver.findElement(locator).sendKeys(value);
//    }
//
//
//    @After
//    public void afterTest() {
//
//        chromeDriver.quit();
//    }
//}
