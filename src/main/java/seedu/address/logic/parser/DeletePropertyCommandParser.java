package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PROPERTY_DISPLAYED_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeletePropertyCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeletePropertyCommand object
 */
public class DeletePropertyCommandParser implements Parser<DeletePropertyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeletePropertyCommand
     * and returns a DeletePropertyCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeletePropertyCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeletePropertyCommand(index);
        } catch (NumberFormatException nfe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePropertyCommand.MESSAGE_USAGE), nfe);
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_PROPERTY_DISPLAYED_INDEX);
        }
    }

}
