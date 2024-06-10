package PageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By nameInput = By.xpath("//label[contains(text(), 'Имя')]/following-sibling::input");

    private final By emailInput = By.xpath("//label[contains(text(), 'Email')]/following-sibling::input");
    private final By passwordInput = By.xpath("//label[contains(text(), 'Пароль')]/following-sibling::input");

    private final By registrationButton = By.xpath("//button[contains(text(), 'Зарегистрироваться')]");

    private final By pageTitle = By.xpath("//h2[contains(text(), 'Регистрация')]");

    private final By errorPasswordMessage = By.xpath("//p[contains(text(), 'Некорректный пароль')]");

    private final By entryLink = By.xpath("//a[contains(text(), 'Войти')]");


    public RegistrationPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    @Step("Fill Name Input")
    public void fillName(String date) {
        WebElement dateField = driver.findElement(nameInput);
        dateField.click();
        dateField.clear();
        dateField.sendKeys(date, Keys.ENTER);
    }

    @Step("Fill Email Input")
    public void fillEmail(String date) {
        WebElement dateField = driver.findElement(emailInput);
        dateField.click();
        dateField.clear();
        dateField.sendKeys(date, Keys.ENTER);
    }

    @Step("Fill Password Input")
    public void fillPassword(String date) {
        wait.withTimeout(Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        WebElement dateField = driver.findElement(passwordInput);
        dateField.click();
        dateField.clear();
        dateField.sendKeys(date, Keys.ENTER);
    }

    @Step("Click Registration Button")
    public void clickRegistrationButton() {
        WebElement dateField = driver.findElement(registrationButton);
        dateField.click();
    }

    @Step("Fill Registration Page")
    public void fillRegistrationPage(String email, String name, String password) {
        fillName(name);
        fillEmail(email);
        fillPassword(password);
    }

    @Step("Check if Error Password Message is Displayed")
    public boolean isErrorPasswordMessageDisplayed() {
        try {
            wait.withTimeout(Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(errorPasswordMessage));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Click Entry Link")
    public void clickEntryLink() {
        WebElement dateField = driver.findElement(entryLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dateField);
        dateField.click();
    }

    @Step("Check if Registration Page is Loaded")
    public boolean isRegistrationPageLoaded() {
        try {
            wait.withTimeout(Duration.ofSeconds(20)).until(ExpectedConditions.presenceOfElementLocated(pageTitle));
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}