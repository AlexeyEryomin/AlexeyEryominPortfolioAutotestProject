package tests.swag_labs_functional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;
import java.util.ArrayList;
import java.util.List;
import static constants.Constant.Urls.SWAG_LABS_HOME_PAGE;

public class Del_product_from_cart_on_product_page extends BaseTest {

        @Test
        public void del_product_from_cart_on_product_page() {
            basePage.open(SWAG_LABS_HOME_PAGE); // откроем стандартную страницу
            swagLabsHomePage.standartLogin(); // авторизуемся беспроблемным пользователем

            /** Используя метод getAllProductsAddToCartButtons() класса(страницы) swagLabsProductsPage
             * соберем все кнопки добавления товара в корзину в List
             * и кликнем на любую из них например на первую(отсчет в методе get ведется с 0) */
            swagLabsProductsPage.getAllProductsAddToCartButtons().get(0).click();

            /** Затем получим название первого товара на странице SwagLabsProductsPage */
            List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class, 'inventory_item_name')]")); // получаем список элементов названий страницы
            String firstproductname = products.get(0).getText(); // название товара предположительно находящегося в корзине

            /** Перед удалением убедимся что товар действительно присутствует в корзине */
            swagLabsCartPage.openCartPage(); // откроем страницу корзины
            List<WebElement> productsOnCartPage = driver.findElements(By.xpath("//div[contains(@class, 'inventory_item_name')]")); // получаем список элементов на странице корзины
            ArrayList<String> productNamesInCart = new ArrayList<>(); // добавим названия товаров со страницы корзины в лист
            for (int i = 0; i < productsOnCartPage.size(); i++) {
                productNamesInCart.add(productsOnCartPage.get(i).getText());
            }
            /** Проверим наличие товара добавленного на странице SwagLabsProductsPage на странице SwagLabsCartPage */
            boolean productIsAdd = false; // здесь будем хранить результат поиска товара в корзине
            if (productNamesInCart.contains(firstproductname)) {
                productIsAdd = true;
            }

            /** Далее если наш товар действительно оказался в корзине возвращаемся на SwagLabsProductPage */
            if (productIsAdd) {
                driver.findElement(By.id("continue-shopping")).click();
            } else Assert.fail("Товар не добавился в корзину нечего будет удалять страничке с товарами");

            /** Найдем товар по названию и кликнем удалить */
            By productForDelete = By.id(String.format("remove-%s", firstproductname.toLowerCase().replaceAll(" ", "-")));
            driver.findElement(productForDelete).click();

            /** Вернемся на страницу корзины и проверим что нашего товара нет в списке */
            swagLabsCartPage.openCartPage(); // откроем страницу корзины
            productsOnCartPage.clear(); // очистим список
            productsOnCartPage = driver.findElements(By.xpath("//div[contains(@class, 'inventory_item_name')]")); // получаем список элементов на странице корзины
            productNamesInCart.clear(); // очистим список и добавим названия товаров со страницы корзины в лист
            for (int i = 0; i < productsOnCartPage.size(); i++) {
                productNamesInCart.add(productsOnCartPage.get(i).getText());
            }

            /** Проверим отсутствие товара добавленного на странице SwagLabsProductsPage на странице SwagLabsCartPage */
            boolean productIsNotAdd = false; // здесь будем хранить результат поиска товара в корзине
            if (!productNamesInCart.contains(firstproductname)) {
                productIsNotAdd = true;
            }
            Assert.assertTrue(productIsNotAdd);
        }
}
