package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.LoginPage;
import Pages.MainPage;

import java.util.ResourceBundle;

public class TC_001_Login extends baseClass {
    public ResourceBundle rb;
    @Test(groups = {"Sanity","Regression"})
    public void loginWithValidCredentials() throws InterruptedException {
        try {
            logger.info("***** Starting TC_001_Login test case *****");
            LoginPage lp=new LoginPage(driver);
            logger.info("Filling username Box");
            lp.usernameBox.sendKeys("standard_user");
            Thread.sleep(1000);
            logger.info("Filling password Box");
            lp.passwordBox.sendKeys("secret_sauce");
            Thread.sleep(1000);
            logger.info("Clicking login Button");
            lp.loginBtn.click();
            Thread.sleep(1000);
            logger.info("Now we are in the main page!!!");
            MainPage mp=new MainPage(driver);
            logger.info("Comparing actual and expected header of main page");
            String actualTitle=mp.getMainPageTitle();
            Assert.assertEquals(actualTitle,"Products");
            logger.info("***** Finishing TC_001_Login test case *****");
        }catch (Exception e){
            logger.info("***** TC_001_Login test case FAILED !!! *****");
            Assert.fail();
        }
    }

}
