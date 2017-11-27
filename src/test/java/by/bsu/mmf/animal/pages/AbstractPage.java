package by.bsu.mmf.animal.pages;

import org.openqa.selenium.WebDriver;

/**
 * Created by Anya) on 23.11.17.
 */
public abstract class AbstractPage {

    protected WebDriver driver;

    public abstract void openPage();

    public AbstractPage(WebDriver driver)
    {
        this.driver = driver;
    }

}
