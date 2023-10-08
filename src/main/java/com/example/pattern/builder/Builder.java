package com.example.pattern.builder;

/**
 * @author xingce
 * @date 2022/12/16 15:11
 */
public class Builder {
    private Builder() {
    }

    private String text;

    private void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static class PatternBuilder {
        private final Builder builder;

        public PatternBuilder() {
            builder = new Builder();
        }

        public PatternBuilder setText(String text) {
            this.builder.setText(text);
            return this;
        }

        public Builder build() {
            return this.builder;
        }
    }
}
