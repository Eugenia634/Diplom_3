import Helpers.User;
import Helpers.UserCreation;
import Helpers.UserRegistration;
import PageObjects.LoginPage;
import PageObjects.MainPage;
import PageObjects.ProfilePage;
import PageObjects.RegistrationPage;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ProfilePageTests extends BaseTest {
    private static final UserRegistration USER_HELPER = new UserRegistration();

    @Test
    @DisplayName("Entrance to Profile Authorized User")
    public void entranceToProfileAuthorizedUser() {
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
        loginPage.fillLoginPage(email, password);
        mainPage.clickProfileLink();

        // переходим в личный кабинет
        ProfilePage personalCabinetPage = new ProfilePage(driver, wait);
        assertTrue("The personal account page is not loaded", personalCabinetPage.isPersonalPageloaded());

        // удаляем пользователя после теста
        Response loginResponse = USER_HELPER.loginUser(email, password);
        USER_HELPER.deleteUser(loginResponse);
    }

    @Test
    @DisplayName("Entrance to Profile Unauthorized User")
    public void entranceToProfileUnauthorizedUser() {
        // перейти на главную страницу
        MainPage mainPage = new MainPage(driver, wait);
        mainPage.clickProfileLink();

        // перейти на страницу логина и выбрать регистрацию
        LoginPage loginPage = new LoginPage(driver, wait);
        assertTrue("The login page is not loaded", loginPage.isLoginPageLoaded());
    }

    @Test
    @DisplayName("Exit from Profile Test")
    public void exitFromProfileTest() {
        // перейти на главную страницу
        MainPage mainPage = new MainPage(driver, wait);
        mainPage.clickProfileLink();

        // перейти на страницу логина и выбрать регистрацию
        LoginPage loginPage = new LoginPage(driver, wait);

        // создание юзера через API
        UserCreation createUser = new UserCreation(USER_HELPER);
        User user = createUser.createUser();
        USER_HELPER.createUserWithApi(user);

        // залогиниться
        loginPage.fillLoginPage(user.getEmail(), user.getPassword());
        mainPage.clickProfileLink();

        // переходим в личный кабинет
        ProfilePage profilePage = new ProfilePage(driver, wait);
        assertTrue("The personal account page is not loaded", profilePage.isPersonalPageloaded());
        profilePage.clickExitButton();
        // переходим на страницу логина
        assertTrue("The login page is not loaded", loginPage.isLoginPageLoaded());

        // удаляем пользователя после теста
        Response loginResponse = USER_HELPER.loginUser(user.getEmail(), user.getPassword());
        USER_HELPER.deleteUser(loginResponse);
    }

}