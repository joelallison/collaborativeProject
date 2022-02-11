package game;

import org.lwjgl.glfw.GLFWCursorPosCallback;


public class mouseInput extends GLFWCursorPosCallback{

    @Override
    public void invoke(long window, double x, double y){
        System.out.println("X: " + x + " Y: " + y);
    }
}
