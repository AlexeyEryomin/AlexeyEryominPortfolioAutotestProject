package pages.swag_labs_overview;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

/** Данный класс отвечает за страницу подтверждения покупки */
public class SwagLabsOverviewPage  extends BasePage {
    public SwagLabsOverviewPage(WebDriver driver) {        super(driver);    }

    private final By finishBuybtn = By.id("finish"); // xpath для кнопки завершения покупки

    /** Метод для завершения покупки */
    public SwagLabsOverviewPage finishBuy() {
        driver.findElement(finishBuybtn).click();
        return this;
    }
}
