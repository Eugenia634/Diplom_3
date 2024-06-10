import PageObjects.LoginPage;
import PageObjects.MainPage;
import PageObjects.RegistrationPage;
import PageObjects.RestorePassword;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class EntranceTests extends BaseTest {

    @Test
    @DisplayName("Entrance Test with Button on Main Page")
    public void entranceTestWithButton() {
        // перейти на главную страницу
        MainPage mainPage = new MainPage(driver, wait);
        mainPage.clickEntryButton();
        // перейти на страницу логина
        LoginPage loginPage = new LoginPage(driver, wait);
        assertTrue("The login page is not loaded", loginPage.isLoginPageLoaded());
    }

    @Test
    @DisplayName("Entrance Test with Link on Main Page")
    public void entranceTestWithLink() {
        // перейти на главную страницу
        MainPage mainPage = new MainPage(driver, wait);
        mainPage.clickProfileLink();
        // перейти на страницу логина
        LoginPage loginPage = new LoginPage(driver, wait);
        assertTrue("The login page is not loaded", loginPage.isLoginPageLoaded());
    }

    @Test
    @DisplayName("Entrance with the Registration Form Button")
    public void entranceWithRegistrationFormButton() {
        // перейти на главную страницу
        MainPage mainPage = new MainPage(driver, wait);
        mainPage.clickEntryButton();
        // перейти на страницу логина
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.clickRegistrationLink();
        // перейти на страницу регистрации
        RegistrationPage registrationPage = new RegistrationPage(driver, wait);
        registrationPage.clickEntryLink();
        // перейти на страницу логина
        assertTrue("The login page is not loaded", loginPage.isLoginPageLoaded());
    }

    @Test
    @DisplayName("Etrance Through Restore Password Page")
    public void entranceThroughRestorePasswordPage() {
        // перейти на главную страницу
        MainPage mainPage = new MainPage(driver, wait);
        mainPage.clickEntryButton();
        // перейти на страницу логина
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.clickRestorePasswordLink();
        // перейти на страницу восстановления пароля
        RestorePassword restorePasswordPage = new RestorePassword(driver, wait);
        restorePasswordPage.clickEntryLink();
        assertTrue("The login page is not loaded", loginPage.isLoginPageLoaded());
    }


}