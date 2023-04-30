package TestCases;

import org.testng.annotations.Test;
import Pages.*;

public class TC_005_Experiment extends baseClass {

    @Test(groups = {"Regression"})
    public void experiment() throws InterruptedException {
        LoginPage lp=new LoginPage(driver);
        lp.loginExp();

        MainPage mp=new MainPage(driver);
        mp.clickAll();
        Thread.sleep(3000);
        //mp.clickAllRemove();
        mp.cartBtn.click();
        CartPage cp=new CartPage(driver);
        cp.removeAllFromCart();
        Thread.sleep(3000);
    }

}
