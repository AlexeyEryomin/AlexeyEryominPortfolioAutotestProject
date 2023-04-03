package tests.swag_labs_regress;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;

import java.util.ArrayList;
import java.util.List;

import static constants.Constant.Urls.SWAG_LABS_HOME_PAGE;

public class Sort_price_high_to_low extends BaseTest {
    @Test
    public void sort_high_to_low_price() {
        basePage.open(SWAG_LABS_HOME_PAGE); // откроем стандартную страницу
        swagLabsHomePage.standartLogin(); // авторизуемся беспроблемным пользователем
        swagLabsProductsPage.selectHighToLowPriceSort(); // выберем сортировку по цене от высокой до низкой

        List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class, 'inventory_item_price')]")); // получаем список элементов цен страницы
        /** Соберем все цены в список предположительно в порядке возрастания */
        ArrayList<Float> prices_high_to_low = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            float z = Float.parseFloat(products.get(i).getText().replaceAll("\\$", "")); // уберем бакс из строки и превратим строку в число с точкой
            prices_high_to_low.add(z); // добавим наше число в список цен
        }

        /** Проверим цены на порядок их убывания */
        boolean is_high_to_low = true;
        for (int i = 0; i < prices_high_to_low.size(); i++) {
            if (i == prices_high_to_low.size() - 1) break;
            if (prices_high_to_low.get(i) < prices_high_to_low.get(i + 1)) {
                is_high_to_low = false;
            }
        }

        /** Поместим результат проверки на возрастание цены в метод Assert чтобы результат проверки отображался в TestNG */
        Assert.assertTrue(is_high_to_low);
    }
}
