package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.Maplet;
import seedu.address.model.ReadOnlyMaplet;
import seedu.address.model.person.Activities;
import seedu.address.model.person.Address;
import seedu.address.model.person.Contact;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Maplet} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Contact("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), new Activities("see the world"),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Contact("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Activities("eat good food"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Contact("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new Activities("visiting museums"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Contact("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new Activities("go hiking"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Contact("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), new Activities("see the eiffel tower"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Contact("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), new Activities("go and watch football matches"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyMaplet getSampleMaplet() {
        Maplet sampleAb = new Maplet();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
