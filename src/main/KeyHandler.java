package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, leftPressed, downPressed, rightPressed, shiftPressed, abilityPressed;
    public int abilityKey = KeyEvent.VK_E;

    @Override
    public void keyTyped(KeyEvent e) { //not used
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();


        //movement keys
        if(code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if(code == KeyEvent.VK_SHIFT) {
            shiftPressed = true;
        }

        if(code == abilityKey) {
            abilityPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();


        //movement keys
        if(code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if(code == KeyEvent.VK_SHIFT) {
            shiftPressed = false;
        }
        if(code == abilityKey){
            abilityPressed = false;
        }
    }
}
