package com.nopcommerce.demo.testsuite;

import com.nopcommerce.demo.customlisteners.CustomListeners;
import com.nopcommerce.demo.pages.HomePage;
import com.nopcommerce.demo.pages.LoginPage;
import com.nopcommerce.demo.pages.RegisterPage;
import com.nopcommerce.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(CustomListeners.class)
public class LoginPageTest extends BaseTest {

    HomePage homePage;
    LoginPage loginPage;
    RegisterPage registerPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        homePage = new HomePage();
        loginPage = new LoginPage();
        registerPage= new RegisterPage();
    }

    @Test(groups = {"sanity", "smoke", "regression"})
    public void userShouldNavigateToLoginPageSuccessFully() {
        //Click on Login Link
        homePage.clickOnLoginLink();

        //verify "Welcome, Please Sign In!" message display
        String expectedMessage = "Welcome, Please Sign In!";
        String actualMessage = loginPage.verifyWelcomeText();
        Assert.assertEquals(actualMessage, expectedMessage, "Login page not displayed");
    }

    @Test(groups = {"smoke", "regression"})
    public void verifyErrorMessageWithInvalidCredentials() throws InterruptedException {
        //Click on login link
        homePage.clickOnLoginLink();

        //Enter Email ID
        loginPage.enterEmailID("Mahak123@gmail.com");

        //Enter Password
        loginPage.enterPassword("Mahak123");
        Thread.sleep(2000);

        //Click on Login button
        loginPage.clickOnLoginButton();

        //Verify the error message
        String expectedErrorMessage = "Login was unsuccessful. Please correct the errors and try again.\n"
                + "No customer account found";
        String actualErrorMessage = loginPage.verifyErrorMessage();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message not displayed");
    }

    @Test(groups = {"regression"})
    public void verifyThatUserShouldLogInSuccessFullyWithValidCredentials() throws InterruptedException {
        //Click on Register Link
        registerPage.clickOnRegisterLink();

        //Select gender "Female"
        registerPage.selectGender("Female");

        //Enter firstname
        registerPage.firstNameOption("Mahak");

        //Enter lastname
        registerPage.lastNameOption("Agarwal");

        //Select day
        registerPage.dateOfBirthDay("23");

        //Select month
        registerPage.dateOfBirthMonth("January");

        //Select year
        registerPage.dateOfBirthYear("1996");
        Thread.sleep(2000);

        //Enter email
        enterEmailAddress();

        //Enter password
        registerPage.enterPasswordOption("Mahak123");

        //Enter Confirm Password
        registerPage.enterConfirmPassword("Mahak123");

        //Click on "REGISTER" button
        registerPage.clickOnRegisterButton();

        //Verify message "Your registration completed"
        String expected7 = "Your registration completed";
        Assert.assertEquals(registerPage.verifyRegistrationCompleteMessage(),expected7,"Error Message");


        // Click on login link
        homePage.clickOnLoginLink();

        //Enter EmailId
        loginPage.enterEmailID(randomEmail);

        //Enter Password
        loginPage.enterPassword("Mahak123");

        //Click on Login Button
        loginPage.clickOnLoginButton();

        // Verify that LogOut link is display
        String expectedLogOutLinkText = "Log out";
        String actualText = loginPage.verifyLogoutLinkIsDisplayed();
        Assert.assertEquals(actualText,expectedLogOutLinkText,"Logout Link is not displayed");

    }
    @Test(groups = {"regression"})
    public void verifyThatUserShouldLogOutSuccessFully() throws InterruptedException {
        verifyThatUserShouldLogInSuccessFullyWithValidCredentials();

        //Click on LogOut Link
        loginPage.clickOnLogoutLink();

        // Verify that LogIn Link Display
        String expected1 = "Log in";
        String actual1 = loginPage.verifyLoginLink();
        Assert.assertEquals(actual1,expected1,"Log in link not displayed");

    }

}


