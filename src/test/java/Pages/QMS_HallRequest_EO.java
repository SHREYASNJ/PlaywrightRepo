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
  FrameLocator rightFrame;

  public QMS_HallRequest_EO(Page page)
    {
      this.page=page;
      this.rightFrame = page.frameLocator(rightframe);
    }

    private static final String searchProp="#quarterSearchInput";
  private static final String rightframe = "iframe[name='right']";
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
  private static final String i_Frame="iframe";
  private static final String ok_Button="OK";
  private static final String transaction_Type="#transType";
  private static final String trans_Mode="#mode";
  private static final String dd_Number="#sInstrumentNumber";
  private static final String dd_Branch="#sInstrumentBranch";
  private static final String dd_Remarks="#sInstrumentRemarks";
  private static final String instrument_Date="#sInstrumentDate";
  private static final String Instrument_Bank="#sInstrumentBank";
  private static final String up_Button="input[value='Update']";
  private static final String pay_Status="td.ellipsis";
  private static final String close_Pay ="button[onclick='closePayment()']";
  private static final String subm_Bttn ="div[data-metric-title='SUBMITTED']";
  private static final String ALERT_MODAL = ".ajs-modal";
  private static final String Alert_Header = ".ajs-header";
  private static final String Alert_okButton = "button.ajs-reset";
  private static final String payment_Frame="#paymentFrame";
  private static final String dp_Selected = "option:checked";

  public void thirdpartyhallRequest_EO(String Property_Value1,String tpempname_Value1,
                           String tpempMobilenumb_Value1, String tpempemail_Value1,
                           String tp_Designation, String tp_organisation,
                           String tp_specDescrption, String checkinDate,
                           String checkoutDate, String Trans_Value1,
                              String Trans_Value2, String Pay_mode,
                              String Payment_Date, String ddNum, String ddBran,
                              String ddRem, String Bank_Name, String statusPayment,
                              String ddN, String ddB, String ddRm,
                              String instru_Date)
    {
      Locator search = rightFrame.locator(searchProp);
        assertThat(search).isVisible();
        assertThat(search).isEnabled();
        search.fill(Property_Value1);

        Locator propertyCode = rightFrame.locator(propcode)
                .filter(new Locator.FilterOptions().setHasText(propcodeText));

        assertThat(propertyCode).containsText(propcodeText);

        Locator BookNowButton = rightFrame.getByRole(
                AriaRole.BUTTON,
                new FrameLocator.GetByRoleOptions().setName(booknow_Button));
        assertThat(BookNowButton).isVisible();
        assertThat(BookNowButton).isEnabled();
        BookNowButton.click();

        Locator bookingModal = rightFrame.locator(booking);
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
      checkIn.scrollIntoViewIfNeeded();
      assertThat(checkIn).isVisible();
      checkIn.click();

      Locator checkInDate = bookingModal.locator(calender)
              .getByRole(AriaRole.LINK,
                      new Locator.GetByRoleOptions()
                              .setName(checkinDate)
                              .setExact(true));

      assertThat(checkInDate).isVisible();
      checkInDate.click();

      // Check-out date
      Locator checkOut = bookingModal.locator(tp_checkOut);
      checkOut.scrollIntoViewIfNeeded();
      assertThat(checkOut).isVisible();
      checkOut.click();

      Locator checkOutDate = bookingModal.locator(calender)
              .getByRole(AriaRole.LINK,
                      new Locator.GetByRoleOptions()
                              .setName(checkoutDate)
                              .setExact(true));

      assertThat(checkOutDate).isVisible();
      checkOutDate.click();

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

      /*FrameLocator alertFrame = rightFrame.frameLocator(i_Frame);
      Locator OkButton = alertFrame.getByRole(AriaRole.BUTTON,
              new FrameLocator.GetByRoleOptions()
              .setName(ok_Button));
      assertThat(OkButton).isVisible();
      OkButton.click();*/

      Locator alertModal = rightFrame.locator(ALERT_MODAL);
      assertThat(alertModal).isVisible();
      assertThat(alertModal.locator(Alert_Header))
              .hasText("Alert");

      Locator okButton = alertModal.locator(Alert_okButton).last();
      assertThat(okButton).isVisible();
      okButton.click();

      FrameLocator paymentFrame = rightFrame.frameLocator(payment_Frame);
      Locator paymentDetails =
              paymentFrame.getByText("PAYMENT DETAILS",
                      new FrameLocator.GetByTextOptions()
                      .setExact(true));
      assertThat(paymentDetails).isVisible();

      Locator TransTypeselect=paymentFrame.locator(transaction_Type);
      TransTypeselect.selectOption(new SelectOption().setLabel(Trans_Value1));
      assertThat(TransTypeselect.locator(dp_Selected))
              .hasText(Trans_Value1);

      Locator modeSelect=paymentFrame.locator(trans_Mode);
      modeSelect.selectOption(new SelectOption().setLabel(Pay_mode));
      assertThat(modeSelect.locator(dp_Selected))
              .hasText(Pay_mode);

      Locator ddNumber=paymentFrame.locator(dd_Number);
      assertThat(ddNumber).isVisible();
      assertThat(ddNumber).isEnabled();
      ddNumber.fill(ddNum);

      Locator ddBranch=paymentFrame.locator(dd_Branch);
      assertThat(ddBranch).isVisible();
      assertThat(ddBranch).isEnabled();
      ddBranch.fill(ddBran);

      Locator ddRemarks=paymentFrame.locator(dd_Remarks);
      assertThat(ddRemarks).isVisible();
      assertThat(ddRemarks).isEnabled();
      ddRemarks.fill(ddRem);

      Locator instrumentDate = paymentFrame.locator(instrument_Date);
      assertThat(instrumentDate).isVisible();
      assertThat(instrumentDate).isEnabled();
      instrumentDate.click();
      Locator calendarDate = paymentFrame.locator(calender)
              .getByRole(
                      AriaRole.LINK,
                      new Locator.GetByRoleOptions()
                              .setName(Payment_Date)
                              .setExact(true)
              );

      assertThat(calendarDate).isVisible();
      calendarDate.click();

      Locator bank =paymentFrame.locator(Instrument_Bank);
      bank.selectOption(new SelectOption().setLabel(Bank_Name));
      assertThat(bank.locator(dp_Selected))
              .hasText(Bank_Name);

      Locator updatebtton =paymentFrame.locator(up_Button);
      assertThat(updatebtton).isVisible();
      assertThat(updatebtton).isEnabled();
      updatebtton.click();

      Locator paymentStatus = paymentFrame.locator(pay_Status)
              .filter(new Locator.FilterOptions().setHasText(statusPayment));
      assertThat(paymentStatus).hasCount(2);
      assertThat(paymentStatus.nth(0)).isVisible();
      assertThat(paymentStatus.nth(0)).hasText(statusPayment);

      TransTypeselect.selectOption(new SelectOption().setLabel(Trans_Value2));
      assertThat(TransTypeselect.locator(dp_Selected))
              .hasText(Trans_Value2);

      modeSelect.selectOption(new SelectOption().setLabel(Pay_mode));
      assertThat(modeSelect.locator(dp_Selected))
              .hasText(Pay_mode);

      assertThat(ddNumber).isVisible();
      assertThat(ddNumber).isEnabled();
      ddNumber.fill(ddN);

      assertThat(ddBranch).isVisible();
      assertThat(ddBranch).isEnabled();
      ddBranch.fill(ddB);

      assertThat(ddRemarks).isVisible();
      assertThat(ddRemarks).isEnabled();
      ddRemarks.fill(ddRm);

      assertThat(instrumentDate).isVisible();
      assertThat(instrumentDate).isEnabled();
      instrumentDate.click();

      Locator calendarDate1 = paymentFrame.locator(calender)
              .getByRole(AriaRole.LINK,
                      new Locator.GetByRoleOptions()
                              .setName(instru_Date)
                              .setExact(true));

      assertThat(calendarDate1).isVisible();
      calendarDate1.click();

      bank.selectOption(new SelectOption().setLabel(Bank_Name));
      assertThat(bank.locator(dp_Selected)).hasText(Bank_Name);

      assertThat(updatebtton).isVisible();
      assertThat(updatebtton).isEnabled();
      updatebtton.click();

      assertThat(paymentStatus.nth(1)).isVisible();
      assertThat(paymentStatus.nth(1)).hasText(statusPayment);

      Locator close_payment=paymentFrame.locator(close_Pay);
      assertThat(close_payment).isVisible();
      assertThat(close_payment).isEnabled();
      close_payment.click();

      Locator submittedCard = rightFrame.locator(subm_Bttn);
      assertThat(submittedCard).isVisible();
      assertThat(submittedCard).isEnabled();
      submittedCard.click();

      assertThat(search).isVisible();
      assertThat(search).isEnabled();
      search.fill(Property_Value1);

      assertThat(propertyCode).containsText(propcodeText);

    }
  public String GetRequestID()
  {
    Locator RequestID = rightFrame.locator("#VendorRequestId2");
    assertThat(RequestID).isVisible();
    String requestID = RequestID.innerText().trim();
    System.out.println("Request ID: " + requestID);
    return requestID;
  }
}
