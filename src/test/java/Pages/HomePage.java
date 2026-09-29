package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HomePage
{
    Page page;
    String baseurl;

    public HomePage(Page page, String baseurl)
    {
     this.baseurl=baseurl;
     this.page=page;
    }

    public LoginPage LogintoApplication(String PageTitle)
    {
        page.navigate(baseurl);

        Locator popupbutton = page.locator("div.sha-pg010-close[title='Close']");
        popupbutton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        popupbutton.click(new Locator.ClickOptions().setForce(true));

        System.out.println(page.title());
        assertThat(page).hasTitle(PageTitle);
        //page.getByLabel("Email").fill("MECON");
        Locator login = page.locator("div.sha-pg001-02-menu-item.sha-pg001-02-menu-item-without-child")
                .filter(new Locator.FilterOptions().setHasText("Login"));
        login.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE));
        login.click();
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("Test2.png")));
        LoginPage login1 = new LoginPage(page);
        return login1;


    }
}
