package com.example.pattern.factory;

/**
 * @author xingce
 * @date 2022/12/16 16:29
 */
public class HtmlDialog extends Dialog{
    @Override
    public Button createButton() {
        return new HtmlButton();
    }
}
