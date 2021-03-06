package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalProperties.BURGHLEY_DRIVE;
import static seedu.address.testutil.TypicalProperties.MAYFAIR;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PropertyBuilder;

public class PropertyTest {

    @Test
    public void isSameProperty() {
        // same object -> returns true
        assertTrue(MAYFAIR.isSameProperty(MAYFAIR));

        // null -> returns false
        assertFalse(MAYFAIR.isSameProperty(null));

        // same address and postal code, all other attributes different -> returns true
        Property editedMayfair = new PropertyBuilder(MAYFAIR).withName("Mayfair 2").withType("Landed")
                .withDeadline(LocalDate.parse("2021-01-01")).build();
        assertTrue(MAYFAIR.isSameProperty(editedMayfair));

        // different address, all other attributes same -> returns false
        editedMayfair = new PropertyBuilder(MAYFAIR).withAddress("100 Jurong East Street 32, #01-01").build();
        assertFalse(MAYFAIR.isSameProperty(editedMayfair));

        // different postal, all other attributes same -> returns false
        editedMayfair = new PropertyBuilder(MAYFAIR).withPostal("731784").build();
        assertFalse(MAYFAIR.isSameProperty(editedMayfair));

        // address has trailing spaces, all other attributes same -> returns false
        String addressWithTrailingSpaces = "1 Jurong East Street 32, #08-111" + " ";
        editedMayfair = new PropertyBuilder(MAYFAIR).withAddress(addressWithTrailingSpaces).build();
        assertFalse(MAYFAIR.isSameProperty(editedMayfair));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Property mayfairCopy = new PropertyBuilder(MAYFAIR).build();
        assertTrue(MAYFAIR.equals(mayfairCopy));

        // same object -> returns true
        assertTrue(MAYFAIR.equals(MAYFAIR));

        // null -> returns false
        assertFalse(MAYFAIR.equals(null));

        // different type -> returns false
        assertFalse(MAYFAIR.equals(5));

        // different person -> returns false
        assertFalse(MAYFAIR.equals(BURGHLEY_DRIVE));

        // different name -> returns false
        Property editedMayfair = new PropertyBuilder(MAYFAIR).withName("Burghley Drive").build();
        assertFalse(MAYFAIR.equals(editedMayfair));

        // different type -> returns false
        editedMayfair = new PropertyBuilder(MAYFAIR).withType("Hdb").build();
        assertFalse(MAYFAIR.equals(editedMayfair));

        // different address -> returns false
        editedMayfair = new PropertyBuilder(MAYFAIR).withAddress("12 Burghley Drive").build();
        assertFalse(MAYFAIR.equals(editedMayfair));

        // different postal -> returns false
        editedMayfair = new PropertyBuilder(MAYFAIR).withPostal("123456").build();
        assertFalse(MAYFAIR.equals(editedMayfair));

        // different deadline -> returns false
        editedMayfair = new PropertyBuilder(MAYFAIR).withDeadline(LocalDate.parse("2021-07-31")).build();
        assertFalse(MAYFAIR.equals(editedMayfair));
    }
}
