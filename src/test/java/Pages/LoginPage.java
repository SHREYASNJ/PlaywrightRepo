package Pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPage
{
    Page page;
    private static final String username_placeholder = "Username";
    private static final String password_placeholder = "Password";

    public LoginPage(Page page)
    {
        this.page= page;
    }

    public void Login_Page(String UN, String PW, String PageTitle)
    {
        System.out.println(page.title());
        assertThat(page).hasTitle(PageTitle);

        FrameLocator frame = page.frameLocator(".cst-pg032-iframe");

        frame.getByPlaceholder(username_placeholder).fill(UN);
        frame.getByPlaceholder(password_placeholder).fill(PW);
        frame.getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("Login")).click();
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("pagescreenshot.png")));
    }
}
