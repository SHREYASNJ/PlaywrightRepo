package Test;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;
import Pages.HomePage;
import Pages.LoginPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class Tender extends BaseTest
{
    @Test(groups = {"framework"})
    public void DemoTest()
    {
        HomePage home = new HomePage(page, baseurl);
        LoginPage loginpage = home.LogintoApplication("Home | DUDCHSN");
        loginpage.Login_Page("DUDCHSN","Secure!@#", "Tender", "LoginPage | DUDCHSN");
    }
}
