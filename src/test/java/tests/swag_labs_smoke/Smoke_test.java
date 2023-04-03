package tests.swag_labs_smoke;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;
import static constants.Constant.Urls.SWAG_LABS_HOME_PAGE;

public class Smoke_test extends BaseTest {
    @Test
    public void smokeTest() {
        basePage.open(SWAG_LABS_HOME_PAGE); // откроем стандартную страницу
        swagLabsHomePage.standartLogin(); // авторизуемся беспроблемным пользователем

        /** Используя метод getAllProductsAddToCartButtons() класса(страницы) swagLabsProductsPage
         * соберем все кнопки добавления товара в корзину в List
         * и кликнем на любую из них например на вторую(отсчет в методе get ведется с 0) */
        swagLabsProductsPage.getAllProductsAddToCartButtons().get(1).click();

        /** Кликнем по элементу корзина используя класс(страницу) SwagLabsCartPage */
        swagLabsCartPage.openCartPage();

        /** Кликнем по кнопке checkout используя класс(страницу) SwagLabsCartPage */
        swagLabsCartPage.checkoutBeforeBuy();

        /** Заполним поля валидными данными на странице заполнения доставки используя класс(страницу)
         * SwagLabsInformationPage и нажмем кнопку продолжить */
        swagLabsInformationPage.sendInformationAndContinue();

        /** Подтверждаем введенную информацию на странице SwagLabsOverviewPage
         * и получаем сообщение об успешном совершении заказа */
        swagLabsOverviewPage.finishBuy();

        /** Будем считать критерием правильно выполненного теста сообщение об успешной покупке поэтому
         * ожидаемый результат будет сообщение "Thank you for your order!" */
        final String expected = "Thank you for your order!";
        Assert.assertEquals(driver.findElement(By.xpath("//h2[contains(@class, 'complete-header')]")).getText(), expected);

    }
}
