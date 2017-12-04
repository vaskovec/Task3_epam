package by.bsu.mmf.animal.steps;

import by.bsu.mmf.animal.driver.DriverSingleton;
import by.bsu.mmf.animal.pages.*;
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

    public boolean searchByCity(String req,String city) {

        SearchPage searchPage = new SearchPage(driver);
        searchPage.openPage();

        searchPage.setCity(city);
        searchPage.search(req);

        return searchPage.isAnyResultsFoundByCity(city);
    }

    public boolean paginationCheck() {

        SearchPage searchPage = new SearchPage(driver);
        searchPage.openPage();
        searchPage.clickOnPaginationLink();

        return searchPage.isPaginationWorking();
    }


    public boolean canAccessToAccount() {

        AccountPage accountPage = new AccountPage(driver);
        accountPage.openPage();

        return accountPage.accessToAccount();
    }

    public void changeLastnameAction(String newLastname) {
        AccountPage accountPage = new AccountPage(driver);
        accountPage.openPage();
        accountPage.changeLastname(newLastname);

    }

    public String getLastname() {
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.openPage();

        return profilePage.getLastname();
    }

    public String searchByCategory(Integer categoryId) {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.openPage();

        searchPage.searchByCategory(categoryId);

        return searchPage.getResultCategory() ;
    }

    public boolean changePassword(String oldPassword,String newPassword) {
        AccountPage accountPage = new AccountPage(driver);
        accountPage.openPage();
        accountPage.changePassword(oldPassword, newPassword);

        return accountPage.isPasswordChanged();
    }

    public boolean openNews() {
        CatalogPage catalogPage = new CatalogPage(driver);
        catalogPage.openPage();

        catalogPage.openNews();

        return catalogPage.isNewsDisplayed();
    }
}
