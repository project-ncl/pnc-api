package org.jboss.pnc.api.bifrost;

import static java.text.MessageFormat.format;

import org.jboss.pnc.api.bifrost.dto.Line;
import org.jboss.pnc.api.bifrost.enums.Format;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LineFormatTest {

    private static final String MESSAGE = "This is a very important message. FOCUS!";
    private static final String TIMESTAMP = "2023-01-01T13:00:00.000Z";
    private static final String LOGGER = "org.jboss.pnc.api.bifrost";
    private static final Line line = Line.newBuilder()
            .id("lineID")
            .message(MESSAGE)
            .timestamp(TIMESTAMP)
            .logger(LOGGER)
            .build();

    @Test
    void testDefaultFormat() {
        Assertions.assertEquals(line.asString(Format.DEFAULT), format("{0} {1} {2}", TIMESTAMP, LOGGER, MESSAGE));
    }

    @Test
    void testTimestampFormat() {
        Assertions.assertEquals(line.asString(Format.TIMESTAMP), format("{0} {1}", TIMESTAMP, MESSAGE));
    }

    @Test
    void testPlainFormat() {
        Assertions.assertEquals(line.asString(Format.PLAIN), format("{0}", MESSAGE));
    }
}
