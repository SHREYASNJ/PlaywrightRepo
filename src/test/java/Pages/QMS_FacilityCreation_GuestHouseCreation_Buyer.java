package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;
import org.testng.Assert;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class QMS_FacilityCreation_GuestHouseCreation_Buyer
{
    Page page;
    private static final String AddNewPropertyButton ="Add New Property";
    private static final String Header ="Create New Property";
    private static final String ModuleDP ="#module-select";
    private static final String Property_Type="#property-type-select";
    private static final String prop_Name="#property-name";
    private static final String RegionDP = "select[name='regionId']";
    private static final String Sector="e.g., Sector 2";
    private static final String state="#state-select";
    private static final String LocationPlaceholder="e.g., Near SBI bank, opp KV school";


    public QMS_FacilityCreation_GuestHouseCreation_Buyer(Page page)
    {
        this.page=page;
    }

    public void GuestHouse_AddProperty()
    {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName(AddNewPropertyButton)).click();
        Locator createPropLabel = page.getByRole(
                AriaRole.HEADING,
                new Page.GetByRoleOptions().setName(Header)
        );
        assertThat(createPropLabel).hasText(Header);
    }
    public void Create_GeneralInfo(String Module_Name, String Property_Value
            , String PropName, String Region_Name, String SectorName, String IndianState, String LocationName)
    {
        Locator Module= page.locator(ModuleDP);
        Module.selectOption(new SelectOption().setLabel(Module_Name));
        assertThat(Module.locator("option:checked"))
                .hasText(Module_Name);

        Locator propType = page.locator(Property_Type);
        propType.selectOption(new SelectOption().setLabel(Property_Value));
        assertThat(propType.locator("option:checked"))
                .hasText(Property_Value);

        Locator propertyName = page.locator(prop_Name);
        propertyName.fill(PropName);
        assertThat(propertyName).hasValue(PropName);

        Locator Region = page.locator(RegionDP);

        // Scroll until the textbox is visible
        Region.scrollIntoViewIfNeeded();
        Region.selectOption(new SelectOption().setLabel(Region_Name));
        assertThat(Region.locator("option:checked"))
                .hasText(Region_Name);

        Locator sector = page.getByPlaceholder(Sector);
        sector.scrollIntoViewIfNeeded();
        sector.fill(SectorName);
        assertThat(sector).hasValue(SectorName);

        Locator stateName = page.locator(state);
        stateName.scrollIntoViewIfNeeded();
        stateName.selectOption(new SelectOption().setLabel(IndianState));
        assertThat(stateName.locator("option:checked"))
                .hasText(IndianState);

        Locator locationName= page.getByPlaceholder(LocationPlaceholder);
        locationName.scrollIntoViewIfNeeded();
        locationName.fill(LocationName);
        assertThat(locationName).hasValue(LocationName);




    }
}
