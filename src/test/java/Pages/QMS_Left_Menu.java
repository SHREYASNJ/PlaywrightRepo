package Pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class QMS_Left_Menu {

    Page page;

    private static final String leftframe = ".Left-frame";

    public QMS_Left_Menu(Page page) {
        this.page = page;
    }

    public void Left_Menu(String MenuName) {

        // Create Switch_Window object
        Switch_Window child = new Switch_Window(page);

        // Switch to child window
        child.Switch_Window_to_Child();

        // Locate iframe
        FrameLocator frame = page.frameLocator(leftframe);

        // Click Facilities
        frame.locator("div.menu-label")
                .filter(new Locator.FilterOptions().setHasText(MenuName))
                .click();

        // Verify Available Properties button is visible
        assertThat(
                frame.getByRole(
                        AriaRole.BUTTON,
                        new FrameLocator.GetByRoleOptions()
                                .setName("Available Properties")
                )
        ).isVisible();
    }
}