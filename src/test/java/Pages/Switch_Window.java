package Pages;

import com.microsoft.playwright.Page;

public class Switch_Window {

    Page page;
    Page parentPage;
    Page childPage;

    public Switch_Window(Page page) {
        this.page = this.page;
        this.parentPage = this.page;
    }

    public void Switch_Window_to_Child() {

        childPage = parentPage.waitForPopup(() -> {
            parentPage.locator("#openWindow").click();
        });

        childPage.waitForLoadState();
        childPage.bringToFront();
    }

    public void Switch_Window_to_Parent() {
        parentPage.bringToFront();
    }
}