package com.rufus.bumblebee.generators.dto.parameters;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public final class GeneratorParameters {

    public static class Field {
        private final String description;
        private final boolean required;
        private final Predicate predicate;
        private final String regexp;

        public Field(String description, boolean required, Predicate predicate, String regexp) {
            this.description = description;
            this.required = required;
            this.predicate = predicate;
            this.regexp = regexp;
        }

        public static class Builder {
            private final String description;
            private final boolean required;
            private Predicate predicate;
            private String regexp;

            public Builder(String description, boolean required) {
                this.description = description;
                this.required = required;

            }

            public Builder setPredicate(Predicate predicate) {
                this.predicate = predicate;
                return this;
            }

            public Builder setRegexp(String regexp) {
                this.regexp = regexp;
                return this;
            }

            public Field build() {
                return new Field(description, required, predicate, regexp);
            }
        }

        public String getDescription() {
            return description;
        }

        public boolean isRequired() {
            return required;
        }

        public Predicate getPredicate() {
            return predicate;
        }

        public String getRegexp() {
            return regexp;
        }
    }

    private final Map<String, Field> fields;

    public GeneratorParameters(int capacity) {
        fields = new HashMap<>(capacity);
    }

    public GeneratorParameters addParameter(String key, Field field) {
        fields.put(key, field);
        return this;
    }

    public Map<String, Field> getFields() {
        return fields;
    }
}
