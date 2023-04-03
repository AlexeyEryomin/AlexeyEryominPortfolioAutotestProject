package tests.swag_labs_functional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import tests.base.BaseTest;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



public class Correct_image_on_product_page extends BaseTest {

    /** Для начала нужно определить что мы имеем в виду под корректным изображением
     * в данном случае я буду сравнивать заранее подготовленное изображение с изображением определенного товара
     * и в случае если они совпадают, то картинка является корректной
      */
    @Test
    public void correct_image_on_product_page() throws IOException, InterruptedException {

        /** Откроем страницу отображаемого изображения (в нашем случае это изображение первого товара) */


        basePage.open("https://www.saucedemo.com/static/media/sauce-backpack-1200x1500.0a0b85a3.jpg"); // откроем страницу изображения

        Thread.sleep(1000);

        /** Из файловой системы компьютера получим ожидаемое изображение */
        BufferedImage expectedImage = ImageIO.read(new File("src/main/resources/saucej.png"));

        /** Сохраним веб элемент нашего изображения со страницы товара */
        WebElement productImageElement = driver.findElement(By.xpath("/html/body/img"));

        // если есть ошибка javascript error: $ is not defined я использую coordsprovider
        /** Получаем скриншот нашего изображения */
        Screenshot productImageScreenshot = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver, productImageElement);

        /** Отведите курсор в сторонку при выполнении теста */

        /** Внимание при создании скриншота для сравнения был выявлен один баг(закономерность)
         * если эталон скриншота был создан с курсором поверх изображения(даже если на самом скриншоте его нет),
         * то во время выполнения теста курсор также должен находиться поверх изображения
         * иначе программа будет считать их разными изображениями(даже если это не так)
         * и наоборот если при создании скриншота курсор не был наведен на изображение, то и при выполнении теста он должен
         * быть в сторонке от картинки
         * в будущем планирую переработать подход к сравнению изображений записав два байтовых потока эталона и картинки со страницы
         * и сравнить уже их соответствие по байтам
         * но мне очень некогда и пока, временно, реализация останется такой */
        // Запись скриншота изображения, был использован для создания эталона изображения, в данном случае не используется
        // ImageIO.write(productImageScreenshot.getImage(), "png", new File("src/main/resources/saucej.png"));

        // Конвертируем класс изображения для дальнейшей работы
        BufferedImage actualImage = productImageScreenshot.getImage();

        ImageDiffer imgDiff = new ImageDiffer();

        ImageDiff diff = imgDiff.makeDiff(expectedImage, actualImage);

        if(diff.hasDiff() == true) {
            Assert.fail("Некорректное изображение");
        } else {
            Assert.assertTrue(true);
        }

    }
}
