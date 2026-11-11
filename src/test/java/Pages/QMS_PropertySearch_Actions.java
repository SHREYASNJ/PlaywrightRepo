package Pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class QMS_PropertySearch_Actions
{
    Page page;
    FrameLocator rightFrame;

    private static final String rightframe = "iframe[name='right']";
    private static final String qtrinputSearch = "#quarterSearchInput";
    private static final String actionsIcon = "button[title='Actions']";

    public QMS_PropertySearch_Actions(Page page)
    {
        this.page = page;
        this.rightFrame = page.frameLocator(rightframe);
    }

    public void search_and_Action(String Property_Value1)
    {
        // Search property
        Locator QuarterSearch = rightFrame.locator(qtrinputSearch);
        assertThat(QuarterSearch).isVisible();
        assertThat(QuarterSearch).isEnabled();
        QuarterSearch.fill(Property_Value1);

        // Retrieve request ID
        QMS_HallRequest_EO hallRequest = new QMS_HallRequest_EO(page);
        String requestID = hallRequest.GetRequestID();
        System.out.println("Retrieved Request ID: " + requestID);

        // Locate the corresponding booking row
        Locator bookingRow = rightFrame.locator("tr")
                .filter(new Locator.FilterOptions().setHasText(requestID));
        assertThat(bookingRow).isVisible();

        // Locate Actions button inside that row
        Locator actionButton = bookingRow.locator(actionsIcon);
        assertThat(actionButton).isVisible();
        assertThat(actionButton).isEnabled();
        actionButton.click();
    }
}