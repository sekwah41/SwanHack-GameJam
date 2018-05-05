package com.sekwah.battleswans;

import com.sekwah.battleswans.screen.GameDisplay;

public class BattleSwans {

    private final GameDisplay display;

    public BattleSwans() {
        display = new GameDisplay(this);

        display.init();
    }
}
