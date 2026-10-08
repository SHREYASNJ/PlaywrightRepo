package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class QMS_FacilityCreation_GuestHouseCreation_Buyer
{
    Page page;
    private static final String AddNewPropertyButton ="Add New Property";
    private static final String Header ="Create New Property";

    public QMS_FacilityCreation_GuestHouseCreation_Buyer(Page page)
    {
        this.page=page;
    }

    public void GuestHousecreation()
    {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName(AddNewPropertyButton)).click();
        Locator createPropLabel = page.getByRole(
                AriaRole.HEADING,
                new Page.GetByRoleOptions().setName(Header)
        );
        assertThat(createPropLabel).hasText(Header);

    }
}
