package Pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class QMS_Faclitiy_Alerts
{
    Page page;
    FrameLocator rightFrame;
    private static final String rightframe = "iframe[name='right']";
    private static final String alertModel = ".ajs-modal";
    private static final String alertHeader = ".ajs-header";
    private static final String alertBody = ".ajs-body";
    private static final String alertokButton = "button.ajs-ok";
    private static final String AlertHeaderValue = "ALERT";

    public QMS_Faclitiy_Alerts(Page page)
   {
    this.page=page;
    this.rightFrame = page.frameLocator(rightframe);
   }
    public void alert(String PropAlert)
    {
        // Locate Alertify popup
        Locator alertModal = rightFrame.locator(alertModel);
        assertThat(alertModal).isVisible();
        // Validate alert header
        assertThat(alertModal.locator(alertHeader))
                .hasText(AlertHeaderValue);
        // Validate success message
        assertThat(alertModal.locator(alertBody))
                .containsText(PropAlert);
        // Locate and click OK
        Locator okButton = alertModal.locator(alertokButton);
        assertThat(okButton).isVisible();
        assertThat(okButton).isEnabled();
        okButton.click();
        // Verify popup is closed
        assertThat(alertModal).isHidden();
    }

}
