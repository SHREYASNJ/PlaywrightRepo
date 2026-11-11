package Pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class QMS_CheckIn_CheckOut_Buyer_GuestHouse
{
    Page page;
    FrameLocator rightFrame;
    QMS_PropertySearch_Actions propsearch;
    private static final String rightframe = "iframe[name='right']";
    private static final String subm_Bttn ="div[data-metric-title='SUBMITTED']";
    private static final String globalaction_Menu="#globalActionMenu";


    public QMS_CheckIn_CheckOut_Buyer_GuestHouse(Page page)
    {
        this.page=page;
        // Maximum time for elements to become actionable
        page.setDefaultTimeout(30000);
        // Maximum time for page navigation
        page.setDefaultNavigationTimeout(60000);
        this.rightFrame = page.frameLocator(rightframe);
        this.propsearch = new QMS_PropertySearch_Actions(page);
    }
    public void guesthouse_Earmark_Submitted(String Property_Value, String sub_Action
                                             )
    {
        Locator submittedCard = rightFrame.locator(subm_Bttn);
        assertThat(submittedCard).isVisible();
        assertThat(submittedCard).isEnabled();
        submittedCard.click();

        propsearch.search_and_Action(Property_Value);

        Locator earmarkButton = rightFrame.locator(globalaction_Menu)
                .getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions()
                        .setName(sub_Action));
        assertThat(earmarkButton).isVisible();
        earmarkButton.click();






    }

}
