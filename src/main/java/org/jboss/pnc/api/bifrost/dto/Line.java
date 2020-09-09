package org.jboss.pnc.api.bifrost.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.Map;

/**
 * @author <a href="mailto:matejonnet@gmail.com">Matej Lazar</a>
 */
@Getter
@Setter
@ToString
public class Line {

    private String id;

    private String timestamp;

    private String sequence;

    private String loggerName;

    private String message;

    private String stackTrace;

    private boolean last;

    private Map<String, String> mdc;

    private String subscriptionTopic;

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Line fromString(String json) {
        Jsonb jsonb = JsonbBuilder.create();
        return jsonb.fromJson(json, Line.class);
    }

    public static Builder newBuilder(Line copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.timestamp = copy.getTimestamp();
        builder.sequence = copy.getSequence();
        builder.loggerName = copy.getLoggerName();
        builder.message = copy.getMessage();
        builder.stackTrace = copy.getStackTrace();
        builder.last = copy.isLast();
        builder.mdc = copy.getMdc();
        return builder;
    }

    public String asString() {
        return getTimestamp() + " " + getLoggerName() + " " + getMessage();
    }

    // @JsonPOJOBuilder(withPrefix = "")
    // public static final class LineBuilder {
    // }

    public Builder cloneBuilder() {
        return newBuilder(this);
    }

    public static final class Builder {

        private String id;

        private String timestamp;

        private String sequence;

        private String loggerName;

        private String message;

        private String stackTrace;

        private boolean last;

        private Map<String, String> mdc;

        private Builder() {
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder timestamp(String timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder sequence(String sequence) {
            this.sequence = sequence;
            return this;
        }

        public Builder logger(String logger) {
            this.loggerName = logger;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder stackTrace(String stackTrace) {
            this.stackTrace = stackTrace;
            return this;
        }

        public Builder last(boolean last) {
            this.last = last;
            return this;
        }

        public Builder mdc(Map<String, String> mdc) {
            this.mdc = mdc;
            return this;
        }

        public Line build() {
            Line line = new Line();
            line.id = this.id;
            line.timestamp = this.timestamp;
            line.sequence = this.sequence;
            line.loggerName = this.loggerName;
            line.message = this.message;
            line.stackTrace = this.stackTrace;
            line.last = this.last;
            line.mdc = this.mdc;
            return line;
        }
    }
}
