package org.Tookaystewie.logic;

import java.util.Scanner;

public class CLI {
    Scanner userInput =  new Scanner(System.in);
    Storage storage = new Storage();



    public void mainLoop(){
        commonOutputs(1); //output greeting
        while(true){
            //Main menu loop
            System.out.println("NEW  VIEW  EXIT");
            String input = userInput.nextLine();
            if(input.equalsIgnoreCase("new")){
                System.out.println("Create a NEW TASK");

                System.out.print("Name: ");
                String name = userInput.nextLine();
                System.out.print("Description: ");
                String description = userInput.nextLine();
                int id = storage.validID();
                boolean completed = false;

                storage.writeLine(name,description,completed,id);
                System.out.println("Successfully created task: "+ name);

            }
            else if(input.equalsIgnoreCase("view")){
                while(true){
                    storage.listTasks();
                    System.out.println("enter a task id or BACK to return to main menu");
                    input = userInput.nextLine();

                    if(input.equalsIgnoreCase("back")){
                        break;
                    }
                    try {
                        if(storage.getTaskMap().containsKey(Integer.valueOf(input))){
                            while(true){
                                Tasks temp =  storage.getTaskMap().get(Integer.valueOf(input));
                                temp.taskView();
                                System.out.println("EDIT "+(temp.getCompleted() ? "" : "COMPLETE  ")+"BACK");
                                input = userInput.nextLine();
                                if(input.equalsIgnoreCase("back")){
                                    break;
                                } else if (input.equalsIgnoreCase("edit")) {
                                    while(true){
                                        System.out.println("What would you like to edit? BACK to return");
                                        System.out.println("(BACK)  NAME  DESCRIPTION");
                                        input = userInput.nextLine();
                                        if(input.equalsIgnoreCase("name")) {
                                            System.out.println("----------OLD NAME----------");
                                            System.out.println(temp.getName());
                                            System.out.println("----------NEW NAME----------");
                                            input = userInput.nextLine();
                                            System.out.println("successfully altered "+ temp.getName()+", now: "+input);
                                            storage.alterLine(input, temp.getId(), 1);
                                            break;
                                        }
                                        else if(input.equalsIgnoreCase("description")) {
                                            System.out.println("----------OLD DESCRIPTION----------");
                                            System.out.println(temp.getDescription());
                                            System.out.println("----------NEW DESCRIPTION----------");
                                            input = userInput.nextLine();
                                            System.out.println("successfully altered "+ temp.getName()+"!");
                                            storage.alterLine(input, temp.getId(), 2);
                                            break;
                                        }
                                        else if(input.equalsIgnoreCase("back")){
                                            break;
                                        }
                                        else {
                                            System.out.println("Sorry, invalid input, try again");
                                        }
                                    }

                                } else if (input.equalsIgnoreCase("complete") && !temp.getCompleted()) {
                                    System.out.println("COMPLETED TASK: " + temp.getName());
                                    storage.alterLine("true",temp.getId(),3);
                                    break;
                                }
                            }


                        }
                    }
                    catch(NumberFormatException e){
                        //input is not a valid number
                        //do nothing -wins
                    }

                }
            }
            else if(input.equalsIgnoreCase("edit")){
                System.out.println("To edit tasks, VIEW them first and select EDIT in the context menu");
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
