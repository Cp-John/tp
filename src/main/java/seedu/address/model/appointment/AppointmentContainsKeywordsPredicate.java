package seedu.address.model.appointment;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Appointment}'s {@code Name} matches any of the keywords given.
 */
public class AppointmentContainsKeywordsPredicate implements Predicate<Appointment> {
    private final List<String> keywords;

    /**
     * Constructs {@code AppointmentContainsKeywordsPredicate}.
     */
    public AppointmentContainsKeywordsPredicate(List<String> keywords) throws IllegalArgumentException {
        this.keywords = new ArrayList<>();
        for (String s : keywords) {
            if (s.trim().isEmpty()) {
                throw new IllegalArgumentException("Appointment name given is empty. ");
            }
            this.keywords.add(s.toLowerCase());
        }
    }

    @Override
    public boolean test(Appointment appointment) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(appointment.getName().name, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AppointmentContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((AppointmentContainsKeywordsPredicate) other).keywords)); // state check
    }

}
