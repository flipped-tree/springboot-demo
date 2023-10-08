package com.example.pattern.strategy;

public class Player {
    private final String name;
    private final Strategy strategy;
    private int winCounts;
    private int loseCounts;
    private int gameCounts;

    public Player(String name, Strategy strategy) {
        this.name = name;
        this.strategy = strategy;
    }

    public Hand nextHand() {
        return strategy.nextHand();
    }

    public void win() {
        strategy.study(true);
        winCounts++;
        gameCounts++;
    }

    public void lose() {
        strategy.study(false);
        loseCounts++;
        gameCounts++;
    }

    public void even() {
        gameCounts++;
    }

    public String toString() {
        return "{" + name + ":" + gameCounts + " games, " + winCounts + " wins, " + loseCounts + " lose." + "}";
    }
}
