package com.example.pattern.abstractfactory.listfactory;

import com.example.pattern.abstractfactory.factory.Tray;

public class ListTray extends Tray {
    public ListTray(String captain) {
        super(captain);
    }

    @Override
    public String makeHTML() {
        return "listTray";
    }
}
