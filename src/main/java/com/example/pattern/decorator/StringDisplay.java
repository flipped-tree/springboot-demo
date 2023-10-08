package com.example.pattern.decorator;

public class StringDisplay extends Display {
    private final String string;

    public StringDisplay(String string) {
        this.string = string;
    }

    @Override
    int getColumns() {
        return string.getBytes().length;
    }

    @Override
    int getRows() {
        return 1;
    }

    @Override
    String getRowText(int row) {
        return row == 0 ? string : null;
    }
}
