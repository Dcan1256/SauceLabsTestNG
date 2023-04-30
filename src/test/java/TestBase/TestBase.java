package TestBase;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class TestBase {
    public WebDriver driver;
    public ResourceBundle config;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(String br){
        config=ResourceBundle.getBundle("config");
        if(br.toLowerCase().equals("chrome")){
            //            HeadlessBrowser

//            ChromeOptions options= new ChromeOptions();
//            options.setHeadless(true);
//            driver=new ChromeDriver(options);
            driver=new ChromeDriver();
        }
        else if(br.toLowerCase().equals("firefox")){
            //            HeadlessBrowser

//            FirefoxOptions options= new FirefoxOptions();
//            options.setHeadless(true);
//            driver=new FirefoxDriver(options);
//            driver=new FirefoxDriver();
        }
        else if(br.toLowerCase().equals("edge")){
            //            HeadlessBrowser

//            EdgeOptions options= new EdgeOptions();
//            options.setHeadless(true);
//            driver=new EdgeDriver(options);
            driver=new EdgeDriver();
        }
        else{
            driver=new InternetExplorerDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(config.getString("appURL"));
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @AfterMethod
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
