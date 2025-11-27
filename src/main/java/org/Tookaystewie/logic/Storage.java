package org.Tookaystewie.logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Storage {
    //should probably change to private
    public HashMap<Integer, Tasks> taskMap = new HashMap<Integer, Tasks>();

    public static void main(String[] args) {
        Storage storage = new Storage();
        storage.readFile();
        storage.listTasks();
    }

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

    //reads task.txt storage file
    public void readFile(){
        taskFileExists();
        File file = new File("data/tasks.txt");
        try(Scanner fileReader = new Scanner(file)){
            while(fileReader.hasNextLine()){
                String line = fileReader.nextLine();
                Tasks temp = readLine(line);
                taskMap.put(temp.getId(),temp);

            }
            //reached end of file

        } catch (FileNotFoundException e) {
            System.out.println("Error finding data file");
            e.printStackTrace();
        }

    }


    private Tasks readLine(String line){
        //ID|NAME|DESC|COMPLETED
        //tasks(name, desc completed, id)
        String[] parts = line.split("\\|");

        if(parts.length != 4){
            System.out.println("ERROR: line does NOT follow convention!");
            return null;
        }

        return new Tasks(parts[1],parts[2],parts[3].equalsIgnoreCase("true"),Integer.valueOf(parts[0]));
    }

    public void writeLine(String name, String description, boolean completed, int id){
        try(FileWriter fileWriter = new FileWriter("data/tasks.txt", true)){
            fileWriter.write(id+"|"+name+"|"+description+"|"+completed + System.lineSeparator());
        } catch (IOException e){
            System.out.println("Error writing data to tasks.txt");
        }
    }

    //lists every single task, seperated by comma, and list if completed
    public void listTasks(){
        System.out.println("Task List:");
        for(Tasks task : taskMap.values()){
            System.out.println(task.getName() + (task.getCompleted() == true ? "(COMPLETED)" : ""));
        }
    }

}
