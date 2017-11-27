package by.bsu.mmf.animal.steps;

import by.bsu.mmf.animal.driver.DriverSingleton;
import by.bsu.mmf.animal.pages.CatalogPage;
import by.bsu.mmf.animal.pages.LoginPage;
import by.bsu.mmf.animal.pages.ProfilePage;
import by.bsu.mmf.animal.pages.SearchPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * Created by Anya) on 23.11.17.
 */
public class Steps {

    private WebDriver driver;

    private final Logger logger = LogManager.getRootLogger();

    public void initBrowser()
    {
        driver = DriverSingleton.getDriver();
    }

    public void closeDriver() {
        driver.quit();
    }

    public void login(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(username, password);
    }

    public boolean isLoggedIn() {
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.openPage();
        return profilePage.profileAccessAbility();
    }

    public boolean searchInCatalog(String request) {

        CatalogPage catalogPage = new CatalogPage(driver);
        catalogPage.openPage();
        catalogPage.search(request);

        SearchPage searchPage = new SearchPage(driver);
        searchPage.openPage();

        searchPage.search(request);
        return searchPage.isAnyResultsFound();
    }

}
