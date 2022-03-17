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

    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[10]; //how many different tile images

        getTileImage();
    }

    public void getTileImage() {

        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/wall.png"));
            tile[0].collision = true;

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/blank.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/top.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2, Player player) {


        String[][] level = FileHandler.level("src/assets/levels/room1");

        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                g2.drawImage(tile[Integer.parseInt(level[j][i])].image, (i*gp.tileSize) - (int) player.getxPos(), j*gp.tileSize - (int) player.getyPos() , gp.tileSize, gp.tileSize, null);
            }

        }

    }
}
