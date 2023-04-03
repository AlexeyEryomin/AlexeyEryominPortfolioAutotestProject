package pages.swag_labs_products;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.base.BasePage;
import java.util.List;


/** Данный класс отвечает за работу страницы после авторизации */
public class SwagLabsProductsPage extends BasePage {

    public SwagLabsProductsPage(WebDriver driver) { super(driver); }


    /** xpath для всех кнопок добавления в корзину */
    private final By addToCartProductButtons = By.xpath("//button[contains(@class, 'btn btn_primary btn_small btn_inventory')]");

    /** xpath для всех названий товаров по которым можно кликнуть*/
    private final By clickNamesButtons = By.xpath("//div[contains(@class, 'inventory_item_name')]");


    /** xpaths для выбора сортировки */
    private final By chooseSort = By.xpath("//select[contains(@class, 'product_sort_container')]"); // открыть выпадающий список
    private final By chooseSortZA = By.xpath("//option[contains(@value, 'za')]"); // выбрать сортировку Z-A
    private final By chooseSortLowToHighPrice = By.xpath("//option[contains(@value, 'lohi')]"); // выбрать сортировку по цене от низкой до высокой
    private final By chooseSortHighToLowPrice = By.xpath("//option[contains(@value, 'hilo')]"); // выбрать сортировку по цене от высокой к низкой

    /** Соберем все кнопки добавления на странице в List */
    public List<WebElement> getAllProductsAddToCartButtons() {
        List<WebElement> products = driver.findElements(addToCartProductButtons);
        return products;
    }

    /** Соберем все кнопки для перехода к карточке товара в List */
    public List<WebElement> getAllProductsClickNamesButtons() {
        List<WebElement> clickNameButtons = driver.findElements(clickNamesButtons);
        return clickNameButtons;
    }

    /** Выберем сортировку от Z-A */
    public SwagLabsProductsPage selectZAsort() {
        driver.findElement(chooseSort).click();
        driver.findElement(chooseSortZA).click();
        return this;
    }

    /** Выберем сортировку по цене от низкой до высокой */
    public SwagLabsProductsPage selectLowToHighPriceSort() {
        driver.findElement(chooseSort).click();
        driver.findElement(chooseSortLowToHighPrice).click();
        return this;
    }

    /** Выберем сортировку по цене от высокой до низкой */
    public SwagLabsProductsPage selectHighToLowPriceSort() {
        driver.findElement(chooseSort).click();
        driver.findElement(chooseSortHighToLowPrice).click();
        return this;
    }
}
