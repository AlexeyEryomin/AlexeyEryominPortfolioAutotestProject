package tests.swag_labs_regress;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;
import java.util.ArrayList;
import java.util.List;
import static constants.Constant.Urls.SWAG_LABS_HOME_PAGE;

public class Sort_A_to_Z extends BaseTest {

    @Test
    public void sort_A_to_Z_test() {
        basePage.open(SWAG_LABS_HOME_PAGE); // откроем стандартную страницу
        swagLabsHomePage.standartLogin(); // авторизуемся беспроблемным пользователем
        List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class, 'inventory_item_name')]")); // получаем список элементов названий страницы


        /** Получим список названий продуктов и сохраним его в Лист */
        ArrayList<String> productnames = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            productnames.add(products.get(i).getText());
        }


        /** Проверим строки на A-Z */
        boolean is_A_to_Z = true; // переменная в которую позже сохранится результат проверки на прямой алфавитный порядок товаров
        String previous = ""; // empty string: guaranteed to be less than or equal to any other
        for (final String current: productnames) {
            if (current.compareTo(previous) < 0)
                is_A_to_Z = false;
            previous = current;
        }

        /** Поместим результат проверки на A-Z в метод Assert чтобы результат проверки отображался в TestNG */
        Assert.assertTrue(is_A_to_Z);
    }
}
