package TestCases;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;
public class baseClass {

    public static WebDriver driver;

    public Logger logger; //for logging

    public ResourceBundle rb;


    @BeforeMethod(groups = {"Master","Sanity","Regression"})
    @Parameters("browser")
    public void setUp(String br){
        rb=ResourceBundle.getBundle("config");
        logger= LogManager.getLogger(this.getClass()); // logging


        if(br.equals("firefox"))
        {
            FirefoxOptions options=new FirefoxOptions();
            options.setHeadless(true);
            driver=new FirefoxDriver(options);
        }
        else if (br.equals("edge"))
        {
            EdgeOptions options=new EdgeOptions();
            options.setHeadless(true);
            driver=new EdgeDriver(options);
        }
        else if (br.equals("chrome"))
        {
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(true);
            driver=new ChromeDriver(options);
        }
        else
        {
            driver=new InternetExplorerDriver();
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get(rb.getString("appURL"));
        driver.manage().window().maximize();
    }
    @AfterMethod(groups = {"Master","Sanity","Regression"})
    public void tearDown(){
        driver.quit();
    }

    public String captureScreen(String tname) throws IOException {
        String timeStamp= new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot takesScreenshot=(TakesScreenshot)driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String destination= System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp+".png";

        try {
            FileUtils.copyFile(source, new File(destination));
        }catch (Exception e){
            e.getMessage();
        }
        return destination;
    }
}
