package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {

    public static void main(String[] args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        window.setResizable(false);
        window.setTitle("GAME !");
        window.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                System.out.println(e.getX() + " " + e.getY());
            }
        });

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.pack();
    }
}