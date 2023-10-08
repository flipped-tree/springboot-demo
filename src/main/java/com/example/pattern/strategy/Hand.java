package com.example.pattern.strategy;

public class Hand {
    // 石头
    public static final int HAND_GUU = 0;
    // 剪刀
    public static final int HAND_CHO = 1;
    // 布
    public static final int HAND_PAA = 2;

    public static final Hand[] hands = {new Hand(HAND_GUU), new Hand(HAND_CHO), new Hand(HAND_PAA)};
    public static final String[] names = {"石头", "剪刀", "布"};

    private final int handValue;

    public Hand(int handValue) {
        this.handValue = handValue;
    }

    public static Hand getHand(int handValue) {
        return hands[handValue];
    }

    public boolean isStrongerThan(Hand hand) {
        return flight(hand) == 1;
    }

    private int flight(Hand h) {
        if (this.handValue == h.handValue) {
            return 0;
        } else if ((this.handValue + 1) % 3 == h.handValue) {
            return 1;
        } else {
            return -1;
        }
    }

    public String toString() {
        return names[handValue];
    }
}
