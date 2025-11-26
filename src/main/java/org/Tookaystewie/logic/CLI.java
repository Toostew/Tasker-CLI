package org.Tookaystewie.logic;

import java.util.Scanner;

public class CLI {
    Scanner userInput =  new Scanner(System.in);
    public void mainLoop(){
        commonOutputs(1); //output greeting
        while(true){
            //Main menu loop
            System.out.println("NEW  VIEW  EDIT  EXIT");
            String input = userInput.nextLine();
            if(input.equalsIgnoreCase("new")){
                System.out.println("Create a NEW TASK");

                System.out.print("Name: ");
                String name = userInput.nextLine();
                System.out.print("Description: ");
                String description = userInput.nextLine();

            }
            else if(input.equalsIgnoreCase("view")){

            }
            else if(input.equalsIgnoreCase("edit")){

            }
            else if(input.equalsIgnoreCase("exit")){
                commonOutputs(2);
                break;
            }
            else {
                commonOutputs(3); //command not recognised
            }
        }
    }



    public void commonOutputs(int id){
        //intro
        if(id == 1){
            System.out.println("Welcome to TASKER CLI version: " + Main.version);
        }
        //Exit
        else if(id == 2){
            System.out.println("GoodBye! exiting.....");
        }
        //unknown command
        else if(id == 3){
            System.out.println("Sorry, that command was not recognised. please try again");
        }
    }
}
