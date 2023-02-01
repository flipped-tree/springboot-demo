package com.example.pattern.factory;

/**
 * @author xingce
 * @date 2022/12/16 16:30
 */
public class WindowsDialog extends Dialog {

    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}
