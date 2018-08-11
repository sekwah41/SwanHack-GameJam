package com.sekwah.battleswans.world;

import com.sekwah.battleswans.entities.Entity;
import com.sekwah.battleswans.gamestages.Stage;

import java.util.ArrayList;
import java.util.List;

public class World {

    private final int width;
    private final int height;
    public final Stage stage;
    public Tile[] tiles;

    public List<Entity> arrayList = new ArrayList<Entity>();

    // -y is up
    public World(Stage stage, int width, int height){
        this.stage = stage;
        this.width = width;
        this.height = height;
    }

    public void doUpdate(){

    }

    public void doRender(){
        for(Tile tile: tiles){
            tile.doRender();
        }
    }

    public void updateCollisions(Entity entity) {
        for(Tile tile: tiles){
            tile.updateCollisions(entity);
        }
        for(Entity collideEntity : arrayList) {
            if(collideEntity != entity) {
                collideEntity.getEntityCollision().updateCollisions(entity);
            }
        }
    }

    public void addEntity(Entity entity) {
        arrayList.add(entity);
    }
}
