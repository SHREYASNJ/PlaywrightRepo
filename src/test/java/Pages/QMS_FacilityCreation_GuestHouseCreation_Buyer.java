package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;
import org.testng.Assert;
import com.microsoft.playwright.Dialog;
import org.testng.Assert;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class QMS_FacilityCreation_GuestHouseCreation_Buyer {
    Page page;
    private static final String AddNewPropertyButton = "Add New Property";
    private static final String Header = "Create New Property";
    private static final String ModuleDP = "#module-select";
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
    private static final String add_Block_Button ="#btn-add-block";
    private static final String blocknamePlaceholder ="e.g., Main Block";
    private static final String blockcodePlaceholder ="e.g., BLK-A";
    private static final String Floors ="input[name='blockFloors_0']";
    private static final String BlockName1 = "select[name='roomBlockId_0']";
    private static final String OptionValue ="option";
    private static final String floorValue ="select[name='roomFloor_0']";
    private static final String Room_Numbers="e.g., 101";
    private static final String rmType="select[name='roomTypeId_0']";
    private static final String rmPrice="select[name='roomPrice_0']";
    private static final String bedCount ="input[name='bedCount_0']";
    private static final String Bed_Type="#bed-type-select";
    private static final String Room_View="button[data-pill-value='courtyard_view']";
    private static final String Policies="button[data-pill-value='smoking_allowed']";
    private static final String Quick_Features="button[data-feature='balcony']";
    private static final String UploadphotoSelector ="#textfield10basic";
    private static final String amenityID="input[name='amenityIds']";
    private static final String PubProp="Publish Property";
    private static final String AvalProp="Available for booking";
    private static final String searchprop="#quarterSearchInput";

    public QMS_FacilityCreation_GuestHouseCreation_Buyer(Page page) {
        this.page = page;
    }

    public void GuestHouse_AddProperty() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName(AddNewPropertyButton)).click();
        Locator createPropLabel = page.getByRole(
                AriaRole.HEADING,
                new Page.GetByRoleOptions().setName(Header)
        );
        assertThat(createPropLabel).hasText(Header);
    }

    public void Create_GeneralInfo(String Module_Name, String Property_Value
            , String PropName, String Region_Name, String SectorName, String IndianState
            , String LocationName, String GH_InchargeName, String ContactValue, String auxArea_Value
            , String DescriptionValue)
    {
        Locator GeneralInfo = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName("1. General Info"));
        GeneralInfo.click();

        Locator Module = page.locator(ModuleDP);
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

    public void Create_Location_Details(String PropRegion, String estate_Name,
                                        String physicaladdressValue)
    {
        page.evaluate("window.scrollTo(0, 0)");
        Locator Location_Details = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
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

    public void Create_Blocks_Floors_Structure(String Block, String Code
            , String Number_of_Floors)
    {
        page.evaluate("window.scrollTo(0, 0)");
        Locator Blocks_Floors = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName("3. Blocks & Floors"));
        Blocks_Floors.click();

     Locator addBlock=page.locator(add_Block_Button);
        addBlock.click();

        Locator blockName=page.getByPlaceholder(blocknamePlaceholder);
        blockName.fill(Block);
        assertThat(blockName).hasValue(Block);

        Locator blockCode=page.getByPlaceholder(blockcodePlaceholder);
        blockCode.fill(Code);
        assertThat(blockCode).hasValue(Code);

        Locator Numberof_Floors= page.locator(Floors);
        Numberof_Floors.fill(Number_of_Floors);
        assertThat(Numberof_Floors).hasValue(Number_of_Floors);

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
    public void Create_Rooms(String Block, String Code
            , String Number_of_Floors, String room_Number, String room_Type, String room_Price,
                             String Bed_Value)
    {
        page.evaluate("window.scrollTo(0, 0)");
        Locator Rooms_Select = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName("4. Rooms"));
        Rooms_Select.click();

        Locator BlockName= page.locator(BlockName1);
        String blockValue = BlockName.locator(OptionValue)
                .nth(1)
                .textContent()
                .trim();
        BlockName.selectOption(new SelectOption().setLabel(blockValue));
        assertThat(BlockName).hasValue(blockValue);

        Locator floorName=page.locator(floorValue);
        String floorValue = floorName.locator(OptionValue)
                .nth(1)
                .textContent()
                .trim();
        floorName.selectOption(new SelectOption().setLabel(floorValue));
        assertThat(floorName).hasValue(floorValue);

       Locator roomNumber= page.getByPlaceholder(Room_Numbers);
        roomNumber.fill(room_Number);
        assertThat(roomNumber).hasValue(room_Number);

        Locator type= page.locator(rmType);
        type.selectOption(new SelectOption().setLabel(room_Type));
        assertThat(type.locator("option:checked"))
                .hasText(room_Type);

        Locator roomPrice=page.locator(rmPrice);
        roomPrice.fill(room_Price);
        assertThat(roomPrice).hasValue(room_Price);

        Locator bed_Count=page.locator(bedCount);
        bed_Count.scrollIntoViewIfNeeded();
        bed_Count.fill(Bed_Value);
        assertThat(bed_Count).hasValue(Bed_Value);

        Locator bedType = page.locator(Bed_Type);
        bedType.selectOption(new SelectOption().setLabel("Double"));
        assertThat(bedType.locator("option:checked"))
                .hasText("Double");

        Locator courtyardView = page.locator(Room_View).first();
        courtyardView.scrollIntoViewIfNeeded();
        courtyardView.click();

        Locator Smoking = page.locator(Policies).first();
        Smoking.scrollIntoViewIfNeeded();
        Smoking.click();

        Locator quickFeature = page.locator(Quick_Features).first();
        quickFeature.scrollIntoViewIfNeeded();
        quickFeature.click();

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

    public void Upload_Images()
    {
        page.evaluate("window.scrollTo(0, 0)");
        Locator Images = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName("5. Images"));
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

    public void Create_pricing()
    {
        page.evaluate("window.scrollTo(0, 0)");
        Locator Pricing = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName("6. Pricing"));
        Pricing.click();

       Locator Property_Amenities = page.locator(amenityID);
        Property_Amenities.first().scrollIntoViewIfNeeded();

        for (int i = 0; i < Property_Amenities.count(); i++)
        {
            Property_Amenities.nth(i).check();
        }
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
    public void Publish_Property(String PropName)
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
        search.fill(PropName);

        Locator PropAvailablelabelname=page.getByText(AvalProp);
        assertThat(PropAvailablelabelname).isVisible();
    }
    }