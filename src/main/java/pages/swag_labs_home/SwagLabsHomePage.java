package pages.swag_labs_home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;


/** Данный класс отвечает за работу страницы авторизации */
public class SwagLabsHomePage extends BasePage {

    public SwagLabsHomePage(WebDriver driver) { super(driver); }

    private final By username = By.id("user-name");
    private final By password = By.id("password");
    public static final String userpassword = "secret_sauce";
    private final By login = By.id("login-button");

    public SwagLabsHomePage standartLogin() {
        driver.findElement(username).sendKeys("standard_user");
        driver.findElement(password).sendKeys(userpassword);
        driver.findElement(login).click();
        return this;
    }

    public SwagLabsHomePage lockedUserLogin() {
        driver.findElement(username).sendKeys("locked_out_user");
        driver.findElement(password).sendKeys(userpassword);
        driver.findElement(login).click();
        return this;
    }

    public SwagLabsHomePage problemUserLogin() {
        driver.findElement(username).sendKeys("problem_user");
        driver.findElement(password).sendKeys(userpassword);
        driver.findElement(login).click();
        return this;
    }

    public SwagLabsHomePage performanceGlitchUserLogin() {
        driver.findElement(username).sendKeys("problem_user");
        driver.findElement(password).sendKeys(userpassword);
        driver.findElement(login).click();
        return this;
    }



}
