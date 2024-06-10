import Helpers.UserRegistration;
import PageObjects.LoginPage;
import PageObjects.MainPage;
import PageObjects.ProfilePage;
import PageObjects.RegistrationPage;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegistrationTests extends BaseTest {

    private static final UserRegistration USER_HELPER = new UserRegistration();

    @Test
    @DisplayName("Successful Registration Test")
    public void successfulRegistrationTest() {
        // перейти на главную страницу
        MainPage mainPage = new MainPage(driver, wait);
        mainPage.clickProfileLink();

        // перейти на страницу логина и выбрать регистрацию
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.clickRegistrationLink();

        // открыть и заполнить страницу регистрации
        RegistrationPage registrationPage = new RegistrationPage(driver, wait);
        String email = USER_HELPER.generateUniqueEmail();
        String name = USER_HELPER.generateName();
        String password = USER_HELPER.generatePassword();
        registrationPage.fillRegistrationPage(email, name, password);

        // залогиниться
        assertTrue("Login page is not displayed", loginPage.isLoginPageLoaded());
        loginPage.fillLoginPage(email, password);

        // переходим в личный кабинет
        mainPage.clickProfileLink();
        ProfilePage personalProfile = new ProfilePage(driver, wait);

        // получаем значения имени и email из личного кабинета
        String nameFromProfile = personalProfile.getNameValue();
        String emailFromProfile = personalProfile.getLoginValue();

        // проверяем, что значения совпадают с ожидаемыми
        assertEquals("The text of the name element does not match the expected text", name, nameFromProfile);
        assertEquals("The text of the email element does not match the expected text", email, emailFromProfile);

        // удаляем пользователя после теста
        Response loginResponse = USER_HELPER.loginUser(email, password);
        USER_HELPER.deleteUser(loginResponse);
    }

    @Test
    @DisplayName("Error message for Invalid Password Test")
    public void errorForInvalidPasswordTest() {
        // перейти на главную страницу
        MainPage mainPage = new MainPage(driver, wait);
        mainPage.clickProfileLink();

        // перейти на страницу логина и выбрать регистрацию
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.clickRegistrationLink();

        // открыть и заполнить страницу регистрации
        RegistrationPage registrationPage = new RegistrationPage(driver, wait);
        String email = USER_HELPER.generateUniqueEmail();
        String name = USER_HELPER.generateName();
        String password = USER_HELPER.generatePassword().replaceAll("password", "");
        registrationPage.fillRegistrationPage(email, name, password);
        registrationPage.clickRegistrationButton();
        assertTrue("Error password message is not displayed", registrationPage.isErrorPasswordMessageDisplayed());
    }

}