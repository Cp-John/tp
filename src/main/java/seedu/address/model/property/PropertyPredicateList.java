package seedu.address.model.property;

import java.util.List;
import java.util.function.Predicate;

/**
 * List of {@code Predicate<Property>}, supports equality comparisons
 * with other {@code PropertyPredicateLists}, and combining of all
 * predicates it holds into a single predicate.
 */
public class PropertyPredicateList {
    private List<Predicate<Property>> predicates;

    public PropertyPredicateList(List<Predicate<Property>> predicates) {
        this.predicates = predicates;
    }

    /**
     * Combines all predicates in its internal list into a single
     * predicate.
     * @return Logical conjunction of {@code Predicate<Property>}
     */
    public Predicate<Property> combine() {
        return this.predicates.stream().reduce(Predicate::and).orElse(x -> true);
    }

    /**
     * Combines all predicates in its internal list into a single
     * predicate.
     * @return Logical disjunction of {@code Predicate<Property>}
     */
    public Predicate<Property> combineDisjunction() {
        return this.predicates.stream().reduce(Predicate::or).orElse(x -> true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof PropertyPredicateList
                    && this.predicates.equals(((PropertyPredicateList) other).predicates));
    }
}
