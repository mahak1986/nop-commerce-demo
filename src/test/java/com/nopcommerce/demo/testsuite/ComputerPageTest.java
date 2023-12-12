package com.nopcommerce.demo.testsuite;

import com.nopcommerce.demo.customlisteners.CustomListeners;
import com.nopcommerce.demo.pages.*;
import com.nopcommerce.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import resources.testdata.TestData;

@Listeners(CustomListeners.class)
public class ComputerPageTest extends BaseTest {
    HomePage homePage;
    LoginPage loginPage;
    RegisterPage registerPage;
    ComputerPage computerPage;
    BuildYourOwnComputerPage buildYourOwnComputerPage;
    DesktopsPage desktopsPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        homePage = new HomePage();
        loginPage = new LoginPage();
        registerPage = new RegisterPage();
        computerPage = new ComputerPage();
        buildYourOwnComputerPage = new BuildYourOwnComputerPage();
        desktopsPage = new DesktopsPage();

    }

    @Test(groups = {"sanity", "smoke", "regression"})
    public void verifyUserShouldNavigateToComputerPageSuccessfully() {
        //Click on Computer tab
        computerPage.mouseHoverOnComputersTab();

        //Verify "Computer" text
        computerPage.verifyComputersText();
    }

    @Test(groups = {"smoke", "regression"})
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully() {
        //Click on Computer tab
        computerPage.mouseHoverOnComputersTab();

        //Click on Desktops link
        computerPage.clickOnDesktopsTab();

        //Verify "Desktops" text
        desktopsPage.verifyDesktopsText();

    }

    @Test(dataProvider = "build your own computer", dataProviderClass = TestData.class, groups = {"regression"})
    public void verifyThatUserShouldBuildYouOwnComputerAndAddThemToCartSuccessfully(String processor, String ram, String hdd, String os, String software1) {
        //Click on Computer tab
        computerPage.mouseHoverOnComputersTab();

        //Click on Desktops link
        computerPage.clickOnDesktopsTab();

        //Click on product name "Build your own computer"
        desktopsPage.clickOnBuildYourOwnComputer();

        //Select processor "processor"
        buildYourOwnComputerPage.processorOption(processor);

        //Select RAM "ram"
        buildYourOwnComputerPage.selectRamOption(ram);

        //Select HDD "hdd"
        buildYourOwnComputerPage.selectHddOption(hdd);

        //Select OS "os"
        buildYourOwnComputerPage.clickOnOsOption(os);

        //Select Software "software"
        buildYourOwnComputerPage.selectSoftwareOption(software1);

        //Click on "ADD TO CART" Button
        buildYourOwnComputerPage.clickOnAddToCartButton();

        //Verify message "The product has been added to your shopping cart"
        String expectedMessage = "The product has been added to your shopping cart";
        Assert.assertEquals(buildYourOwnComputerPage.productAddedToCartText(), expectedMessage, "Error Message");
    }
}
