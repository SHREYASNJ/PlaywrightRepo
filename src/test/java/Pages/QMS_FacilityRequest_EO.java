package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class QMS_FacilityRequest_EO
{
    Page page;
    public QMS_FacilityRequest_EO(Page page)
    {
        this.page=page;
    }

    public void hall_Request(String Property_Value1)
    {
      Locator search = page.locator("#quarterSearchInput");
        assertThat(search).isVisible();
        assertThat(search).isEnabled();
        search.fill(Property_Value1);

        Locator propertyCode = page.locator("span")
                .filter(new Locator.FilterOptions().setHasText("Property Code"));

        assertThat(propertyCode).containsText("Property Code");

        Locator BookNowButton = page.getByRole(
                AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Book Now")
        );
        assertThat(BookNowButton).isVisible();
        assertThat(BookNowButton).isEnabled();
        BookNowButton.click();

        Locator bookingModal = page.locator("#bookingdiv");
        assertThat(bookingModal).isVisible();
        assertThat(
                bookingModal.getByText("Book This Property",
                        new Locator.GetByTextOptions().setExact(true))
        ).isVisible();


        Locator selfButton = bookingModal.getByText(
                "Third Party",
                new Locator.GetByTextOptions().setExact(true)
        );
        assertThat(selfButton).isVisible();
        selfButton.click();

        Locator fullName = bookingModal.locator("#EmployeenameThirdParty");
        fullName.fill("SHREYAS NJ");

        Locator mobileNumber =bookingModal.locator("#mobilenumberThirdParty");
        mobileNumber.fill("7406719001");

        Locator emailID =bookingModal.locator("#EmailThirdParty");
        emailID.fill("shreyas.n@antaressystems.com");

        Locator designation =bookingModal.locator("#designationThirdParty");
        designation.fill("SENIOR TEST ENGINEER");

        Locator organisation =bookingModal.locator("#departmentThirdParty");
        organisation.fill("ANTARES");

        Locator specialDescr = bookingModal.locator("#specialdescriptionThirdParty");
        specialDescr.fill("TESTING");







    }

}
