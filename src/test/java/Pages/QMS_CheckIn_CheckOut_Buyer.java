package Pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class QMS_CheckIn_CheckOut_Buyer
{
    Page page;
    FrameLocator rightFrame;
    private static final String rightframe = "iframe[name='right']";
    private static final String subm_Bttn ="div[data-metric-title='SUBMITTED']";
    private static final String qtrinputSearch = "#quarterSearchInput";
    private static final String actionsIcon = "button[title='Actions']";
    private static final String globalaction_Menu="#globalActionMenu";
    private static final String pop_Div="#popupdiv";
    private static final String facroom_Number="#facilities_room_number";
    private static final String hall_Check="input.facilitiesRoomCheckbox";
    private static final String hall_Check_Selected=".selected-room-text";
    private static final String hall_Remarks="e.g. Room reserved pending clearance, adjacent room arranged";
    private static final String sub_eo_Provision="#submit-Eo-provision";
    private static final String success_Message=".ajs-content";
    private static final String successMessage_Text="Provision details updated successfully.";
    private static final String success_Message_OK="button.ajs-ok";


    public QMS_CheckIn_CheckOut_Buyer(Page page)
    {
     this.page=page;
     this.rightFrame = page.frameLocator(rightframe);
    }

    public void earmark_Provision_Buyer_Hall(String Property_Value1,
                                            String sub_Action, String hall_Value,
                                            String remarksText
    )
    {
        Locator submittedCard = rightFrame.locator(subm_Bttn);
        assertThat(submittedCard).isVisible();
        assertThat(submittedCard).isEnabled();
        submittedCard.click();

        Locator QuarterSearch = rightFrame.locator(qtrinputSearch);
        assertThat(QuarterSearch).isVisible();
        assertThat(QuarterSearch).isEnabled();
        QuarterSearch.fill(Property_Value1);

        QMS_HallRequest_EO hallRequest = new QMS_HallRequest_EO(page);
        String requestID = hallRequest.GetRequestID();
        System.out.println("Retrieved Request ID: " + requestID);

        Locator bookingRow = rightFrame.locator("tr")
                .filter(new Locator.FilterOptions().setHasText(requestID));
        assertThat(bookingRow).isVisible();
        Locator actionButton = bookingRow.locator(actionsIcon);
        assertThat(actionButton).isVisible();
        assertThat(actionButton).isEnabled();
        actionButton.click();

        Locator earmarkButton = rightFrame.locator(globalaction_Menu)
                .getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions()
                                .setName(sub_Action));
        assertThat(earmarkButton).isVisible();
        earmarkButton.click();

        Locator bookingModal = rightFrame.locator(pop_Div);
        assertThat(bookingModal).isVisible();
        assertThat(bookingModal.getByText(sub_Action, new Locator.GetByTextOptions()
                .setExact(true))).isVisible();

        Locator facilitiesRoom = bookingModal.locator(facroom_Number);
        assertThat(facilitiesRoom).isVisible();
        assertThat(facilitiesRoom).isEnabled();
        facilitiesRoom.click();

        Locator hallCheckbox = bookingModal.locator(hall_Check);
        assertThat(hallCheckbox).isVisible();
        hallCheckbox.check();
        assertThat(hallCheckbox).isChecked();
        assertThat(facilitiesRoom.locator(hall_Check_Selected)).hasText(hall_Value);

        Locator remarks = bookingModal.getByPlaceholder(hall_Remarks);
        assertThat(remarks).isVisible();
        assertThat(remarks).isEnabled();
        remarks.fill(remarksText);

        Locator submitporv =bookingModal.locator(sub_eo_Provision);
        assertThat(submitporv).isVisible();
        assertThat(submitporv).isEnabled();
        submitporv.click();

        // Validate success message
        Locator successMessage = rightFrame.locator(success_Message);
        assertThat(successMessage).isVisible();
        assertThat(successMessage).hasText(successMessage_Text);
        // Click OK
        Locator okButton = rightFrame.locator(success_Message_OK);
        assertThat(okButton).isVisible();
        assertThat(okButton).isEnabled();
        okButton.click();
    }
    public void earmark_Provision_Buyer_Hall()
    {

    }
}
