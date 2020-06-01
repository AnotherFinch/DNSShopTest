import org.hamcrest.core.Is;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ResourcesAndMethods {


    protected static Properties properties = SingleProperty.getInstance().getProperties();
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static String baseUrl;
    protected String productName;


    public static Properties getProperties() {
        return properties;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static void setDriver(WebDriver driver) {
        ResourcesAndMethods.driver = driver;
    }

    public static WebDriverWait getWait() {
        return wait;
    }

    public static WebDriver getDriver() {
        return driver;
    }


    void waitAndClick(String XPath) {
        Actions actions = new Actions(getDriver());
        WebElement element = getDriver().findElement(By.xpath(XPath));
        getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPath)));
        getWait().until(ExpectedConditions.elementToBeClickable(element));
        actions.moveToElement(element).build().perform();
        JavascriptExecutor executor;
        executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    void waitAndClickByElement(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    void findOnText(String XPath, String badText, String goodText) {
        String findText = getDriver().findElement(By.xpath(XPath)).getText();
        Assert.assertThat(badText, findText, Is.is(goodText));
    }

    void findIs(String XPath, String badText, boolean yesOrNo) {
        WebElement elementAlert = getDriver().findElement(By.xpath(XPath));
        getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPath)));
        Assert.assertEquals(badText, yesOrNo, elementAlert.isDisplayed());
    }


    void sendKey(String XPath, String text) {
        WebElement element = getDriver().findElement(By.xpath(XPath));
        element.sendKeys(text);
    }

    void assertElement(String badText, String goodText, String XPath) {
        WebElement element = getDriver().findElement(By.xpath(XPath));
        Assert.assertEquals(badText, goodText, element.getAttribute("value"));
    }
    void  waitElementsToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
/////////////


    public static class ProductTotalPrice {
        private static Map<String,Double> totalPrices= new HashMap<>();;
        private static Map<String,Integer> totalCounts= new HashMap<>();
        private  Double totalPriceInCart = 0.0;

        public  Double getTotalPriceInCart() {
            return totalPriceInCart;
        }





        public static void addCountAndPrice(String name, Double price){
            totalCounts.put(name,totalCounts.getOrDefault(name,0) + 1);
            totalPrices.put(name,totalPrices.getOrDefault(name,0.0) + price);
        }

        public static Map<String, Double> getTotalPrices() {
            return totalPrices;
        }

        public static Map<String, Integer> getTotalCounts() {
            return totalCounts;
        }

        public static double getTotalPrice(){
            double sum = 0.0;
            for (double d:totalPrices.values()) {
                sum += d;
            }
            return sum;
        }
    }
}



