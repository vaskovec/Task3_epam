package by.bsu.mmf.animal.util;

import org.openqa.selenium.By;

/**
 * Created by Anya) on 14.12.17.
 */
public class ExternalSelectorFactory {

    public static By getSelectorByName(String selectorName) {

        switch (selectorName) {
            case "CURRENT_PASSWORD":
                return By.cssSelector("#current_user_password");
            case "NEW_PASSWORD":
                return By.cssSelector("#user_password");
            case "CONFIRM_PASSWORD":
                return By.cssSelector("#confirm_user_password");
            case "UPDATE_PASSWORD":
                return By.cssSelector("div.um-col-alt:nth-child(5) > div:nth-child(1) > input:nth-child(1)");
            case "PARENT_RESULTS":
                return By.cssSelector("#sabai-entity-content-162");
        }

        return null;
    }
}
