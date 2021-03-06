package seedu.address.model.property.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a client's email.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmail(String)}.
 */
public class Email {
    private static final String SPECIAL_CHARACTERS = "_.-";

    public static final String MESSAGE_CONSTRAINTS = "Emails should be of the format prefix@domain.topleveldomain \n"
            + "and adhere to the following constraints:\n"
            + "1. The prefix should only contain alphanumeric characters and these special characters, excluding "
            + "the parentheses, (" + SPECIAL_CHARACTERS + "). "
            + "A special character cannot appear as the first or last character in the prefix "
            + "or appear consecutively two or more times.\n"
            + "2. This is followed by a '@' and then the domain name. "
            + "The domain name is made up of one or more domain labels separated by periods "
            + "and each domain label must:\n"
            + "  - start and end with alphanumeric characters\n"
            + "  - consist of alphanumeric characters, separated only by hyphens, if any\n"
            + "3. The top level domain label must only contain alphanumeric characters "
            + "and is at least 2 characters long.\n"
            + "E.g. anthony_123@example.sg is a valid email but anthony_123@sg is an invalid email";

    private static final String ALPHANUMERIC = "[^\\W_]+";
    private static final String PREFIX_REGEX = ALPHANUMERIC + "([" + SPECIAL_CHARACTERS + "]" + ALPHANUMERIC + ")*";
    private static final String DOMAIN_REGEX = ALPHANUMERIC + "(-" + ALPHANUMERIC + ")*";
    private static final String TOP_LEVEL_DOMAIN_REGEX = "(" + ALPHANUMERIC + "){2,}";
    public static final String VALIDATION_REGEX = PREFIX_REGEX + "@" + "(" + DOMAIN_REGEX
            + "\\.)+" + TOP_LEVEL_DOMAIN_REGEX;

    public final String email;

    /**
     * Constructs an {@code Email}.
     *
     * @param email A valid email address.
     */
    public Email(String email) {
        requireNonNull(email);
        checkArgument(isValidEmail(email), MESSAGE_CONSTRAINTS);
        this.email = email;
    }

    /**
     * Returns true if a given string is a valid email address.
     *
     * @param test The string to test.
     * @return True if the given string is a valid email address, otherwise false.
     */
    public static boolean isValidEmail(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return email;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Email)) {
            return false;
        }
        Email otherEmail = (Email) other;
        return email.equals(otherEmail.email);
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }
}
