package org.Tookaystewie.logic;

import java.util.HashMap;
import java.io.File;

public class Storage {
    HashMap<Integer, String> taskMap = new HashMap<Integer, String>();


    public boolean doesFileExist() {
        File file = new File("data/tasks.txt");
        return file.exists();
    }

    //reads task storage file
    public void readFile(){

        while(true){

        }
    }

    //lists every single task, seperated by comma
    public void listTasks(){

    }

}
