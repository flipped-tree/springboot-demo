package com.example.pattern.builder;

/**
 * @author xingce
 * @date 2022/12/16 15:11
 */
public class Pattern {
    private String text;

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static class PatternBuilder {
        private final Pattern pattern;

        public PatternBuilder() {
            pattern = new Pattern();
        }

        public PatternBuilder setText(String text) {
            this.pattern.setText(text);
            return this;
        }

        public Pattern build() {
            return this.pattern;
        }
    }

    public static void main(String[] args) {
        Pattern pattern = new Pattern.PatternBuilder().setText("test").build();
        System.out.println(pattern.getText());
    }
}
