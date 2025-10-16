package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import seedu.address.model.util.SampleDataUtil;

public class SampleDataUtilTest {
    @Test
    public void assertSampleNotNull() {
        assertNotNull(SampleDataUtil.getSampleAttractions());
    }
}
