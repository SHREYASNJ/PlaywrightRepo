package Pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class QMS_HallRequest_EO
{
    Page page;
    public QMS_HallRequest_EO(Page page)
    {
        this.page=page;
    }

    private static final String searchProp="#quarterSearchInput";
  private static final String propcode="span";
  private static final String propcodeText="Property Code";
  private static final String booknow_Button="Book Now";
  private static final String booking="#bookingdiv";
  private static final String bookingProp="Book This Property";
  private static final String tparty="Third Party";
  private static final String tpemployeeName="#EmployeenameThirdParty";
  private static final String tpemployee="#mobilenumberThirdParty";
  private static final String tpemail="#EmailThirdParty";
  private static final String tpdesignation="#designationThirdParty";
  private static final String tpDepartment="#departmentThirdParty";
  private static final String tpspecdesc="#specialdescriptionThirdParty";
  private static final String tpcheckIN="#bookingcheckinThirdParty";
  private static final String calender=".ui-datepicker-calendar td a";
  private static final String tp_checkOut="#bookingcheckoutThirdParty";
  private static final String tp_payNow="Pay Now";
  private static final String toBookingRemarks="#bookingremarksThirdParty";
  private static final String tp_closeTerms="button[onclick='closeTermsConditions()']";
  private static final String tp_remarksvalue="Third Party Booking";
  private static final String tp_terms_clickHere="Click Here";
  private static final String conf_Booking="#bookingbtn";
  private static final String conf_BookingCB="#TermsandConditionsCheckThirdParty";



  public void hall_Request_EO(String Property_Value1,String tpempname_Value1,
                           String tpempMobilenumb_Value1, String tpempemail_Value1,
                           String tp_Designation, String tp_organisation,
                           String tp_specDescrption, String checkinDate,
                           String checkoutDate
                           )
    {
      Locator search = page.locator(searchProp);
        assertThat(search).isVisible();
        assertThat(search).isEnabled();
        search.fill(Property_Value1);

        Locator propertyCode = page.locator(propcode)
                .filter(new Locator.FilterOptions().setHasText(propcodeText));

        assertThat(propertyCode).containsText(propcodeText);

        Locator BookNowButton = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName(booknow_Button));
        assertThat(BookNowButton).isVisible();
        assertThat(BookNowButton).isEnabled();
        BookNowButton.click();

        Locator bookingModal = page.locator(booking);
        assertThat(bookingModal).isVisible();
        assertThat(
                bookingModal.getByText(bookingProp,
                        new Locator.GetByTextOptions().setExact(true))).isVisible();

        Locator tpButton = bookingModal.getByText(tparty, new Locator.GetByTextOptions()
                .setExact(true));
        assertThat(tpButton).isVisible();
        tpButton.click();

        Locator fullName = bookingModal.locator(tpemployeeName);
        fullName.fill(tpempname_Value1);
        Locator mobileNumber =bookingModal.locator(tpemployee);
        mobileNumber.fill(tpempMobilenumb_Value1);
        Locator emailID =bookingModal.locator(tpemail);
        emailID.fill(tpempemail_Value1);
        Locator designation =bookingModal.locator(tpdesignation);
        designation.fill(tp_Designation);
        Locator organisation =bookingModal.locator(tpDepartment);
        organisation.fill(tp_organisation);
        Locator specialDescr = bookingModal.locator(tpspecdesc);
        specialDescr.fill(tp_specDescrption);
        // Check-in date
        Locator checkIn = bookingModal.locator(tpcheckIN);
        // Open the calendar
        checkIn.scrollIntoViewIfNeeded();
        assertThat(checkIn).isVisible();
      assertThat(checkIn).isEnabled();
      checkIn.click();

      // Select date from the calendar
        bookingModal.locator(calender)
                .filter(new Locator.FilterOptions().setHasText(checkinDate))
                .click();
      // Check-in date
      Locator checkOut = bookingModal.locator(tp_checkOut);
      // Open the calendar
      checkOut.scrollIntoViewIfNeeded();
      assertThat(checkOut).isVisible();
      assertThat(checkOut).isEnabled();
      checkOut.click();
      // Select date from the calendar
      bookingModal.locator(calender)
              .filter(new Locator.FilterOptions().setHasText(checkoutDate))
              .click();

      Locator payNow=bookingModal.getByRole(AriaRole.BUTTON,
              new Locator.GetByRoleOptions()
              .setName(tp_payNow));
      payNow.scrollIntoViewIfNeeded();
      assertThat(payNow).isVisible();
      assertThat(payNow).isEnabled();
      payNow.click();

      Locator remarksTP=bookingModal.locator(toBookingRemarks);
      remarksTP.scrollIntoViewIfNeeded();
      remarksTP.fill(tp_remarksvalue);

      Locator Terms=bookingModal.getByRole(AriaRole.LINK,
              new Locator.GetByRoleOptions()
              .setName(tp_terms_clickHere));
      Terms.scrollIntoViewIfNeeded();
      Terms.click();

      Locator closeTerms=bookingModal.locator(tp_closeTerms);
      closeTerms.scrollIntoViewIfNeeded();
      assertThat(closeTerms).isVisible();
      assertThat(closeTerms).isEnabled();
      closeTerms.click();

      Locator closeTerms_checkBox=bookingModal.locator(conf_BookingCB);
      closeTerms_checkBox.scrollIntoViewIfNeeded();
      assertThat(closeTerms_checkBox).isVisible();
      assertThat(closeTerms_checkBox).isEnabled();
      closeTerms_checkBox.click();

      Locator confirm_Booking=bookingModal.locator(conf_Booking);
      confirm_Booking.scrollIntoViewIfNeeded();
      assertThat(confirm_Booking).isVisible();
      assertThat(confirm_Booking).isEnabled();
      confirm_Booking.click();

      FrameLocator alertFrame = page.frameLocator("iframe");
      Locator OkButton = alertFrame.getByRole(AriaRole.BUTTON,
              new FrameLocator.GetByRoleOptions()
              .setName("OK"));
      assertThat(OkButton).isVisible();
      OkButton.click();

      Locator TransTypeselect=page.locator("#transType");
      TransTypeselect.selectOption(new SelectOption().setLabel("Property SD Fee1"));
      assertThat(TransTypeselect.locator("option:checked"))
              .hasText("Property SD Fee1");

      Locator modeSelect=page.locator("#mode");
      modeSelect.selectOption(new SelectOption().setLabel("DD"));
      assertThat(modeSelect.locator("option:checked"))
              .hasText("DD");



    }
}
