package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHandler {

    public void load(int mode, String filename){
        switch(mode){
            case 0: //level
                level(filename);
                break;
            case 1: //weapon data

                break;
            case 2: //enemy data

                break;
        }
    }

    public static String[][] level(String filename){
        String[][] level = new String[0][0];
        File file = new File(filename);
        int lineNum = 0;

        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {

                String data = myReader.nextLine();
                String[] line = data.split(" ");

                if (lineNum == 0) {
                    level = new String[Integer.parseInt(line[1])][Integer.parseInt(line[0])];
                } //set dimensions of the 2D array
                else {
                    level[lineNum - 1] = line;
                }

                lineNum++;
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return level;
    }



}
