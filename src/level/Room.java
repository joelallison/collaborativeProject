package level;

public class Room {
    private int width;
    private int height;
    public String[][] layout;

    public Room(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setLayout(String[][] layout){
        this.layout = layout;
    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String[][] getLayout() {
        return layout;
    }

}
