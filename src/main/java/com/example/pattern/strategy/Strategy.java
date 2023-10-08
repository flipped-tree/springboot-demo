package com.example.pattern.strategy;

public interface Strategy {
    Hand nextHand();

    void study(boolean win);
}
