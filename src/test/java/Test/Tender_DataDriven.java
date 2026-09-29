package Test;

import Pages.HomePage;
import Pages.LoginPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.DataProviderUtil;

import java.io.IOException;
import java.util.HashMap;


public class Tender_DataDriven extends BaseTest
{
    @DataProvider(name ="jsonData")
    public Object[][] loginJsondata() throws IOException
    {
        return DataProviderUtil.jsonData("/src/test/resources/testData_TW.json");
    }
    @Test(dataProvider = "jsonData", groups = {"framework"})
    public void DemoTest(HashMap<String, String> data)
    {
        String username = data.get("UN");
        String Password = data.get("PW");
        String PageTitle = data.get("Title");
        HomePage home = new HomePage(page, baseurl);
        LoginPage loginpage = home.LogintoApplication(PageTitle);
        loginpage.Login_Page(username,Password, PageTitle);
    }
}