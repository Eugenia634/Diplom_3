import PageObjects.MainPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MainPageTests extends BaseTest {

    @Test
    @DisplayName("Skip To The Buns Section Test")
    public void skipToTheBunsSectionTest() {
        MainPage mainPage = new MainPage(driver, wait);

        mainPage.clickBunsSection();
        assertTrue("Element is not selected", mainPage.isBunsSelected());
    }

    @Test
    @DisplayName("Skip To The Souce Section Test")
    public void skipToTheSouceSectionTest() {
        MainPage mainPage = new MainPage(driver, wait);
        mainPage.clickSouseSection();
        assertTrue("Element is not selected", mainPage.isSouseSelected());
    }

    @Test
    @DisplayName("Skip To The Fillings Section Test")
    public void skipToTheFillingsSectionTest() {
        MainPage mainPage = new MainPage(driver, wait);
        mainPage.clickFillingsSection();
        assertTrue("Element is not selected", mainPage.isFillingsSelected());
    }

}