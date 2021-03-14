package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSTAL_MAYFAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_MAYFAIR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProperties.MAYFAIR;
import static seedu.address.testutil.TypicalProperties.WOODLANDS_CRESCENT;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.property.exceptions.DuplicatePropertyException;
import seedu.address.model.property.exceptions.PropertyNotFoundException;
import seedu.address.testutil.PropertyBuilder;

public class UniquePropertyListTest {
    private final UniquePropertyList uniquePropertyList = new UniquePropertyList();

    @Test
    public void contains_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePropertyList.contains(null));
    }

    @Test
    public void contains_propertyNotInList_returnsFalse() {
        assertFalse(uniquePropertyList.contains(WOODLANDS_CRESCENT));
    }

    @Test
    public void contains_propertyInList_returnsTrue() {
        uniquePropertyList.add(WOODLANDS_CRESCENT);
        assertTrue(uniquePropertyList.contains(WOODLANDS_CRESCENT));
    }

    @Test
    public void contains_propertyWithSameIdentityFieldsInList_returnsTrue() {
        uniquePropertyList.add(WOODLANDS_CRESCENT);
        Property editedWoodlandsCrescent = new PropertyBuilder(WOODLANDS_CRESCENT)
                .withAddress(VALID_ADDRESS_MAYFAIR).withType(VALID_TYPE_MAYFAIR)
                .withPostal(VALID_POSTAL_MAYFAIR).withDeadline(LocalDate.parse(VALID_DEADLINE_MAYFAIR))
                .build();
        assertTrue(uniquePropertyList.contains(editedWoodlandsCrescent));
    }

    @Test
    public void add_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePropertyList.add(null));
    }

    @Test
    public void add_duplicateProperty_throwsDuplicatePropertyException() {
        uniquePropertyList.add(WOODLANDS_CRESCENT);
        assertThrows(DuplicatePropertyException.class, () -> uniquePropertyList.add(WOODLANDS_CRESCENT));
    }

    @Test
    public void setProperty_nullTargetProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePropertyList.setProperty(null, WOODLANDS_CRESCENT));
    }

    @Test
    public void setProperty_nullEditedProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePropertyList.setProperty(WOODLANDS_CRESCENT, null));
    }

    @Test
    public void setProperty_targetPropertyNotInList_throwsPropertyNotFoundException() {
        assertThrows(PropertyNotFoundException.class, () -> uniquePropertyList
                .setProperty(WOODLANDS_CRESCENT, WOODLANDS_CRESCENT));
    }

    @Test
    public void setProperty_editedPropertyIsSameProperty_success() {
        uniquePropertyList.add(WOODLANDS_CRESCENT);
        uniquePropertyList.setProperty(WOODLANDS_CRESCENT, WOODLANDS_CRESCENT);
        UniquePropertyList expectedUniquePropertyList = new UniquePropertyList();
        expectedUniquePropertyList.add(WOODLANDS_CRESCENT);
        assertEquals(expectedUniquePropertyList, uniquePropertyList);
    }

    @Test
    public void setProperty_editedPropertyHasSameIdentity_success() {
        uniquePropertyList.add(WOODLANDS_CRESCENT);
        Property editedWoolandsCrescent = new PropertyBuilder(WOODLANDS_CRESCENT).withAddress(VALID_ADDRESS_MAYFAIR)
                .withAddress(VALID_ADDRESS_MAYFAIR).withType(VALID_TYPE_MAYFAIR)
                .withPostal(VALID_POSTAL_MAYFAIR).withDeadline(LocalDate.parse(VALID_DEADLINE_MAYFAIR))
                .build();
        uniquePropertyList.setProperty(WOODLANDS_CRESCENT, editedWoolandsCrescent);
        UniquePropertyList expectedUniquePropertyList = new UniquePropertyList();
        expectedUniquePropertyList.add(editedWoolandsCrescent);
        assertEquals(expectedUniquePropertyList, uniquePropertyList);
    }

    @Test
    public void setProperty_editedPropertyHasDifferentIdentity_success() {
        uniquePropertyList.add(WOODLANDS_CRESCENT);
        uniquePropertyList.setProperty(WOODLANDS_CRESCENT, MAYFAIR);
        UniquePropertyList expectedUniquePropertyList = new UniquePropertyList();
        expectedUniquePropertyList.add(MAYFAIR);
        assertEquals(expectedUniquePropertyList, uniquePropertyList);
    }

    @Test
    public void setProperty_editedPropertyHasNonUniqueIdentity_throwsDuplicatePropertyException() {
        uniquePropertyList.add(WOODLANDS_CRESCENT);
        uniquePropertyList.add(MAYFAIR);
        assertThrows(DuplicatePropertyException.class, () -> uniquePropertyList
                .setProperty(WOODLANDS_CRESCENT, MAYFAIR));
    }

    @Test
    public void remove_nullProperty_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePropertyList.remove(null));
    }

    @Test
    public void remove_propertyDoesNotExist_throwsPropertyNotFoundException() {
        assertThrows(PropertyNotFoundException.class, () -> uniquePropertyList.remove(WOODLANDS_CRESCENT));
    }

    @Test
    public void remove_existingProperty_removesProperty() {
        uniquePropertyList.add(WOODLANDS_CRESCENT);
        uniquePropertyList.remove(WOODLANDS_CRESCENT);
        UniquePropertyList expectedUniquePropertyList = new UniquePropertyList();
        assertEquals(expectedUniquePropertyList, uniquePropertyList);
    }

    @Test
    public void setProperties_nullUniquePropertyList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePropertyList.setProperties((UniquePropertyList) null));
    }

    @Test
    public void setProperties_uniquePropertyList_replacesOwnListWithProvidedUniquePropertyList() {
        uniquePropertyList.add(WOODLANDS_CRESCENT);
        UniquePropertyList expectedUniquePropertyList = new UniquePropertyList();
        expectedUniquePropertyList.add(MAYFAIR);
        uniquePropertyList.setProperties(expectedUniquePropertyList);
        assertEquals(expectedUniquePropertyList, uniquePropertyList);
    }

    @Test
    public void setProperties_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePropertyList.setProperties((List<Property>) null));
    }

    @Test
    public void setProperties_list_replacesOwnListWithProvidedList() {
        uniquePropertyList.add(WOODLANDS_CRESCENT);
        List<Property> propertyList = Collections.singletonList(MAYFAIR);
        uniquePropertyList.setProperties(propertyList);
        UniquePropertyList expectedUniquePropertyList = new UniquePropertyList();
        expectedUniquePropertyList.add(MAYFAIR);
        assertEquals(expectedUniquePropertyList, uniquePropertyList);
    }

    @Test
    public void setProperties_listWithDuplicateProperties_throwsDuplicatePropertyException() {
        List<Property> listWithDuplicateProperties = Arrays.asList(WOODLANDS_CRESCENT, WOODLANDS_CRESCENT);
        assertThrows(DuplicatePropertyException.class, () -> uniquePropertyList
                .setProperties(listWithDuplicateProperties));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> uniquePropertyList
                .asUnmodifiableObservableList().remove(0));
    }
}

