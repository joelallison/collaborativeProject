package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity){

        int entityLeftWorldX = (int) entity.getxPos() + entity.solidArea.x;
        int entityRightWorldX = (int) entity.getxPos() + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = (int) entity.getyPos() + entity.solidArea.y;
        int entityBottomWorldY = (int) entity.getyPos() + entity.solidArea.y + entity.solidArea.height;

        //UNFINISHED !!!!!! NEED TO IMPLEMENT LEVEL GENERATION BEFORE COLLISION


    }

}
