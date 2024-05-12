package org.example;

import org.example.Commands.AbstractCommand;

public class Printer {
    public static void printStatusMessage(AbstractCommand command, boolean status){
        if(status){
            System.out.println(command.getSuccessMessage());
        }
    }
}
