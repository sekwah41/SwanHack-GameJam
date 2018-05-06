package com.sekwah.battleswans.world;

import com.sekwah.battleswans.gamestages.Stage;

import java.util.ArrayList;

public class TestWorld extends World {
    
    public TestWorld(Stage stage, int width, int height) {
        super(stage, width, height);
        ArrayList<Tile> tileList = new ArrayList<>();
        tileList.add(new CollisionTile(this, 400,40).setPos(0,150));
        //tileList.add(new CollisionTile(this, 400,40).setPos(0,-20));
        tileList.add(new PlatformTile(this, 9).setPos(0,-20));
        tiles = tileList.toArray(new Tile[0]);
    }

    public void doUpdate(long passedTime) {

    }

    public void doRender(){
        super.doRender();
        //world.stage.drawTexture(posX, posY, 23, 23, 3, 0, 0);
    }
}
