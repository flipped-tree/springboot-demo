package com.example.pattern.factory;

/**
 * @author xingce
 * @date 2022/12/16 16:26
 */
public interface Button {
    void render();

    void onClick();

    public static void main(String[] args) {
        if (System.getProperty("os.name").equals("Windows 10")) {
            Dialog dialog = new HtmlDialog();
            dialog.renderWindows();
        }
    }
}
