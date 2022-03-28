package main;

public class Room {

    public int width;
    public int height;
    public String filename;
    public String[][] layout;
    int howMany;


    public Room(String filename, int howMany) {
        this.filename = filename;
        this.howMany = howMany;

        this.layout = processRoom(FileHandler.level("src/assets/levels/" + filename));

        this.width = FileHandler.getRoomDimensions("src/assets/levels/" + filename)[0];
        this.height = FileHandler.getRoomDimensions("src/assets/levels/" + filename)[1];
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

    public int getHowMany() {
        return howMany;
    }

    public String[][] getLayout() {
        return layout;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
