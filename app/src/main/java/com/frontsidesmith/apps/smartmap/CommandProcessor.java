package com.frontsidesmith.apps.smartmap;

import java.util.ArrayList;

/**
 * Created by alec on 7/2/15.
 */
public class CommandProcessor extends Commands {

    private String intake;
    private String result;
    private String command;
    private int commandId = -1;

    public CommandProcessor(String command) {
        this.command = command;
        super.collectCommands();
    }

    public int findCommand(String command){

        for(int i = 0; i <= super.commandList.size(); i++){
            if(command.equals(commandList.get(i))){
                commandId = i;
            }
        }

        if(commandId == -1){
         //Command does not exist
            return -1;
        }

        return commandId;
    }

}
