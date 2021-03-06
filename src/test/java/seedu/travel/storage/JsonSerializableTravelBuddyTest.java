package seedu.travel.storage;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.travel.commons.exceptions.IllegalValueException;
import seedu.travel.commons.util.JsonUtil;

public class JsonSerializableTravelBuddyTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableTravelBuddyTest");
    private static final Path TYPICAL_PLACES_FILE = TEST_DATA_FOLDER.resolve("typicalPlacesTravelBuddy.json");
    private static final Path INVALID_PLACE_FILE = TEST_DATA_FOLDER.resolve("invalidPlaceTravelBuddy.json");
    private static final Path DUPLICATE_PLACE_FILE = TEST_DATA_FOLDER.resolve("duplicatePlaceTravelBuddy.json");

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /*
    @Test
    public void toModelType_typicalPlacesFile_success() throws Exception {
        JsonSerializableTravelBuddy dataFromFile = JsonUtil.readJsonFile(TYPICAL_PLACES_FILE,
                JsonSerializableTravelBuddy.class).get();


        TravelBuddy travelBuddyFromFile = dataFromFile.toModelType();
        TravelBuddy typicalPlacesTravelBuddy = TypicalPlaces.getTypicalTravelBuddy();

        assertEquals(travelBuddyFromFile, typicalPlacesTravelBuddy);
    }
    */

    @Test
    public void toModelType_invalidPlaceFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTravelBuddy dataFromFile = JsonUtil.readJsonFile(INVALID_PLACE_FILE,
                JsonSerializableTravelBuddy.class).get();
        thrown.expect(IllegalValueException.class);
        dataFromFile.toModelType();
    }

    @Test
    public void toModelType_duplicatePlaces_throwsIllegalValueException() throws Exception {
        JsonSerializableTravelBuddy dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PLACE_FILE,
                JsonSerializableTravelBuddy.class).get();
        thrown.expect(IllegalValueException.class);
        dataFromFile.toModelType();
    }

}
