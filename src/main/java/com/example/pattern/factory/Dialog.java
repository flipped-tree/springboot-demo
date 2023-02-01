package com.example.pattern.factory;

/**
 * @author xingce
 * @date 2022/12/16 16:28
 */
public abstract class Dialog {
    public void renderWindows() {
        Button button = createButton();
        button.render();
    }

    public abstract Button createButton();
}
