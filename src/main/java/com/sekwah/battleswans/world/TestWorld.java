package com.sekwah.battleswans.world;

import com.sekwah.battleswans.gamestages.Stage;

public class TestWorld extends World {
    
    public TestWorld(Stage stage, int width, int height) {
        super(stage, width, height);
        tiles = new Tile[3];
        tiles[0] = new CollisionTestTile(this, 900,10);
        tiles[0].posY = 150;

        tiles[1] = new CollisionTestTile(this, 50,50);
        tiles[1].posY = 130;

        tiles[2] = new CollisionTestTile(this, 500,50);
        tiles[2].posY = -150;
    }

    public void doUpdate(long passedTime){

    }

    public void doRender(){
        super.doRender();
        //world.stage.drawTexture(posX, posY, 23, 23, 3, 0, 0);
    }
}
