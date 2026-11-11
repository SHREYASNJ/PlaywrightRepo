package Pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class QMS_CheckIn_CheckOut_Buyer_GuestHouse {
    Page page;
    FrameLocator rightFrame;
    QMS_PropertySearch_Actions propsearch;
    private static final String rightframe = "iframe[name='right']";
    private static final String subm_Bttn = "div[data-metric-title='SUBMITTED']";
    private static final String globalaction_Menu = "#globalActionMenu";
    private static final String pop_Div = "#popupdiv";
    private static final String facroom_Number = "#facilities_room_number";
    private static final String hall_Check = "input.facilitiesRoomCheckbox";
    private static final String hall_Check_Selected = ".selected-room-text";
    private static final String hall_Remarks = "e.g. Room reserved pending clearance, adjacent room arranged";
    private static final String sub_eo_Provision = "#submit-Eo-provision";
    private static final String success_Message = ".ajs-content";
    private static final String successMessage_Text = "Provision details updated successfully.";
    private static final String success_Message_OK = "button.ajs-ok";
    private static final String occupied_Bttn = "div[data-metric-title='OCCUPIED']";
    private static final String Allotted_Bttn = "div[data-metric-title='ALLOTTED']";
    private static final String number_of_Adults ="#NoOfAdults";
    private static final String number_of_children= "#NoOfChildrens";


    public QMS_CheckIn_CheckOut_Buyer_GuestHouse(Page page) {
        this.page = page;
        // Maximum time for elements to become actionable
        page.setDefaultTimeout(30000);
        // Maximum time for page navigation
        page.setDefaultNavigationTimeout(60000);
        this.rightFrame = page.frameLocator(rightframe);
        this.propsearch = new QMS_PropertySearch_Actions(page);
    }

    public void guesthouse_earmark_Submitted(String Property_Value, String sub_Action,
                                             String room_Number, String remarksText) {
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
        assertThat(facilitiesRoom.locator(hall_Check_Selected)).hasText(room_Number);

        Locator remarks = bookingModal.getByPlaceholder(hall_Remarks);
        assertThat(remarks).isVisible();
        assertThat(remarks).isEnabled();
        remarks.fill(remarksText);

        Locator subBtton = bookingModal.locator(sub_eo_Provision);
        assertThat(subBtton).isVisible();
        assertThat(subBtton).isEnabled();
        subBtton.click();

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

    public void guestHouse_process_checkin_Allotted(String Property_Value,
                                                    String allottedAction, String adults,
                                                    String child)
    {
        Locator allottedCard = rightFrame.locator(Allotted_Bttn);
        assertThat(allottedCard).isVisible();
        assertThat(allottedCard).isEnabled();
        allottedCard.click();

        propsearch.search_and_Action(Property_Value);
        
        Locator globeAction=rightFrame.locator("#globalActionMenu");
        Locator allottedActn=globeAction.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions()
                .setName(allottedAction));
        assertThat(allottedActn).isVisible();
        assertThat(allottedActn).isEnabled();
        allottedActn.click();

        Locator bookingModal = rightFrame.locator(pop_Div);
        assertThat(bookingModal).isVisible();
        assertThat(bookingModal.getByText(allottedAction,
                new Locator.GetByTextOptions()
                        .setExact(true))).isVisible();

        Locator noAdults=bookingModal.locator(number_of_Adults);
        assertThat(noAdults).isVisible();
        assertThat(noAdults).isEnabled();
        noAdults.fill(adults);

        Locator nochildrn=bookingModal.locator(number_of_children);
        assertThat(nochildrn).isVisible();
        assertThat(nochildrn).isEnabled();
        noAdults.fill(child);















    }

    public void guestHouse_process_checkout_Occupied(String Property_Value,
                                            String occupiedAction) {
        Locator occBtn = rightFrame.locator(occupied_Bttn);
        assertThat(occBtn).isVisible();
        assertThat(occBtn).isEnabled();
        occBtn.click();

        propsearch.search_and_Action(Property_Value);

    }
}