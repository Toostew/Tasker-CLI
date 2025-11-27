package org.Tookaystewie;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.io.File;
import java.util.Scanner;

public class StorageTest {
    static HashMap<Integer, Tasks> taskMap = new HashMap<Integer, Tasks>();


    public void taskFileExists() {
        try{
            File file = new File("data/tasks.txt");
            if(file.createNewFile()){
                System.out.println("created data file");
            } else {
                System.out.println("data file already exists");
            }
        } catch (IOException e) {
            System.out.println("Error creating data file");
            e.printStackTrace();
        }
    }

    //reads task storage file
    public void readFile(){
        taskFileExists();
        File file = new File("data/tasks.txt");
        try(Scanner fileReader = new Scanner(file)){
            while(fileReader.hasNextLine()){
                String line = fileReader.nextLine();
                Tasks temp = readLine(line);
                taskMap.put(temp.getId(),temp);

            }

        } catch (FileNotFoundException e) {
            System.out.println("Error finding data file");
            e.printStackTrace();
        }

    }


    private Tasks readLine(String line){
        //ID|NAME|DESC|COMPLETED
        //tasks(name, desc completed, id)
        String[] parts = line.split("\\|");
        if(parts.length != 4){System.out.println("ERROR: line does NOT follow convention!");}
        return new Tasks(parts[1],parts[2],parts[3].equalsIgnoreCase("true"),Integer.valueOf(parts[0]));
    }

    //lists every single task, seperated by comma
    public void listTasks(){

    }

}