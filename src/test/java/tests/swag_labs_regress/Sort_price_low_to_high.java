package tests.swag_labs_regress;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;
import java.util.ArrayList;
import java.util.List;
import static constants.Constant.Urls.SWAG_LABS_HOME_PAGE;

public class Sort_price_low_to_high extends BaseTest {
        @Test
        public void sort_low_to_high_price() {
            basePage.open(SWAG_LABS_HOME_PAGE); // откроем стандартную страницу
            swagLabsHomePage.standartLogin(); // авторизуемся беспроблемным пользователем
            swagLabsProductsPage.selectLowToHighPriceSort(); // выберем сортировку по цене от низкой до высокой

            List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class, 'inventory_item_price')]")); // получаем список элементов цен страницы
            /** Соберем все цены в список предположительно в порядке возрастания */
            ArrayList<Float> prices_low_to_high = new ArrayList<>();
            for (int i = 0; i < products.size(); i++) {
                float z = Float.parseFloat(products.get(i).getText().replaceAll("\\$", "")); // уберем бакс из строки и превратим строку в число с точкой
                prices_low_to_high.add(z); // добавим наше число в список цен
            }

            /** Проверим цены на порядок их возрастания */
            boolean is_low_to_high = true;
            for (int i = 0; i < prices_low_to_high.size(); i++) {
                if (i == prices_low_to_high.size() - 1) break;
                if (prices_low_to_high.get(i) > prices_low_to_high.get(i + 1)) {
                    is_low_to_high = false;
                }
            }

            /** Поместим результат проверки на возрастание цены в метод Assert чтобы результат проверки отображался в TestNG */
            Assert.assertTrue(is_low_to_high);
        }
}
