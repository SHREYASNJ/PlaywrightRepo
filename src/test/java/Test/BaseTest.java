package Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class BaseTest
{
    Page page;
    Playwright playwright;
    Browser browser;
    String baseurl;

    @BeforeMethod(alwaysRun = true)
    public void setup() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
        prop.load(fis);
        //JAVA TERNARY OPERATOR
        String BrowserName= System.getProperty("browser")!=null ?System.getProperty("browser")
                : prop.getProperty("browser");
        String qaURL= System.getProperty("env")!=null ?System.getProperty("env") : prop.getProperty("env");

        playwright =Playwright.create();

        if("firefox".equals(BrowserName))
        {
            browser = playwright.firefox().launch();
        }
        else if ("safari".equals(BrowserName))
        {
            browser = playwright.webkit().launch();
        }
        else
        {
            browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        }

        page = browser.newPage();
        baseurl=prop.getProperty(qaURL+".baseurl");
    }
}