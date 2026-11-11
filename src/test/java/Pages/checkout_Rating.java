
package Pages;

import com.microsoft.playwright.Locator;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class checkout_Rating {

    public void selectRating(Locator bookingModal,
                             String ratingAttribute,
                             String ratingValue) {

        Locator ratingRow = bookingModal.locator(
                ".rating-row[data-attribute='" + ratingAttribute + "']"
        );

        assertThat(ratingRow).isVisible();

        Locator ratingStar = ratingRow.locator(
                "span[data-v='" + ratingValue + "']"
        );

        assertThat(ratingStar).isVisible();

        ratingStar.click();

        // Validate selected rating
        assertThat(ratingRow)
                .hasAttribute("data-score", String.valueOf(ratingValue));
    }
}