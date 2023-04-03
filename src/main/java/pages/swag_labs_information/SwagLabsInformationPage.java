package pages.swag_labs_information;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

/** Данный класс отвечает за страницу заполнения информации перед покупкой */
public class SwagLabsInformationPage extends BasePage {
    public SwagLabsInformationPage(WebDriver driver) {        super(driver);    }

    /** Пути к полям для заполнения информации о доставке */
    public final By firstname = By.id("first-name");
    public final By lastname = By.id("last-name");
    public final By postalcode = By.id("postal-code");

    /** Путь к кнопке продолжить после заполнения */
    public final By continuebtn = By.id("continue");

    /** Передадим в поля любую валидную информацию */
    public SwagLabsInformationPage sendInformationAndContinue() {
        driver.findElement(firstname).sendKeys("Peter");
        driver.findElement(lastname).sendKeys("Parker");
        driver.findElement(postalcode).sendKeys("654789");
        driver.findElement(continuebtn).click();
        return this;
    }
}
