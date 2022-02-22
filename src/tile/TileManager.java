package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager {

    GamePanel gp;
    Tile[] tile;

    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[10];

        getTileImage();
    }

    public void getTileImage() {

        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/assets/tiles/main_tile.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 14; j++) {
                g2.drawImage(tile[0].image, i*gp.tileSize, j*gp.tileSize, gp.tileSize, gp.tileSize, null);
            }

        }

    }
}
