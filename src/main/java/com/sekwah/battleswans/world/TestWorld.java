package com.sekwah.battleswans.world;

import com.sekwah.battleswans.entities.Entity;
import com.sekwah.battleswans.gamestages.Stage;

import java.util.ArrayList;

public class TestWorld extends World {

    public ArrayList<Entity> entityList = new ArrayList<>();
    
    public TestWorld(Stage stage, int width, int height) {
        super(stage, width, height);
        ArrayList<Tile> tileList = new ArrayList<>();
        for(int i = -1; i <= 1; i+= 2) {
            tileList.add(new CollisionTile(this, 800,40).setPos(600 * i,150));
            tileList.add(new CollisionTile(this, 200,40).setPos(600 * i,-100));
            tileList.add(new CollisionTile(this, 40,40).setPos(980 * i,110));
            tileList.add(new PlatformTile(this, 24).setPos(980 * i, 0));
            tileList.add(new PlatformTile(this, 12).setPos(12 * 14 * i / 2, -450 + 250 / 2));
            tileList.add(new PlatformTile(this, 12).setPos(12 * 14 * i / 2, -450 - 250 / 2));
            tileList.add(new CollisionTile(this, 40,1600).setPos((188 + 980) * i,-794));

            for (int j = 0; j < 10; j++) {
                tileList.add(new PlatformTile(this, 12).setPos((1148 - 12 * 7) * i, -170 * j));
            }
        }
        tileList.add(new CollisionTile(this, 40,250).setPos(0,-450));
        //tileList.add(new CollisionTile(this, 400,40).setPos(0,-20));
        tileList.add(new PlatformTile(this, 30).setPos(0,0));
        tiles = tileList.toArray(new Tile[0]);


    }

    public void doUpdate(long passedTime) {

    }

    public void doRender(){
        super.doRender();
        //world.stage.drawTexture(posX, posY, 23, 23, 3, 0, 0);
    }
}
