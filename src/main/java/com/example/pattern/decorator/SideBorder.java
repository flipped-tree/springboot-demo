package com.example.pattern.decorator;

public class SideBorder extends Border {

    private final char borderChar;

    public SideBorder(Display display, char borderChar) {
        super(display);
        this.borderChar = borderChar;
    }

    @Override
    int getColumns() {
        return 1 + display.getColumns();
    }

    @Override
    int getRows() {
        return display.getRows();
    }

    @Override
    String getRowText(int row) {
        return borderChar + display.getRowText(row) + borderChar;
    }
}
