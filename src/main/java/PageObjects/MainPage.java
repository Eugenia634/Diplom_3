package PageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By personalCabinetLink = By.xpath("//p[contains(text(), 'Личный Кабинет')]/..");
    private final By pageTitle = By.xpath("//h1[contains(text(), 'Соберите бургер')]");

    private final By bunsSection = By.xpath("//span[contains(text(), 'Булки')]/..");

    private final By souseSection = By.xpath("//span[contains(text(), 'Соусы')]/..");
    private final By fillingSection = By.xpath("//span[contains(text(), 'Начинки')]/..");

    private final By entryButton = By.xpath("//button[contains(text(), 'Войти в аккаунт')]");

    public MainPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    @Step("Click Personal Cabinet link")
    public void clickProfileLink() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(personalCabinetLink));
        driver.findElement(personalCabinetLink).click();
    }

    @Step("Check if Main Page loaded")
    public boolean isMainPageLoaded() {
        try {
            wait.withTimeout(Duration.ofSeconds(20)).until(ExpectedConditions.presenceOfElementLocated(pageTitle));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Click Entry Button")
    public void clickEntryButton() {
        wait.until(ExpectedConditions.presenceOfElementLocated(entryButton));
        driver.findElement(entryButton).click();
    }

    @Step("Click Buns Section")
    public void clickBunsSection() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(bunsSection));
    }

    @Step("Click Souse Section")
    public void clickSouseSection() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(souseSection));
    }

    @Step("Click Fillings Section")
    public void clickFillingsSection() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(fillingSection));
    }

    @Step("Is Buns Selected")
    public boolean isBunsSelected() {
        WebElement element = driver.findElement(bunsSection);
        String classAttributeValue = element.getAttribute("class");
        return classAttributeValue.contains("tab_tab_type_current");
    }

    @Step("Is Souse Selected")
    public boolean isSouseSelected() {
        WebElement element = driver.findElement(souseSection);
        String classAttributeValue = element.getAttribute("class");
        return classAttributeValue.contains("tab_tab_type_current");
    }

    @Step("Is Fillings Selected")
    public boolean isFillingsSelected() {
        WebElement element = driver.findElement(fillingSection);
        String classAttributeValue = element.getAttribute("class");
        return classAttributeValue.contains("tab_tab_type_current");
    }
}