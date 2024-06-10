package PageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By emailInput = By.xpath("//label[contains(text(), 'Email')]/following-sibling::input");
    private final By passwordInput = By.xpath("//label[contains(text(), 'Пароль')]/following-sibling::input");
    private final By registrationLink = By.xpath("//a[contains(text(), 'Зарегистрироваться')]");
    private final By pageTitle = By.xpath("//h2[contains(text(), 'Вход')]");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    @Step("Click Registration Link")
    public void clickRegistrationLink() {
        wait.until(ExpectedConditions.presenceOfElementLocated(registrationLink));
        driver.findElement(registrationLink).click();
    }

    @Step("Fill Email Input")
    public void fillEmailInput(String date) {
        wait.withTimeout(Duration.ofSeconds(20)).until(ExpectedConditions.presenceOfElementLocated(emailInput));
        wait.until(ExpectedConditions.elementToBeClickable(emailInput));
        driver.findElement(emailInput).click();
        driver.findElement(emailInput).sendKeys(date, Keys.ENTER);
    }

    @Step("Fill Password Input")
    public void fillPasswordInput(String date) {
        wait.until(ExpectedConditions.presenceOfElementLocated(passwordInput));
        WebElement dateField = driver.findElement(passwordInput);
        dateField.click();
        dateField.sendKeys(date, Keys.ENTER);
    }

    @Step("Fill Login Page")
    public void fillLoginPage(String email, String password) {
        fillEmailInput(email);
        fillPasswordInput(password);
    }

    @Step("Check if Login Page is Loaded")
    public boolean isLoginPageLoaded() {
        try {
            wait.withTimeout(Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Click Restore Password Link")
    public void clickRestorePasswordLink() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Восстановить пароль')]")));
        driver.findElement(By.xpath("//a[contains(text(), 'Восстановить пароль')]")).click();
    }
}