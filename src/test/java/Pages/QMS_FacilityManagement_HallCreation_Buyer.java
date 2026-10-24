package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;
import org.testng.Assert;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class QMS_FacilityManagement_HallCreation_Buyer
{
    Page page;
    private static final String CreateProp="#AvlProperty";
    private static final String Header = "Create New Property";
    private static final String module_select="#module-select";
    private static final String Property_Type = "#property-type-select";
    private static final String prop_Name = "#property-name";
    private static final String RegionDP = "select[name='regionId']";
    private static final String Sector = "e.g., Sector 2";
    private static final String state = "#state-select";
    private static final String LocationPlaceholder = "e.g., Near SBI bank, opp KV school";
    private static final String Incharge_Name = "#hallInchargeName";
    private static final String Contact_Details = "#hallContactDetails";
    private static final String Auxiliary_Area = "#hallAuxiliaryArea";
    private static final String Description = "Provide a brief description of this property";
    private static final String save_Button = "#btnSaveDraft";
    private static final String prop_Region = "#property-region-select";
    private static final String estate = "#property-estate-select";
    private static final String physicaladdressPlaceholder = "Enter complete address with landmarks...";
    private static final String UploadphotoSelector ="#textfield10basic";
    private static final String PubProp="Publish Property";
    private static final String AvalProp="Available for booking";
    private static final String searchprop="#quarterSearchInput";
    private static final String mh_seating="e.g., 500";
    private static final String Din_Capacity="e.g., 200";
    private static final String guestRoomPlaceholder="e.g., 5";
    private static final String ac_RoomPlaceHolder="e.g., 2";
    private static final String nonac_RoomPlaceHolder="e.g., 3";
    private static final String twowheelerParking="e.g., 100";
    private static final String fourwheelerParkingPlaceholder="e.g., 50";
    private static final String kitchensizePlaceholder="e.g., 500";

    public QMS_FacilityManagement_HallCreation_Buyer()
    {
     this.page=page;
    }
    public void create_Prop()
    {
     Locator AvailableProperty=page.locator(CreateProp);
        AvailableProperty.click();
        AvailableProperty.isEnabled();
        AvailableProperty.click();

        Locator createPropLabel = page.getByRole(
                AriaRole.HEADING,
                new Page.GetByRoleOptions().setName(Header)
        );
        assertThat(createPropLabel).hasText(Header);
    }
    public void create_Hall(String Module_Name, String Property_Value1,
                            String HallName, String Region_Name,
                            String SectorName, String IndianState
            , String LocationName, String GH_InchargeName,
                            String ContactValue, String auxArea_Value
            , String DescriptionValue)
    {
        Locator GeneralInfo = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions()
                .setName("1. General Info"));
        GeneralInfo.click();

        Locator moduleSelect=page.locator(module_select);
        moduleSelect.selectOption(new SelectOption().setLabel(Module_Name));
        assertThat(moduleSelect.locator("option:checked"))
                .hasText(Module_Name);

        Locator type = page.locator(Property_Type);
        type.selectOption(new SelectOption().setLabel(Property_Value1));
        assertThat(type.locator("option:checked"))
                .hasText(Property_Value1);

        Locator propName=page.locator(prop_Name);
        propName.scrollIntoViewIfNeeded();
        propName.fill(HallName);

       Locator region= page.locator(RegionDP);
        region.selectOption(new SelectOption().setLabel(Region_Name));
        assertThat(region.locator("option:checked"))
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

        Locator locationName = page.getByPlaceholder(LocationPlaceholder);
        locationName.scrollIntoViewIfNeeded();
        locationName.fill(LocationName);
        assertThat(locationName).hasValue(LocationName);

        Locator inchargeName = page.locator(Incharge_Name);
        inchargeName.scrollIntoViewIfNeeded();
        inchargeName.fill(GH_InchargeName);
        assertThat(inchargeName).hasValue(GH_InchargeName);

        Locator Contact = page.locator(Contact_Details);
        Contact.scrollIntoViewIfNeeded();
        Contact.fill(ContactValue);
        assertThat(Contact).hasValue(ContactValue);

        Locator aux_Area = page.locator(Auxiliary_Area);
        aux_Area.scrollIntoViewIfNeeded();
        aux_Area.fill(auxArea_Value);
        assertThat(Contact).hasValue(auxArea_Value);

        Locator desc = page.getByPlaceholder(Description);
        desc.scrollIntoViewIfNeeded();
        desc.fill(DescriptionValue);
        assertThat(desc).hasValue(DescriptionValue);

        page.evaluate("window.scrollTo(0, 0)");
        Locator saveBttn = page.locator(save_Button);
        assertThat(saveBttn).isVisible();
        assertThat(saveBttn).isEnabled();
        saveBttn.click();

        page.onceDialog(dialog -> {
            Assert.assertEquals(
                    dialog.message(),
                    "Property created successfully.",
                    "Alert message validation failed"
            );
            dialog.accept();
        });
    }
    public void Create_HallLocation_Details(String PropRegion, String estate_Name,
                                        String physicaladdressValue)
    {
        page.evaluate("window.scrollTo(0, 0)");
        Locator Location_Details = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions()
                .setName("2. Location"));
        Location_Details.click();

        Locator regionSelect = page.locator(prop_Region);
        regionSelect.selectOption(new SelectOption().setLabel(PropRegion));
        assertThat(regionSelect.locator("option:checked"))
                .hasText(PropRegion);

        Locator prop_estate = page.locator(estate);
        prop_estate.selectOption(new SelectOption().setLabel(estate_Name));
        assertThat(prop_estate.locator("option:checked"))
                .hasText(estate_Name);

        Locator physicalAddress = page.getByPlaceholder(physicaladdressPlaceholder);
        physicalAddress.fill(physicaladdressValue);

        page.evaluate("window.scrollTo(0, 0)");
        Locator saveBttn = page.locator(save_Button);
        assertThat(saveBttn).isVisible();
        assertThat(saveBttn).isEnabled();
        saveBttn.click();

        page.onceDialog(dialog -> {
            Assert.assertEquals(
                    dialog.message(),
                    "Property updated successfully.",
                    "Alert message validation failed"
            );
            dialog.accept();
        });
    }
public void Create_HALL_Details(String hallseatingValue, String DininghallseatingValue,
                                String guestroomsValue, String acRoomsValue,
                                String nonacRoomsValue, String twowheelerparkingValue,
                                String fourwheelerparkingValue, String kitchensizeValue,
                                String rowText, String amount
                                )
{
    page.evaluate("window.scrollTo(0, 0)");
    Locator HallDetails = page.getByRole(AriaRole.BUTTON,
            new Page.GetByRoleOptions()
                    .setName("3. Hall Details"));
    HallDetails.click();

    Locator Main_Hall_Seating =page.getByPlaceholder(mh_seating);
    Main_Hall_Seating.clear();
    Main_Hall_Seating.fill(hallseatingValue);
    assertThat(Main_Hall_Seating).hasValue(hallseatingValue);

    Locator Dining_Hall_Seating =page.getByPlaceholder(Din_Capacity);
    Dining_Hall_Seating.clear();
    Dining_Hall_Seating.fill(DininghallseatingValue);
    assertThat(Dining_Hall_Seating).hasValue(DininghallseatingValue);

    Locator guestRoom =page.getByPlaceholder(guestRoomPlaceholder);
    guestRoom.clear();
    guestRoom.fill(guestroomsValue);
    assertThat(guestRoom).hasValue(guestroomsValue);

    Locator acRoom =page.getByPlaceholder(ac_RoomPlaceHolder);
    acRoom.clear();
    acRoom.fill(acRoomsValue);
    assertThat(acRoom).hasValue(acRoomsValue);

    Locator nonacRoom =page.getByPlaceholder(nonac_RoomPlaceHolder);
    nonacRoom.clear();
    nonacRoom.fill(nonacRoomsValue);
    assertThat(nonacRoom).hasValue(nonacRoomsValue);

    Locator two_wheeler_parking =page.getByPlaceholder(twowheelerParking);
    two_wheeler_parking.clear();
    two_wheeler_parking.fill(twowheelerparkingValue);
    assertThat(two_wheeler_parking).hasValue(twowheelerparkingValue);

    Locator four_wheeler_parking =page.getByPlaceholder(fourwheelerParkingPlaceholder);
    four_wheeler_parking.scrollIntoViewIfNeeded();
    four_wheeler_parking.clear();
    four_wheeler_parking.fill(fourwheelerparkingValue);
    assertThat(four_wheeler_parking).hasValue(fourwheelerparkingValue);

    Locator kitchensize_parking =page.getByPlaceholder(kitchensizePlaceholder);
    kitchensize_parking.scrollIntoViewIfNeeded();
    kitchensize_parking.clear();
    kitchensize_parking.fill(kitchensizeValue);
    assertThat(kitchensize_parking).hasValue(kitchensizeValue);

    //Below can also be used to enter amount
    //Locator AmountField = page.getByRole(AriaRole.SPINBUTTON,new Page.GetByRoleOptions().setName("Enter Amount"));
    //AmountField.nth(0).fill("10");
    //AmountField.nth(1).fill("20");

    fill_amount("Base Hall Rent (NMDC Employee)","10");
    fill_amount("Base Hall Rent (Non-NMDC Employee)","20");
    fill_amount("Music Charges (NMDC Employee)","10");
    fill_amount("Music Charges (Non-NMDC Employee)","20");
    fill_amount("Hall Rent (NMDC Employee)","10");
    fill_amount("Hall Rent (Non-NMDC Employee)","20");
    fill_amount("Water Utility Fee","10");
    fill_amount("Electricity Charges","10");
    fill_amount("Security Deposit (NMDC Employee)","100");
    fill_amount("Security Deposit (Non-NMDC Employee)","150");
    fill_amount("Lighting Charges","10");
    fill_amount("Gas Charges","10");
    fill_amount("Damage Recovery Policy","10");
    fill_amount("Operating Time & Noise Policy","10");
    fill_amount("Cleaning Charges","10");
    fill_amount("GST Applicable %","18");

    Locator musicSystem = page.getByRole(
            AriaRole.CHECKBOX,
            new Page.GetByRoleOptions().setName("Music System")
    );
    musicSystem.scrollIntoViewIfNeeded();
    musicSystem.check();

    Locator waterSupply = page.getByRole(
            AriaRole.CHECKBOX,
            new Page.GetByRoleOptions().setName("Water Supply")
    );
    waterSupply.scrollIntoViewIfNeeded();
    waterSupply.check();

}

public void fill_amount(String rowText, String amount)
{
    Locator row = page.locator("tr")
            .filter(new Locator.FilterOptions().setHasText(rowText));

    // Verify that exactly one row matches
    assertThat(row).hasCount(1);

    Locator amountField = row.getByRole(
            AriaRole.SPINBUTTON,
            new Locator.GetByRoleOptions().setName("Enter Amount")
    );

    // Verify that exactly one amount field exists in the row
    assertThat(amountField).hasCount(1);

    amountField.scrollIntoViewIfNeeded();
    amountField.fill(amount);
}
    public void Upload_Images()
    {
        page.evaluate("window.scrollTo(0, 0)");
        Locator Images = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName("4. Images"));
        Images.click();

        Locator uploadPhotos = page.locator(UploadphotoSelector);

        uploadPhotos.setInputFiles(new java.nio.file.Path[] {
                Paths.get("C:\\Users\\shreyasn\\Downloads\\HOUSE\\PROPERTYPHOTO1.jpg"),
                Paths.get("C:\\Users\\shreyasn\\Downloads\\HOUSE\\PROPERTYPHOTO2.jpg"),
        });

        page.evaluate("window.scrollTo(0,0)");
        Locator saveBttn = page.locator(save_Button);
        assertThat(saveBttn).isVisible();
        assertThat(saveBttn).isEnabled();
        saveBttn.click();

        page.onceDialog(dialog -> {
            Assert.assertEquals(
                    dialog.message(),
                    "Property updated successfully.",
                    "Alert message validation failed"
            );
            dialog.accept();
        });
    }
    public void Publish_Property(String HallName)
    {
        page.evaluate("window.scrollTo(0,0)");
        Locator Publish_Button = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName(PubProp));
        assertThat(Publish_Button).isVisible();
        assertThat(Publish_Button).isEnabled();
        Publish_Button.click();

        //Locator Publish_Button = page.locator("#btnPublish");
        //Publish_Button.click();

        Locator search=page.locator(searchprop);
        search.fill(HallName);

        Locator PropAvailablelabelname=page.getByText(AvalProp);
        assertThat(PropAvailablelabelname).isVisible();
    }
}
