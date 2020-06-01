import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TestTest extends ResourcesAndMethods {

    @BeforeClass
    public static void startUp() {
        String browser = System.getProperty("browser", "chrome");
        if ("chrome".equals(browser)) {
            System.setProperty("webdriver.chrome.driver", properties.getProperty("pathChromeDriver"));
            driver = new ChromeDriver();
        } else if ("firefox".equals(browser)) {
            System.setProperty("webdriver.gecko.driver", properties.getProperty("pathFirefoxDriver"));
            driver = new FirefoxDriver();
        }

        baseUrl = properties.getProperty("DNSURL");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 20);
    }

    @Test
    public void simpleTest() throws InterruptedException {
        driver.get(baseUrl);
        MainPage mainPage = new MainPage(driver);
        waitAndClickByElement(mainPage.inputSearch);
        mainPage.inputSearch.sendKeys("playstation");

        mainPage.inputSearch.sendKeys(Keys.ENTER);

        waitAndClickByElement(mainPage.ps4);
        Product playstation = new Product("playstation");
        ProductPage productPs4 = new ProductPage(driver, "playstation");
        productPs4.savePrice(playstation);
        productPs4.buy();

        waitAndClickByElement(mainPage.inputSearch);
        mainPage.inputSearch.sendKeys("Detroit");

        mainPage.inputSearch.sendKeys(Keys.ENTER);
        Product detroit = new Product("Detroit");
        ProductPage productDetroit = new ProductPage(driver, "Detroit");
        productDetroit.savePrice(detroit);
        productDetroit.buy();
        Thread.sleep(5000);
        waitAndClickByElement(productDetroit.getCartLink());

        System.out.println("Цена на playstation : " + playstation.getPrice());
        System.out.println("Цена на detroit : " + detroit.getPrice());
        System.out.println("Общая цена : " + ProductTotalPrice.getTotalPrice());

        BasketPage basket = new BasketPage(driver);
        Thread.sleep(5000);
        basket.clickButton24();
        Thread.sleep(10000);
    }


    @AfterClass
    public static void quit() {
        driver.quit();
    }
}


/**
 //1. открыть dns-shop
 //2. в поиске найти playstation
 //3. кликнуть по playstation 4 slim black
//4 запомнить цену
 //5. Нажать Купить
 //6. выполнить поиск Detroit
 //7. запомнить цену
 //8. нажать купить
 //9. - пункт задания убрали
 // 10. перейри в корзину
 //11. проверить цену каждого из товаров и сумму
 12. В корзине для playstation Доп.гарантия - выбрать 24 месяца
 13. дождаться изменения цены и запомнить цену с гарантией
 14. удалить из корзины Detroit
 15. проверить что Detroit нет больше в корзине и что сумма уменьшилась на цену Detroit
 16. добавить еще 2 playstation (кнопкой +) и проверить что сумма верна (равна 3*(цена playstation+гарантия))
 17. удалить (кнопка "удалить") Playstation из корзины
 18. нажать вернуть удаленный товаров
 19. проверить что 3 playstation снова в корзине и выбрана гарантия 24 месяца
 */