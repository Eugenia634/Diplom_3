package Helpers;

import io.qameta.allure.Step;

public class UserCreation {
    private final UserRegistration userHelper;

    public UserCreation(UserRegistration userHelper) {
        this.userHelper = userHelper;
    }

    @Step("User Generation")
    public User createUser() {
        String email = userHelper.generateUniqueEmail();
        String password = userHelper.generatePassword();
        String name = userHelper.generateName();
        return new User(email, password, name);
    }
}
