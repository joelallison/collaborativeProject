package main;

import java.util.ArrayList;
import java.util.Random;

public class MapBuilder {

    public ArrayList<Room> rooms = new ArrayList<>();
    public String[][] map = new String[64][64];

    public MapBuilder() {
        rooms.add(new Room("door_room", 1));
        rooms.add(new Room("key_room", 3));
        rooms.add(new Room("room_1", 4));
    }

    public void generate() {
        Random random = new Random();

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                map[y][x] = "x";
            }
        }

        for (Room r : rooms) {
            int x;
            int y;

            for (int i = 0; i < r.getHowMany(); i++) {
                y = random.nextInt(map.length - r.getHeight());
                x = random.nextInt(map[0].length - r.getWidth());

                while (!map[y][x].equals("x")) {
                    y = random.nextInt(map.length - r.getHeight());
                    x = random.nextInt(map[0].length - r.getWidth());
                }

                for (int roomRow = 0; roomRow < r.getHeight(); roomRow++) {
                    for (int roomCol = 0; roomCol < r.getWidth(); roomCol++) {
                        map[y + roomRow][x + roomCol] = r.getLayout()[roomRow][roomCol];
                    }
                }


            }

        }

    }

}
