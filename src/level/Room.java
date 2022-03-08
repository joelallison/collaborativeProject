package level;

public class Room {
    private String name;
    private int width;
    private int height;
    private int[][] layout = new int[width][height];

    public Room(String name, int width, int height, int[][] layout) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.layout = layout;
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[][] getLayout() {
        return layout;
    }

}
