package tile;

import entity.Player;
import main.FileHandler;
import main.GamePanel;
import main.LevelGenerator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager {

    GamePanel gp;
    Tile[] tile;
    LevelGenerator lg;

    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[32]; //how many different tile images

        getTileImage();

    }

    public void getTileImage() {

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

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/door_1.png"));

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/door_2.png"));

            tile[12] = new Tile();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/door_3.png"));

            tile[13] = new Tile();
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/door_4.png"));

            tile[14] = new Tile();
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/door_5.png"));

            tile[15] = new Tile();
            tile[15].image = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/door_6.png"));

            tile[16] = new Tile();
            tile[16].image = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/door_7.png"));

            tile[17] = new Tile();
            tile[17].image = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/door_8.png"));

            tile[18] = new Tile();
            tile[18].image = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/door_9.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2, Player player) {

        for (int y = 0; y < lg.room_1.length; y++) {
            for (int x = 0; x < lg.room_1[y].length; x++) {
                g2.drawImage(tile[Integer.parseInt(lg.room_1[y][x])].image, (x * gp.tileSize) - (int) player.getxPos(), y * gp.tileSize - (int) player.getyPos(), gp.tileSize, gp.tileSize, null);
            }
        }

        /*for (int y = 0; y < lg.door_room.length; y++) {
            for (int x = 0; x < lg.door_room[y].length; x++) {
                g2.drawImage(tile[Integer.parseInt(lg.door_room[y][x])].image, ((x + 3) * gp.tileSize) - (int) player.getxPos(), (y * gp.tileSize) - (lg.door_room.length * gp.tileSize) - (int) player.getyPos(), gp.tileSize, gp.tileSize, null);
            }
        }*/

    }
}
