package com.example.pattern.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class NumberGenerator {
    private final List<Observer> observers = new ArrayList<>();        // 保存Observer们

    public void addObserver(Observer observer) {    // 注册Observer
        observers.add(observer);
    }

    public void deleteObserver(Observer observer) { // 删除Observer
        observers.remove(observer);
    }

    public void notifyObservers() {               // 向Observer发送通知
        for (Observer o : observers) {
            o.update(this);
        }
    }

    public abstract int getNumber();                // 获取数值

    public abstract void execute();                 // 生成数值
}
