package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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

    public String[][] level(String filename){
        File file = new File(filename);
        //String[][] level;

        //do like png with dimensions at start, then declare the String[][] after, with that data

        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return level;
    }



}
