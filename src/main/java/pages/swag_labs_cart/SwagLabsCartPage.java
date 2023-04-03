package pages.swag_labs_cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;


/** Данный класс отвечает за работу страницы корзины */
public class SwagLabsCartPage extends BasePage {
    public SwagLabsCartPage(WebDriver driver) { super(driver); }

    private final By cartPageButton = By.id("shopping_cart_container"); // путь к кнопке корзина

    private final By checkout = By.id("checkout"); // путь к кнопке проверка информации перед покупкой



    public SwagLabsCartPage openCartPage() {
        driver.findElement(cartPageButton).click();
        return this;
    }

    public SwagLabsCartPage checkoutBeforeBuy() {
        driver.findElement(checkout).click();
        return this;
    }


}
