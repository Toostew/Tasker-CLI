package org.Tookaystewie.logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Storage {
    //should probably change to private --yeah did that
    private HashMap<Integer, Tasks> taskMap = new HashMap<Integer, Tasks>();

    public HashMap<Integer, Tasks> getTaskMap() {
        return taskMap;
    }






    public void taskFileExists() {
        try{
            File file = new File("data/tasks.txt");
            if(file.createNewFile()){
                System.out.println("created data file");
            } else {
                //data file already exists
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
                if(temp != null){
                    taskMap.put(temp.getId(),temp);
                }


            }
            //reached end of file

        } catch (FileNotFoundException e) {
            System.out.println("Error finding data file");
            e.printStackTrace();
        }

    }

    //unfortunately altering a line in place is not possible in java
    //we can instead alter the whole file, removing the previous unedited entry
    //replacing it with the edited one

    public void alterLine(String newContent,int id,int value){
        //value = 1: modify name
        //value = 2: modify desc
        //value = 3: modify completed
        var lines = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();

        File file = new File("data/tasks.txt");
        try(Scanner fileReader = new Scanner(file)){
            while(fileReader.hasNextLine()){
                String line = fileReader.nextLine();
                String[] parts =  line.split("\\|");
                if(Integer.valueOf(parts[0]) == id){
                    switch(value){
                        case 1:
                            //modify name
                            parts[1] = newContent;
                            break;
                        case 2:
                            //modify desc
                            parts[2] = newContent;
                            break;
                        case 3:
                            //modify completed
                            parts[3] = newContent.toLowerCase();
                            break;

                    }
                    sb.setLength(0); //clears sb to avoid buildup
                    sb.append(parts[0]);
                    sb.append("|");
                    sb.append(parts[1]);
                    sb.append("|");
                    sb.append(parts[2]);
                    sb.append("|");
                    sb.append(parts[3]);
                    lines.add(sb.toString());
                    continue;
                }
                lines.add(line);
            }
            //all files have been stored in arraylist, now reprint them
            try(var fileWriter = new FileWriter(file, false)){
                for(String line : lines){
                    fileWriter.write(line + System.lineSeparator());
                }
            }catch (IOException e){
                System.out.println("Error writing to file");
            }


        } catch (FileNotFoundException e) {
            System.out.println("Error finding data file");
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
        refreshTasks();
        System.out.println("Task List:");
        if(taskMap.isEmpty()){
            System.out.println("Task List is empty! make one with new!");
            return;
        }
        for(Tasks task : taskMap.values()){
            System.out.println("("+task.getId()+")"+task.getName() + (task.getCompleted() == true ? "(COMPLETED)" : ""));
        }
    }
    private void refreshTasks(){
        taskMap.clear();
        readFile();
    }

    //taskMap should reload
    public int validID(){
        refreshTasks();
        int largestID = -1;
        for(Tasks task : taskMap.values()){
            if(task.getId() > largestID){
                largestID = task.getId();
            }
        }
        return largestID + 1;
    }
}