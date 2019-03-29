package seedu.travel.ui.testutil;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import guitests.guihandles.PlaceCardHandle;
import guitests.guihandles.PlaceListPanelHandle;
import guitests.guihandles.ResultDisplayHandle;
import seedu.travel.model.place.Place;

/**
 * A set of assertion methods useful for writing GUI tests.
 */
public class GuiTestAssert {
    private static final String LABEL_DEFAULT_STYLE = "label";

    /**
     * Asserts that {@code actualCard} displays the same values as {@code expectedCard}.
     */
    public static void assertCardEquals(PlaceCardHandle expectedCard, PlaceCardHandle actualCard) {
        assertEquals(expectedCard.getId(), actualCard.getId());
        assertEquals(expectedCard.getAddress(), actualCard.getAddress());
        assertEquals(expectedCard.getCountryCode(), actualCard.getCountryCode());
        assertEquals(expectedCard.getDescription(), actualCard.getDescription());
        assertEquals(expectedCard.getName(), actualCard.getName());
        assertEquals(expectedCard.getRating(), actualCard.getRating());
        assertEquals(expectedCard.getTags(), actualCard.getTags());
        expectedCard.getTags().forEach(tag ->
            assertEquals(expectedCard.getTagStyleClasses(tag), actualCard.getTagStyleClasses(tag)));
    }

    /**
     * Asserts that {@code actualCard} displays the details of {@code expectedPlace}.
     */
    public static void assertCardDisplaysPlace(Place expectedPlace, PlaceCardHandle actualCard) {
        assertEquals(expectedPlace.getName().fullName, actualCard.getName());
        assertEquals(expectedPlace.getCountryCode().code, actualCard.getCountryCode());
        assertEquals(expectedPlace.getRating().value, actualCard.getRating());
        assertEquals(expectedPlace.getDescription().value, actualCard.getDescription());
        assertEquals(expectedPlace.getAddress().value, actualCard.getAddress());
        assertTagsEqual(expectedPlace, actualCard);
    }

    /**
     * Returns the color style for {@code tagName}'s label. The tag's color is determined by looking up the color
     * in {@code PlaceCard#TAG_COLOR_STYLES}, using an index generated by the hash code of the tag's content.
     *
     * @param tagName Name of the tag.
     */
    private static String getTagColorStyleFor(String tagName) {
        switch (tagName) {
        case "shoppingMall":
            return "teal";

        case "zoo":
        case "cemetery":
        case "heritageSite":
            return "orange";

        case "amusementPark":
        case "animals":
            return "brown";

        case "temple":
        case "mrt":
            return "grey";

        case "placeOfInterest":
        case "recreation":
            return "green";

        case "airport":
            return "salmon";

        case "school":
            return "black";

        case "eastWestLine":
            return "yellow";

        default:
            throw new AssertionError(tagName + " does not have a color assigned.");
        }
    }

    /**
     * Asserts that the tags in {@code actualCard} matches all the tags in {@code expectedPlace} with the correct
     * color.
     */
    private static void assertTagsEqual(Place expectedPlace, PlaceCardHandle actualCard) {
        List<String> expectedTags = expectedPlace.getTags().stream()
            .map(tag -> tag.tagName).collect(Collectors.toList());
        assertEquals(expectedTags, actualCard.getTags());
        expectedTags.forEach(tag ->
            assertEquals(Arrays.asList(LABEL_DEFAULT_STYLE, getTagColorStyleFor(tag)),
                actualCard.getTagStyleClasses(tag)));
    }

    /**
     * Asserts that the list in {@code placeListPanelHandle} displays the details of {@code places} correctly and
     * in the correct order.
     */
    public static void assertListMatching(PlaceListPanelHandle placeListPanelHandle, Place... places) {
        for (int i = 0; i < places.length; i++) {
            placeListPanelHandle.navigateToCard(i);
            assertCardDisplaysPlace(places[i], placeListPanelHandle.getPlaceCardHandle(i));
        }
    }

    /**
     * Asserts that the list in {@code placeListPanelHandle} displays the details of {@code places} correctly and
     * in the correct order.
     */
    public static void assertListMatching(PlaceListPanelHandle placeListPanelHandle, List<Place> places) {
        assertListMatching(placeListPanelHandle, places.toArray(new Place[0]));
    }

    /**
     * Asserts the size of the list in {@code placeListPanelHandle} equals to {@code size}.
     */
    public static void assertListSize(PlaceListPanelHandle placeListPanelHandle, int size) {
        int numberOfPeople = placeListPanelHandle.getListSize();
        assertEquals(size, numberOfPeople);
    }

    /**
     * Asserts the message shown in {@code resultDisplayHandle} equals to {@code expected}.
     */
    public static void assertResultMessage(ResultDisplayHandle resultDisplayHandle, String expected) {
        assertEquals(expected, resultDisplayHandle.getText());
    }
}