package TestCases;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.LoginPage;
import Pages.MainPage;

public class TC_002_SortingAll extends baseClass {

    @Test(groups = {"Regression"})
    public void sorting() throws InterruptedException {

            // Login page
            LoginPage lp=new LoginPage(driver);
            lp.loginExp();

            //Main page
            MainPage mp=new MainPage(driver);

            // Sorting a to z and validating
            Select value1=new Select(mp.sortingDropDown);
            value1.selectByValue(rb.getString("a-z"));
            String expectedAzValue= rb.getString("azText");
            String actualAzValue=value1.getFirstSelectedOption().getText();
            Assert.assertEquals(actualAzValue,expectedAzValue);

            // Sorting z to a and validating
            Select value2=new Select(mp.sortingDropDown);
            value2.selectByValue(rb.getString("z-a"));
            String expectedZaValue= rb.getString("zaText");
            String actualZaValue=value2.getFirstSelectedOption().getText();
            Assert.assertEquals(actualZaValue,expectedZaValue);

            // Sorting high to low and validating
            Select value3=new Select(mp.sortingDropDown);
            value3.selectByValue(rb.getString("high-low"));
            String expectedHiLoValue= rb.getString("hiloText");
            String actualHiLoValue=value3.getFirstSelectedOption().getText();
            Assert.assertEquals(actualHiLoValue,expectedHiLoValue);

            // Sorting low to high and validating
            Select value4=new Select(mp.sortingDropDown);
            value4.selectByValue(rb.getString("low-high"));
            String expectedLoHiValue= rb.getString("lohiText");
            String actualLoHiValue=value4.getFirstSelectedOption().getText();
            Assert.assertEquals(actualLoHiValue,expectedLoHiValue);


    }
}
