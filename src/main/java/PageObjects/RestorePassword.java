package PageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RestorePassword {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By pageTitle =  By.xpath("//h2[contains(text(), 'Восстановление пароля')]");

    private final By entryLink = By.xpath("//a[contains(text(), 'Войти')]");

    public RestorePassword(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    @Step("Click Entry Link")
    public void clickEntryLink() {
        wait.until(ExpectedConditions.presenceOfElementLocated(entryLink));
        driver.findElement(entryLink).click();
    }

    @Step("Check if Restore Password Page is Loaded")
    public boolean isRestorePasswordPageLoaded() {
        try {
            wait.withTimeout(Duration.ofSeconds(20)).until(ExpectedConditions.presenceOfElementLocated(pageTitle));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}