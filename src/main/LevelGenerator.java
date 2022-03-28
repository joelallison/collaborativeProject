package main;

import main.FileHandler;

import java.io.IOException;
import java.util.ArrayList;

public class LevelGenerator {

    private int width;
    private int height;
    public String[][] door_room;
    public String[][] room_1;

    ArrayList<String[][]> rooms = new ArrayList<>();

    public LevelGenerator() {
        door_room = processRoom(FileHandler.level("src/assets/levels/door_room"));
        room_1 = processRoom(FileHandler.level("src/assets/levels/room_1"));
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

    public void addRoom(String[][] room){ rooms.add(room); }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
