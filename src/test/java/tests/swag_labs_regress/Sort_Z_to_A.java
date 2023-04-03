package tests.swag_labs_regress;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static constants.Constant.Urls.SWAG_LABS_HOME_PAGE;

public class Sort_Z_to_A extends BaseTest {
    @Test
    public void sort_Z_to_A_test() {
        basePage.open(SWAG_LABS_HOME_PAGE); // откроем стандартную страницу
        swagLabsHomePage.standartLogin(); // авторизуемся беспроблемным пользователем

        List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class, 'inventory_item_name')]")); // получаем список элементов страницы
        /** Предварительно получим список названий продуктов предположительно в прямом алфавитном порядке
         *  и сохраним его в Лист */
        ArrayList<String> productnamesA_Z = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            productnamesA_Z.add(products.get(i).getText());
        }

        /** Проверим строки на A-Z */
        boolean is_A_to_Z = true; // переменная в которую позже сохранится результат проверки на прямой алфавитный порядок товаров
        String previous = ""; // empty string: guaranteed to be less than or equal to any other
        for (final String current: productnamesA_Z) {
            if (current.compareTo(previous) < 0)
                is_A_to_Z = false;
            previous = current;
        }


        /** Если порядок от A до Z правильный, то сохраним его в обратном порядке и
         *  используем его для сравнения с обратным списком полученным со страницы SwagLabsProductsPage */
        if (is_A_to_Z) {
            Collections.reverse(productnamesA_Z);
        } else Assert.fail("Порядок от A до Z изначально был неправильным нечего реверсировать");

        /** Выберем сортировку Z-A */
        swagLabsProductsPage.selectZAsort();

        /** Снова получим список элементов товаров с вебстраницы предполагаемо в обратном порядке и сохраним его в ArrayList */
        products = driver.findElements(By.xpath("//div[contains(@class, 'inventory_item_name')]"));
        ArrayList<String> productnamesZ_A = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            productnamesZ_A.add(products.get(i).getText());
        }

        /** Сравним массив изначально с прямым порядком, который затем реверсируется и становится обратным массивом
         *  сравним с массивом названий который получили со страницы товаров предварительно указав условие Z_A
         *  так мы будем знать что выбор условия Z_A показывает товары в обратном алфавитном порядке */
        boolean isZ_A = Arrays.equals(productnamesA_Z.toArray(), productnamesZ_A.toArray());
        Assert.assertTrue(isZ_A);
    }
}
