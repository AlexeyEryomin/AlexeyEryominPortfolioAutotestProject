package tests.swag_labs_functional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;
import java.util.ArrayList;
import java.util.List;
import static constants.Constant.Urls.SWAG_LABS_HOME_PAGE;

public class Del_product_from_cart_on_personal_product_page extends BaseTest {
    @Test
    public void del_product_from_cart_on_personal_product_page() {
        basePage.open(SWAG_LABS_HOME_PAGE); // откроем стандартную страницу
        swagLabsHomePage.standartLogin(); // авторизуемся беспроблемным пользователем
        swagLabsProductsPage.getAllProductsClickNamesButtons().get(0).click(); // кликнем по какому-нибудь товару например по первому и перейдем на его страничку
        String currentProduct = driver.findElement(By.xpath("//div[contains(@class, 'inventory_details_name')]")).getText(); // получим название продукта который мы добавили в корзину
        driver.findElement(By.id(String.format("add-to-cart-%s", currentProduct.toLowerCase().replaceAll(" ", "-")))).click(); // добавим товар в корзину

        swagLabsCartPage.openCartPage(); // откроем страницу корзины
        List<WebElement> productsOnCartPage = driver.findElements(By.xpath("//div[contains(@class, 'inventory_item_name')]")); // получаем список элементов на странице корзины
        ArrayList<String> productNamesInCart = new ArrayList<>(); // добавим названия товаров со страницы корзины в лист
        for (int i = 0; i < productsOnCartPage.size(); i++) {
            productNamesInCart.add(productsOnCartPage.get(i).getText());
        }

        /** Проверим наличие товара добавленного на персональной странице товара на странице SwagLabsCartPage */
        boolean productIsAdd = false; // здесь будем хранить результат поиска товара в корзине
        if (productNamesInCart.contains(currentProduct)) {
            productIsAdd = true;
        }

        /** Далее если наш товар действительно оказался в корзине возвращаемся на страничку нашего товара */
        if (productIsAdd) {
            driver.findElement(By.xpath("//div[contains(@class, 'inventory_item_name')]")).click();
        } else Assert.fail("Товар не добавился в корзину нечего будет удалять на страничке нашего товара");

        /** Найдем товар по названию и кликнем удалить */
        By productForDelete = By.id(String.format("remove-%s", currentProduct.toLowerCase().replaceAll(" ", "-")));
        driver.findElement(productForDelete).click();

        /** Вернемся на страницу корзины и проверим что нашего товара нет в списке */
        swagLabsCartPage.openCartPage(); // откроем страницу корзины
        productsOnCartPage.clear(); // очистим список
        productsOnCartPage = driver.findElements(By.xpath("//div[contains(@class, 'inventory_item_name')]")); // получаем список элементов на странице корзины
        productNamesInCart.clear(); // очистим список и добавим названия товаров со страницы корзины в лист
        for (int i = 0; i < productsOnCartPage.size(); i++) {
            productNamesInCart.add(productsOnCartPage.get(i).getText());
        }

        /** Проверим отсутствие товара добавленного на персональной странице товара на странице SwagLabsCartPage */
        boolean productIsNotAdd = false; // здесь будем хранить результат поиска товара в корзине
        if (!productNamesInCart.contains(currentProduct)) {
            productIsNotAdd = true;
        }
        Assert.assertTrue(productIsNotAdd);
    }
}
