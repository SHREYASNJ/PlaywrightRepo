package Pages;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;
import org.testng.Assert;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
public class QMS_FacilityManagement_HallCreation_Buyer
{
    Page page;
    FrameLocator rightFrame;
    QMS_Faclitiy_Alerts alertsHandle;
    // Configuration properties
    private final Properties properties = new Properties();
    // Image paths
    private Path photo1;
    private Path photo2;

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
    private static final String rightframe = "iframe[name='right']";
    private static final String genInfo_Tab = "1. General Info";
    private static final String dp_Selected = "option:checked";
    private static final String location_Tab = "2. Location";
    private static final String hallDetailsTab="3. Hall Details";
    private static final String images_Tab="4. Images";
    public static final String availablepropertiesCardText="AVAILABLE PROPERTIES";


    public QMS_FacilityManagement_HallCreation_Buyer(Page page)
    {
     this.page=page;
     this.rightFrame = page.frameLocator(rightframe);
     this.alertsHandle=new QMS_Faclitiy_Alerts(page);
        loadConfig();
    }

    public void create_Prop()
    {
     Locator AvailableProperty=rightFrame.locator(CreateProp);
        assertThat(AvailableProperty).isVisible();
        assertThat(AvailableProperty).isEnabled();
        AvailableProperty.click();

        Locator createPropLabel = rightFrame.getByRole(AriaRole.HEADING,
                new FrameLocator.GetByRoleOptions().setName(Header));
        assertThat(createPropLabel).hasText(Header);
    }
    private void loadConfig()
    {
        try (InputStream input = getClass()
                .getClassLoader()
                .getResourceAsStream("config.properties"))
        {
            if (input == null) {
                throw new RuntimeException("config.properties not found");
            }
            properties.load(input);
            String imageDirectory =
                    properties.getProperty("image.directory");
            if (imageDirectory == null || imageDirectory.isBlank()) {
                throw new RuntimeException("image.directory is missing");
            }
            photo1 = Paths.get(imageDirectory, "PROPERTYPHOTO1.jpg");
            photo2 = Paths.get(imageDirectory, "PROPERTYPHOTO2.jpg");
            // Verify image files exist
            if (!Files.isRegularFile(photo1)) {
                throw new RuntimeException("Image not found: " + photo1);
            }
            if (!Files.isRegularFile(photo2)) {
                throw new RuntimeException("Image not found: " + photo2);
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to load configuration", e);
        }
    }

    public void create_Hall(String Module_Name, String Property_Value1,
                            String HallName, String Region_Name,
                            String SectorName, String IndianState
            , String LocationName, String GH_InchargeName,
                            String ContactValue, String auxArea_Value
            , String DescriptionValue, String PropAlert)
    {
        Locator GeneralInfo = rightFrame.getByRole(AriaRole.BUTTON,
                new FrameLocator.GetByRoleOptions()
                .setName(genInfo_Tab));
        assertThat(GeneralInfo).isVisible();
        assertThat(GeneralInfo).isEnabled();
        GeneralInfo.click();

        Locator moduleSelect=rightFrame.locator(module_select).nth(0);
        moduleSelect.selectOption(new SelectOption().setLabel(Module_Name));
        assertThat(moduleSelect.locator(dp_Selected))
                .hasText(Module_Name);

        Locator type = rightFrame.locator(Property_Type).nth(0);
        type.selectOption(new SelectOption().setLabel(Property_Value1));
        assertThat(type.locator(dp_Selected))
                .hasText(Property_Value1);

        Locator propName=rightFrame.locator(prop_Name).nth(0);
        propName.scrollIntoViewIfNeeded();
        propName.fill(HallName);

        Locator region= rightFrame.locator(RegionDP);
        region.selectOption(new SelectOption().setLabel(Region_Name));
        assertThat(region.locator(dp_Selected))
                .hasText(Region_Name);

        Locator sector = rightFrame.getByPlaceholder(Sector);
        sector.scrollIntoViewIfNeeded();
        sector.fill(SectorName);
        assertThat(sector).hasValue(SectorName);

        Locator stateName = rightFrame.locator(state).nth(0);
        stateName.scrollIntoViewIfNeeded();
        stateName.selectOption(new SelectOption().setLabel(IndianState));
        assertThat(stateName.locator(dp_Selected))
                .hasText(IndianState);

        Locator locationName = rightFrame.getByPlaceholder(LocationPlaceholder);
        locationName.scrollIntoViewIfNeeded();
        locationName.fill(LocationName);
        assertThat(locationName).hasValue(LocationName);

        Locator inchargeName = rightFrame.locator(Incharge_Name);
        inchargeName.scrollIntoViewIfNeeded();
        inchargeName.fill(GH_InchargeName);
        assertThat(inchargeName).hasValue(GH_InchargeName);

        Locator Contact = rightFrame.locator(Contact_Details);
        Contact.scrollIntoViewIfNeeded();
        Contact.fill(ContactValue);
        assertThat(Contact).hasValue(ContactValue);

        Locator aux_Area = rightFrame.locator(Auxiliary_Area);
        aux_Area.scrollIntoViewIfNeeded();
        aux_Area.fill(auxArea_Value);
        assertThat(aux_Area).hasValue(auxArea_Value);

        Locator desc = rightFrame.getByPlaceholder(Description);
        desc.scrollIntoViewIfNeeded();
        desc.fill(DescriptionValue);
        assertThat(desc).hasValue(DescriptionValue);

        // Scroll to the top of the iframe
        rightFrame.locator("body")
                .evaluate("element => element.scrollTo(0, 0)");
        Locator saveBttn = rightFrame.locator(save_Button).nth(0);
        assertThat(saveBttn).isVisible();
        assertThat(saveBttn).isEnabled();
        saveBttn.click();

        alertsHandle.alert(PropAlert);
    }

    public void Create_HallLocation_Details(String PropRegion, String estate_Name,
                                        String physicaladdressValue, String PropAlert)
    {
        rightFrame.locator("body")
                .evaluate("element => element.scrollTo(0, 0)");
        Locator Location_Details = rightFrame.getByRole(AriaRole.BUTTON,
                new FrameLocator.GetByRoleOptions()
                .setName(location_Tab));
        Location_Details.click();

        Locator regionSelect = rightFrame.locator(prop_Region).nth(0);
        regionSelect.selectOption(new SelectOption().setLabel(PropRegion));
        assertThat(regionSelect.locator(dp_Selected))
                .hasText(PropRegion);

        Locator prop_estate = rightFrame.locator(estate);
        prop_estate.selectOption(new SelectOption().setLabel(estate_Name));
        assertThat(prop_estate.locator(dp_Selected))
                .hasText(estate_Name);

        Locator physicalAddress = rightFrame.getByPlaceholder(physicaladdressPlaceholder);
        physicalAddress.fill(physicaladdressValue);

        rightFrame.locator("body")
                .evaluate("element => element.scrollTo(0, 0)");
        Locator saveBttn = rightFrame.locator(save_Button);
        assertThat(saveBttn).isVisible();
        assertThat(saveBttn).isEnabled();
        saveBttn.click();

        alertsHandle.alert(PropAlert);
    }
public void Create_HALL_Details(String hallseatingValue, String DininghallseatingValue,
                                String guestroomsValue, String acRoomsValue,
                                String nonacRoomsValue, String twowheelerparkingValue,
                                String fourwheelerparkingValue, String kitchensizeValue,
                                String rowText, String amount, String PropAlert,
                                String availableFacility1,
                                String availableFacility2)
{
    rightFrame.locator("body")
            .evaluate("element => element.scrollTo(0, 0)");
    Locator HallDetails = rightFrame.getByRole(AriaRole.BUTTON,
            new FrameLocator.GetByRoleOptions()
                    .setName(hallDetailsTab));
    HallDetails.click();

    Locator Main_Hall_Seating =rightFrame.getByPlaceholder(mh_seating)
            .nth(1);
    Main_Hall_Seating.clear();
    Main_Hall_Seating.fill(hallseatingValue);
    assertThat(Main_Hall_Seating).hasValue(hallseatingValue);

    Locator Dining_Hall_Seating =rightFrame.getByPlaceholder(Din_Capacity);
    Dining_Hall_Seating.clear();
    Dining_Hall_Seating.fill(DininghallseatingValue);
    assertThat(Dining_Hall_Seating).hasValue(DininghallseatingValue);

    Locator guestRoom =rightFrame.getByPlaceholder(guestRoomPlaceholder)
            .nth(2);
    guestRoom.clear();
    guestRoom.fill(guestroomsValue);
    assertThat(guestRoom).hasValue(guestroomsValue);

    Locator acRoom =rightFrame.getByPlaceholder(ac_RoomPlaceHolder).nth(1);
    acRoom.clear();
    acRoom.fill(acRoomsValue);
    assertThat(acRoom).hasValue(acRoomsValue);

    Locator nonacRoom =rightFrame.getByPlaceholder(nonac_RoomPlaceHolder).nth(0);
    nonacRoom.clear();
    nonacRoom.fill(nonacRoomsValue);
    assertThat(nonacRoom).hasValue(nonacRoomsValue);

    Locator two_wheeler_parking =rightFrame.getByPlaceholder(twowheelerParking)
            .nth(0);
    two_wheeler_parking.clear();
    two_wheeler_parking.fill(twowheelerparkingValue);
    assertThat(two_wheeler_parking).hasValue(twowheelerparkingValue);

    Locator four_wheeler_parking =rightFrame
            .getByPlaceholder(fourwheelerParkingPlaceholder).nth(2);
    four_wheeler_parking.scrollIntoViewIfNeeded();
    four_wheeler_parking.clear();
    four_wheeler_parking.fill(fourwheelerparkingValue);
    assertThat(four_wheeler_parking).hasValue(fourwheelerparkingValue);

    Locator kitchensize_parking =rightFrame
            .getByPlaceholder(kitchensizePlaceholder).nth(2);
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

    Locator musicSystem = rightFrame.getByRole(
            AriaRole.CHECKBOX,
            new FrameLocator.GetByRoleOptions().setName(availableFacility1)
    );
    musicSystem.scrollIntoViewIfNeeded();
    musicSystem.check();
    assertThat(musicSystem).isChecked();

    Locator waterSupply = rightFrame.getByRole(
            AriaRole.CHECKBOX,
            new FrameLocator.GetByRoleOptions().setName(availableFacility2)
    );
    waterSupply.scrollIntoViewIfNeeded();
    waterSupply.check();
    assertThat(waterSupply).isChecked();

    rightFrame.locator("body")
            .evaluate("element => element.scrollTo(0, 0)");
    Locator saveBttn = rightFrame.locator(save_Button);
    assertThat(saveBttn).isVisible();
    assertThat(saveBttn).isEnabled();
    saveBttn.click();

    alertsHandle.alert(PropAlert);

}

    public void fill_amount(String rowText, String amount)
    {
        // Locate the row containing the charge name
        Locator row = rightFrame.locator("tr")
                .filter(new Locator.FilterOptions().setHasText(rowText));
        // Verify exactly one row matches
        assertThat(row).hasCount(1);
        // Locate the amount input inside that row
        Locator amountField = row.getByPlaceholder("Enter Amount");
        // Verify exactly one amount field exists
        assertThat(amountField).hasCount(1);
        amountField.scrollIntoViewIfNeeded();
        amountField.fill(amount);
        // Verify the entered amount
        assertThat(amountField).hasValue(amount);
    }
    public void Upload_Images(String PropAlert)
    {
        rightFrame.locator("body")
                .evaluate("element => element.scrollTo(0, 0)");
        Locator Images = rightFrame.getByRole(
                AriaRole.BUTTON,
                new FrameLocator.GetByRoleOptions()
                        .setName(images_Tab)
        );
        Images.click();
        Locator uploadPhotos = rightFrame.locator(UploadphotoSelector);
        // Upload both images
        uploadPhotos.setInputFiles(new Path[]{
                photo1,
                photo2
        });
        // Save
        rightFrame.locator("body")
                .evaluate("element => element.scrollTo(0, 0)");
        Locator saveBttn = rightFrame.locator(save_Button);
        assertThat(saveBttn).isVisible();
        assertThat(saveBttn).isEnabled();
        saveBttn.click();
        alertsHandle.alert(PropAlert);
    }
    public void Publish_Property(String HallName)
    {
        rightFrame.locator("body")
                .evaluate("element => element.scrollTo(0, 0)");
        Locator Publish_Button = rightFrame.getByRole(AriaRole.BUTTON,
                new FrameLocator.GetByRoleOptions()
                .setName(PubProp));
        assertThat(Publish_Button).hasCount(2);
        assertThat(Publish_Button.nth(1)).isVisible();
        assertThat(Publish_Button.nth(1)).isEnabled();
        Publish_Button.nth(1).click();

        Locator availPropCard = rightFrame.getByText(availablepropertiesCardText,
                new FrameLocator.GetByTextOptions().setExact(true));
        assertThat(availPropCard).isVisible();
        availPropCard.click();

        Locator search=rightFrame.locator(searchprop);
        assertThat(search).isVisible();
        assertThat(search).isEnabled();
        search.fill(HallName);

        Locator hallResult = rightFrame.getByText(HallName,
                new FrameLocator.GetByTextOptions().setExact(true));
        assertThat(hallResult).isVisible();
    }
}
