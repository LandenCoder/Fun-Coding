package main.java.runners;

import main.java.strings.Input;
import main.java.docs.AllowedPrograms;

public class Introducer {

    private Input input;

    private String[] programs;

    public Introducer() {
        programs = AllowedPrograms.allowedProgramList();
        input = new Input();
    }

    public String getWhatToRun() {
        System.out.println("Welcome to the Fun-Coding workspace! Which program would you like to run?");
        boolean stop = false;
        for (String option : programs) {
            System.out.print(option + " ");
        }
        System.out.println();
        while (!stop) {
            String val = input.getReply();
            for (String option : programs) {
                if (option.equals(val)) {
                    return val;
                }
            }
            System.out.println("Program not found. Please try again.");
        }
        return null;
    }
}