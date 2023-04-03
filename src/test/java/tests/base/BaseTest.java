package tests.base;

import common.CommonActions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import pages.base.BasePage;
import pages.swag_labs_cart.SwagLabsCartPage;
import pages.swag_labs_home.SwagLabsHomePage;
import pages.swag_labs_information.SwagLabsInformationPage;
import pages.swag_labs_overview.SwagLabsOverviewPage;
import pages.swag_labs_products.SwagLabsProductsPage;
import static common.Config.CLEAR_COOKIES_AND_STORAGE;
import static common.Config.HOLD_BROWSER_OPEN;


public class BaseTest {

    /** Нужно создать драйвер используя заранее подготовленный класс CommonActions */
    protected WebDriver driver = CommonActions.createDriver();

    /** Нужно создать экземпляр базовой страницы */
    /** В конструкторе нужно передать драйвер, он через цепочку наследования(если страница не родитель)
     * присвоит драйвер переменной driver в Базовой (классе BasePage) странице */
    protected BasePage basePage = new BasePage(driver);

    /** А так же нужно создать экземпляры всех используемых страниц теста для работы с их методами */
    protected SwagLabsHomePage swagLabsHomePage = new SwagLabsHomePage(driver);
    protected SwagLabsCartPage swagLabsCartPage = new SwagLabsCartPage(driver);
    protected SwagLabsProductsPage swagLabsProductsPage = new SwagLabsProductsPage(driver);
    protected SwagLabsInformationPage swagLabsInformationPage = new SwagLabsInformationPage(driver);
    protected SwagLabsOverviewPage swagLabsOverviewPage = new SwagLabsOverviewPage(driver);

    /**
     * Clear browser cookies after each iteration
     * If CLEAR_COOKIES_AND_STORAGE true - clear cookies
     */
    @AfterTest
    public void clearCookiesAndLocalStorage() {
        if (CLEAR_COOKIES_AND_STORAGE) {
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            driver.manage().deleteAllCookies();
            javascriptExecutor.executeScript("window.sessionStorage.clear()");
        }
    }

    /**
     * To keep the browser open after suite
     * if HOLD_BROWSER_OPEN true - browser close
     */
    @AfterTest (alwaysRun = true)
    public void close() {
        if (HOLD_BROWSER_OPEN) {
            driver.quit();
        }
    }
}
