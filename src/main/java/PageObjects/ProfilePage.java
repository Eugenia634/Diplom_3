package PageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By nameInput = By.xpath("//label[contains(text(), 'Имя')]/following-sibling::input");
    private final By emailInput = By.xpath("//label[contains(text(), 'Логин')]/following-sibling::input");
    private final By pageTitle = By.xpath("//a[contains(text(), 'Профиль')]");

    private final By exitButton = By.xpath("//button[contains(text(), 'Выход')]");

    public ProfilePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    @Step("Get Name Value")
    public String getNameValue() {
        wait.until(ExpectedConditions.presenceOfElementLocated(nameInput));
        WebElement nameElement = driver.findElement(nameInput);
        return nameElement.getAttribute("value");
    }

    @Step("Get Login Value")
    public String getLoginValue() {
        wait.until(ExpectedConditions.presenceOfElementLocated(emailInput));
        WebElement emailElement = driver.findElement(emailInput);
        return emailElement.getAttribute("value");
    }

    @Step("Check if Personal Page is Loaded")
    public boolean isPersonalPageloaded() {
        try {
            wait.withTimeout(Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    @Step("Exit button click")
    public void clickExitButton() {
        wait.until(ExpectedConditions.presenceOfElementLocated(exitButton));
        driver.findElement(exitButton).click();
    }
}