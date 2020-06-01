import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
public class BasketPage extends ResourcesAndMethods{

        @FindBy(xpath = "//div[@class='total-amount__label']//div[@class='price__block price__block_main']//span[@class='price__current']")
        private WebElement totalPrice;
        @FindBy(xpath = "//div[contains(text(),'1626724')]/parent::div/parent::div/parent::div//span[@class='price__current']")
        private WebElement product1;
        @FindBy(xpath = "//div[contains(text(),'1225442')]/parent::div/parent::div/parent::div//span[@class='price__current']")
        private WebElement product2;
        @FindBy(xpath = "//div[@class='base-ui-radio-button additional-warranties-row__radio']//span[contains(text(),'+ 24  мес.')]")
        private WebElement guaranteeButton24;

    public BasketPage (WebDriver driver) {
        this.driver = driver;
    }


        public Double getPrice(){
            Double priceOfProducts = Double.parseDouble(product1.getText().replaceAll(" ", ""));
            return priceOfProducts;
        }

        public void clickButton24(){
            guaranteeButton24.click();
        }
}
