package tile;

import main.FileHandler;
import main.GamePanel;
import entity.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager {

    GamePanel gp;
    Tile[] tile;
    String[][] level = FileHandler.level("src/assets/levels/room1");

    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[16]; //how many different tile images

        getTileImage();


    }

    public void getTileImage() {

        level = processRoom(level);

        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/blank.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/top.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/right.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/left.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/bottom.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/topright.png"));

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/topleft.png"));

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/bottomright.png"));

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/bottomleft.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2, Player player) {

        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                g2.drawImage(tile[Integer.parseInt(level[j][i])].image, (i*gp.tileSize) - (int) player.getxPos(), j*gp.tileSize - (int) player.getyPos() , gp.tileSize, gp.tileSize, null);
            }

        }

    }

    public String[][] processRoom(String[][] room){
        for (int i = 0; i < room.length; i++) { //y
            for (int j = 0; j < room[i].length; j++) { //x
                if(room[j][i].equals("1")){
                    System.out.println(j + " " + i);
                    if(j > 0 && room[j-1][i].equals("0")) { //bottom
                        room[j-1][i] = "5";
                    }if(j < room[i].length-1 && room[j+1][i].equals("0")) { //top
                        room[j+1][i] = "2";
                    }if(i > 0 && room[j][i-1].equals("0")) { //right
                        room[j][i-1] = "3";
                    }if(i < room.length-1 && room[j][i+1].equals("0")) { //left
                        room[j][i+1] = "4";
                    }
                }
                if(!(room[j][i].equals("1"))){
                    if((i < room.length-1 && j > 0) && room[j-1][i].equals("1") && room[j][i+1].equals("1")) { //topright
                        room[j][i] = "6";
                    }if ((i > 0 && j > 0) && room[j-1][i].equals("1") && room[j][i-1].equals("1")) {
                        room[j][i] = "7";
                    }if ((i < room.length-1 && j < room[i].length-1) && room[j+1][i].equals("1") && room[j][i+1].equals("1")) {
                        room[j][i] = "8";
                    }if ((i > 0 && j < room[i].length-1) && room[j+1][i].equals("1") && room[j][i-1].equals("1")) {
                        room[j][i] = "9";
                    }
                }
            }
        }
        return room;
    }
}
