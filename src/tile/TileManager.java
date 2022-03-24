package tile;

import entity.Player;
import main.FileHandler;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager {

    GamePanel gp;
    Tile[] tile;
    String[][] door_room = FileHandler.level("src/assets/levels/door_room");
    String[][] room_1 = FileHandler.level("src/assets/levels/room_1");

    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[32]; //how many different tile images

        getTileImage();


    }

    public void getTileImage() {

        door_room = processRoom(door_room);
        room_1 = processRoom(room_1);

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

        for (int y = 0; y < room_1.length; y++) {
            for (int x = 0; x < room_1[y].length; x++) {
                g2.drawImage(tile[Integer.parseInt(room_1[y][x])].image, (x * gp.tileSize) - (int) player.getxPos(), y * gp.tileSize - (int) player.getyPos(), gp.tileSize, gp.tileSize, null);
            }
        }

        for (int y = 0; y < door_room.length; y++) {
            for (int x = 0; x < door_room[y].length; x++) {
                g2.drawImage(tile[Integer.parseInt(door_room[y][x])].image, ((x + 3) * gp.tileSize) - (int) player.getxPos(), (y * gp.tileSize) - (door_room.length * gp.tileSize) - (int) player.getyPos(), gp.tileSize, gp.tileSize, null);
            }
        }

    }

    public String[][] processRoom(String[][] room) {
        for (int y = 0; y < room.length; y++) { //y
            for (int x = 0; x < room[y].length; x++) { //x
                if (room[y][x].equals("1")) {
                    if (y < room.length - 1 && room[y + 1][x].equals("0")) { //top
                        room[y + 1][x] = "2";
                    }
                    if (y > 0 && room[y - 1][x].equals("0")) { //bottom
                        room[y - 1][x] = "5";
                    }
                    if (x > 0 && room[y][x - 1].equals("0")) { //right
                        room[y][x - 1] = "3";
                    }
                    if (x < room[y].length - 1 && room[y][x + 1].equals("0")) { //left
                        room[y][x + 1] = "4";
                    }
                }

                if (!(room[y][x].equals("1"))) {
                    if ((x < room[y].length - 1 && y > 0) && room[y - 1][x].equals("1") && room[y][x + 1].equals("1")) { //topright
                        room[y][x] = "6";
                    }
                    if ((x > 0 && y > 0) && room[y - 1][x].equals("1") && room[y][x - 1].equals("1")) { //topleft
                        room[y][x] = "7";
                    }
                    if ((y < room.length - 1 && x < room[y].length - 1) && room[y + 1][x].equals("1") && room[y][x + 1].equals("1")) { //bottomright
                        room[y][x] = "8";
                    }
                    if ((x > 0 && y < room.length - 1) && room[y + 1][x].equals("1") && room[y][x - 1].equals("1")) { //bottomleft
                        room[y][x] = "9";
                    }
                }
            }
        }
        return room;
    }
}
