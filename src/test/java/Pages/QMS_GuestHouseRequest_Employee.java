package Pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;

import java.time.LocalDate;


import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class QMS_GuestHouseRequest_Employee
{
    Page page;
    FrameLocator rightFrame;
    Locator bookingModal;
    private static final String rightframe = "iframe[name='right']";
    private static final String qtrinputSearch = "#quarterSearchInput";
    private static final String bookNowbutton ="Book Now";
    private static final String mobileNumber ="#mobilenumber";
    private static final String numberofAdult ="#numberofadult";
    private static final String numberofChildren ="#numberofchildren";
    private static final String specDesc ="#specialdescription";
    private static final String propcode ="span.rounded-full:has-text('Property Code :')";
    private static final String datepicker =".ui-datepicker";
    private static final String checkin_Date="#bookingcheckin";
    private static final String checkoutdate="#bookingcheckout";
    private static final String bookngroomType= "#bookingroomtype";
    private static final String select_Option= "option:checked";
    private static final String noofRooms= "#numberofroom";
    private static final String bookprop_Remarks="#bookingremarks";
    private static final String confirm_booking_Button ="#bookingbtn";
    private static final String pNow="Pay Now";
    private static final String alert_Message="div.ajs-content";
    private static final String alertmessage_Content="You can download the acknowledgement from the Submitted stage once the payment is done.";
    private static final String alertmessage_OK="OK";
    private static final String t_Type="#transType";
    private static final String t_Mode="#mode";
    private static final String insNumber="#sInstrumentNumber";
    private static final String insBranch="#sInstrumentBranch";
    private static final String insRemaks="#sInstrumentRemarks";
    private static final String pay_Status="td.ellipsis";
    private static final String close_Pay ="button[onclick='closePayment()']";
    private static final String subm_Bttn ="div[data-metric-title='SUBMITTED']";
    private static final String booking="#bookingdiv";
    private static final String bookingProp="Book This Property";
    private static final String ALERT_MODAL = ".ajs-modal";
    private static final String Alert_Header = ".ajs-header";
    private static final String Alert_okButton = "button.ajs-button.ajs-ok";
    private static final String payment_Frame="#paymentFrame";
    private static final String instBank = "#sInstrumentBank";
    private static final String instBankChecked = "option:checked";
    private static final String instDate = "#sInstrumentDate";
    private static final String ddUpdateBtton = "input[value='Update']";
    private static final String dd_Date="#sInstrumentDate";
    private static final String DATE_PICKER = "#ui-datepicker-div";
    private static final String DATE_CALENDAR = ".ui-datepicker-calendar";
    private static final String DATEPICKER_CALENDAR = ".ui-datepicker-calendar td a";


    public QMS_GuestHouseRequest_Employee(Page page)
    {
        this.page=page;
        // Maximum time for elements to become actionable
        page.setDefaultTimeout(30000);
        // Maximum time for page navigation
        page.setDefaultNavigationTimeout(60000);
        this.rightFrame = page.frameLocator(rightframe);
        this.bookingModal = rightFrame.locator(booking);

    }

    public void guesthouseRequest_Employee(String Property_Value1, String Adult,
                                           String Children, String SpeciDescription,
                                           String proptypedrpDown, String number_of_Rooms,
                                           String booking_Prop_Remarks, String transtype_Value,
                                           String transMode_Value, String ddNum,
                                           String ddBran, String ddRem,
                                           String statusPayment, String Bank_Name)
    {
        LocalDate checkIn = LocalDate.now().plusDays(1);
        LocalDate checkOut = checkIn.plusDays(1);
        Locator QuarterSearch = rightFrame.locator(qtrinputSearch);
        assertThat(QuarterSearch).isVisible();
        QuarterSearch.fill(Property_Value1);
        assertThat(QuarterSearch).hasValue(Property_Value1);

        Locator PropertyCode = rightFrame.locator(propcode);
        assertThat(PropertyCode).isVisible();
       Locator bookNowButton =  rightFrame.getByRole(AriaRole.BUTTON,
               new FrameLocator.GetByRoleOptions()
                .setName(bookNowbutton));
        bookNowButton.scrollIntoViewIfNeeded();
        assertThat(bookNowButton).isVisible();
        assertThat(bookNowButton).isEnabled();
        bookNowButton.click();

        assertThat(bookingModal).isVisible();
        assertThat(bookingModal.getByText(bookingProp,
                        new Locator.GetByTextOptions()
                                .setExact(true))).isVisible();

        Locator PhoneNumber = bookingModal.locator(mobileNumber);
        assertThat(PhoneNumber).isVisible();
        assertThat(PhoneNumber).isDisabled();

        Locator noofAdult = bookingModal.locator(numberofAdult);
        assertThat(noofAdult).isVisible();
        assertThat(noofAdult).isEnabled();
        noofAdult.fill(Adult);

        Locator noofChildren = bookingModal.locator(numberofChildren);
        assertThat(noofChildren).isVisible();
        assertThat(noofChildren).isEnabled();
        noofChildren.fill(Children);

        Locator specialDesc = bookingModal.locator(specDesc);
        assertThat(specialDesc).isVisible();
        assertThat(specialDesc).isEnabled();
        specialDesc.fill(SpeciDescription);

// Check-in
        Locator checkinDate = bookingModal.locator(checkin_Date);
        checkinDate.scrollIntoViewIfNeeded();
        assertThat(checkinDate).isVisible();
        assertThat(checkinDate).isEnabled();
        checkinDate.click();
// Select Check-in date
        SelectCalendarDate(checkIn);
// Validate Check-in
        assertThat(checkinDate).isVisible();
        System.out.println("Check-in Date: " + checkIn);

// Check-out
        Locator checkoutDate = bookingModal.locator(checkoutdate);
        checkoutDate.scrollIntoViewIfNeeded();
        assertThat(checkoutDate).isVisible();
        assertThat(checkoutDate).isEnabled();
        checkoutDate.click();
// Select Check-out date
        SelectCalendarDate(checkOut);
// Validate Check-out
        assertThat(checkoutDate).isVisible();
        System.out.println("Check-out Date: " + checkOut);

        Locator bookingroom_Type=bookingModal.locator(bookngroomType);
        bookingroom_Type.scrollIntoViewIfNeeded();
        bookingroom_Type.selectOption(new SelectOption()
                .setLabel(proptypedrpDown));
        assertThat(bookingroom_Type.locator(select_Option))
                .hasText(proptypedrpDown);

        Locator noof_Rooms=bookingModal.locator(noofRooms);
        noof_Rooms.scrollIntoViewIfNeeded();
        noof_Rooms.selectOption(new SelectOption()
                .setLabel(number_of_Rooms));
        assertThat(noof_Rooms.locator(select_Option))
                .hasText(number_of_Rooms);

        Locator payNow_Button=bookingModal.getByRole(AriaRole.BUTTON,
                new Locator.GetByRoleOptions()
                        .setName(pNow));
        payNow_Button.scrollIntoViewIfNeeded();
        assertThat(payNow_Button).isVisible();
        assertThat(payNow_Button).isEnabled();
        payNow_Button.click();

        Locator bookthisprop_Remarks=bookingModal.locator(bookprop_Remarks);
        bookthisprop_Remarks.scrollIntoViewIfNeeded();
        assertThat(bookthisprop_Remarks).isVisible();
        assertThat(bookthisprop_Remarks).isEnabled();
        bookthisprop_Remarks.fill(booking_Prop_Remarks);

        Locator confirmbook_Button=bookingModal.locator(confirm_booking_Button);
        confirmbook_Button.scrollIntoViewIfNeeded();
        assertThat(confirmbook_Button).isVisible();
        assertThat(confirmbook_Button).isEnabled();
        confirmbook_Button.click();

        Locator alertModal = rightFrame.locator(ALERT_MODAL);
        assertThat(alertModal).isVisible();
        assertThat(alertModal.locator(Alert_Header))
                .hasText("Alert");

        Locator okButton = alertModal.locator(Alert_okButton);
        assertThat(okButton).isVisible();
        assertThat(okButton).isEnabled();
        okButton.click();

        FrameLocator paymentFrame = rightFrame.frameLocator(payment_Frame);
        Locator paymentDetails =
                paymentFrame.getByText("PAYMENT DETAILS",
                        new FrameLocator.GetByTextOptions()
                                .setExact(true));
        assertThat(paymentDetails).isVisible();

        Locator trans_Type= paymentFrame.locator(t_Type)
                .nth(0);
        assertThat(trans_Type).isVisible();
        assertThat(trans_Type).isEnabled();

        trans_Type.selectOption(new SelectOption().setValue(transtype_Value));
        assertThat(trans_Type.locator(select_Option))
                .hasValue(transtype_Value);

        Locator trans_Mode= paymentFrame.locator(t_Mode)
                .nth(0);
        assertThat(trans_Mode).isVisible();
        assertThat(trans_Mode).isEnabled();

        trans_Mode.selectOption(new SelectOption().setValue(transMode_Value));
        assertThat(trans_Mode.locator(select_Option))
                .hasValue(transMode_Value);

        Locator ddNumber=paymentFrame.locator(insNumber);
        ddNumber.scrollIntoViewIfNeeded();
        assertThat(ddNumber).isVisible();
        assertThat(ddNumber).isEnabled();
        ddNumber.fill(ddNum);

        Locator ddBranch=paymentFrame.locator(insBranch);
        ddBranch.scrollIntoViewIfNeeded();
        assertThat(ddBranch).isVisible();
        assertThat(ddBranch).isEnabled();
        ddBranch.fill(ddBran);

        Locator ddremarks=paymentFrame.locator(insRemaks);
        ddremarks.scrollIntoViewIfNeeded();
        assertThat(ddremarks).isVisible();
        assertThat(ddremarks).isEnabled();
        ddremarks.fill(ddRem);

        Locator DDBank = paymentFrame.locator(instBank);
        DDBank.selectOption(new SelectOption().setLabel(Bank_Name));
        assertThat(DDBank.locator(instBankChecked))
                .hasText(Bank_Name);

        //Select Date
        // Payment instrument date
        Locator ddDate = paymentFrame.locator(instDate);
        ddDate.scrollIntoViewIfNeeded();
        assertThat(ddDate).isVisible();
        assertThat(ddDate).isEnabled();
        ddDate.click();
        // Date picker
        Locator datePicker = paymentFrame.locator(DATE_PICKER);
        assertThat(datePicker).isVisible();
        assertThat(datePicker.locator(DATE_CALENDAR)).isVisible();
        // Select exact date
        LocalDate instrumentDate = LocalDate.now().minusDays(1);
        String year = String.valueOf(instrumentDate.getYear());
        String month = String.valueOf(instrumentDate.getMonthValue() - 1);
        String day = String.valueOf(instrumentDate.getDayOfMonth());
        Locator selectDate = datePicker.locator(
                "td[data-year='" + year +
                        "'][data-month='" + month +
                        "'] a:text-is('" + day + "')"
        );
        assertThat(selectDate).hasCount(1);
        assertThat(selectDate).isVisible();
        selectDate.click();

        Locator DDupdate =paymentFrame.locator(ddUpdateBtton);
        DDupdate.scrollIntoViewIfNeeded();
        assertThat(DDupdate).isVisible();
        assertThat(DDupdate).isEnabled();
        DDupdate.click();

        Locator paymentStatus = paymentFrame.locator(pay_Status)
                .filter(new Locator.FilterOptions().setHasText(statusPayment));
        assertThat(paymentStatus.first()).isVisible();
        assertThat(paymentStatus.first()).containsText(statusPayment);

        Locator close_payment=paymentFrame.locator(close_Pay);
        assertThat(close_payment).isVisible();
        assertThat(close_payment).isEnabled();
        close_payment.click();

        Locator submittedCard = rightFrame.locator(subm_Bttn);
        assertThat(submittedCard).isVisible();
        assertThat(submittedCard).isEnabled();
        submittedCard.click();

        assertThat(QuarterSearch).isVisible();
        assertThat(QuarterSearch).isEnabled();
        QuarterSearch.fill(Property_Value1);

        assertThat(PropertyCode).isVisible();

    }
    private void SelectCalendarDate(LocalDate date)
    {
        Locator calendar = bookingModal.locator(datepicker);
        assertThat(calendar).isVisible();
        String year = String.valueOf(date.getYear());
        String month = String.valueOf(date.getMonthValue() - 1);
        String day = String.valueOf(date.getDayOfMonth());
        Locator selectDate = calendar.locator(
                "td[data-year='" + year +
                        "'][data-month='" + month + "'] a:text-is('" + day + "')"
        );
        assertThat(selectDate).isVisible();
        selectDate.click();
    }
}