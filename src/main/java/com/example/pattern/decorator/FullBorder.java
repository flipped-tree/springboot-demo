package com.example.pattern.decorator;

public class FullBorder extends Border {
    protected FullBorder(Display display) {
        super(display);
    }

    @Override
    int getColumns() {
        return 1 + display.getColumns() + 1;
    }

    @Override
    int getRows() {
        return 1 + display.getRows() + 1;
    }

    @Override
    String getRowText(int row) {
        if (row == 0) {
            return "+" + makeLine('-', display.getColumns()) + "+";
        } else if (row == display.getRows() + 1) {
            return "+" + makeLine('-', display.getColumns()) + "+";
        } else {
            return "|" + display.getRowText(row - 1) + "|";
        }
    }

    private String makeLine(char ch, int columns) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < columns; i++) {
            builder.append(ch);
        }
        return builder.toString();
    }
}
